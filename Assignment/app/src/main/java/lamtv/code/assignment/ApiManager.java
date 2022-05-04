package lamtv.code.assignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    String BASE_URL = "https://dataservice.accuweather.com/";

    @GET("/forecasts/v1/hourly/5day/353412?apikey=YvLXLyI3SiE8xuQKF3p4A1B1I1RksiXR&language=vi-vn&metric=true")
    Call<List<Weather>> getDay();

    @GET("/forecasts/v1/hourly/12hour/353412?apikey=CxILqfbYMdKI30fs02iXyl2JZJdF2MeU&language=vi-vn&metric=true")
    Call<List<Weather>> getHour();
}
