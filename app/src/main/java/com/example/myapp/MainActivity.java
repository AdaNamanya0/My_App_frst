package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");

        TextView messageView =
                (TextView)findViewById(R.id.messageTextView);
        messageView.setText(message);
    }
    public void sendMessage(View view) {
        EditText message = (EditText)findViewById(R.id.message);
        Toast.makeText(this, "Sending message " + message.getText().toString(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        intent.putExtra("MESSAGE",message.getText().toString()); startActivity(intent);

        message.setText("");



    }

}
