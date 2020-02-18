package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String soccer="http://www.premierlegue.com";
    public static String football="http://www.thefa.com";
    Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.enjoy);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,sound.class);
                startActivity(intent);
            }


        });
    }
    public void sendMessage(View view) {
        EditText message = (EditText)findViewById(R.id.message);
        Toast.makeText(this, "Sending message " + message.getText().toString(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        intent.putExtra("MESSAGE",message.getText().toString()); startActivity(intent);

        message.setText("");



    }
public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.teams,menu);
        return true;

}
public boolean onOptionsItemSelected(MenuItem club){
        int id =club.getItemId();
        switch (id) {
            case R.id.top_teams:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(soccer));
                if (intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }
            case R.id.fa_cup:
                Intent fa = new Intent(Intent.ACTION_VIEW,Uri.parse(football));
                if (fa.resolveActivity(getPackageManager())!=null){
                    startActivity(fa);
                }
              }
    return super.onOptionsItemSelected(club);
    }

    }

