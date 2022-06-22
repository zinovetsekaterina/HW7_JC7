package ru.geekbrains.lesson7;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.lang.String;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class WeatherResponse {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/294021
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String API_KEY = "jGp39jUhyNWO6w54zrGzhoQxXmLjVAUV";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "294021";
    private static final String AUTOCOMPLETE = "autocomplete";
    private String[] strings;


    public void Weather() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();


        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(FORECASTS)
                .addPathSegment(VERSION)
                .addPathSegment(DAILY)
                .addPathSegment(ONE_DAY)
                .addPathSegment(CITIES)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
        String weatherResponse = oneDayForecastResponse.body().string();
        System.out.println(weatherResponse);
    }
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherResponse Weather = new WeatherResponse("294021", "", "" );
        String weatherFromJson = objectMapper.writeValueAsString(Weather);
        System.out.println(weatherFromJson);
        WeatherResponse weather = objectMapper.readValue(weatherFromJson, WeatherResponse.class);
        System.out.println(weather);
    }
    }