package com.base.jsonplaceholderphotos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPhoto(List<PhotoEntity> photos);

    @Query("SELECT * from photos")
    LiveData<List<PhotoEntity>> getAllPhotos();
}
