package com.example.androidphotolibrary.controller;
import com.example.androidphotolibrary.model.*;
import com.example.androidphotolibrary.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.net.Uri;
import android.widget.Toast;
import android.provider.MediaStore;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.bumptech.glide.Glide;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import android.util.Log;
import java.util.ArrayList;




import androidx.appcompat.app.AppCompatActivity;
public class DisplayPhotoController extends AppCompatActivity{
    private ImageView photoImageView;
    private Button previousPhotoButton, nextPhotoButton, addTagButton, deleteTagButton, backButton;
    private Photo photoToDisplay = AlbumDisplayController.getSelectedPhoto();

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

//        Uri imageUri = photoToDisplay.getImageUri();
//        photoImageView.setImageURI(imageUri);

        ImageAdapter imageAdapter = new ImageAdapter(DisplayPhotoController.this, new ArrayList<>());
        imageAdapter.loadImageIntoImageView(photoToDisplay, photoImageView);

//        Glide.with(this)
//                .load(imageUri)
//                .into(photoImageView);

//        Log.d("DisplayPhotoController", "Image Uri: " + imageUri);

//        try {
//            InputStream inputStream = getContentResolver().openInputStream(imageUri);
//            Bitmap photo = BitmapFactory.decodeStream(inputStream);
//            photoImageView.setImageBitmap(photo);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Error reading file", Toast.LENGTH_SHORT).show();
//        }

//        try {
//            Bitmap photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//            photoImageView.setImageBitmap(photo);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Error reading file", Toast.LENGTH_SHORT).show();
//        }

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
