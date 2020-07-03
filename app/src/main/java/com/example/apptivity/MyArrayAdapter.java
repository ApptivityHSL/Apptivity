package com.example.apptivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<cards> {

    public MyArrayAdapter(Context context, int resourceId, List<cards> item) {
        super(context, resourceId, item);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        cards cardItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.nameC);
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        assert cardItem != null;
        name.setText(cardItem.getName());
        Glide.with(getContext())
                .load(cardItem.getImURL().replace("\"", "").replace("[", "").replace("]", ""))
                .into(image);
        return convertView;
    }
}
