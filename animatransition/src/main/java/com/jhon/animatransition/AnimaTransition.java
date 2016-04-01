package com.jhon.animatransition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by jzg on 2016/1/11.
 * 进行带动画的转换
 */
public class AnimaTransition {
    private int duration = 1000;
    private View toView;
    private Intent fromIntent;

    private int mWidth;

    private int mHeight;

    private Animator.AnimatorListener mListener;
    private TimeInterpolator interpolator;

    private AnimaTransition(Intent in){
        this.fromIntent = in;
    }
    public static AnimaTransition with(Intent in){
        return new AnimaTransition(in);
    }

    public AnimaTransition to(View toView) {
        this.toView = toView;
        return this;
    }
    public AnimaTransition duration(int duration) {
        this.duration = duration;
        return this;
    }

    public AnimaTransition interpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public AnimaTransition listener(Animator.AnimatorListener listener){
        this.mListener = listener;
        return  this;
    }



    public AnimaTransition setWidth(int width){
        mWidth = width;
        return this;
    }

    public AnimaTransition setHeight(int height){
        mHeight = height;
        return  this;
    }

    public void start(Bundle savedInstanceState){
        if (interpolator == null) {
            interpolator = new DecelerateInterpolator();
        }
        final Context context = toView.getContext();
        final Bundle bundle = fromIntent.getExtras();
        AnimaEntity.startAnimation(context,toView,bundle,savedInstanceState,duration,interpolator,mWidth,mHeight,mListener);
    }


}
