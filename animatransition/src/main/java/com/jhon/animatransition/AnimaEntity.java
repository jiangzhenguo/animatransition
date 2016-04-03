package com.jhon.animatransition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by jzg on 2016/1/11.
 */
public class AnimaEntity {
    public static Boolean First = true;
    public static ViewData startAnimation(final Context context, final View toView, Bundle transitionBundle, Bundle savedInstanceState, final int duration, final TimeInterpolator interpolator, final int width, final int height , final Animator.AnimatorListener listener) {

        final TransitionData transitionData = new TransitionData(context, transitionBundle);
        final ViewData viewData = new ViewData();
        viewData.duration = duration;
        viewData.toView = toView;
        First = true;
        if (savedInstanceState == null) {
            ViewTreeObserver TreeObserver =  viewData.toView.getViewTreeObserver();

            TreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    viewData.toView.getViewTreeObserver().removeOnPreDrawListener(this);
                    if(First) {
                        int[] position = new int[2];
                        int dwidth = width ;
                        int dheight = height;

                        int screenwidth = ScreenUtils.getScreenWidth((Activity) context);
                        int screenheigh = ScreenUtils.getScreenHeight((Activity) context);

                        float dScalewidth = 1;
                        float dScaleHeith = 1;

                        toView.getLocationOnScreen(position);

                        if(toView instanceof ImageView){

                            if(((float)dwidth/(float)dheight) > (float)screenwidth / (float) screenheigh ){
                                dheight = (int)((float)screenwidth * dheight/dwidth);
                                dScaleHeith =  ((float)(screenheigh - dheight)) / dheight;

                            } else {
                                dwidth = (int)((float)screenheigh * dwidth / dheight);
                                dScalewidth = ((float)(screenwidth -  dwidth)) / (dwidth);
                            }

                        }


                        if(dScalewidth == 1) {
                            viewData.widthScale = (float) transitionData.thumbnailWidth / toView.getWidth();
                            viewData.heightScale =  (float)transitionData.thumbnailHeight / dheight;
                            viewData.x = transitionData.thumbnailLeft - position[0];
                            viewData.y = (int)(transitionData.thumbnailTop - position[1] - transitionData.thumbnailHeight * (dScaleHeith)/2);
                        } else {
                            viewData.heightScale = (float) transitionData.thumbnailHeight / toView.getHeight();
                            viewData.widthScale = (float) transitionData.thumbnailWidth / dwidth;
                            viewData.x = (int)(transitionData.thumbnailLeft - position[0] - transitionData.thumbnailWidth * (dScalewidth)/2);
                            viewData.y = transitionData.thumbnailTop - position[1];
                        }

                        runAnimation(viewData, interpolator, listener);
                        First = false;
                        return false;
                    } else {
                        return  true;
                    }
                }
            });
        }
        return viewData;
    }

    public static void runAnimation(ViewData viewData, TimeInterpolator interpolator,Animator.AnimatorListener listener) {
        if (viewData != null) {
            View view = viewData.toView;
            view.setPivotY(0);
            view.setPivotX(0);
            view.setScaleX(viewData.widthScale);
            view.setScaleY(viewData.heightScale);
            view.setTranslationX(viewData.x);
            view.setTranslationY(viewData.y);



            view.animate().setDuration(viewData.duration).
                    scaleX(1).scaleY(1).
                    translationX(0).translationY(0).
                    setInterpolator(interpolator).setListener(listener);
        }
    }

    public static void startfinishAnimation(final Context context,ViewData viewData,Animator.AnimatorListener listener){
          View view = viewData.toView;
          if(view != null){
              view.animate().setDuration(viewData.duration).scaleX(viewData.widthScale).scaleY(viewData.heightScale)
                      .translationX(viewData.x).translationY(viewData.y).setListener(listener).start();
          }
    }
}
