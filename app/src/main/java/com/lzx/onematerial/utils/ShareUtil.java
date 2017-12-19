package com.lzx.onematerial.utils;

import android.content.Context;
import android.widget.Toast;

import com.lzx.onematerial.R;
import com.lzx.onematerial.cn.sharesdk.onekeyshare.OnekeyShare;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.pocket.Pocket;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;


/**
 * Created by lizhenxin on 17-12-1.
 *
 * http://wiki.mob.com/%e4%b8%8d%e5%90%8c%e5%b9%b3%e5%8f%b0%e5%88%86%e4%ba%ab%e5%86%85%e5%ae%b9%e7%9a%84%e8%af%a6%e7%bb%86%e8%af%b4%e6%98%8e/#map-5
 */

public class ShareUtil {

    private static final int COMPLETE = -1;
    private static final int FAILED = -2;
    private static final int CANCEL = -3;

    private Context mContext;

    public ShareUtil(Context context) {
        mContext = context;
    }

    private void toastResult(int flag) {
        String result = "";
        switch (flag) {
            case COMPLETE:
                result = mContext.getString(R.string.share_complete);
                break;
            case FAILED:
                result = mContext.getString(R.string.share_failed);
                break;
            case CANCEL:
                result = mContext.getString(R.string.share_cancel);
                break;
            default:break;
        }
        Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
    }


    public void oneKeyShare(String title, String text, String author, String imageUrl, String pageUrl){
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();

        oks.setSilent(true);        //是否直接分享

        oks.setText(text + "        \nby" + author);              //所有
            oks.setImageUrl(imageUrl);      //微博、空间

        oks.setUrl(pageUrl);            //微信系列

        oks.setTitle(title);            //微信系列、QQ空间
        oks.setTitleUrl(pageUrl);


        oks.setSite("OneMaterial");     //仅QQ空间
        oks.setSiteUrl(ApiUtil.KUAN_LINK);      //仅QQ空间

        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                toastResult(COMPLETE);
                //platform.isClientValid();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                toastResult(FAILED);
                //throwable.getMessage();
                //throwable.printStackTrace();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                //toastResult(CANCEL);
            }
        });
        oks.show(mContext);
    }


    public void shareToWeibo(String text, String imageUrl, String contentUrl) {
        Platform platform = ShareSDK.getPlatform (SinaWeibo.NAME);
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setText(text);
        sp.setUrl(contentUrl);
        sp.setImageUrl(imageUrl);
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                toastResult(COMPLETE);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                toastResult(FAILED);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                //toastResult(CANCEL);
            }
        });
        platform.share(sp);
    }

    public void shareToQQ(String title, String text, String imageUrl, String contentUrl) {
        Platform platform = ShareSDK.getPlatform (QQ.NAME);
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setText(text);
        sp.setTitle(title);
        sp.setTitleUrl(contentUrl);
        sp.setImageUrl(imageUrl);
        sp.setSite("OneMaterial");
        sp.setSiteUrl(ApiUtil.KUAN_LINK);
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                toastResult(COMPLETE);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                toastResult(FAILED);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                //toastResult(CANCEL);
            }
        });
        platform.share(sp);
    }

    public void shareToQZone(String title, String text, String imageUrl, String contentUrl) {
        Platform platform = ShareSDK.getPlatform (QZone.NAME);
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setText(text);
        sp.setTitle(title);
        sp.setTitleUrl(contentUrl);
        sp.setImageUrl(imageUrl);
        sp.setSite("OneMaterial");
        sp.setSiteUrl(ApiUtil.KUAN_LINK);
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                toastResult(COMPLETE);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                toastResult(FAILED);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                //toastResult(CANCEL);
            }
        });
        platform.share(sp);
    }

    public void shareToWeiXin(String name, String text, String title, String imageUrl, String contentUrl) {
        Platform platform = ShareSDK.getPlatform (name);
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setText(text);
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setTitle(title);
        sp.setImageUrl(imageUrl);
        sp.setUrl(contentUrl);
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                toastResult(COMPLETE);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                toastResult(FAILED);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                toastResult(CANCEL);
            }
        });
        platform.share(sp);
    }


    public void shareToPocket(String title, String url) {
        Platform platform = ShareSDK.getPlatform (Pocket.NAME);
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setUrl(url);
        sp.setTitle(title);
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                toastResult(COMPLETE);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                toastResult(FAILED);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                //toastResult(CANCEL);
            }
        });
        platform.share(sp);
    }
}
