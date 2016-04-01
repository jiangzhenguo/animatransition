package com.example.imageanima;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jhon.animatransition.AnimaTransition;
import com.jhon.animatransition.TransitionActivityLunch;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class VIewPagerActivity extends AppCompatActivity {

    ViewPager mPager;
    ArrayList<String> lists;
    LayoutInflater mInflater;
    int selectitem;
    AnimaTransition MyTransition;
    boolean isFirst = true;
    RelativeLayout layout;
    Animator.AnimatorListener mListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
             layout.setBackgroundColor(0xFFFFFFFF);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mPager = (ViewPager)findViewById(R.id.viewpager);
        lists = getIntent().getStringArrayListExtra("list");
        mInflater = LayoutInflater.from(this);

        selectitem = getIntent().getIntExtra("select",0);
        layout = (RelativeLayout)findViewById(R.id.bg);
        mPager.setAdapter(new MyAdapter());
        mPager.setCurrentItem(selectitem);
        MyTransition = AnimaTransition.with(getIntent()).duration(500);
    }


    public static void  launch(Context context,ArrayList<String> lists,int selectitem,View view){
        Intent in = new Intent(context,VIewPagerActivity.class);
        in.putExtra("list",lists);
        in.putExtra("select",selectitem);
        TransitionActivityLunch.with((Activity)context).into(view).launch(in);
    }

    class MyAdapter extends PagerAdapter {
        SparseArray<WeakReference<View>> list = new SparseArray<>();

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(list.get(position).get());
            WeakReference<View> week = list.get(position);
            if(week != null) {
                list.get(position).clear();
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final View view = mInflater.inflate(R.layout.item_viewpager,null);
            final ImageView imageView = (ImageView)view.findViewById(R.id.image_pic);
            imageView.setVisibility(View.GONE);
            if(isFirst) {
                Glide.with(VIewPagerActivity.this).load(lists.get(position)).asBitmap().fitCenter().listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setImageBitmap(resource);
                        MyTransition.setHeight(resource.getHeight()).setWidth(resource.getWidth()).to(imageView).listener(mListener).start(null);
                        isFirst = false;
                        return true;

                    }
                }).into(1920, 1080);
            } else {
                imageView.setVisibility(View.VISIBLE);
                Glide.with(VIewPagerActivity.this).load(lists.get(position)).asBitmap().fitCenter().into(imageView);
            }

            container.addView(view,0);
            list.put(position,new WeakReference<View>(imageView));
            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {


            return view == object;
        }
    }
}
