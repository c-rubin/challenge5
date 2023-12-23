package validation;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;

public class DataValidator {
    // Constants for field names
    public static final String MIN_TEMP_FIELD = "MinTemp";
    public static final String MAX_TEMP_FIELD = "MaxTemp";
    public static final String MAX_TEMP_TEMP_FIELD = "Maxtemp";

    public static boolean validateData(JSONArray jsonData, String timestampField, String[] values, double allowedVariationSpeed) {
        for (int i = 1; i < jsonData.length(); i++) {
            JSONObject currentDataPoint = jsonData.getJSONObject(i);
            JSONObject previousDataPoint = jsonData.getJSONObject(i - 1);

            Timestamp currentDate = convertToTimestamp(currentDataPoint.getString(timestampField));
            Timestamp previousDate = convertToTimestamp(previousDataPoint.getString(timestampField));

            for (String value : values) {
                int currentValue = currentDataPoint.getInt(value);
                int previousValue = previousDataPoint.getInt(value);

                double timeDifferenceInSeconds = (currentDate.getTime() - previousDate.getTime()) / 1000.0;
                double timeDifferenceInDays = timeDifferenceInSeconds / 86400;

                double valueDiff = Math.abs(currentValue - previousValue);
                double changeSpeed = valueDiff / timeDifferenceInDays;

                if (changeSpeed > allowedVariationSpeed) {
                    return false; // Invalid data
                }
            }
        }
        return true; // All data points are valid
    }

    private static Timestamp convertToTimestamp(String timestampString) {
        return Timestamp.valueOf(timestampString.replace("T", " "));
    }
}

