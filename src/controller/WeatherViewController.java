package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ApiClient;
import model.WeatherData;

import java.io.IOException;

public class WeatherViewController {
  @FXML  private TextField txtCity;
  @FXML private Button btnSearch;
  @FXML private Label lblTemp;
  @FXML  private Label lblHumidity;
  @FXML private Label lblDescription;
  @FXML private Label lblCityName;
  @FXML  private ImageView weatherIcon;

  private static final String API_KEY = "b2e0d8800640a2e77c7df47dc47e8a20";

  @FXML
  public void initialize(){
    btnSearch.setOnAction(event -> fetchWeatherData());


  }

  private void fetchWeatherData() {
    String cityName = txtCity.getText();
    if(cityName.isEmpty()){
      showError("please enter a city name");
      return;
    }
    try{
      WeatherData weatherData = ApiClient.getWeather(cityName, API_KEY);
      if(weatherData != null){
        updateUI(weatherData);
      }else {
        showError("failt to fetch weather data. Please try again.");
      }
    }catch (Exception ex){
      showError(ex.getMessage());

    }
  }

  private void updateUI(WeatherData data){
    lblCityName.setText(data.getCity());
    lblTemp.setText(String.format("%.2f °C",data.getTemp()));
    lblHumidity.setText(String.format("Humidity: %.2f%%", data.getHumidity()));
    lblDescription.setText(data.getDescription());

    Image img = new Image("http://openweathermap.org/img/w/"+ data.getIcon() + ".png");
    weatherIcon.setImage(img);
  }

  private void showError(String msg){
    Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
    alert.showAndWait();
  }

}