import org.json.JSONObject;
import org.json.JSONArray;

import java.sql.Timestamp;

public class DataValidator {

    public static boolean validateData(JSONArray jsonData, String timestampField, String[] values, double allowedVariationSpeed) {
        for (int i = 1; i < jsonData.length(); i++) {

            JSONObject currentDataPoint = jsonData.getJSONObject(i);
            JSONObject previousDataPoint = jsonData.getJSONObject(i - 1);
//            JSONObject jsonObj = (JSONObject) jsonData.getJSONObject(i);

//            Timestamp.valueOf(secondDate.getString("date").replace("T"," "));
//            JSONObject currentDataPoint = jsonData.getJSONObject(i);

//            long currentTimestamp = currentDataPoint.getLong(timestampField);
//            long previousTimestamp = previousDataPoint.getLong(timestampField);
            Timestamp currentDate = Timestamp.valueOf(currentDataPoint.getString(timestampField).replace("T"," "));
            Timestamp previousDate = Timestamp.valueOf(previousDataPoint.getString(timestampField).replace("T"," "));

            //values
//            double currentValue = currentDataPoint.getDouble(valueField);
//            double previousValue = previousDataPoint.getDouble(valueField);
//            MinTemp
            int currentMin = currentDataPoint.getInt("MinTemp");
            int previousMin = previousDataPoint.getInt("MinTemp");
            //Maxtemp
            int currentMaxtemp = currentDataPoint.getInt("Maxtemp");
            int previousMaxtemp = previousDataPoint.getInt("Maxtemp");
            //MaxTemp
            int currentMaxTemp = currentDataPoint.getInt("MaxTemp");
            int previousMaxTemp = previousDataPoint.getInt("MaxTemp");

            for(String value : values){
                //get x value
                int currValue = currentDataPoint.getInt(value);
                int prevValue = previousDataPoint.getInt(value);

                double timeDifferenceInSeconds = (currentDate.getTime() - previousDate.getTime()) / 1000.0;
                double timeDifferenceInDays = timeDifferenceInSeconds / 86400;

                double valueDiff = Math.abs(currentMin - previousMin);
                double changeSpeed = valueDiff / timeDifferenceInDays;
                if (changeSpeed > allowedVariationSpeed) {
                    return false; // Invalid data
                }
            }
//
//
//            double timeDifferenceInSeconds = (currentDate.getTime() - previousDate.getTime()) / 1000.0;
//
//            double minDiff = Math.abs(currentMin - previousMin);
//            double maxtempDiff = Math.abs(currentMaxtemp - previousMaxtemp);
//            double maxTempDiff = Math.abs(currentMaxTemp - previousMaxTemp);
//
//            double changeSpeedMin = minDiff / timeDifferenceInSeconds;
//            double changeSpeedMaxtemp = maxtempDiff / timeDifferenceInSeconds;
//            double changeSpeedMaxTemp = maxTempDiff / timeDifferenceInSeconds;
//
//            if (changeSpeedMin > allowedVariationSpeed) {
//                return false; // Invalid data
//            }
//            if (changeSpeedMaxtemp > allowedVariationSpeed) {
//                return false; // Invalid data
//            }
//            if (changeSpeedMaxTemp > allowedVariationSpeed) {
//                return false; // Invalid data
//            }
        }
        return true; // All data points are valid
    }
}