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

import java.util.List;

public class CustomWishlistAdapter extends ArrayAdapter<Wishlist> {

    private Context context;
    private int resource;
    List<Wishlist> wishlists;
    String place_imgege;
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public CustomWishlistAdapter(@NonNull Context context, int resource, @NonNull List<Wishlist> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.wishlists = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String place_name = this.wishlists.get(position).getPlace_name();

        if(this.wishlists.get(position).getPlace_img()==null){
                place_imgege = "android.resource://com.example.appdevelopment/drawable/no_image";

        }else {
            place_imgege = this.wishlists.get(position).getPlace_img();
        }

        // Connect the row layout file to this adapter
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource,parent,false);


        // Get the textviews and images from the row layout file

        TextView placeName = (TextView) convertView.findViewById(R.id.wishlist_of_places);
        ImageView image_im = (ImageView) convertView.findViewById(R.id.wishlist_of_place_image);


        //set text for each text views
        placeName.setText(place_name);
        image_im.setImageURI(Uri.parse(place_imgege));




        return convertView;
    }
}
