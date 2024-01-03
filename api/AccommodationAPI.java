package api;

import org.json.JSONArray;
import org.json.JSONObject;

public class AccommodationAPI {
    public JSONArray getData() {
        String urlAddr = "https://tourism.opendatahub.com/v1/accommodation";

        try {
            String response = SimpleHttpClient.getResponse(urlAddr);
            System.out.println("Accommodation API Response: " + response);
            JSONObject json = new JSONObject(response);

            JSONArray accommodationData = json.getJSONArray("Items");

            return accommodationData;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
