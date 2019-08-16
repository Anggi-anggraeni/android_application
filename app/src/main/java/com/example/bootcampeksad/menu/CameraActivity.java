package com.example.bootcampeksad.menu;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bootcampeksad.R;

import java.io.ByteArrayOutputStream;

public class CameraActivity extends AppCompatActivity {
    private Context context = this;
    private Button buttonCamera;
    private ImageView previewPhoto;

    private int ID_PEMANGGIL = 1;
    private int ID_PERMISSION_CAMERA = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        buttonCamera = (Button) findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logout
                if (checkPermissionCamera()) {
                    ambilImageDariCamera();
                }
            }
        });

        previewPhoto = (ImageView) findViewById(R.id.previewPhoto);
    }

    private boolean checkPermissionCamera() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        ID_PERMISSION_CAMERA);
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
        setContentView(R.layout.activity_camera);

        buttonCamera = (Button) findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissionCamera()) {

                }
            }
        });
    }

    //untuk memanggil intent camera
    private void ambilImageDariCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, ID_PEMANGGIL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ID_PEMANGGIL) {
                tampilkanImageDariCamera(data);
            }
        }
    }

    private void tampilkanImageDariCamera(Intent data) {
        Bitmap bitmap = null;

        if (data != null) {
            bitmap = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, outputStream);
            previewPhoto.setImageBitmap(bitmap);
        }
    }
}