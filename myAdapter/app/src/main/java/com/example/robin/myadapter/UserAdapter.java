package com.example.robin.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, ArrayList<User> users){
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        User user = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item, parent, false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
        TextView firstname  = (TextView) convertView.findViewById(R.id.label);
        TextView lastname = (TextView) convertView.findViewById(R.id.label2);

        firstname.setText(user.firstname);
        lastname.setText(user.lastname);
        image.setImageResource(android.R.drawable.btn_star);
        return convertView;
    }
}
