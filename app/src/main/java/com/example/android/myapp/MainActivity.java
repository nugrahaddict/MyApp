package com.example.android.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        String etUsername = getIntent().getStringExtra("username");
        tvUsername.setText(etUsername);
    }
}
