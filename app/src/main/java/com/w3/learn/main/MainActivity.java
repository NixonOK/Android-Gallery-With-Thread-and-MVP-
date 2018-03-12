package com.w3.learn.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.w3.learn.R;
import com.w3.learn.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends BaseActivity<MainMvpView, MainPresenter> implements MainMvpView, View.OnClickListener {
    Handler foregroundHandler;
    Handler backgroundHandler;

    HandlerThread handlerThread;

    GridView imageGridView;
    ArrayList<File> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=imageReader( Environment.getExternalStorageDirectory() );
        imageGridView =findViewById(R.id.imageGridView);
        imageGridView.setAdapter( new GridAdapter());


        imageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity( new Intent(getApplicationContext(), ShowImageFullScreen.class).putExtra("img", list.get(i).toString() ) );
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }
    @Override
    protected void startUI() {

        foregroundHandler = new Handler(Looper.getMainLooper());
        handlerThread = new HandlerThread("Background", Thread.MAX_PRIORITY);
        handlerThread.start();

        backgroundHandler = new Handler(handlerThread.getLooper());
    }

    @Override
    protected void stopUI() {

    }

    @Override
    public void showMessage(final String msg) {
        foregroundHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, msg,Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onClick(View v) {
      /* if(v.getId() == R.id.presenter_button){
            presenter.showMessage();
        }else {
            foregroundThread();
            //backgroundThread();

        }
*/
    }

    /*****************Simple thread ****************/

    private void foregroundThread(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Button Click",Toast.LENGTH_LONG).show();
            }
        };

        foregroundHandler.post(runnable);
    }


    private void backgroundThread(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Button Click",Toast.LENGTH_LONG).show();
            }
        };

        backgroundHandler.post(runnable);
    }

    class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();//returns number of items in grid
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);//returns the i-th positioned data of list
        }

        //this function is to use database
        @Override
        public long getItemId(int i) {
            return 0;
        }

        //it's the main task. It sets every image
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate( R.layout.single_grid, viewGroup, false ); //the custom grid_view, which i wanna show
            //inflate is used to pick a XML file to java
            //the object convertView holds everything of that layout. We can process/access the input section of that layout

            ImageView iv= (ImageView) view.findViewById(R.id.imageView);

            iv.setImageURI( Uri.parse( getItem(i).toString()) );
            return view;
        }
    }
    // reads all the existing files in root
    ArrayList<File> imageReader(File root){

        ArrayList<File> a = new ArrayList<>();
        File[] files= root.listFiles();
        for(int i=0; i<files.length; i++){
            if(files[i].isDirectory()){
                a.addAll(imageReader(files[i]));
            }
            else {
                if( files[i].getName().endsWith(".jpg") ){
                    a.add( files[i] );
                }
            }
        }
        return  a;// returns a File-typed array List, which reads all the jpj files in sdCARD
    }


}
