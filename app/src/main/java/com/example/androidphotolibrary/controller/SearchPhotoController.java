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
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidphotolibrary.R;

import java.util.ArrayList;
public class SearchPhotoController extends AppCompatActivity{
    private Spinner tagTypeSpinner;
    private EditText tagValueEditText;
    private ListView tagsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_photo_display);

        tagTypeSpinner = findViewById(R.id.tagTypeSpinner);
        tagValueEditText = findViewById(R.id.tagValueEditText);
        tagsListView = findViewById(R.id.tagsListView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tag_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagTypeSpinner.setAdapter(adapter);

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
            }
        });
    }
}


