package com.w3.learn.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.w3.learn.R;
import com.w3.learn.base.BaseActivity;
import com.w3.learn.main.MainMvpView;
import com.w3.learn.main.MainPresenter;
import com.w3.learn.main.ShowImageFullScreen;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends BaseActivity<MainMvpView, MainPresenter> implements MainMvpView, View.OnClickListener, AdapterView.OnItemClickListener {
    GridView gv;
    ArrayList<File> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void startUI() {
        list = presenter.imageReader(Environment.getExternalStorageDirectory() );
        gv = findViewById(R.id.imageGridView);
        gv.setAdapter(new GridAdapter());
        gv.setOnItemClickListener(this);
    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getApplicationContext(), ShowImageFullScreen.class).putExtra("img", list.get(position)));
    }

    @Override
    public void showMessage(String msg) {

    }

    class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.single_grid, parent, false);
            ImageView iv = convertView.findViewById(R.id.imageView);

            iv.setImageURI(Uri.parse(getItem(position).toString() ));


            return convertView;
        }
    }
}
