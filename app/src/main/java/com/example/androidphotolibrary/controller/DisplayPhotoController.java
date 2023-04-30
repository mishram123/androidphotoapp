package com.example.androidphotolibrary.controller;
import com.example.androidphotolibrary.model.*;
import com.example.androidphotolibrary.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
public class DisplayPhotoController extends AppCompatActivity{
    private ImageView photoImageView;
    private Button previousPhotoButton, nextPhotoButton, addTagButton, deleteTagButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_display);

        // Find views by their IDs
        photoImageView = findViewById(R.id.photo_image_view);
        previousPhotoButton = findViewById(R.id.previous_photo_button);
        nextPhotoButton = findViewById(R.id.next_photo_button);
        addTagButton = findViewById(R.id.add_tag_button);
        deleteTagButton = findViewById(R.id.delete_tag_button);
        backButton = findViewById(R.id.back_button);

        // Set click listeners for buttons
        previousPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle previous photo button click
            }
        });

        nextPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle next photo button click
            }
        });

        addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle add tag button click
            }
        });

        deleteTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle delete tag button click
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle back button click
                Intent intent = new Intent(DisplayPhotoController.this, AlbumDisplayController.class);
                startActivity(intent);
            }
        });
    }
}
