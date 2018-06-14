package com.kraftsbay.kraftsbay;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Hillul on 9/6/2017.
 */

public class CustomAdapter extends BaseAdapter{
    Context c;
    ArrayList<Categories> categories;

    public CustomAdapter(Context c, ArrayList<Categories> categories) {
        this.c = c;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.categories_row,parent,false);
            //convertView= LayoutInflater.from(c).inflate((R.layout.categories_row,parent,false);
        }
        TextView nameTxt=(TextView)convertView.findViewById(R.id.categories_name);
        TextView apriceTxt=(TextView)convertView.findViewById(R.id.categories_actualprice);
        TextView offersTxt=(TextView)convertView.findViewById(R.id.categories_offers);

        final Categories s=(Categories)this.getItem(position);

        nameTxt.setText(s.getName());
        apriceTxt.setText(s.getActual_price());
        offersTxt.setText(s.getOffers());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,s.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
