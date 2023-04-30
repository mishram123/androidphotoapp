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

public class AlbumDisplayController extends AppCompatActivity{
    private GridView gridView;
    private List<Photo> imageList;
    private ImageAdapter imageAdapter;
    private Album albumToView = UserSystemController.getSelectedAlbumObject();

    public static Photo selectedPhoto;

    public static Album currentAlbum;

    public static Photo getSelectedPhoto(){
        return selectedPhoto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_display);

        currentAlbum = UserSystemController.getSelectedAlbumObject();

        imageList = new ArrayList<>();
        imageList = currentAlbum.getPhotos();

        gridView = findViewById(R.id.image_gridview);
        imageAdapter = new ImageAdapter(AlbumDisplayController.this, imageList);
        gridView.setAdapter(imageAdapter);

//        List<Photo> albumPhotos = albumToView.getPhotos();
//        if(albumPhotos != null){
//            imageList.addAll(albumPhotos);
//        }
        imageAdapter.notifyDataSetChanged();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPhoto = (Photo) parent.getItemAtPosition(position);
            }
        });

        Button add_photo_button = findViewById(R.id.add_photo_button);
        Button delete_photo_button = findViewById(R.id.delete_photo_button);
        Button display_photo_button = findViewById(R.id.display_photo_button);
        Button back_button = findViewById(R.id.back_button);


        delete_photo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSystemController.getSelectedAlbumObject().deletePhoto(selectedPhoto);
                imageList.remove(selectedPhoto);
                imageAdapter.notifyDataSetChanged();
            }
        });

        display_photo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AlbumDisplayController.this, DisplayPhotoController.class);
                startActivity(intent);
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlbumDisplayController.this, UserSystemController.class);
                startActivity(intent);
            }
        });
        add_photo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data.getData() != null) {
                //try {
                    Uri imageUri = data.getData();
                    //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    //imageList.add(new Photo(bitmap));
                    Photo photo = new Photo(imageUri);
                    //imageList.add(photo);
                    currentAlbum.addPhoto(photo);
                    imageAdapter.notifyDataSetChanged();
                    Toast.makeText(AlbumDisplayController.this, "Photo added successfully", Toast.LENGTH_SHORT).show();
                    UserSystemController.saveAlbumsToSharedPreferences(AlbumDisplayController.this);
                    //finish();
                //} catch (IOException e) {
                    //e.printStackTrace();
                    //Toast.makeText(AlbumDisplayController.this, "Failed to add photo", Toast.LENGTH_SHORT).show();
                //}
            }
        }
    }

}
