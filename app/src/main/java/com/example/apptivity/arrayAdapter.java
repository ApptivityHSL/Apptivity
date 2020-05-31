package com.example.apptivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<cards> {
    Context context;

    public arrayAdapter(Context context, int resourceId, List<cards> item){
        super(context, resourceId, item);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        cards card_item = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.nameC);
        ImageView image = (ImageView) convertView.findViewById(R.id.image);

        name.setText(card_item.getName());
        Log.d("Glidinger", card_item.getImURL().replace("\"", "").replace("[", "").replace("]", ""));
        Glide.with(getContext())
                .load(card_item.getImURL().replace("\"", "").replace("[", "").replace("]", ""))
                .into(image);
        //image.setImageResource(R.mipmap.ic_launcher);

        return convertView;
    }
}
