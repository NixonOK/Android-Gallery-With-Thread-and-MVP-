package com.w3.learn.image;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.w3.learn.R;
import com.w3.learn.base.BaseActivity;

/**
 * Created by USER24 on 3/12/2018.
 */

public class ShowImageFullScreen extends BaseActivity<ShowImageMvpView, ShowImagePresenter> implements ShowImageMvpView, View.OnClickListener, AdapterView.OnItemClickListener  {

    ImageView fullScreenImageView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_image_full_screen;
    }

    @Override
    protected void startUI() {
        Intent intent = getIntent();
        String getImageName = intent.getStringExtra("img");
        fullScreenImageView = findViewById(R.id.fullScreenImageShow);
        fullScreenImageView.setImageURI(Uri.parse( getImageName ));
    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected ShowImagePresenter initPresenter() {
        return new ShowImagePresenter();
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}