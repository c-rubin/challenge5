package api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class WeatherApiV2 {
    public List<List<JSONObject>> getData(){
        String urlAddr = "https://mobility.api.opendatahub.com/v2/tree%2Cnode/MeteoStation/air-temperature/latest?";
        String query="select=tmeasurements";

        try {
            String response = SimpleHttpClient.getResponse(urlAddr + query);
//        printResponse(response);

            JSONObject json = new JSONObject(response);

            JSONObject stations = json.getJSONObject("data")
                    .getJSONObject("MeteoStation")
                    .getJSONObject("stations");
//                    .getJSONObject("00390SF")
//                    .getJSONObject("sdatatypes")
//                    .getJSONObject("air-temperature")
//                    .getJSONArray("tmeasurements");

            List<List<JSONObject>> stationsList = new ArrayList<>();

            for(String i : stations.keySet()){
                List<JSONObject> measurements = new ArrayList<>();

                JSONArray x = stations.getJSONObject(i)
                        .getJSONObject("sdatatypes")
                        .getJSONObject("air-temperature")
                        .getJSONArray("tmeasurements");

                for(int j=0;j<x.length();j++){
                    measurements.add( (JSONObject) x.get(j));
                }
                measurements.sort(Comparator.comparing(o -> Timestamp.valueOf(o.getString("mvalidtime").replace(".000+0000",""))));
                stationsList.add(measurements);
//                System.out.println("ka");
            }

            // Sort the list based on the "mvalidtime" field in each JSONObject

            return stationsList;
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
