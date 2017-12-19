package com.example.android.myapp;

import android.content.Intent;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Adi Nugraha on 12/19/2017.
 */

public class LoginActivity extends BaseActivity {

    private static String UNAME = "nugraha";
    private static String PWD = "0000";

    @BindView(R.id.etUsername)
    EditText username;
    @BindView(R.id.pass)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        ButterKnife.bind(LoginActivity.this);
    }



    @OnClick(R.id.btnlogin)
    void gotoLogin() {

        String u = username.getText().toString();
        String p = password.getText().toString();
        Call<ResponseLogin> call = getApi().dologin(u, p);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, retrofit2.Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    ResponseLogin res = response.body();
                    if (res.getStatus().equals("200")) {
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("username", res.getFullname());
                        startActivity(i);
                        finish();
                    } else if (res.getStatus().equals("500")) {
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
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "Connection Refused",
                        Toast.LENGTH_LONG)
                        .show();

            }
        });


    }


}
