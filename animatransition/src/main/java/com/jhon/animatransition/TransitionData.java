package com.jhon.animatransition;

import android.content.Context;
import android.os.Bundle;


/**
 * Created by jzg on 2016/1/11.
 * 帮助用户进行转换的数据类型
 */
public class TransitionData {
    public static final String EXTRA_IMAGE_LEFT = ".left";
    public static final String EXTRA_IMAGE_TOP = ".top";
    public static final String EXTRA_IMAGE_WIDTH = ".width";
    public static final String EXTRA_IMAGE_HEIGHT = ".height";
    public static final String EXTRA_IMAGE_PATH = ".imageFilePath";

    public final int thumbnailTop;
    public final int thumbnailLeft;
    public final int thumbnailWidth;
    public final int thumbnailHeight;

    private String appId;

    public TransitionData(Context context, int thumbnailLeft, int thumbnailTop, int thumbnailWidth, int thumbnailHeight) {
        setAppId(context);
        this.thumbnailLeft = thumbnailLeft;
        this.thumbnailTop = thumbnailTop;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
    }

    public TransitionData(Context context, Bundle bundle) {
        setAppId(context);
        thumbnailTop = bundle.getInt(appId + EXTRA_IMAGE_TOP);
        thumbnailLeft = bundle.getInt(appId + EXTRA_IMAGE_LEFT);
        thumbnailWidth = bundle.getInt(appId + EXTRA_IMAGE_WIDTH);
        thumbnailHeight = bundle.getInt(appId + EXTRA_IMAGE_HEIGHT);
    }

    private void setAppId(Context context) {
        appId = (String) BuildConfigUtils.getBuildConfigValue(context, "APPLICATION_ID");
    }


    public Bundle getBundle() {
        final Bundle bundle = new Bundle();
        bundle.putInt(appId + EXTRA_IMAGE_LEFT, thumbnailLeft);
        bundle.putInt(appId + EXTRA_IMAGE_TOP, thumbnailTop);
        bundle.putInt(appId + EXTRA_IMAGE_WIDTH, thumbnailWidth);
        bundle.putInt(appId + EXTRA_IMAGE_HEIGHT, thumbnailHeight);
        return bundle;

    }
}
