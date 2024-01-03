//package validation;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.sql.Timestamp;
//
//public class DataValidator {
//    // Constants for field names
//    public static final String MIN_TEMP_FIELD = "MinTemp";
//    public static final String MAX_TEMP_FIELD = "MaxTemp";
//    public static final String MAX_TEMP_TEMP_FIELD = "Maxtemp";
//
//    public static boolean validateData(JSONArray jsonData, String timestampField, String[] values, double allowedVariationSpeed) {
//        for (int i = 1; i < jsonData.length(); i++) {
//            JSONObject currentDataPoint = jsonData.getJSONObject(i);
//            JSONObject previousDataPoint = jsonData.getJSONObject(i - 1);
//
//            Timestamp currentDate = convertToTimestamp(currentDataPoint.getString(timestampField));
//            Timestamp previousDate = convertToTimestamp(previousDataPoint.getString(timestampField));
//
//            for (String value : values) {
//                int currentValue = currentDataPoint.getInt(value);
//                int previousValue = previousDataPoint.getInt(value);
//
//                double timeDifferenceInSeconds = (currentDate.getTime() - previousDate.getTime()) / 1000.0;
//                double timeDifferenceInDays = timeDifferenceInSeconds / 86400;
//
//                double valueDiff = Math.abs(currentValue - previousValue);
//                double changeSpeed = valueDiff / timeDifferenceInDays;
//
//                if (changeSpeed > allowedVariationSpeed) {
//                    return false; // Invalid data
//                }
//            }
//        }
//        return true; // All data points are valid
//    }
//
//    private static Timestamp convertToTimestamp(String timestampString) {
//        return Timestamp.valueOf(timestampString.replace("T", " "));
//    }
//}
//
//
//
//package validation;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.sql.Timestamp;
//
//public class DataValidator {
//    // Constants for field names
//    public static final String CHECKIN_DATE_FIELD = "checkin_date";
//    public static final String MIN_TEMP_FIELD = "MinTemp";
//    public static final String MAX_TEMP_FIELD = "MaxTemp";
//    public static final String MAX_TEMP_TEMP_FIELD = "Maxtemp";
//
//    public static boolean validateData(JSONArray jsonData, String timestampField, String[] values, double allowedVariationSpeed) {
//        for (int i = 1; i < jsonData.length(); i++) {
//            JSONObject currentDataPoint = jsonData.getJSONObject(i);
//            JSONObject previousDataPoint = jsonData.getJSONObject(i - 1);
//
//            Timestamp currentDate = convertToTimestamp(currentDataPoint.optString(timestampField, ""));
//            Timestamp previousDate = convertToTimestamp(previousDataPoint.optString(timestampField, ""));
//
//            for (String value : values) {
//                // Check if the field is present in the current and previous data points
//                if (currentDataPoint.has(value) && previousDataPoint.has(value)) {
//                    int currentValue = currentDataPoint.optInt(value, 0);
//                    int previousValue = previousDataPoint.optInt(value, 0);
//
//                    double timeDifferenceInSeconds = (currentDate.getTime() - previousDate.getTime()) / 1000.0;
//                    double timeDifferenceInDays = timeDifferenceInSeconds / 86400;
//
//                    double valueDiff = Math.abs(currentValue - previousValue);
//                    double changeSpeed = valueDiff / timeDifferenceInDays;
//
//                    if (changeSpeed > allowedVariationSpeed) {
//                        return false; // Invalid data
//                    }
//                }
//            }
//        }
//        return true; // All data points are valid
//    }
//
//    private static Timestamp convertToTimestamp(String timestampString) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
//        try {
//            Date parsedDate = dateFormat.parse(timestampString);
//            return new Timestamp(parsedDate.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}



package validation;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataValidator {
    // Constants for field names
    public static final String CHECKIN_DATE_FIELD = "checkin_date";
    public static final String MIN_TEMP_FIELD = "MinTemp";
    public static final String MAX_TEMP_FIELD = "MaxTemp";
    public static final String MAX_TEMP_TEMP_FIELD = "Maxtemp";

    public static boolean validateData(JSONArray jsonData, String timestampField, String[] values, double allowedVariationSpeed) {
        for (int i = 1; i < jsonData.length(); i++) {
            JSONObject currentDataPoint = jsonData.getJSONObject(i);
            JSONObject previousDataPoint = jsonData.getJSONObject(i - 1);

            Timestamp currentDate = convertToTimestamp(currentDataPoint.optString(timestampField, ""));
            Timestamp previousDate = convertToTimestamp(previousDataPoint.optString(timestampField, ""));

            if (currentDate == null || previousDate == null) {
                // Handle the case where date parsing fails
                return false;
            }

            for (String value : values) {
                // Check if the field is present in the current and previous data points
                if (currentDataPoint.has(value) && previousDataPoint.has(value)) {
                    int currentValue = currentDataPoint.optInt(value, 0);
                    int previousValue = previousDataPoint.optInt(value, 0);

                    double timeDifferenceInSeconds = (currentDate.getTime() - previousDate.getTime()) / 1000.0;
                    double timeDifferenceInDays = timeDifferenceInSeconds / 86400;

                    double valueDiff = Math.abs(currentValue - previousValue);
                    double changeSpeed = valueDiff / timeDifferenceInDays;

                    if (changeSpeed > allowedVariationSpeed) {
                        return false; // Invalid data
                    }
                }
            }
        }
        return true; // All data points are valid
    }

    private static Timestamp convertToTimestamp(String timestampString) {
        if (timestampString == null || timestampString.isEmpty()) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date parsedDate = dateFormat.parse(timestampString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

