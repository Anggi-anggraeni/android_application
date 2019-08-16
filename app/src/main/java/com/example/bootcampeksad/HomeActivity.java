package com.example.bootcampeksad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bootcampeksad.Utility.Constanta;
import com.example.bootcampeksad.Utility.SessionManager;
import com.example.bootcampeksad.menu.CameraActivity;
import com.example.bootcampeksad.menu.GaleryActivity;
import com.example.bootcampeksad.menu.PicassoActivity;

public class HomeActivity extends AppCompatActivity {
    private Context context = this;
    private TextView labelUsername;
    private Button buttonLogout;

    private CardView cardCamera, cardGalery, cardVolley, cardPicasso;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        labelUsername = (TextView) findViewById(R.id.labelUsername);
        //tangkap intent extra
        //tangkap username
        if (getIntent().getStringExtra(Constanta.ID_EXTRA_USERNAME) != null) {
            String username = getIntent().getStringExtra(Constanta.ID_EXTRA_USERNAME);
            labelUsername.setText(username);
        }

        //ambil username dari Session manager
        labelUsername.setText(SessionManager.getUsername(context));
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logout
                logoutConfirmation();
            }
        });

        cardCamera = (CardView) findViewById(R.id.cardCamera);
        cardCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahkeActivityCamera();
            }
        });

        cardPicasso = (CardView) findViewById(R.id.cardPicasso);
        cardPicasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahkeActivityPicasso();
            }
        });

        cardGalery = (CardView)findViewById (R.id.cardGalery);
        cardGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahkeActivityGalery();
            }
        });

        cardVolley = (CardView) findViewById(R.id.cardVolley);
        cardVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahkeActivityVolley();
            }
        });
    }
    private void pindahkeActivityPicasso(){
        Intent pindah = new Intent(context, PicassoActivity.class);
        startActivity(pindah);
    }
    private void pindahkeActivityGalery(){
        Intent pindah = new Intent(context, GaleryActivity.class);
        startActivity(pindah);
    }

    private void pindahkeActivityCamera()
    {
        Intent pindah = new Intent(context, CameraActivity.class);
        startActivity(pindah);
    }

    private void pindahkeActivityVolley()
    {
        Intent pindah = new Intent(context, CameraActivity.class);
        startActivity(pindah);
    }
    private void logoutConfirmation()
    {
        AlertDialog.Builder confirmation = new AlertDialog.Builder(context);
        confirmation.setMessage("Apakah Anda Yakin Ingin Logout")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //aksi jika tekan ya
                        logoutAplication();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //aksi jika tekan tidak
                        dialog.cancel();
                    }
                })
                .setCancelable(false);
        AlertDialog showConfirmation = confirmation.create();
        showConfirmation.show();
    }

    private void logoutAplication(){
        //kembalikan flag login ke false
        SessionManager.setLoginFlag(context, false);

        //panggil login screen
        Intent pindah = new Intent(context, LoginActivity.class);
        startActivity(pindah);

        //tutup home activity
        finish();
    }
}
