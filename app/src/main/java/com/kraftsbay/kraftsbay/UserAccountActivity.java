package com.kraftsbay.kraftsbay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class UserAccountActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView tvEmaill;
    private Button btSignOUT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        firebaseAuth=FirebaseAuth.getInstance();
        btSignOUT=(Button)findViewById(R.id.buttonSignout);
        tvEmaill=(TextView)findViewById(R.id.textViewEmailForAccount);
        String s=firebaseAuth.getCurrentUser().getEmail().toString();
        tvEmaill.setText(s);
        btSignOUT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    finish();
                firebaseAuth.getInstance().signOut();
                //Logining out FACEBOOK SDK
                FacebookSdk.sdkInitialize(getApplicationContext());
                LoginManager.getInstance().logOut();

                    Intent intent=new Intent(UserAccountActivity.this,MainActivity.class);
                    startActivity(intent);


            }
        });

    }
}
