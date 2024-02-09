package com.example.samst.Dashboard;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("event-master/get-event-master-details.php")
    Call<EventResponse> getEventDetails(@Query("eventId") int eventId);
}
