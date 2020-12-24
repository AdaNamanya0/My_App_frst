package com.example.myapp;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class external extends AppCompatActivity {
    Button button_save;
    Button button_loading;
    EditText edtinput;
    String filename="";
    String filepath="";
    String filecontent="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external);


        button_save = findViewById(R.id.button_save);
        button_loading = findViewById(R.id.button_loading);
        edtinput = findViewById(R.id.edtinput);
        filename = "myFile.text";
        filepath = "MyFileDir";

        if (isexternalAvailableForRW()) {
            button_save.setEnabled(true);
        }
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_loading.setText("");
                filecontent = edtinput.getText().toString().trim();
                if (!filecontent.equals("")) {
                    File myExternalFile = new File(getExternalFilesDir(filepath), filename);
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(myExternalFile);
                        fos.write(filecontent.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    edtinput.setText("");
                    Toast.makeText(getApplicationContext(), "saved on the external card", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "empty text field eneter something", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean isexternalAvailableForRW() {
        String extStorageState = Environment.getExternalStorageState();
        if (extStorageState.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

}
