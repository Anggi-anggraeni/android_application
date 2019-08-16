package com.example.bootcampeksad.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.bootcampeksad.R;
import com.example.bootcampeksad.Utility.LoadingClass;

public class volleyActivity extends AppCompatActivity {
    private Context context = this;
    private Button buttonLoadImage;
    private ImageView previewImageWeb;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

            buttonLoadImage = (Button) findViewById(R.id.buttonLoadImage);
            buttonLoadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ambilImageDariWeb();
                }
             });

            previewImageWeb = (ImageView) findViewById(R.id.previewImageWeb);
    }

    private void ambilImageDariWeb()
    {
        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context, "Loading");
        loading.show();

        String urlImage = "https://samuraigames.fandom.com/wiki/Yoshitsune_Minamoto";

        ImageRequest imageRequest = new ImageRequest(urlImage,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        loading.dismiss();
                        previewImageWeb.setImageBitmap(response);
                    }
                },
                0,
                0,
                ImageView.ScaleType.FIT_CENTER,
                Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error panggil image : " +error.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                    }
                }
        );

        Volley.newRequestQueue(context).add(imageRequest);

    }

}
