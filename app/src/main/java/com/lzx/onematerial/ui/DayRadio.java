package com.lzx.onematerial.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lzx.onematerial.R;
import com.lzx.onematerial.utils.DeviceUtil;


/**
 * Created by lizhenxin on 17-8-18.
 */

public class DayRadio extends CardView {
    private String mTitle;
    private String mAudioUrl;
    private String mImageUrl;
    private String mArticleUrl;

    private Context mContext;

    private ImageButton toggle;
    private ImageView background;

    public DayRadio(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public DayRadio(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public DayRadio(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setParams(String title, String author, String audioUrl, String imageUrl, String articleUrl) {
        mTitle = title;
        mAudioUrl = audioUrl;
        mImageUrl = imageUrl;
        mArticleUrl = articleUrl;

        setContent(author);
    }

    public void setOnToggleClick(OnClickListener onClickListener){
        toggle.setOnClickListener(onClickListener);
    }

    public void setToggleImage(int resId){
        toggle.setBackgroundResource(resId);
    }

    private void setContent(String author) {
        LayoutInflater.from(mContext).inflate(R.layout.day_item_radio, this, true);

        background = findViewById(R.id.day_radio_pic);
        /*ImageManager imageManager = new ImageManager();
        imageManager.setImageFromUrl((Activity) mContext, background, mImageUrl, true);*/
        //ImageLoader.with(mContext).url(mImageUrl).into(background);
        Glide.with(mContext).asBitmap().load(mImageUrl).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                float picWidth = resource.getWidth();
                float picHeight = resource.getHeight();
                int viewWidth = new DeviceUtil((Activity) mContext).getWidthPx() - new DeviceUtil().dip2px(mContext, 32);
                int viewHeight = (int) (picHeight / picWidth * viewWidth);
                Matrix matrix = new Matrix();
                matrix.postScale(viewWidth/picWidth, viewHeight/picHeight);
                background.setImageBitmap(Bitmap.createBitmap(resource, 0, 0, (int) picWidth, (int) picHeight, matrix, true));
            }
        });

        ((TextView)findViewById(R.id.day_radio_author)).setText(author);

        ((TextView)findViewById(R.id.day_radio_title)).setText(mTitle);
        toggle = findViewById(R.id.radio_toggle);
    }

    public ImageView getBackgroundImageView() {
        return background;
    }
}
