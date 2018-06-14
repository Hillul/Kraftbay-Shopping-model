package com.kraftsbay.kraftsbay;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NoInternetActivity extends AppCompatActivity {
    private Button BnNoInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        BnNoInternet = (Button) findViewById(R.id.bnNoIntenet);


        final boolean status = isNetworkAvailable();
        //String ack = "NO Internet Connection";
       // Bundle b=getIntent().getExtras();
        ///final String previousActivity=b.getString("ComingActivity");


        BnNoInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status) {
                    finishActivity(RESULT_OK);
                   // Intent intent=new Intent(NoInternetActivity.this,previousActivity.getClass());
                   // startActivity(intent);

                } else {

                    //Toast.makeText(NoInternetActivity.this, ack, Toast.LENGTH_SHORT).show();
                }
            }


        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
