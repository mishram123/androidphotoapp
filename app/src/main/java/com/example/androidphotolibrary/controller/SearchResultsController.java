package com.example.androidphotolibrary.controller;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.androidphotolibrary.R;
import com.example.androidphotolibrary.model.*;
import android.widget.BaseAdapter;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.File;

public class SearchResultsController extends AppCompatActivity{
    private GridView imageGrid;
    public static List<Photo> imageResultList;
    private ImageAdapter imageAdapter;
    private Button backButton;
    private Photo selectedResultPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_display);

        imageGrid = findViewById(R.id.imageGrid);
        backButton = findViewById(R.id.backButton);



        //imageResultList = new ArrayList<>();
        imageAdapter = new ImageAdapter(SearchResultsController.this, imageResultList);
        imageGrid.setAdapter(imageAdapter);

        //imageAdapter.notifyDataSetChanged();

        imageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedResultPhoto = (Photo) parent.getItemAtPosition(position);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultsController.this, SearchPhotoController.class);
                startActivity(intent);
            }
        });

    }
}
