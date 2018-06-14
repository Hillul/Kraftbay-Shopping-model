package com.kraftsbay.kraftsbay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QueryActivity extends AppCompatActivity {

    EditText name,email,mobile,query;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        name=(EditText)findViewById(R.id.query_name);
        email=(EditText)findViewById(R.id.query_email);
        mobile=(EditText)findViewById(R.id.query_mobile);
        query=(EditText)findViewById(R.id.query_query);
        submit=(Button)findViewById(R.id.query_submit);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senderName,senderEmail,senderMobile,senderQuery;
                senderEmail=email.getText().toString().trim();
                senderQuery=query.getText().toString().trim();
                senderMobile=mobile.getText().toString().trim();
                senderName=name.getText().toString().trim();
                if(correctName(senderName)&&correctEmail(senderEmail)&&correctPhone(senderMobile)&&notNull(senderQuery)) {


                    //condition to check if the inputs are valid
                    if (FirebaseUtilData.addToQueries(senderName, senderEmail, senderMobile, senderQuery)){

                        Toast.makeText(QueryActivity.this, "We have recieved your query, you will get our reply soon!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(QueryActivity.this,MainActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(QueryActivity.this, "Sorry!Connection failed! Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(QueryActivity.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    mobile.setText("");
                    query.setText("");

                }

            }
        });

    }






    private boolean correctPhone(String number) {
        if(number != null && number.matches("[0-9]{10}")){
            return  true;

        }
        else{
            Toast.makeText(this, "Enter a valid mobile number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean correctEmail(String senderEmail) {


        if(senderEmail!=null&& android.util.Patterns.EMAIL_ADDRESS.matcher(senderEmail).matches()){
            return true;
        }
        else{
            Toast.makeText(this, "Enter a valid Email", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean correctName(String senderName) {

        for(int i=0;i<senderName.length();i++){
            Character c=senderName.charAt(i);
            if((c>=65&&c<=92)||(c>=97&&c<=122)||c==32){            }
            else{
                Toast.makeText(this, "Please Enter a valid name", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    private boolean notNull(String  feedback) {
        if(feedback.isEmpty()){
            Toast.makeText(this, "Feedback field should not be Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(QueryActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }



}
