package com.example.appdevelopment;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AttractionListViewAdapter extends ArrayAdapter<Places> {

    private List<Places> placesArrayList;
    private Context context;
    private int resource;
    String place_image_uri;


    public AttractionListViewAdapter(@NonNull Context context, int resource, @NonNull List<Places> objects) {

        super(context, resource, objects);

        this.placesArrayList = objects;     // set the data source
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String place_name = this.placesArrayList.get(position).getPlace_name();
        String place_add = this.placesArrayList.get(position).getPlace_address();
        String place_descri = this.placesArrayList.get(position).getPlace_description();
        if(this.placesArrayList.get(position).getPlace_img_like() ==null) {
           // place_image_uri = "content://com.google.android.apps.photos.contentprovider/-1/1/content%3A%2F%2Fmedia%2Fexternal%2Fimages%2Fmedia%2F81/ORIGINAL/NONE/image%2Fjpeg/1568261137";
             place_image_uri = "android.resource://com.example.appdevelopment/drawable/no_image";
        }else {

             place_image_uri = this.placesArrayList.get(position).getPlace_img_like();
        }

       // long population = this.countries.get(position).getPopulation();


        // Connect the row layout file to this adapter
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource,parent,false);


        // Get the textviews and images from the row layout file

        TextView placeName = (TextView) convertView.findViewById(R.id.custom_place_name);
        TextView placeAddress = (TextView) convertView.findViewById(R.id.custom_place_address);
        TextView placeDescription = (TextView) convertView.findViewById(R.id.custom_place_Description);
        ImageView image = (ImageView) convertView.findViewById(R.id.adapter_image);

        //set text for each text views
        placeName.setText(place_name);
        placeAddress.setText(place_add);
        placeDescription.setText(place_descri);

            image.setImageURI(Uri.parse(place_image_uri));


        return convertView;
    }
}
