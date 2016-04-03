package com.jhon.animatransition;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.system.ErrnoException;


/**
 * Created by jzg on 2016/4/2.
 */
public class TransitionActivityFinish {
    ViewData mViewData;
    Context context;
    finishListener mFinishListener;
    Animator.AnimatorListener mListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
              if(animation != null){
                  mFinishListener.onAnimaStart();
              }
        }

        @Override
        public void onAnimationEnd(Animator animation) {
              if(mFinishListener == null){
                  throw new NullPointerException();
              } else {
                  mFinishListener.onfinish();
              }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };


    public TransitionActivityFinish setViewData(ViewData viewData){
        this.mViewData = viewData;
        return  this;
    }


    public TransitionActivityFinish with(Context context){
        this.context =context;
        return this;
    }

    public TransitionActivityFinish listener(finishListener listener){
        mFinishListener = listener;
        return this;
    }


    public void start(){
        AnimaEntity.startfinishAnimation(context,mViewData,mListener);
    }


    public interface finishListener {
        void onAnimaStart();
        void onfinish();
    }

}
