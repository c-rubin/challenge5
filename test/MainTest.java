package test;

import api.SimpleHttpClient;
import api.WeatherAPI;
import org.json.JSONArray;
import org.junit.Test;
import validation.DataValidator;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testWeatherAPI() {
        WeatherAPI weatherAPI = new WeatherAPI();
        JSONArray data = weatherAPI.getData();
        assertNotNull("Weather API data should not be null", data);
        assertFalse("Weather API data should not be empty", data.isEmpty());
    }

    @Test
    public void testSimpleHttpClient() {
        try {
            String response = SimpleHttpClient.getResponse("https://www.example.com");
            assertNotNull("SimpleHttpClient response should not be null", response);
            assertTrue("SimpleHttpClient response should not be empty", response.length() > 0);
        } catch (Exception e) {
            fail("Exception occurred while testing SimpleHttpClient: " + e.getMessage());
        }
    }

    @Test
    public void testDataValidator() {
        JSONArray testData = new JSONArray("[{\"date\": \"2022-01-01T12:00:00\", \"MinTemp\": 10, \"MaxTemp\": 20}, " +
                "{\"date\": \"2022-01-02T12:00:00\", \"MinTemp\": 15, \"MaxTemp\": 25}]");

        String timestampField = "date";
        String[] values = {DataValidator.MIN_TEMP_FIELD, DataValidator.MAX_TEMP_FIELD};
        double valueAllowancePerDay = 5.0;

        assertTrue("DataValidator should validate valid data", DataValidator.validateData(testData, timestampField, values, valueAllowancePerDay));

        // Introduced invalid data and checked if validator catches it
        testData.getJSONObject(1).put("MaxTemp", 30);
        assertFalse("DataValidator should catch invalid data", DataValidator.validateData(testData, timestampField, values, valueAllowancePerDay));
    }

}
