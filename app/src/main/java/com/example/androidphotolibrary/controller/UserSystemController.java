package com.example.androidphotolibrary.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidphotolibrary.R;

import java.util.ArrayList;

public class UserSystemController extends AppCompatActivity{

    private ListView albumListView;
    private TextView selectedAlbumTextView;
    private Button createAlbumButton, deleteAlbumButton, renameAlbumButton, openAlbumButton, searchPhotosButton;
    private ArrayList<String> albumsList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_system);

        albumListView = findViewById(R.id.album_list_view);
        selectedAlbumTextView = findViewById(R.id.selected_album_text_view);
        createAlbumButton = findViewById(R.id.create_album_button);
        deleteAlbumButton = findViewById(R.id.delete_album_button);
        renameAlbumButton = findViewById(R.id.rename_album_button);
        searchPhotosButton = findViewById(R.id.search_photos_button);

        albumsList = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, albumsList);
        albumListView.setAdapter(adapter);

        albumListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = albumsList.get(position);
                selectedAlbumTextView.setText("Selected Item: " + selectedItem);
            }
        });

        createAlbumButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        deleteAlbumButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        renameAlbumButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        searchPhotosButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }
}
