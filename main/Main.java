//package main;
//
//import api.WeatherAPI;
//import api.AccommodationAPI;
//import org.json.JSONArray;
//import validation.DataValidator;
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//            // Fetch data from the weather API
//            WeatherAPI temperatureAPI = new WeatherAPI();
//            JSONArray weatherData = temperatureAPI.getData();
//
//            // Configure and validate weather data
//            String weatherTimestampField = "date";
//            String[] weatherValues = {DataValidator.MIN_TEMP_FIELD, DataValidator.MAX_TEMP_FIELD, DataValidator.MAX_TEMP_TEMP_FIELD};
//            double weatherValueAllowancePerDay = 1.0;
//
//            boolean isWeatherDataValid = DataValidator.validateData(
//                    weatherData,
//                    weatherTimestampField,
//                    weatherValues,
//                    weatherValueAllowancePerDay
//            );
//
//            if (isWeatherDataValid) {
//                System.out.println("Weather data is valid.");
//            } else {
//                System.out.println("Weather data contains invalid points.");
//            }
//
//            // Fetch data from the Accommodation API
//            AccommodationAPI accommodationAPI = new AccommodationAPI();
//            JSONArray accommodationData = accommodationAPI.getData();
//
//            // Configure and validate accommodation data
//            String accommodationTimestampField = "checkin_date";
//            String[] accommodationValues = {"price", "occupancy"};
//            double accommodationValueAllowancePerDay = 10.0;
//
//            boolean isAccommodationDataValid = DataValidator.validateData(
//                    accommodationData,
//                    accommodationTimestampField,
//                    accommodationValues,
//                    accommodationValueAllowancePerDay
//            );
//
//            if (isAccommodationDataValid) {
//                System.out.println("Accommodation data is valid.");
//            } else {
//                System.out.println("Accommodation data contains invalid points.");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//
//package main;
//
//import api.AccommodationAPI;
//import org.json.JSONArray;
//import validation.DataValidator;
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//            // Fetch data from the Accommodation API
//            AccommodationAPI accommodationAPI = new AccommodationAPI();
//            JSONArray data = accommodationAPI.getData();
//
//            // Configure and validate accommodation data
//            String accommodationTimestampField = "LastChange";  // Use the appropriate timestamp field for accommodation data
//            String[] values = {DataValidator.MIN_TEMP_FIELD, DataValidator.MAX_TEMP_FIELD, DataValidator.MAX_TEMP_TEMP_FIELD, DataValidator.CHECKIN_DATE_FIELD};
//            double valueAllowancePerDay = 1.0;
//
//            boolean isAccommodationDataValid = DataValidator.validateData(
//                    data,
//                    accommodationTimestampField,
//                    values,
//                    valueAllowancePerDay
//            );
//
//            if (isAccommodationDataValid) {
//                System.out.println("Accommodation data is valid.");
//            } else {
//                System.out.println("Accommodation data contains invalid points.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}


package main;

import api.AccommodationAPI;
import api.WeatherAPI;
import org.json.JSONArray;
import validation.DataValidator;

public class Main {
    public static void main(String[] args) {
        try {
            // Fetch data from the Weather API
            WeatherAPI weatherAPI = new WeatherAPI();
            JSONArray weatherData = weatherAPI.getData();

            // Configure and validate weather data
            String weatherTimestampField = "date";
            String[] weatherValues = {DataValidator.MIN_TEMP_FIELD, DataValidator.MAX_TEMP_FIELD, DataValidator.MAX_TEMP_TEMP_FIELD};
            double weatherValueAllowancePerDay = 1.0;

            boolean isWeatherDataValid = DataValidator.validateData(
                    weatherData,
                    weatherTimestampField,
                    weatherValues,
                    weatherValueAllowancePerDay
            );

            if (isWeatherDataValid) {
                System.out.println("Weather data is valid.");
            } else {
                System.out.println("Weather data contains invalid points.");
            }

            // Fetch data from the Accommodation API
            AccommodationAPI accommodationAPI = new AccommodationAPI();
            JSONArray accommodationData = accommodationAPI.getData();

            // Configure and validate accommodation data
            String accommodationTimestampField = "checkin_date";
            String[] accommodationValues = {"price", "occupancy"};
            double accommodationValueAllowancePerDay = 10.0;

            boolean isAccommodationDataValid = DataValidator.validateData(
                    accommodationData,
                    accommodationTimestampField,
                    accommodationValues,
                    accommodationValueAllowancePerDay
            );

            if (isAccommodationDataValid) {
                System.out.println("Accommodation data is valid.");
            } else {
                System.out.println("Accommodation data contains invalid points.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
