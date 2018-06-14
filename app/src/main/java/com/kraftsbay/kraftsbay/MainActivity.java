package com.kraftsbay.kraftsbay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth;
    private TextView tvEmaill;
    private RecyclerView categorieslist;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ///Intent intent = getIntent();
        // String ExtraData = intent.getStringExtra("CATEGORIES");


        mDatabase= FirebaseDatabase.getInstance().getReference().child("Categories");//.child(ExtraData);

        firebaseAuth=FirebaseAuth.getInstance();

        categorieslist=(RecyclerView)findViewById(R.id.categories_list);
        //categorieslist.setHasFixedSize(true);
        categorieslist.setLayoutManager(new LinearLayoutManager(this));



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Checking if user is logged In
        if(null != firebaseAuth.getCurrentUser()){
            String s=firebaseAuth.getCurrentUser().getEmail().toString();
            View header=navigationView.getHeaderView(0);
            tvEmaill=(TextView)header.findViewById(R.id.textViewLoginEmaill);
            s=s.split("@")[0];
            tvEmaill.setText("Hello! "+s);
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Categories,CategoriesViewHolder>firebaseRecyclerAdapter=new
                FirebaseRecyclerAdapter<Categories, CategoriesViewHolder>(
                Categories.class,
                R.layout.categories_row,
                CategoriesViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(final CategoriesViewHolder viewHolder, Categories model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setActual_price(model.getActual_price());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.setOffers(model.getOffers());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent3=new Intent(MainActivity.this,BuyActivity.class);
                       //String Data= Integer.toString(viewHolder.getAdapterPosition());
                       ///Toast.makeText(MainActivity.this,Data,Toast.LENGTH_SHORT).show();
                        startActivity(intent3);
                    }
                });
            }
        };
       categorieslist.setAdapter(firebaseRecyclerAdapter);

    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public CategoriesViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            //itemView.setOnClickListener(this);
        }


        public void setName(String name){
            TextView post_name=(TextView)mView.findViewById(R.id.categories_name);
            post_name.setText(name);
        }

        public void setActual_price(String actual_price){
            TextView post_actualprice=(TextView)mView.findViewById(R.id.categories_actualprice);
            post_actualprice.setText(actual_price);
        }

        public void setImage(Context ctx,String image){
            ImageView post_image=(ImageView)mView.findViewById(R.id.categories_image);
            Picasso.with(ctx).load(image).into(post_image);
           // Picasso.with(ctx).load(image).placeholder(getResources().getDrawable(R.drawable.ic_launcher)).into(post_image);
        }
        public void setOffers(String offers){
            TextView post_offers=(TextView)mView.findViewById(R.id.categories_offers);
            post_offers.setText(offers+"\noff");
        }



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





  @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        firebaseAuth=FirebaseAuth.getInstance();

        if (id == R.id.nav_home) {
            Intent intent=new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_categoriesM){
            Intent intent=new Intent(MainActivity.this,CategoriesActivity.class);
            startActivity(intent);
        }else if (id ==R.id.nav_account){
            if(null != firebaseAuth.getCurrentUser()){
                Intent intent1=new Intent(MainActivity.this,UserAccountActivity.class);
                startActivity(intent1);

            }else{
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }

        } else if (id == R.id.nav_contact) {
            Intent intent=new Intent(MainActivity.this,ContactActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_query) {
            Intent intent=new Intent(MainActivity.this,QueryActivity.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void LogIn(View view)
    {
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
