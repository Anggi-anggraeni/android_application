package com.example.bootcampeksad.menu;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.eksad.bootcampeksad.R;
import com.example.bootcampeksad.R;

public class GlideActivity extends AppCompatActivity {
    private Context context=this;
    private Button loadbuttonimageGlide;
    private ImageView previewPhotoWebGlide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        loadbuttonimageGlide = (Button) findViewById(R.id.loadbuttonimageGlide);
        loadbuttonimageGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadimagedenganGlide();
            }
        });

        previewPhotoWebGlide = (ImageView) findViewById(R.id.previewPhotoWebGlide);
    }

    private void loadimagedenganGlide(){
        String urlImage = "https://shiroshrine.files.wordpress.com/2014/09/shanaou.jpg";
        Glide.with(context).load(urlImage).into(previewPhotoWebGlide);
    }
}
