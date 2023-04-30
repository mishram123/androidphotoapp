package com.example.androidphotolibrary.controller;

import com.example.androidphotolibrary.model.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import android.content.SharedPreferences;



public class UserSystemController extends AppCompatActivity{

    private ListView albumListView;
    private TextView selectedAlbumTextView;
    private Button createAlbumButton, deleteAlbumButton, renameAlbumButton, openAlbumButton, searchPhotosButton;
    private ArrayList<String> albumsList;

    private String selectedAlbum;
    private static Album selectedAlbumObject;

    public String getSelectedAlbum(){
        return selectedAlbum;
    }

    public static Album getSelectedAlbumObject(){
        return selectedAlbumObject;
    }

    public void setSelectedAlbumObject(String album){
        for(int i = 0; i<mainUser.getAlbums().size();i++){
            if(album.equals(mainUser.getAlbums().get(i).getName())){
                selectedAlbumObject = mainUser.getAlbums().get(i);
                break;
            }
        }
    }

    User mainUser = new User("user");
    private UserDatabaseHelper dbhelper;

    @Override
    protected void onResume() {
        super.onResume();
        loadAlbumsFromSharedPreferences();
        updateAlbumsList();
    }

    private void updateAlbumsList() {
        albumsList.clear();
        for (int i = 0; i < mainUser.getAlbums().size(); i++) {
            albumsList.add(mainUser.getAlbums().get(i).getName());
        }
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) albumListView.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void saveAlbumsToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mainUser.getAlbums());
        editor.putString("albums", json);
        editor.apply();
    }

    private void loadAlbumsFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("albums", null);
        Type type = new TypeToken<ArrayList<Album>>() {}.getType();
        ArrayList<Album> loadedAlbums = gson.fromJson(json, type);

        if (loadedAlbums != null) {
            mainUser.setAlbums(loadedAlbums);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_system);


        albumListView = findViewById(R.id.album_list_view);
        selectedAlbumTextView = findViewById(R.id.selected_album_text_view);
        createAlbumButton = findViewById(R.id.create_album_button);
        deleteAlbumButton = findViewById(R.id.delete_album_button);
        renameAlbumButton = findViewById(R.id.rename_album_button);
        openAlbumButton = findViewById(R.id.open_album_button);
        searchPhotosButton = findViewById(R.id.search_photos_button);

        albumsList = new ArrayList<>();

        for(int i = 0; i<mainUser.getAlbums().size(); i++){
            albumsList.add(mainUser.getAlbums().get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, albumsList);
        albumListView.setAdapter(adapter);


        albumListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedAlbum = albumsList.get(position);
                selectedAlbumTextView.setText("Selected Item: " + selectedAlbum);

            }
        });


        createAlbumButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(UserSystemController.this);
                builder.setTitle("Enter album name:");

                final EditText input = new EditText(UserSystemController.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String albumName = input.getText().toString();
                        Album newAlbum = new Album(albumName);
                        mainUser.addAlbum(newAlbum);
                        albumsList.add(albumName);
                        adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        deleteAlbumButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String deletingAlbum = getSelectedAlbum();

                if(deletingAlbum != null){
                    for(int i = 0; i<mainUser.getAlbums().size(); i++){
                        if(deletingAlbum.compareTo(mainUser.getAlbums().get(i).getName()) == 0){
                            mainUser.getAlbums().remove(i);
                        }
                    }
                    albumsList.remove(deletingAlbum);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        renameAlbumButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(UserSystemController.this);
                builder.setTitle("Enter album name:");

                final EditText input = new EditText(UserSystemController.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        String albumName = input.getText().toString();
                        for(int i = 0; i<mainUser.getAlbums().size(); i++){
                            if(albumName.compareTo(mainUser.getAlbums().get(i).getName())==0){
                                mainUser.getAlbums().get(i).setName(albumName);
                                break;
                            }
                        }
                        String deletingAlbum = getSelectedAlbum();
                        albumsList.remove(deletingAlbum);
                        albumsList.add(albumName);
                        adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });



        openAlbumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAlbumsToSharedPreferences();
                for(int i = 0; i<mainUser.getAlbums().size(); i++){
                    if(selectedAlbum.equals(mainUser.getAlbums().get(i).getName())){
                        selectedAlbumObject = mainUser.getAlbums().get(i);
                        break;
                    }
                }
                Intent intent = new Intent(UserSystemController.this, AlbumDisplayController.class);
                startActivity(intent);
            }
        });

        searchPhotosButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }
}
