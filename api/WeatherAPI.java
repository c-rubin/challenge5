package api;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherAPI {
    public JSONArray getData() {
        String urlAddr = "https://tourism.opendatahub.com/v1/Weather?";
        String query = "locfilter=3";

        try {
            String response = SimpleHttpClient.getResponse(urlAddr + query);
            JSONObject json = new JSONObject(response);

            return json.getJSONArray("Stationdata");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

