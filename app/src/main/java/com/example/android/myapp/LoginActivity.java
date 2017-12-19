package com.example.android.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

import static android.net.wifi.WifiEnterpriseConfig.Eap.PWD;

/**
 * Created by Adi Nugraha on 12/19/2017.
 */

public class LoginActivity extends BaseActivity {

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

        String u = username.getText().toString();
        String p = password.getText().toString();
        Call<Response> call = getApi().dologin(u, p);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()){
                    Response res = response.body();
                    if (res.getStatus().equals("200")){
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("username", res.getFullname());
                        startActivity(i);
                        finish();
                    } else if (res.getStatus().equals("500")){
                        Toast.makeText(LoginActivity.this,
                                response.body().getMessage(),
                                Toast.LENGTH_LONG)
                        .show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Connection Refused", Toast.LENGTH_LONG)
                            .show();
                    ;
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "Connection Refused",
                        Toast.LENGTH_LONG)
                        .show();

            }
        })


            if(validatePwd()){
                String u = username.getText().toString();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("username", u);
                startActivity(i);
                finish();
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
