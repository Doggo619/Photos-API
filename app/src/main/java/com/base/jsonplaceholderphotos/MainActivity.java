package com.base.jsonplaceholderphotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private PhotoViewModel photoViewModel;
    ProgressBar progressBar;
    FloatingActionButton refreshButton;
    RecyclerView recyclerView;
    private TextView fetchingDataText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        refreshButton = findViewById(R.id.floatingButton);
        fetchingDataText = findViewById(R.id.tv_refresh);

        PhotoAdapter adapter = new PhotoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        photoViewModel = new ViewModelProvider(this).get(PhotoViewModel.class);
        photoViewModel.getAllPhotos().observe(this, photos -> {
            adapter.setPhotos(photos);
            progressBar.setVisibility(View.GONE);
            fetchingDataText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        });
        refreshButton.setOnClickListener(view -> {
            refreshData();
        });


        progressBar.setVisibility(View.VISIBLE);
        fetchingDataText.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
    private void refreshData() {

        progressBar.setVisibility(View.VISIBLE);
        fetchingDataText.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);


        photoViewModel.refreshPhotos();
    }
}