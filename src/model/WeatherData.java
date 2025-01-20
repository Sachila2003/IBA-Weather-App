package model;

public class WeatherData {
    private String city;
    private double temp;
    private double humidity;
    private  String description;
    private String icon;

    public WeatherData(String city, double temp, double humidity, String description, String icon) {
        this.city = city;
        this.temp = temp;
        this.humidity = humidity;
        this.description = description;
        this.icon = icon;
    }

    public String getCity() {
        return city;

    }

    public double getTemp() {
        return temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
}
}