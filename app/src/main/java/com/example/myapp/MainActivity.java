package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String soccer = "http://www.premierlegue.com";
    public static String football = "http://www.thefa.com";
    //public static String message = "http://www.gmail.com";
    Button play;
    Button alrt;
    private BroadcastReceiver MyReceiver= new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            //get baterry
            int level = intent.getIntExtra("level",0);
            //find the progress bar crtd in the main.xml
            ProgressBar pb =(ProgressBar)findViewById(R.id.progressBar);
            pb.setProgress(level);

            //accessing text view controll crtd in main.xml
            TextView textView=(TextView) findViewById(R.id.power);
            textView.setText("Baterylevel is at:" + Integer.toString(level) + "%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(MyReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));



        play = findViewById(R.id.enjoy);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sound.class);
                startActivity(intent);
            }


        });
    }

    //dfng a mtd to send alert
    public void alarm(View view) {
        EditText text = (EditText) findViewById(R.id.alarm);
        int i = Integer.parseInt(text.getText().toString());
        alrt = (Button)findViewById(R.id.btn_alarm);

        //crtg the intent and call your receiver
        Intent intent = new Intent(getApplicationContext(), MyReceiver.class);

        //crtg a pending intent to be fired when alarm is ready
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, 0);

        //use the alarm manager class to generate an alarm
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //Real time Clock to be used
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 1000), pendingIntent);

        Toast.makeText(this, "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();


    }

    public void sendMessage(View view) {
        EditText message = (EditText) findViewById(R.id.message);
        Toast.makeText(this, "Sending message " + message.getText().toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("MESSAGE", message.getText().toString());
        startActivity(intent);

        message.setText("");


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.teams, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem club) {
        int id = club.getItemId();
        switch (id) {
            case R.id.top_teams:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(soccer));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            case R.id.fa_cup:
                Intent fa = new Intent(Intent.ACTION_VIEW, Uri.parse(football));
                if (fa.resolveActivity(getPackageManager()) != null) {
                    startActivity(fa);
                }
            case R.id.progee:
                startActivity(new Intent(this, best_teams.class));
                return true;




            case R.id.list_view:
        startActivity(new Intent(this,list_view1.class));
        return true;
            case  R.id.send_mail:
                Intent m = new Intent(Intent.ACTION_SEND);
                m.setData(Uri.parse("mailto"));
                String to [] = {"namanyaada0@gmail.com","mupatt@gmail.com","bryntu9@gmail.com"};
                m.putExtra(Intent.EXTRA_EMAIL, to);
                m.putExtra(Intent.EXTRA_SUBJECT,"GREETINGS");
                m.putExtra(Intent.EXTRA_TEXT,"hello hope your fine");
                m.setType("message/rfc822");
                startActivity(m);
                return true;
            case R.id.call:

                try {
                    // check for call permissions
                    int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

                    // Here, thisActivity is the current activity
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {

                            // Show an explanation to the user *asynchronously*
                            Toast.makeText(this, "This permission is required to call a phone number", Toast.LENGTH_LONG).show();

                        } else {

                            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);

                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }
                    }
                    Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0784561422"));
                    startActivity(intent2);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return true;

    }
        return super.onOptionsItemSelected(club);
}

    public BroadcastReceiver getMyReceiver() {
        return MyReceiver;
    }

    public void setMyReceiver(BroadcastReceiver myReceiver) {
        MyReceiver = myReceiver;
    }
}