package validator;

import org.json.JSONObject;
import java.sql.Timestamp;
import java.util.List;

public class DataValidator {

    public static boolean validateData(List<JSONObject> jsonData, String timestampField, String[] values, double allowedVariationSpeed) {
        for (int i = 1; i < jsonData.size(); i++) {

            JSONObject currentDataPoint = jsonData.get(i);
            JSONObject previousDataPoint = jsonData.get(i - 1);

            Timestamp currentDate = Timestamp.valueOf(currentDataPoint.getString(timestampField).replace("T"," ").replace(".000+0000",""));
            Timestamp previousDate = Timestamp.valueOf(previousDataPoint.getString(timestampField).replace("T"," ").replace(".000+0000",""));

            //values
            for(String value : values){
                //get x value
                int currValue = currentDataPoint.getInt(value);
                int prevValue = previousDataPoint.getInt(value);

                double timeDifferenceInSeconds = (currentDate.getTime() - previousDate.getTime()) / 1000.0;
                double timeDifferenceInDays = timeDifferenceInSeconds / 86400;
                if(timeDifferenceInDays<1)timeDifferenceInDays=1;

                double valueDiff = Math.abs(currValue - prevValue);
                double changeSpeed = valueDiff / timeDifferenceInDays;
                if (changeSpeed > allowedVariationSpeed) {
                    return false; // Invalid data
                }
            }
        }
        return true; // All data points are valid
    }
}