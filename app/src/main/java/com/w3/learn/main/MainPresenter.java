package com.w3.learn.main;

/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 2/1/2018 at 1:48 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Azizul Islam on 2/1/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/


import com.w3.learn.base.BasePresenter;
import com.w3.learn.base.MvpView;

import java.io.File;
import java.util.ArrayList;

public class MainPresenter extends BasePresenter<MainMvpView> {

    public void showMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getMvpView().showMessage("From Presenter");
            }
        }).start();
    }

    // reads all the existing files in root
    public ArrayList<File> imageReader(File root){

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
        return  a;
    }
}
