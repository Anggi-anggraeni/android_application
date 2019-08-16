package com.example.bootcampeksad.menu;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bootcampeksad.R;
import com.squareup.picasso.Picasso;

public class PicassoActivity extends AppCompatActivity {
    private Context context = this;
    private Button buttonLoadImagePicasso;
    private ImageView previewImageWebPicasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        buttonLoadImagePicasso = (Button) findViewById(R.id.buttonLoadImagePicasso);
        buttonLoadImagePicasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageDenganPicasso();
            }
        });

        previewImageWebPicasso = (ImageView) findViewById(R.id.previewImageWebPicasso);
    }

    private void loadImageDenganPicasso(){
        String urlImage = "https://shiroshrine.files.wordpress.com/2014/09/shanaou.jpg";
        //pemanggilan picasso
        Picasso.get()
                .load(urlImage)
                .placeholder(R.drawable.minion)
                .error(R.drawable.error)
                .into(previewImageWebPicasso);
    }
}
