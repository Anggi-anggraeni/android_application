package com.example.bootcampeksad.menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bootcampeksad.R;

import java.io.IOException;

public class GaleryActivity extends AppCompatActivity {
    private Context context = this;
    private Button buttonGalery;
    private ImageView previewPhotoGalery;

    private int ID_PERMISSION_GALERY = 2;
    private int ID_PEMANGGIL_GALERY = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        buttonGalery = (Button) findViewById(R.id.buttonGalery);
        buttonGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissionGalery()) {
                    ambilImageDariGalery();
                }
            }
        });

        previewPhotoGalery = (ImageView) findViewById(R.id.previewPhotoGalery);
    }

    private boolean checkPermissionGalery() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        ID_PERMISSION_GALERY);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == ID_PERMISSION_GALERY) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ambilImageDariGalery();
            } else {
                Toast.makeText(context,
                        "Anda Harus Memberikan Ijin Akses Read External Storage",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ambilImageDariGalery() {
        Intent galery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galery.setType("image/*");
        galery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galery, "Pilih Photo"), ID_PEMANGGIL_GALERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ID_PEMANGGIL_GALERY) {
                tampilkanPhotoDariGalery(data);
            }
        }
    }

    private void tampilkanPhotoDariGalery(Intent data) {
        Bitmap bitmap = null;

        if (data != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());

            } catch (IOException ex) {
                System.out.println("Error accesing image :" + ex.getMessage());
            }
        }
    }
}
