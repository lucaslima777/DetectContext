package detect.context.lln;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import detect.context.lln.Adapter.MainAdapter;
import detect.context.lln.Model.MainModel;


public class MainActivity extends AppCompatActivity {

    private String TAG = "packageInfo";
    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;
    private List<MainModel> modelList;
    private Drawable icon;
    private String name;
    private ProgressDialog progressDialog;
    private boolean sort = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modelList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        new LongOperation().execute();
    }


    private class LongOperation extends AsyncTask<Boolean, String, Boolean> {


        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMax(100);
            progressDialog.setProgressDrawable(getResources().getDrawable(R.drawable.ic_progress));
            progressDialog.setTitle("Listando Apps");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIcon(getResources().getDrawable(R.drawable.load));
            progressDialog.show();
            progressDialog.setCancelable(false);

        }

        @Override
        protected Boolean doInBackground(Boolean... booleans) {
            final PackageManager pm = getPackageManager();
            List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
            int progress = 0;

            for (ApplicationInfo packageInfo : packages) {
                Log.d(TAG, "Installed package :" + packageInfo.packageName);

                name = (String) packageInfo.loadLabel(getPackageManager());
                progressDialog.setProgress(progress);
                progressDialog.setSecondaryProgress(progress + progress);

                if (progress == 0) progressDialog.setMax(packages.size());

                icon = packageInfo.loadIcon(getPackageManager());
                modelList.add(new MainModel(packageInfo.packageName, name, icon));
                progress++;

            }
            mAdapter = new MainAdapter(modelList);
            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            progressDialog.dismiss();
            mRecyclerView.setAdapter(mAdapter);
        }

        @Override
        protected void onProgressUpdate(String... progress) {


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item:

                if (sort) {
                    Collections.sort(modelList, new Comparator<MainModel>() {
                        @Override
                        public int compare(MainModel mainModel, MainModel t1) {
                            return mainModel.getNameApp().compareTo(t1.getNameApp());
                        }
                    });

                    item.setIcon(getDrawable(R.drawable.disort));
                    sort = false;
                } else {
                    Collections.sort(modelList, new Comparator<MainModel>() {
                        @Override
                        public int compare(MainModel mainModel, MainModel t1) {
                            return t1.getNameApp().compareTo(mainModel.getNameApp());
                        }
                    });

                    item.setIcon(getDrawable(R.drawable.sort_alphabet));
                    sort = true;
                }

                mAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
