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

public class MainPresenter extends BasePresenter<MainMvpView> {

    public void showMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getMvpView().showMessage("From Presenter");
            }
        }).start();
    }
}
