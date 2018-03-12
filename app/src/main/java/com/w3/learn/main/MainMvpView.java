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


import com.w3.learn.base.MvpView;

public interface MainMvpView extends MvpView {
    void showMessage(String msg);
}
