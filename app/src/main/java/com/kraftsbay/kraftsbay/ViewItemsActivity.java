package com.kraftsbay.kraftsbay;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.Map;
import java.util.jar.Attributes;

public class ViewItemsActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Firebase mRef;
    private TextView namee, actual_pricee, offerss;
    ImageView imagess;
    Button buy_now,add_to_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);
        namee = (TextView) findViewById(R.id.tv_name_descriptionn);
        actual_pricee = (TextView) findViewById(R.id.tv_actualpricee);
        offerss = (TextView) findViewById(R.id.tv_offerss);
        imagess = (ImageView) findViewById(R.id.iv_item_image);
        buy_now=(Button)findViewById(R.id.bnBuy_Now);
        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewItemsActivity.this,BuyActivity.class);
                startActivity(intent);
            }
        });
        add_to_card=(Button)findViewById(R.id.bnAdd_to_Card);

        firebaseAuth= FirebaseAuth.getInstance();

        Intent intent = getIntent();
        final String Name = intent.getStringExtra("NAME");
        final String actual_price = intent.getStringExtra("ACTUAL_PRICE");
        final String image = intent.getStringExtra("IMAGE");
        final String offers = intent.getStringExtra("OFFERS");

        namee.setText(Name);
        actual_pricee.setText(actual_price);
        Picasso.with(getApplicationContext()).load(image).into(imagess);
        offerss.setText(offers);

        add_to_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(null != firebaseAuth.getCurrentUser()) {
                    String emails = firebaseAuth.getCurrentUser().getEmail().toString();
                    emails = emails.split("@")[0];

                    if (FirebaseUtilData.addToCard(emails,Name, actual_price, image, offers)) {

                        Toast.makeText(ViewItemsActivity.this, "Item is add to the Card!", Toast.LENGTH_SHORT).show();
                        //Intent intent=new Intent(QueryActivity.this,MainActivity.class);
                        //startActivity(intent);

                    } else {
                        Toast.makeText(ViewItemsActivity.this, "Sorry!Connection failed! Please try again.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }
}
