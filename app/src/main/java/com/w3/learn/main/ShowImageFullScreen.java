package com.w3.learn.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.w3.learn.R;

/**
 * Created by USER24 on 3/12/2018.
 */

public class ShowImageFullScreen extends AppCompatActivity {

    ImageView iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_full_screen);
        //Intent i=getIntent();
        //File f = i.getExtras().getParcelable("img");

        String f= getIntent().getStringExtra("img");
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv2.setImageURI(Uri.parse(f) );





    }
}