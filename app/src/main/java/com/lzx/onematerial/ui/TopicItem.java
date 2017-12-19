package com.lzx.onematerial.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzx.onematerial.R;
import com.lzx.onematerial.utils.ImageManager;
import com.lzx.onematerial.utils.MyApp;

import androidlib.image.loader.ImageLoader;

/**
 * TODO: document your custom view class.
 */
public class TopicItem extends CardView {

    private Context mContext;

    private ImageView mImageView;
    private TextView mTitle;

    public TopicItem(Context context) {
        super(context);
        mContext = context;
        init(null, 0);
    }

    public TopicItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs, 0);
    }

    public TopicItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.topic_item, this, true);
        mImageView = view.findViewById(R.id.topic_imageview);
        mTitle = view.findViewById(R.id.topic_title);
    }

    public void setImage(String link, ImageManager imageManager) {
        //mageManager.setImageFromUrl((Activity) mContext, mImageView, link, true);
        ImageLoader.with(MyApp.getContext()).url(link).into(mImageView);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    public TextView getmTitle() {
        return mTitle;
    }
}
