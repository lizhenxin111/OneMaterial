package com.lzx.onematerial.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lzx.onematerial.R;
import com.lzx.onematerial.utils.DeviceUtil;
import com.lzx.onematerial.utils.ImageManager;
import com.lzx.onematerial.utils.MyApp;
import com.lzx.onematerial.utils.ShareUtil;

import androidlib.activity.BaseActivity;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 *
 */
public class ImageViewActivity extends BaseActivity {

    private String SHARE_AUTHOR;
    private String SHARE_IMAGE_URL;
    private String SHARE_CONTENT;
    private String SHARE_WEB_URL;
    private String SHARE_WORDS_AUTHOR;


    //FLAG = true: 黑色背景，不显示文字； FLAG = false: 白色背景，显示文字
    private boolean FLAG = false;

    private ImageManager mImageManager;

    private ImageView mImageView;
    private ImageButton mBottomSheetToggle;
    private ImageButton mDownload, mShareWb, mShareQQ, mShareQZone, mShareWxFriend, mShareWxCircle, mShareWxCollect, mSharePocket;
    private TextView mAuthor, mContent, mWordsAuthor;
    private View mRoot;
    private BottomSheetBehavior behavior;

    private ShareUtil mShareUtil;

    @Override
    protected void initViews(Bundle saveBundleInstance) {
        setContentView(R.layout.activity_image_view);
        mRoot = findViewById(R.id.image_root);

        mImageManager = new ImageManager(this);

        mAuthor = findViewById(R.id.image_author);
        mContent = findViewById(R.id.image_content);
        mWordsAuthor = findViewById(R.id.image_words_author);
        mImageView = findViewById(R.id.image_large);

        mBottomSheetToggle = findViewById(R.id.image_bottom_sheet_toggle);
        behavior = BottomSheetBehavior.from(findViewById(R.id.image_bottom_sheet));

        mDownload = findViewById(R.id.image_download);
        mShareWb = findViewById(R.id.image_share_wb);
        mShareQQ = findViewById(R.id.image_share_qq);
        mShareQZone = findViewById(R.id.image_share_qzone);
        mShareWxFriend = findViewById(R.id.image_share_wx_friend);
        mShareWxCircle = findViewById(R.id.image_share_wx_circle);
        mShareWxCollect = findViewById(R.id.image_share_wx_collect);
        mSharePocket = findViewById(R.id.image_share_pocket);

    }

    @Override
    protected void initData() {
        SHARE_AUTHOR = getBundle().getString("author");
        SHARE_IMAGE_URL = getBundle().getString("imageUrl");
        SHARE_CONTENT = getBundle().getString("content");
        SHARE_WEB_URL = getBundle().getString("webUrl");
        SHARE_WORDS_AUTHOR = getBundle().getString("wordsAuthor");

        mAuthor.setText(SHARE_AUTHOR);
        mWordsAuthor.setText(SHARE_WORDS_AUTHOR);
        //ImageLoader.with(this).url(SHARE_IMAGE_URL).into(mImageView);
        Glide.with(this).asBitmap().load(SHARE_IMAGE_URL).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                float picWidth = resource.getWidth();
                float picHeight = resource.getHeight();
                int viewWidth = new DeviceUtil(ImageViewActivity.this).getWidthPx();
                int viewHeight = (int) (picHeight / picWidth * viewWidth);
                Matrix matrix = new Matrix();
                matrix.postScale(viewWidth/picWidth, viewHeight/picHeight);
                mImageView.setImageBitmap(Bitmap.createBitmap(resource, 0, 0, (int) picWidth, (int) picHeight, matrix, true));
            }
        });
        mContent.setText(SHARE_CONTENT);

        mShareUtil = new ShareUtil(this);
    }

    @Override
    protected void initListeners() {
        mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED){
                    mBottomSheetToggle.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down));
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetToggle.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_share));
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });


        mBottomSheetToggle.setOnClickListener(mOnImageButtonClickListener);

        mDownload.setOnClickListener(mOnImageButtonClickListener);
        mShareWb.setOnClickListener(mOnImageButtonClickListener);
        mShareQQ.setOnClickListener(mOnImageButtonClickListener);
        mShareQZone.setOnClickListener(mOnImageButtonClickListener);
        mShareWxFriend.setOnClickListener(mOnImageButtonClickListener);
        mShareWxCircle.setOnClickListener(mOnImageButtonClickListener);
        mShareWxCollect.setOnClickListener(mOnImageButtonClickListener);
        mSharePocket.setOnClickListener(mOnImageButtonClickListener);

    }


    private View.OnClickListener mOnImageButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.image_bottom_sheet_toggle:
                    onBottomSheetToggleClick();
                    break;
                case R.id.image_download:
                    saveImageRequest();
                    //mShareUtil.oneKeyShare("", "ImageViewAct分享", "", SHARE_IMAGE_URL, SHARE_WEB_URL);
                    break;
                case R.id.image_share_wb:
                    mShareUtil.shareToWeibo(SHARE_CONTENT + "\n        " + SHARE_WORDS_AUTHOR, SHARE_IMAGE_URL, SHARE_WEB_URL);
                    break;
                case R.id.image_share_qq:
                    mShareUtil.shareToQQ("分享自OneMaterial",SHARE_CONTENT + "\n        " + SHARE_WORDS_AUTHOR,
                            SHARE_IMAGE_URL, SHARE_WEB_URL);
                    break;
                case R.id.image_share_qzone:
                    mShareUtil.shareToQZone("分享自OneMaterial",SHARE_CONTENT + "\n        " + SHARE_WORDS_AUTHOR, SHARE_IMAGE_URL, SHARE_WEB_URL);
                    break;
                case R.id.image_share_wx_friend:
                    mShareUtil.shareToWeiXin(Wechat.NAME, SHARE_CONTENT + "\n        " + SHARE_WORDS_AUTHOR,
                            SHARE_CONTENT, SHARE_IMAGE_URL, SHARE_WEB_URL);
                    break;
                case R.id.image_share_wx_circle:
                    mShareUtil.shareToWeiXin(WechatMoments.NAME, SHARE_CONTENT + "\n        " + SHARE_WORDS_AUTHOR,
                            SHARE_CONTENT, SHARE_IMAGE_URL, SHARE_WEB_URL);
                    break;
                case R.id.image_share_wx_collect:
                    mShareUtil.shareToWeiXin(WechatFavorite.NAME, SHARE_CONTENT + "\n        " + SHARE_WORDS_AUTHOR,
                            SHARE_CONTENT, SHARE_IMAGE_URL, SHARE_WEB_URL);
                    break;
                case R.id.image_share_pocket:
                    mShareUtil.shareToPocket(SHARE_CONTENT, SHARE_WEB_URL);
                    break;
                default: break;
            }
        }
    };


    private void onBottomSheetToggleClick() {
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }


    //下载图片入口函数
    private void saveImageRequest() {
        if (MyApp.needCheckPermission()) {
            if (ContextCompat.checkSelfPermission(ImageViewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ImageViewActivity.this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                saveImage();
            }
        }
    }

    //下载图片的下载部分
    private void saveImage() {
        //imageManager.saveImage(ImageViewAct.this, imageView, Environment.getExternalStorageDirectory().toString(), DateManager.getYear() + getIntent().getStringExtra("date") + ".jpg");
        String message = null;
        int result = mImageManager.saveImage(SHARE_IMAGE_URL);
        if (result == ImageManager.IMAGE_EXIST){
            message = getResources().getString(R.string.image_exist);
        }else {
            message = getResources().getString(R.string.save_complete);
        }
        Toast.makeText(ImageViewActivity.this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    saveImage();
                } else {
                    Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
                }
            default:break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
