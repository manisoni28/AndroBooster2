package com.emre.androbooster;

/**
 * Created by ogrenci on 23.12.2016.
 */
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.List;

public class AppBooster extends Fragment implements AdapterView.OnItemClickListener {

    private GridView gridview;
    private List<ApplicationInfo> applist = null;
    private PackageManager packageManager = null;
    private ApplicationAdapter listadaptor = null;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View abc =  inflater.inflate(R.layout.app_booster, container, false);
        context = getActivity();
        gridview = (GridView) abc.findViewById(R.id.list);
        packageManager = context.getPackageManager();
        new LoadApplications().execute();
        return abc;
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> implements AdapterView.OnItemClickListener {
        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

            listadaptor = new ApplicationAdapter(context, R.layout.list_item, applist);

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {

            gridview.setAdapter(listadaptor);
            gridview.setOnItemClickListener(this);
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // TODO Auto-generated method stub
            final ApplicationInfo app = applist.get(i);
            Intent abcd = new Intent(context,BoostingAppActivity.class);
            abcd.putExtra("app",app.packageName);
            startActivity(abcd);
            getActivity().finish();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return applist;
    }
}
