package com.base.jsonplaceholderphotos;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PhotoEntity.class}, version = 1)
public abstract class PhotoDatabase extends RoomDatabase {
    public abstract PhotoDao photoDao();

    private static volatile PhotoDatabase INSTANCE;

    public static PhotoDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (PhotoDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhotoDatabase.class, "photo_databse")
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
