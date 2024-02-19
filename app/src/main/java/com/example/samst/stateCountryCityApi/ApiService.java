package com.example.samst.stateCountryCityApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v0.1/countries")
    Call<CountryResponse> getCountries();

    @GET("countries/state/cities/q")
    Call<CityData> getCities(
            @Query("country") String country,
            @Query("state") String state
    );

    @Headers("Content-Type: application/json")
    @POST("countries/states")
    Call<ShowStatesResponse> getStates(@Body CountryRequestBody body);
}
