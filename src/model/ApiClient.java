package model;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiClient {

    public static WeatherData getWeather(String city, String apiKey) throws IOException {
        String stringurl = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric"
                ,city, apiKey);//web location
        URL url = new URL(stringurl);
        HttpURLConnection connection= (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");

        try(Scanner scanner = new Scanner(connection.getInputStream())){
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()){
                response.append(scanner.nextLine());

            }
            JSONObject jsonResponse = new JSONObject(response.toString());
            if(jsonResponse.getInt("cod") == 200){  //200=ok
                JSONObject main = jsonResponse.getJSONObject("main");
                JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);
                return new WeatherData(
                        jsonResponse.getString("name"),
                        main.getDouble("temp"),
                        main.getDouble("humidity"),
                        weather.getString("description"),
                        weather.getString("icon")
                );
            } else {
                return null;
            }


}
    }
}
