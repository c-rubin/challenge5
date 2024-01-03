//package test;
//
//import api.AccommodationAPI;
//import api.SimpleHttpClient;
//import api.WeatherAPI;
//import org.json.JSONArray;
//import org.junit.Test;
//import validation.DataValidator;
//
//import static org.junit.Assert.*;
//
//public class MainTest {
//
//    @Test
//    public void testWeatherAPI() {
//        WeatherAPI weatherAPI = new WeatherAPI();
//        JSONArray data = weatherAPI.getData();
//        assertNotNull("Weather API data should not be null", data);
//        assertFalse("Weather API data should not be empty", data.isEmpty());
//    }
//
//    @Test
//    public void testAccommodation() {
//        AccommodationAPI accommodationAPI = new AccommodationAPI();
//        JSONArray data = accommodationAPI.getData();
//        assertNotNull("Accommodation API data should not be null", data);
//        assertFalse("Accommodation API data should not be empty", data.isEmpty());
//    }
//
//    @Test
//    public void testSimpleHttpClient() {
//        String response = null;
//        try {
//            response = SimpleHttpClient.getResponse("https://tourism.opendatahub.com/v1/Weather?");
//            } catch (Exception e) {
//            fail("Exception occurred while testing SimpleHttpClient: " + e.getMessage());
//        }
//
//        assertNotNull("SimpleHttpClient response should not be null", response);
//        assertTrue("SimpleHttpClient response should not be empty", response.length() > 0);
//    }
//
//    @Test
//    public void dataValidatorShouldValidateData() {
//        // Arrange
//        JSONArray testData = new JSONArray("[{\"date\": \"2022-01-01T12:00:00\", \"MinTemp\": 10, \"MaxTemp\": 20}, " +
//                "{\"date\": \"2022-01-02T12:00:00\", \"MinTemp\": 15, \"MaxTemp\": 25}]");
//
//        String timestampField = "date";
//        String[] values = {DataValidator.MIN_TEMP_FIELD, DataValidator.MAX_TEMP_FIELD};
//        double valueAllowancePerDay = 5.0;
//
//        // Act & Assert (Valid Data)
//        assertTrue("DataValidator should validate valid data", DataValidator.validateData(testData, timestampField, values, valueAllowancePerDay));
//
//        // Act & Assert (Invalid Data)
//        testData.getJSONObject(1).put("MaxTemp", 30);
//        assertFalse("DataValidator should catch invalid data", DataValidator.validateData(testData, timestampField, values, valueAllowancePerDay));
//    }
//
//}
//


package test;

import api.AccommodationAPI;
import api.SimpleHttpClient;
import api.WeatherAPI;
import org.json.JSONArray;
import org.junit.Test;
import validation.DataValidator;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void weatherAPIShouldReturnValidData() {
        // Arrange
        WeatherAPI weatherAPI = new WeatherAPI();

        // Act
        JSONArray data = weatherAPI.getData();

        // Assert
        assertNotNull("Weather API data should not be null", data);
        assertFalse("Weather API data should not be empty", data.isEmpty());
    }

    @Test
    public void accommodationAPIShouldReturnValidData() {
        // Arrange
        AccommodationAPI accommodationAPI = new AccommodationAPI();

        // Act
        JSONArray data = accommodationAPI.getData();

        // Assert
        assertNotNull("Accommodation API data should not be null", data);
        assertFalse("Accommodation API data should not be empty", data.isEmpty());
    }

    @Test
    public void simpleHttpClientShouldReturnValidResponseForWeatherAPI() {
        // Arrange & Act
        String response = null;
        try {
            response = SimpleHttpClient.getResponse("https://tourism.opendatahub.com/v1/Weather");
        } catch (Exception e) {
            fail("Exception occurred while testing SimpleHttpClient for Weather API: " + e.getMessage());
        }

        // Assert
        assertNotNull("SimpleHttpClient response for Weather API should not be null", response);
        assertTrue("SimpleHttpClient response for Weather API should not be empty", response.length() > 0);
    }

    @Test
    public void simpleHttpClientShouldReturnValidResponseForAccommodationAPI() {
        // Arrange & Act
        String response = null;
        try {
            response = SimpleHttpClient.getResponse("https://tourism.opendatahub.com/v1/accommodation");
        } catch (Exception e) {
            fail("Exception occurred while testing SimpleHttpClient for Accommodation API: " + e.getMessage());
        }

        // Assert
        assertNotNull("SimpleHttpClient response for Accommodation API should not be null", response);
        assertTrue("SimpleHttpClient response for Accommodation API should not be empty", response.length() > 0);
    }

    @Test
    public void dataValidatorShouldValidateData() {
        // Arrange
        JSONArray testData = new JSONArray("[{\"date\": \"2022-01-01T12:00:00\", \"MinTemp\": 10, \"MaxTemp\": 20}, " +
                "{\"date\": \"2022-01-02T12:00:00\", \"MinTemp\": 15, \"MaxTemp\": 25}]");

        String timestampField = "date";
        String[] values = {DataValidator.MIN_TEMP_FIELD, DataValidator.MAX_TEMP_FIELD};
        double valueAllowancePerDay = 5.0;

        // Act & Assert (Valid Data)
        assertTrue("DataValidator should validate valid data", DataValidator.validateData(testData, timestampField, values, valueAllowancePerDay));

        // Act & Assert (Invalid Data)
        testData.getJSONObject(1).put("MaxTemp", 30);
        assertFalse("DataValidator should catch invalid data", DataValidator.validateData(testData, timestampField, values, valueAllowancePerDay));
    }
}
