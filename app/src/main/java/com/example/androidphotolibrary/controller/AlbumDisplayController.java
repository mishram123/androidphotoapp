package com.example.androidphotolibrary.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.example.androidphotolibrary.R;
import com.example.androidphotolibrary.model.*;
public class AlbumDisplayController extends AppCompatActivity{
    private GridView gridView;
    private List<Photo> imageList;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_display);

        imageList = new ArrayList<>();

        gridView = findViewById(R.id.image_gridview);
        imageAdapter = new ImageAdapter(AlbumDisplayController.this, imageList);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        Button add_photo_button = findViewById(R.id.add_photo_button);
        Button delete_photo_button = findViewById(R.id.delete_photo_button);
        Button display_photo_button = findViewById(R.id.display_photo_button);
        Button back_button = findViewById(R.id.back_button);

        add_photo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        delete_photo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        display_photo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlbumDisplayController.this, UserSystemController.class);
                startActivity(intent);
            }
        });

    }
    
}
