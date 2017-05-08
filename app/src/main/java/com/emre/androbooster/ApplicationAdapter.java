package com.emre.androbooster;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by emre on 24.04.2016.
 */
 
public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> {
    private List<ApplicationInfo> appsList = null;
    private Context context;
    private ThemeManager themeManager;
    private PackageManager packageManager;
    public ApplicationAdapter(Context context, int textViewResourceId,
                              List<ApplicationInfo> appsList) {
        super(context, textViewResourceId, appsList);
        this.context = context;
        this.appsList = appsList;
        themeManager = new ThemeManager(context);
        packageManager = context.getPackageManager();
    }
    @Override
    public int getCount() {
        return ((null != appsList) ? appsList.size() : 0);
    }

    @Override
    public ApplicationInfo getItem(int position) {
        return ((null != appsList) ? appsList.get(position) : null);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
        if (null == convertView) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.all_apps_c, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.app_name_tv2);
            holder.app_icon = (ImageView) convertView.findViewById(R.id.app_icon_iv2);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ApplicationInfo data = appsList.get(position);
        if (null != data) {
            if (themeManager.isDark()){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.text.setTextColor(context.getColor(R.color.white));
                }else {
                    holder.text.setTextColor(context.getResources().getColor(R.color.white));
                }
            }
            if (themeManager.isGamerTheme()){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.text.setTextColor(context.getColor(R.color.white));
                }else {
                    holder.text.setTextColor(context.getResources().getColor(R.color.white));
                }
            }
            holder.text.setText(data.loadLabel(packageManager));
            holder.app_icon.setImageDrawable(data.loadIcon(packageManager));
        }
        return convertView;
    }
    private static class ViewHolder {
        public TextView text;
        public ImageView app_icon;
    }
}
