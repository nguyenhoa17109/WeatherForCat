package com.nguyenhoa.weatherforcast.retrofit;



import com.nguyenhoa.weatherforcast.model.HourWeatherForecastResult;
import com.nguyenhoa.weatherforcast.model.WeatherForecastResult;
import com.nguyenhoa.weatherforcast.model.WeatherResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeatherMap {
    @GET("weather")
    Observable<WeatherResult>  getWeatherByLatLng(@Query("lat") String lat,
                                                  @Query("lon") String lng,
                                                  @Query("appid") String appid,
                                                  @Query("units") String unit);
    @GET("forecast")
    Observable<WeatherForecastResult>  getWeatherForecastByLatLng(@Query("lat") String lat,
                                                                  @Query("lon") String lng,
                                                                  @Query("appid") String appid,
                                                                  @Query("units") String unit);
    @GET("weather")
    Observable<WeatherResult>  getWeatherByCityName(@Query("q") String cityName,
                                                  @Query("appid") String appid,
                                                  @Query("units") String unit);
    @GET("onecall")
    Observable<HourWeatherForecastResult>  getWeatherByHour(@Query("lat") String lat,
                                                            @Query("lon") String lng,
                                                            @Query("exclude") String exclude,
                                                            @Query("appid") String appid,
                                                            @Query("units") String unit);
}
