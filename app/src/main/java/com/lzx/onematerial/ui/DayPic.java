package com.lzx.onematerial.ui;

import android.app.Activity;
import android.graphics.Matrix;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lzx.onematerial.R;
import com.lzx.onematerial.utils.DeviceUtil;


/**
 * 每日一图的自定义View
 */

public class DayPic extends CardView{
    private Context mContext;
    private ImageView pic;
    private TextView picAuthor, words, wordsAuthor;
    private CardView cardView;
    private String tUrl, date;

    public DayPic(Context context) {
        super(context);
        initViews(context);
        mContext = context;
    }
    private void initViews(Context context){
        LayoutInflater.from(context).inflate(R.layout.day_item_pic, this, true);
        cardView = findViewById(R.id.day_item_one_pic);
        pic = findViewById(R.id.day_item_pic);
        picAuthor = findViewById(R.id.day_item_pic_author);
        words = findViewById(R.id.day_item_words);
        wordsAuthor = findViewById(R.id.day_item_words_author);
    }

    public void setPic(Bitmap image){
        pic.setImageBitmap(image);
    }
    public void setPicByUrl(String url){
        /*ImageManager imageManager = new ImageManager();
        imageManager.setImageFromUrl((Activity) mContext, pic, url, true);*/
        //ImageLoader.with(mContext).url(url).into(pic);
        Glide.with(mContext).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
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
    public void setPicAuthor(String author){
        picAuthor.setText(author);
    }
    public void setWords(String word){
        words.setText(word);
    }
    public void setWordsAuthor(String author){
        wordsAuthor.setText(author);
    }
    public void setUrl(String url){
        this.tUrl = url;
    }

    public void setDate(String date){
        this.date = date;
    }
    public String getUrl(){
        return tUrl;
    }

    public String getSharedText(){
        return words.getText().toString() + "    " + wordsAuthor.getText().toString();
    }


    public ImageView getPic() {
        return pic;
    }
}
