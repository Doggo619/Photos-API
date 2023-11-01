package com.base.jsonplaceholderphotos;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepository {
    private final PhotoDao photoDao;
    private final PhotoApiService photoApiService;
    Context context;

    public PhotoRepository(PhotoDao photoDao, PhotoApiService photoApiService) {
        this.photoDao = photoDao;
        this.photoApiService = photoApiService;
    }

    public LiveData<List<PhotoEntity>> getAllPhotos() {
        return photoDao.getAllPhotos();
    }

    public void refreshPhotos() {
        photoApiService.getPhotos().enqueue(new Callback<List<PhotoEntity>>() {
            @Override
            public void onResponse(Call<List<PhotoEntity>> call, Response<List<PhotoEntity>> response) {
                if (response.isSuccessful()) {
                    List<PhotoEntity> photos = response.body();
                    if (photos != null && !photos.isEmpty()) {
                        insertPhotos(photos);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PhotoEntity>> call, Throwable t) {
                Toast.makeText(context, "Network request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertPhotos(List<PhotoEntity> photos) {
        new Thread(() -> {
            photoDao.insertPhoto(photos);
        }).start();
    }
}
