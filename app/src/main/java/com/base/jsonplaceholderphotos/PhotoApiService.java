package com.base.jsonplaceholderphotos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface PhotoApiService {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    @GET("photos")
    Call<List<PhotoEntity>> getPhotos();

    public static PhotoApiService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(PhotoApiService.class);
    }
}
