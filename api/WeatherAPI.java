package api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherAPI {
//    private final String BASE_URL = "https://tourism.opendatahub.com/v1/Weather";
//
//    private SimpleHttpClient httpClient; // need to resolve this **simpleHttpClient
//
//    public WeatherAPI() {
//        httpClient = new SimpleHttpClient(); //
//    }
//
//    public String getData() throws Exception {
//        return httpClient.get(BASE_URL);
//    }

    public List<JSONObject> getData(){
        String urlAddr = "https://tourism.opendatahub.com/v1/Weather?";
        String query="locfilter=3";

        try {
            String response = SimpleHttpClient.getResponse(urlAddr + query);
//        printResponse(response);

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

//    public static void printResponse(List<String> response){
//        System.out.println(response.get(0));
//        System.out.println(response.get(1));
//    }
}


