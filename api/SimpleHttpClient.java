package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.*;

public class SimpleHttpClient {
//    HttpClient client;

//    public SimpleHttpClient() {
//        client = HttpClient.newBuilder().build();
//    }

//    public String get(final String URL) throws Exception {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(URL))
//                .header("Content-Type", "application/json")
//                .header("Referer", "com.opendatahub.examples.java")
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        return response.body();
//    }

    public static String getResponse(String urlAddr) throws Exception{
        //create url and connection
        URL url = new URL(urlAddr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        //get response
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        return content.toString();
    }
}