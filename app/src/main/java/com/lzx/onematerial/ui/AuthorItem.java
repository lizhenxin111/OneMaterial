package com.lzx.onematerial.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzx.onematerial.R;

/**
 * Created by lizhenxin on 17-11-25.
 */

public class AuthorItem extends CardView{

    private ImageView mImage;
    private TextView mName;
    private TextView mWbName;
    private TextView mDesc;

    public AuthorItem(Context context) {
        super(context);
        initView(context);
    }

    public AuthorItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AuthorItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void  initView(Context context) {
        View contextView = LayoutInflater.from(context).inflate(R.layout.author_item, this, true);
        mImage = contextView.findViewById(R.id.author_image);
        mName = contextView.findViewById(R.id.author_name);
        /*mWbName = (TextView) contextView.findViewById(R.id.author_wb);
        mDesc = (TextView) contextView.findViewById(R.id.author_desc);*/
    }

    public void setAuthorImage(Context context, String link) {
        //ImageLoader.with(context).url(link).into(mImage);
        Glide.with(context).load(link).into(mImage);
    }

    public void setAuthorName(String name) {
        mName.setText(name);
    }

    public void setAuthorWeName(String weName) {
        mWbName.setText(weName);
    }

    public void setAuthorDesc(String desc) {
        mDesc.setText(desc);
    }
}
