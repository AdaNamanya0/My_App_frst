package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;


public class RecyclerView<AppCompatActivity> extends AppCompatActivity implements MyAdapterClass.ItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView;
        MyAdapterClass adapter;





            recyclerView=findViewById(R.id.productitems);

            ArrayList<String> elements = new ArrayList<>();
            elements.add("Mbarara");
            elements.add("Masaka");
            elements.add("Mukono");
            elements.add("Lira");
            elements.add("Mpigi");
            elements.add("Kampala");

            //set up the RecyclerView
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter=new MyAdapterClass(this,elements);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);

        }

        @Override
        public void onItemClick(View view, int position) {

        }
    }


