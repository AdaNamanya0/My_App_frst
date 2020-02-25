package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class best_teams extends AppCompatActivity {
    TextView textView;
    private Object CharSequence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_teams);
        textView = findViewById(R.id.cfile);
        // TextView text = (TextView) findViewById(R.id.cfile);

        String str = "";
        try {
            InputStream inputStream = getAssets().open("program.c");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            str = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.setText((CharSequence)str);
    }
}