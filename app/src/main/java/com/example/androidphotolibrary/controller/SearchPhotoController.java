package com.example.androidphotolibrary.controller;

import com.example.androidphotolibrary.model.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidphotolibrary.R;

import java.util.ArrayList;
import java.util.List;

public class SearchPhotoController extends AppCompatActivity{
    private Spinner tagTypeSpinner;
    private EditText tagValueEditText;
    private ListView tagsListView;

    private ArrayList<String> tagsList = new ArrayList<>();

    private Photo searchedPhoto;

    private String selectedTag;

    public String getSelectedTag(){
        return selectedTag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_photo_display);

        tagTypeSpinner = findViewById(R.id.tagTypeSpinner);
        tagValueEditText = findViewById(R.id.tagValueEditText);
        tagsListView = findViewById(R.id.tagsListView);

        ArrayAdapter<CharSequence> spinneradapter = ArrayAdapter.createFromResource(this,
                R.array.tag_types, android.R.layout.simple_spinner_item);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagTypeSpinner.setAdapter(spinneradapter);

        User user = UserSystemController.getMainUser();
        List<Album> albums = user.getAlbums();
        for (int i = 0; i < albums.size(); i++) {
            Album album = albums.get(i);
            List<Photo> photos = album.getPhotos();
            for (int j = 0; j < photos.size(); j++) {
                Photo photo = photos.get(j);
                List<Tag> tags = photo.getTags();
                for (int k = 0; k < tags.size(); k++) {
                    tagsList.add(tags.get(k).getKey() + " " + tags.get(k).getValue());
                }
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tagsList);
        tagsListView.setAdapter(adapter);

        tagValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Filter the data based on the search text
                ArrayList<String> filteredData = new ArrayList<>();
                for (String item : tagsList) {
                    if (item.toLowerCase().startsWith(s.toString().toLowerCase())) {
                        filteredData.add(item);
                    }
                }
                // Update the list view with the filtered data
                adapter.clear();
                adapter.addAll(filteredData);
            }
        });
        tagsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Tag = (String) parent.getItemAtPosition(position);
                // TODO: Handle the click event for the selected tag
                selectedTag = Tag;
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPhotoController.this, UserSystemController.class);
                startActivity(intent);
            }
        });

        Button openPhotoButton = findViewById(R.id.openPhotoButton);


        openPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Open the photo
                String Tag = selectedTag;


                if(Tag!=null){
                    String[] parts = Tag.split(" ");
                    Tag tagtoSearch = new Tag(parts[0], parts[1]);
                    outerloop:
                    for(int i = 0; i<UserSystemController.getMainUser().getAlbums().size();i++){
                        for(int j = 0; j<UserSystemController.getMainUser().getAlbums().get(i).getNumPhotos();j++){
                            for(int k=0; k<UserSystemController.getMainUser().getAlbums().get(i).getPhotos().get(j).getTags().size(); i++){
                                if(UserSystemController.getMainUser().getAlbums().get(i).getPhotos().get(j).tagEquals(UserSystemController.getMainUser().getAlbums().get(i).getPhotos().get(j).getTags().get(k), tagtoSearch) == true){
                                    searchedPhoto = UserSystemController.getMainUser().getAlbums().get(i).getPhotos().get(j);
                                    AlbumDisplayController.setSelectedPhoto(searchedPhoto);
                                    break outerloop;
                                }
                            }

                        }

                    }
                }
                Intent intent = new Intent(SearchPhotoController.this, DisplayPhotoController.class);
                startActivity(intent);
            }
        });
    }
}


