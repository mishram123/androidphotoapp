package com.example.androidphotolibrary.model;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.androidphotolibrary.R;
import com.example.androidphotolibrary.controller.AlbumDisplayController;

import java.io.File;
import java.util.*;

public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    private List<Photo> photosinAlbum;



    public ImageAdapter(Context c, List<Photo> photosinAlbum){
        mContext = c;
        this.photosinAlbum = photosinAlbum;
    }

    public int getCount(){
        return photosinAlbum.size();
    }

    public Object getItem(int position){
        return photosinAlbum.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView;
        ImageView imgview = null;

        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            //imgview = imageView.findViewById();

        }else{
            imageView = (ImageView) convertView;
        }

        Uri imguri = photosinAlbum.get(position).getImageUri();
        imageView.setImageURI(imguri);
        return imageView;
    }
}
