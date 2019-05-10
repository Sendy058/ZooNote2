package weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class Weather {

    private int temp;
    private String desc;

    public int getTemp() {
        return temp;
    }

    public String getDesc() {
        return desc;
    }

    public Weather() {
        try {
            String url = "http://api.openweathermap.org/data/2.5/weather?q=Košice&appid=753cb58a0e7309276357da1b45fa03df";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject (response.toString());

            JSONObject jsonMain= new JSONObject(json.getJSONObject("main").toString());
            temp = (int) (jsonMain.getDouble("temp") - 273.15);

            JSONArray jsonWeather = json.getJSONArray("weather");
            int length = jsonWeather.length();
            for(int i=0; i<length; i++)
            {
                JSONObject jObj = jsonWeather.getJSONObject(i);
                desc = jObj.optString("description");
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
