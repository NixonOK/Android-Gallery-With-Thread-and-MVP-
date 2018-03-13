package com.w3.learn.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.w3.learn.R;
import com.w3.learn.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;

import com.w3.learn.image.ShowImageFullScreen;

public class MainActivity extends BaseActivity<MainMvpView, MainPresenter> implements MainMvpView, View.OnClickListener, AdapterView.OnItemClickListener {
    GridView galleryGridView;
    ArrayList<File> storageImageFileList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void startUI() {
        storageImageFileList = presenter.imageReader(Environment.getExternalStorageDirectory() );
        galleryGridView = findViewById(R.id.imageGridView);
        galleryGridView.setAdapter(new GridAdapter());
        galleryGridView.setOnItemClickListener(this);
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
        startActivity(new Intent(getApplicationContext(), ShowImageFullScreen.class).putExtra("img", storageImageFileList.get(position)));
    }

    @Override
    public void showMessage(String msg) {

    }

    class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return storageImageFileList.size();
        }

        @Override
        public Object getItem(int position) {
            return storageImageFileList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.single_grid_item, parent, false);
            ImageView iv = convertView.findViewById(R.id.singleImageGridItem);

            iv.setImageURI(Uri.parse(getItem(position).toString() ));


            return convertView;
        }
    }
}
