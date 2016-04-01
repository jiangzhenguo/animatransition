package com.jhon.animatransition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by jzg on 2016/1/11.
 *  启动类
 */
public class TransitionActivityLunch {
    private Activity context;
    private View mFromView;
    private  TransitionActivityLunch(Activity context){
        this.context = context;
    }

    public static TransitionActivityLunch with(Activity context){
        return new TransitionActivityLunch(context);
    }

    public TransitionActivityLunch into(View view){
        this.mFromView = view;
        return  this;
    }

    public Bundle createBundle() {
        int[] position = new int[2];
        int width = 0;
        int height = 0;
        if(mFromView != null) {
            mFromView.getLocationOnScreen(position);

             width = mFromView.getWidth();
             height = mFromView.getHeight();
        }
        TransitionData data = new TransitionData(context,position[0],position[1],width,height);
        return data.getBundle();
    }

    public void launch(Intent intent) {
        intent.putExtras(createBundle());
        context.startActivity(intent);
        context.overridePendingTransition(0, 0);
    }

    public void forresultlaunch(Intent intent,int request) {
        intent.putExtras(createBundle());
        context.startActivityForResult(intent,request);
        context.overridePendingTransition(0, 0);
    }
}
