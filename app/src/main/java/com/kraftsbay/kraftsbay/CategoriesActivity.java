package com.kraftsbay.kraftsbay;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.client.authentication.Constants;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

import static android.R.*;
import static android.R.layout.*;

public class CategoriesActivity extends AppCompatActivity  {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        lv=(ListView)findViewById(R.id.listViewCategories);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://kraftsbay-8d36f.firebaseio.com/Categoriess");
       FirebaseListAdapter<String> firebaseListAdapter=new FirebaseListAdapter<String>(
               CategoriesActivity.this,
                String.class,
               android.R.layout.simple_list_item_1,
               databaseReference
       ) {
           @Override
           protected void populateView(View v, String model, int position) {
               TextView textView=(TextView)v.findViewById(android.R.id.text1);
               textView.setText(model);
           }
       };

        lv.setAdapter(firebaseListAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(CategoriesActivity.this,ViewCategoriesActivity.class);
                String data = lv.getItemAtPosition(position).toString();
                Toast.makeText(CategoriesActivity.this,data,Toast.LENGTH_SHORT).show();
               // Toast.makeText(getApplicationContext(), position+""+lv.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                intent.putExtra("CATEGORIES",data);
                startActivity(intent);
            }
        });
    }
}
