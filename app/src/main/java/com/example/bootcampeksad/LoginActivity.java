package com.example.bootcampeksad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bootcampeksad.Utility.SessionManager;

public class LoginActivity extends AppCompatActivity {
    private Context context = this;
    private int counter_back = 1;

    private EditText inputUsername, inputPassword;
    private CheckBox checkBoxRemember;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername =(EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        checkBoxRemember = (CheckBox) findViewById(R.id.checkBoxRemember);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiInput();
            }
        });

      checkBoxRemember();
    }

    private void checkBoxRemember()
    {
        if (SessionManager.cekRemember(context)){
            inputUsername.setText(SessionManager.getUsername(context));
            inputPassword.setText(SessionManager.getPassword(context));

            checkBoxRemember.setChecked(true);
        }else {

        }
    }

    private void validasiInput(){
        String valueUsername = inputUsername.getText().toString().trim();
        String valuePassword = inputPassword.getText().toString().trim();

        if (valueUsername.length() == 0){
            Toast.makeText(context, "Username masih kosong", Toast.LENGTH_SHORT).show();
        } else if (valuePassword.length() == 0){
            Toast.makeText(context, "Password masih kosong", Toast.LENGTH_SHORT).show();
        } else {
            //validasi sukses
            boolean remember = checkBoxRemember.isChecked();

            //simpan data login
            SessionManager.simpandatalogin(context, valueUsername, valuePassword, remember);

            Intent pindah = new Intent(context, HomeActivity.class);
            pindah.putExtra("USERNAME",valueUsername);
            pindah.putExtra("PASSWORD",valuePassword);
            startActivity(pindah);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (counter_back < 2) {
            Toast.makeText(context, "Tekan Back Satu kali Lagi Untuk Keluar", Toast.LENGTH_SHORT).show();
            counter_back++;
        } else if (counter_back == 2) {
            finish();
        }
    }

}
