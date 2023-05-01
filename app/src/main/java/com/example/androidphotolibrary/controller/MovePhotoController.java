package com.example.androidphotolibrary.controller;
import com.example.androidphotolibrary.model.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidphotolibrary.R;

import java.util.ArrayList;

import android.content.Context;


import android.widget.BaseAdapter;
public class MovePhotoController extends AppCompatActivity{
    private ListView albumListView;
    private TextView selectedAlbumTextView;
    private Button movePhotoButton, homeButton;
    private ArrayList<String> albumslist;
    private String selectedAlbum;
    public String getSelectedAlbum(){
        return selectedAlbum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_photo_display);

        albumListView = findViewById(R.id.album_list_view2);
        selectedAlbumTextView = findViewById(R.id.selected_album_text_view2);
        movePhotoButton = findViewById(R.id.move_to_album_button);
        homeButton = findViewById(R.id.home_button);

        movePhotoButton.setOnClickListener(v -> {
            // Handle the Move Photo button click
        });

        homeButton.setOnClickListener(v -> {
            // Handle the Home button click
        });
    }

}
