package com.example.appdevelopment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddNewPlaces extends AppCompatActivity  {

    static  final int PICK_IMAGE = 100;
    Uri imageUri;
    String uri_lik;
    ImageView choose_pic_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_places);

        choose_pic_btn = (ImageView) findViewById(R.id.imageView3);
        Button  choose_photo2 = (Button) findViewById(R.id.choose_photo);

        choose_photo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("In", "clic");
                openGallery();
            }
        });

        Button add_place = (Button) findViewById(R.id.add_placeTO);
        add_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText txt_name =  findViewById(R.id.txt_place_name);
                String place_name = txt_name.getText().toString();

                EditText txt_address =  findViewById(R.id.txt_place_address);
                String place_address = txt_address.getText().toString();

                EditText txt_descri =  findViewById(R.id.txt_place_description);
                String place_description = txt_descri.getText().toString();

                if(!place_name.isEmpty() && !place_address.isEmpty() && !place_description.isEmpty()) {


                    Places places = new Places();
                    places.setPlace_name(place_name);
                    places.setPlace_address(place_address);
                    places.setPlace_description(place_description);
                    places.setPlace_img_like(uri_lik);

                    Login.connect.placesDAO().addPlace(places);

                    txt_name.setText("");
                    txt_address.setText("");
                    txt_descri.setText("");
                    List<Places> pl = Login.connect.placesDAO().getAllplaces();
                    // ArrayList<String> placeData = new ArrayList<>();
                    for (int i = 0; i < pl.size(); i++) {
                        Log.d("isAdmin kese before if", " name: " + pl.get(i).getPlace_name());
                        Log.d("isAdmin kese before if", " add: " + pl.get(i).getPlace_address());
                        Log.d("isAdmin kese before if", " dec: " + pl.get(i).getPlace_description());
                        Log.d("isAdmin kese before if", " Img: " + pl.get(i).getPlace_img_like());
                    }
                    Intent intent = new Intent(getApplicationContext(), AddNewPlaces.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Fileds should not be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button button1 = (Button) findViewById(R.id.goToLogin);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), Login.class);
                startActivity(in);
            }
        });



    }

    private void openGallery()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();

            if(imageUri != null){
                uri_lik = imageUri.toString();

                choose_pic_btn.setImageURI(imageUri);
            }else{
                Toast.makeText(this, "Image fild should not be empty", Toast.LENGTH_SHORT).show();
            }


            Log.d("uri", ""+imageUri);

        }
    }
}
