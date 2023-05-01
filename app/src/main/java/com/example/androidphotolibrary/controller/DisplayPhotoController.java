package com.example.androidphotolibrary.controller;
import com.example.androidphotolibrary.model.*;
import com.example.androidphotolibrary.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.net.Uri;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.List;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class DisplayPhotoController extends AppCompatActivity{
    private ImageView photoImageView;
    private Button previousPhotoButton, nextPhotoButton, addTagButton, deleteTagButton, backButton, movePhotoButton;
    private Photo photoToDisplay = AlbumDisplayController.getSelectedPhoto();

    private ListView tagListView;

    private int currentPhotoIndex;

    private ArrayList<String> tagsList;

    private String selectedTag;

    List<Photo> imageList = AlbumDisplayController.imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_display);

//        List<Photo> imageList = AlbumDisplayController.imageList;
        currentPhotoIndex = imageList.indexOf(photoToDisplay);

        tagsList = new ArrayList<>();

        for(int i = 0; i< photoToDisplay.getTags().size(); i++){
            String tag = photoToDisplay.getTags().get(i).getKey() + " " + photoToDisplay.getTags().get(i).getValue();
            tagsList.add(tag);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);



        // Find views by their IDs
        photoImageView = findViewById(R.id.photo_image_view);
        previousPhotoButton = findViewById(R.id.previous_photo_button);
        nextPhotoButton = findViewById(R.id.next_photo_button);
        addTagButton = findViewById(R.id.add_tag_button);
        deleteTagButton = findViewById(R.id.delete_tag_button);
        backButton = findViewById(R.id.back_button);
        movePhotoButton = findViewById(R.id.move_photo_button);
        tagListView = findViewById(R.id.tag_list_view);
        tagListView.setAdapter(adapter);

        Uri imageUri = photoToDisplay.getImageUri();
        photoImageView.setImageURI(imageUri);

//        ImageAdapter imageAdapter = new ImageAdapter(DisplayPhotoController.this, new ArrayList<>());
//        imageAdapter.loadImageIntoImageView(photoToDisplay, photoImageView);

        Glide.with(this)
                .load(imageUri)
                .into(photoImageView);


        tagListView.setBackgroundColor(getResources().getColor(android.R.color.white));
        tagListView.setVerticalScrollBarEnabled(true);





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
        tagListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedTag2 = (String) adapterView.getItemAtPosition(i);
                // Do something with the selected tag
                selectedTag = selectedTag2;
            }
        });

        previousPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle previous photo button click
                if (currentPhotoIndex > 0) {
                    currentPhotoIndex--;
                    updateDisplayedPhoto();
                } else {
                    Toast.makeText(DisplayPhotoController.this, "No previous photo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nextPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle next photo button click
                if (currentPhotoIndex < imageList.size() - 1) {
                    currentPhotoIndex++;
                    updateDisplayedPhoto();
                } else {
                    Toast.makeText(DisplayPhotoController.this, "No next photo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle add tag button click
                AlertDialog.Builder builder = new AlertDialog.Builder(DisplayPhotoController.this);
                builder.setTitle("Add Tag");

                // Create the input fields for person and location
                final EditText personEditText = new EditText(DisplayPhotoController.this);
                personEditText.setHint("Person");
                final EditText locationEditText = new EditText(DisplayPhotoController.this);
                locationEditText.setHint("Location");

                // Add the input fields to the AlertDialog
                LinearLayout layout = new LinearLayout(DisplayPhotoController.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(personEditText);
                layout.addView(locationEditText);
                builder.setView(layout);

                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String person = personEditText.getText().toString();
                        String location = locationEditText.getText().toString();
                        photoToDisplay.addTag(person, location);
                        String tagToDisplay = person + " " + location;

                        tagsList.add(tagToDisplay);
                        ArrayAdapter<String> adapter = (ArrayAdapter<String>) tagListView.getAdapter();
                        adapter.clear();
                        adapter.addAll(tagsList);
                        adapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.show();
            }
        });

        deleteTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle delete tag button click
                String deletingTag = selectedTag;
                String[] parts = deletingTag.split(" ");
                Tag tagtoDelete = new Tag(parts[0], parts[1]);

                if (deletingTag!= null){
                    for(int i = 0; i<photoToDisplay.getTags().size(); i++){
                        if(photoToDisplay.tagEquals(tagtoDelete, photoToDisplay.getTags().get(i))){
                            photoToDisplay.removeTag(tagtoDelete);
                            break;
                        }
                    }
                    tagsList.remove(deletingTag);
                    ArrayAdapter<String> adapter = (ArrayAdapter<String>) tagListView.getAdapter();
                    adapter.clear();
                    adapter.addAll(tagsList);
                    adapter.notifyDataSetChanged();
                }

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
        movePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void updateDisplayedPhoto() {
        photoToDisplay = imageList.get(currentPhotoIndex);
        Uri imageUri = photoToDisplay.getImageUri();
        Glide.with(this)
                .load(imageUri)
                .into(photoImageView);
    }


}
