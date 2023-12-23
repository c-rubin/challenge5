package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleHttpClient {

    public static String getResponse(String urlAddr) throws Exception {
        HttpURLConnection con = null;
        try {
            URL url = new URL(urlAddr);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("HTTP GET request failed with status code: " + status);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            // Get response
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            return content.toString();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
}



