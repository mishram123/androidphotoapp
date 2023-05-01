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
import android.widget.Toast;

public class MovePhotoController extends AppCompatActivity{
    private ListView albumListView;
    private TextView selectedAlbumTextView;
    private Button movePhotoButton, homeButton;
    private ArrayList<String> albumslist;
    private String selectedAlbum;
    public String getSelectedAlbum(){
        return selectedAlbum;
    }

    private Photo photoToMove = AlbumDisplayController.getSelectedPhoto();
    private Album originalAlbum = UserSystemController.getSelectedAlbumObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_photo_display);

        albumListView = findViewById(R.id.album_list_view2);
        selectedAlbumTextView = findViewById(R.id.selected_album_text_view2);
        movePhotoButton = findViewById(R.id.move_to_album_button);
        homeButton = findViewById(R.id.home_button);

        albumslist = new ArrayList<>();

        for(int i = 0; i<UserSystemController.getMainUser().getAlbums().size(); i++){
            albumslist.add(UserSystemController.getMainUser().getAlbums().get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, albumslist);
        albumListView.setAdapter(adapter);
        albumListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedAlbum = albumslist.get(position);
                selectedAlbumTextView.setText("Selected Item: " + selectedAlbum);

            }
        });

        movePhotoButton.setOnClickListener(v -> {
            // Handle the Move Photo button click
            if(getSelectedAlbum().equals(originalAlbum.getName())){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alert");
                builder.setMessage("Cannot move to same album");
                builder.setPositiveButton("OK", null);
                AlertDialog alert = builder.create();
                alert.show();
            }else{
                for(int i = 0; i<UserSystemController.getMainUser().getAlbums().size(); i++){
                    if(selectedAlbum.equals(UserSystemController.getMainUser().getAlbums().get(i).getName())){
                        UserSystemController.getMainUser().getAlbums().get(i).addPhoto(photoToMove);
                        break;
                    }
                }
                originalAlbum.deletePhoto(photoToMove);
                Toast.makeText(MovePhotoController.this, "Photo moved successfully", Toast.LENGTH_SHORT).show();

            }
        });

        homeButton.setOnClickListener(v -> {
            // Handle the Home button click
            Intent intent = new Intent(MovePhotoController.this, UserSystemController.class);
            startActivity(intent);
        });
    }

}
