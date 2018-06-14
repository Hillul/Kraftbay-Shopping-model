package com.kraftsbay.kraftsbay;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Hillul on 9/3/2017.
 */

public abstract class FirebaseUtilData {

    private static DatabaseReference firebaseRef= FirebaseDatabase.getInstance().getReferenceFromUrl("https://kraftsbay-8d36f.firebaseio.com/");

    public static boolean addToQueries(String name,String email,String mobile,String query) {
        boolean status = false;
        try {
            DatabaseReference tempRef = firebaseRef.child("Queries").child(name);
            tempRef.child("Query at" + SystemClock.currentThreadTimeMillis()).setValue(query);
            tempRef.child("Email").setValue(email);
            tempRef.child("Mobile Number").setValue(mobile);

            status = true;

        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }

        return status;
    }
    public static boolean addToCard(String emails,String Name,String actual_price,String image,String offers) {
        boolean status = false;
        try {
            DatabaseReference tempRef = firebaseRef.child("Users").child(emails).child(Name);
            tempRef.child("At").setValue(SystemClock.currentThreadTimeMillis());
            tempRef.child("Name").setValue(Name);
            tempRef.child("actual_price").setValue(actual_price);
            tempRef.child("image").setValue(image);
            tempRef.child("offers").setValue(offers);

            status = true;

        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }

        return status;
    }
    public static void  addUser(String emailOfUser){
        DatabaseReference tempRef = firebaseRef.child("Users").child(emailOfUser);
        tempRef.child("Email").setValue(emailOfUser);

    }
}
/*public abstract class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;

    public CategoriesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        //itemView.setOnClickListener(this);
    }


    public void setName(String name) {
        TextView post_name = (TextView) mView.findViewById(R.id.categories_name);
        post_name.setText(name);
    }

    public void setActual_price(String actual_price) {
        TextView post_actualprice = (TextView) mView.findViewById(R.id.categories_actualprice);
        post_actualprice.setText(actual_price);
    }

    public void setImage(Context ctx, String image) {
        ImageView post_image = (ImageView) mView.findViewById(R.id.categories_image);
        Picasso.with(ctx).load(image).into(post_image);
        // Picasso.with(ctx).load(image).placeholder(getResources().getDrawable(R.drawable.ic_launcher)).into(post_image);
    }

    public void setOffers(String offers) {
        TextView post_offers = (TextView) mView.findViewById(R.id.categories_offers);
        post_offers.setText(offers + "\noff");
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context,MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
*/