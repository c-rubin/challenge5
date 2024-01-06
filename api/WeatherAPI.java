package api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherAPI {

    public List<JSONObject> getData(){
        String urlAddr = "https://tourism.opendatahub.com/v1/Weather?";
        String query="locfilter=3";

        try {
            String response = SimpleHttpClient.getResponse(urlAddr + query);

            JSONObject json = new JSONObject(response);

            List<JSONObject> list = new ArrayList<>();
            JSONArray data = json.getJSONArray("Stationdata");
            for(int i=0;i<data.length();i++){
                list.add(data.getJSONObject(i));
            }

            return list;
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public List<JSONObject> getData(String raw){
        JSONObject json = new JSONObject(raw);

        List<JSONObject> list = new ArrayList<>();
        JSONArray data = json.getJSONArray("Stationdata");
        for(int i=0;i<data.length();i++){
            list.add(data.getJSONObject(i));
        }

        return list;
    }
}


