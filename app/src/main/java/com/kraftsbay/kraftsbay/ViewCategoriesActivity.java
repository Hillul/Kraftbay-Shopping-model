package com.kraftsbay.kraftsbay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ViewCategoriesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth;
    private TextView tvEmaill;
    private RecyclerView categorieslist;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_categories);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Intent intent = getIntent();
        String ExtraData = intent.getStringExtra("CATEGORIES");

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Cloths").child(ExtraData);

        firebaseAuth=FirebaseAuth.getInstance();

        categorieslist=(RecyclerView)findViewById(R.id.categories_listt);
        categorieslist.setHasFixedSize(true);
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
    protected void onStart() {
        super.onStart();

       FirebaseRecyclerAdapter<Categories,ViewCategoriesActivity.CategoriesViewHolder> firebaseRecyclerAdapter=
               new FirebaseRecyclerAdapter<Categories, ViewCategoriesActivity.CategoriesViewHolder>(
                Categories.class,
                R.layout.categories_row,
                ViewCategoriesActivity.CategoriesViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(final ViewCategoriesActivity.CategoriesViewHolder viewHolder, Categories model, final int position) {
               final String name= viewHolder.setName(model.getName());
                final String actual_price=viewHolder.setActual_price(model.getActual_price());
                final String image=viewHolder.setImage(getApplicationContext(),model.getImage());
                final String offers=viewHolder.setOffers(model.getOffers());


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent3=new Intent(ViewCategoriesActivity.this,ViewItemsActivity.class);
                      // int pos=viewHolder.getAdapterPosition();
                        //String Data= Integer.toString(pos);
                      //int Dd=getItemViewType(pos);
                        // Toast.makeText(ViewCategoriesActivity.this,image,Toast.LENGTH_SHORT).show();
                        intent3.putExtra("NAME",name);
                        intent3.putExtra("ACTUAL_PRICE",actual_price);
                        intent3.putExtra("IMAGE",image);
                        intent3.putExtra("OFFERS",offers);
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
        }

        public String setName(String name){
            TextView post_name=(TextView)mView.findViewById(R.id.categories_name);
            post_name.setText(name);
            return name;
        }

        public String setActual_price(String actual_price){
            TextView post_actualprice=(TextView)mView.findViewById(R.id.categories_actualprice);
            post_actualprice.setText(actual_price);
            return actual_price;
        }

        public String setImage(Context ctx, String image){
            ImageView post_image=(ImageView)mView.findViewById(R.id.categories_image);
            Picasso.with(ctx).load(image).into(post_image);
            return image;
            // Picasso.with(ctx).load(image).placeholder(getResources().getDrawable(R.drawable.ic_launcher)).into(post_image);
        }
        public String setOffers(String offers){
            TextView post_offers=(TextView)mView.findViewById(R.id.categories_offers);
            post_offers.setText(offers+"\noff");
            return offers;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_categories, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        firebaseAuth=FirebaseAuth.getInstance();

        if (id == R.id.nav_home) {
            Intent intent=new Intent(ViewCategoriesActivity.this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_categoriesM){
            Intent intent=new Intent(ViewCategoriesActivity.this,CategoriesActivity.class);
            startActivity(intent);
        }else if (id ==R.id.nav_account){
            if(null != firebaseAuth.getCurrentUser()){
                Intent intent1=new Intent(ViewCategoriesActivity.this,UserAccountActivity.class);
                startActivity(intent1);

            }else{
                Intent intent=new Intent(ViewCategoriesActivity.this,LoginActivity.class);
                startActivity(intent);

            }

        } else if (id == R.id.nav_contact) {
            Intent intent=new Intent(ViewCategoriesActivity.this,ContactActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_query) {
            Intent intent=new Intent(ViewCategoriesActivity.this,QueryActivity.class);
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
