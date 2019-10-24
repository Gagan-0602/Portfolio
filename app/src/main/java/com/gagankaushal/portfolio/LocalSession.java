package com.gagankaushal.portfolio;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalSession {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static int PRIVATE_MODE = 0;
     Context context;
    public LocalSession(Context context){
        this.context=context;
        this.sharedPreferences= context.getSharedPreferences("",PRIVATE_MODE);
        this.editor=sharedPreferences.edit();
    }

    public void createSession(String email,String name){
        editor.putBoolean("isLogin",true);
        editor.putString("email",email);
        editor.putString("name",name);
        editor.commit();
    }

    public void logOutUser(){
        editor.clear();
        editor.commit();
    }

    public Boolean isLogin(){
        Boolean isLogin=sharedPreferences.getBoolean("isLogin",false);
        return isLogin;
    }


}
