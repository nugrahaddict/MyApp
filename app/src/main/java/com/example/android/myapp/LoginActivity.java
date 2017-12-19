package com.example.android.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;

import static android.net.wifi.WifiEnterpriseConfig.Eap.PWD;

/**
 * Created by Adi Nugraha on 12/19/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private static String UNAME = "nugraha";
    private static String PWD = "00000000000";

    @BindView(R.id.etUsername) EditText username;
    @BindView(R.id.pass) EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

    }

    @OnClick(R.id.btnlogin)
        void gotoLogin(){
            if(validatePwd()){
                String u = username.getText().toString();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("username", u);
                startA
            }
        }



    private boolean validatePwd(){
        String u = username.getText().toString();
        String p = password.getText().toString();

        if(u.equals(UNAME) && p.equals(PWD)){
            return true;
        } else {
            return false;
        }
    }

}
