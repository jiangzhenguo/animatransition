package com.jhon.animatransition;

import android.app.Activity;
import android.content.Context;

/**
 * Created by jhon on 2016/3/27.
 */
public class ScreenUtils {
    static  public int getScreenWidth(Activity context){
         return  context.getWindowManager().getDefaultDisplay().getWidth();
    }

    static public int getScreenHeight(Activity context){
        return context.getWindowManager().getDefaultDisplay().getHeight();
    }
}
