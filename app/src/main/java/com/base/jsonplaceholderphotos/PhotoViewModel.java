package com.base.jsonplaceholderphotos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhotoViewModel extends AndroidViewModel {
    private final PhotoRepository photoRepository;
    private LiveData<List<PhotoEntity>> allPhotos;
    public PhotoViewModel(@NonNull Application application) {
        super(application);
        PhotoDatabase db = PhotoDatabase.getDatabase(application);
        PhotoDao photoDao = db.photoDao();
        PhotoApiService photoApiService = PhotoApiService.create();

        photoRepository = new PhotoRepository(photoDao, photoApiService);
        allPhotos = photoRepository.getAllPhotos();
        photoRepository.refreshPhotos();
    }

    public LiveData<List<PhotoEntity>> getAllPhotos() {
        return allPhotos;
    }
}
