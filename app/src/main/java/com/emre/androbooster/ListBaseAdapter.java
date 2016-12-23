package com.emre.androbooster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Emre2 on 28.7.2016.
 */
public class ListBaseAdapter extends BaseAdapter {
    String[] data;
    GetSlowingApplications getSlowingApplications;
    Context context;
    LayoutInflater layoutInflater;


    public ListBaseAdapter(String[] data, Context context) {
        super();
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        getSlowingApplications = new GetSlowingApplications(context);
    }

    @Override
    public int getCount() {

        return data.length;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView= layoutInflater.inflate(R.layout.list_item, null);
        ImageView app_icon = (ImageView) convertView.findViewById(R.id.app_icon_iv);
        TextView txt = (TextView) convertView.findViewById(R.id.app_name_tv);
        txt.setText(data[position]);
        app_icon.setImageDrawable(GetSlowingApplications.icons.get(position));

        return convertView;
    }


}
