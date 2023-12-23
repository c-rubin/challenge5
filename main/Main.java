package main;

import api.WeatherAPI;
import org.json.JSONArray;
import validation.DataValidator;

public class Main {
    public static void main(String[] args) {
        try {
            // Fetch data from the Temperature API
            WeatherAPI temperatureAPI = new WeatherAPI();
            JSONArray data = temperatureAPI.getData();

            // Configure and validate temperature data
            String temperatureTimestampField = "date";
            String[] values = {DataValidator.MIN_TEMP_FIELD, DataValidator.MAX_TEMP_FIELD, DataValidator.MAX_TEMP_TEMP_FIELD};
            double valueAllowancePerDay = 1.0;

            boolean isTemperatureDataValid = DataValidator.validateData(
                    data,
                    temperatureTimestampField,
                    values,
                    valueAllowancePerDay
            );

            if (isTemperatureDataValid) {
                System.out.println("Temperature data is valid.");
            } else {
                System.out.println("Temperature data contains invalid points.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

