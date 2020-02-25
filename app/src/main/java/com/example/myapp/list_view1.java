package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class list_view1 extends AppCompatActivity {
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view1);
        listView=findViewById(R.id.list);
        final ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Nicklie");
        arrayList.add("Josephine");
        arrayList.add("Perius");
        arrayList.add("Shallon");
        arrayList.add("Layora");
        arrayList.add("Pauline");
        arrayList.add("Ingrid");
        arrayList.add("Faith");
        arrayList.add("Lucky");
        arrayList.add("Ritah");
        arrayList.add("Joel");
        arrayList.add("Tyson");
        arrayList.add("Benneth");
        arrayList.add("Generous");
        arrayList.add("Collins");
        arrayList.add("Elijah");
        arrayList.add("Cuthbert");
        arrayList.add("Akleo");
        arrayList.add("Ingrid");
        arrayList.add("Faith");
        arrayList.add("Lucky");
        arrayList.add("Ritah");
        arrayList.add("Joel");
        arrayList.add("Tyson");
        arrayList.add("Benneth");
        arrayList.add("Generous");
        arrayList.add("Collins");
        arrayList.add("Elijah");
        arrayList.add("Cuthbert");
        arrayList.add("Akleo");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
