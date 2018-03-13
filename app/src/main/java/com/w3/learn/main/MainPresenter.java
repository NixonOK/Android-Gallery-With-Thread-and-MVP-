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

    // reads all the existing files in root
    public ArrayList<File> imageReader(final File root){

        final ArrayList<File> imageFileList = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                File[] imageFiles= root.listFiles();
                if(imageFiles != null){
                    for(int i=0; i<imageFiles.length; i++){
                        if(imageFiles[i].isDirectory()){
                            imageFileList.addAll(imageReader(imageFiles[i]));
                        }
                        else {
                            if( imageFiles[i].getName().endsWith(".jpg") || imageFiles[i].getName().endsWith(".png") ){
                                imageFileList.add( imageFiles[i] );
                            }
                        }
                    }
                }

            }
        }).start();


        return  imageFileList;
    }
}
