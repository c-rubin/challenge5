import org.json.JSONObject;

import java.io.*;
import java.net.*;
//import java.util.Arrays;
import java.sql.Timestamp;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException{
        String urlAddr = "https://tourism.opendatahub.com/v1/Weather?";
        String query="locfilter=3";

        List<String> response = getResponse(urlAddr+query);
        printResponse(response);

        JSONObject json = new JSONObject(response.get(1));
        //dates
        JSONObject firstDate = (JSONObject) json.getJSONArray("Stationdata").get(0);
        JSONObject secondDate = (JSONObject) json.getJSONArray("Stationdata").get(1);

        //temperatures - MinTemp, Maxtemp, MaxTemp

        //print dates
        System.out.println( Timestamp.valueOf(firstDate.getString("date").replace("T"," ")));
        System.out.println( Timestamp.valueOf(secondDate.getString("date").replace("T"," ")));
        //MinTemp
        System.out.println( firstDate.getInt("MinTemp"));
        System.out.println( secondDate.getInt("MinTemp"));
        //Maxtemp
        System.out.println( firstDate.getInt("Maxtemp"));
        System.out.println( secondDate.getInt("Maxtemp"));
        //MaxTemp
        System.out.println( firstDate.getInt("MaxTemp"));
        System.out.println( secondDate.getInt("MaxTemp"));

//        query="locfilter=3";
//        List<String> response2 = getResponse(urlAddr+query);
//        printResponse(response2);
    }

    public static List<String> getResponse(String urlAddr) throws IOException{
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

        return List.of(Integer.toString(status),content.toString());
    }

    public static void printResponse(List<String> response){
        System.out.println(response.get(0));
        System.out.println(response.get(1));
    }
}