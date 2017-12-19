package com.lzx.onematerial.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lzx.onematerial.R;
import com.lzx.onematerial.utils.DeviceUtil;


/**
 * 各个项目的自定义View
 */

public class DayItem extends CardView {
    private TextView category, title, author, abstracts;
    private ImageView pic;
    private Context mContext;

    public DayItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initViews();
    }

    public DayItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews();
    }

    public DayItem(Context context){
        super(context);
        mContext = context;
        initViews();
    }

    private void initViews(){
        LayoutInflater.from(mContext).inflate(R.layout.day_item_normal, this, true);
        pic = findViewById(R.id.day_item_pic);
        category = findViewById(R.id.day_item_category);
        title = findViewById(R.id.day_item_title);
        author = findViewById(R.id.day_item_author);
        abstracts = findViewById(R.id.day_item_abstract);

    }

    public void setCategory(String mCategory){
        category.setText(mCategory);
    }
    public void setTitle(String mTitle){
        title.setText(mTitle);
    }
    public void setAuthor(String mAuthor){
        author.setText(mAuthor);
    }
    public void setAbstracts(String mAbstracts){
        abstracts.setText(mAbstracts);
    }
    public void setPic(Bitmap image){
        pic.setImageBitmap(image);
    }
    public void setPicByUrl(final String url){
        //ImageLoader.with(mContext).url(url).into(pic);
        Glide.with(mContext).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                Log.d("IMAGE", resource.getWidth() + "      " + resource.getHeight());
                /*pic.setImageBitmap(resource);
                Palette palette = Palette.from(resource).generate();
                Palette.Swatch swatch = palette.getLightVibrantSwatch();
                if (swatch != null) {
                    title.setTextColor(swatch.getTitleTextColor());
                }*/
                float picWidth = resource.getWidth();
                float picHeight = resource.getHeight();
                int viewWidth = new DeviceUtil((Activity) mContext).getWidthPx() - new DeviceUtil().dip2px(mContext, 32);
                int viewHeight = (int) (picHeight / picWidth * viewWidth);
                Matrix matrix = new Matrix();
                matrix.postScale(viewWidth/picWidth, viewHeight/picHeight);
                pic.setImageBitmap(Bitmap.createBitmap(resource, 0, 0, (int) picWidth, (int) picHeight, matrix, true));
            }
        });
    }

    public TextView getCategory() {
        return category;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getAuthor() {
        return author;
    }

    public TextView getAbstracts() {
        return abstracts;
    }

    public ImageView getPic() {
        return pic;
    }
}
