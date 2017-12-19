package com.lzx.onematerial.activity;

import android.annotation.SuppressLint;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzx.onematerial.R;

import com.lzx.onematerial.utils.ShareUtil;
import com.lzx.onematerial.utils.StringUtil;

import androidlib.activity.ExtraWebView;
import androidlib.activity.WebActivity;
import androidlib.ui.SimpleLoading;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ArticleActivity extends WebActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private int UI_ANIMATION_DELAY = 300;
    private Handler mHideHandler = new Handler();
    private View mContentView;
    private Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    };
    private boolean mVisible;
    private Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_article);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.article_webview);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }
*/


    private String SHARE_TITLE= "",SHARE_IMAGE_URL, SHARE_CONTENT_URL= "",SHARE_SUBTITLE = "", SHARE_AUTHOR = "";



    private ShareUtil mShareUtil;
    private TextView mTitle;
    private ImageButton mBack, mShare;
    private LinearLayout mToolbar;
    private SimpleLoading mLoading;

    private BottomSheetBehavior mShareList;
    private boolean isToolbarVisible = false;


    @Override
    protected void initViews(Bundle saveBundleInstance) {
        setContentView(R.layout.activity_article);
        mLoading = new SimpleLoading(this);
        mLoading.show();

        mShareList = BottomSheetBehavior.from(findViewById(R.id.share_bottom_sheet));

        mShareUtil = new ShareUtil(this);

        mVisible = true;
        mContentView = findViewById(R.id.article_webview);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        setSlideBack(true);
    }

    @Override
    protected void initData() {
        SHARE_TITLE = getBundle().getString(WebActivity.PAGE_TITLE);
        SHARE_IMAGE_URL = getBundle().getString(WebActivity.PAGE_IMAGEURL);
        SHARE_CONTENT_URL = getBundle().getString(WebActivity.PAGE_URL);
        SHARE_SUBTITLE = getBundle().getString(WebActivity.PAGE_SUBTITLE);
        SHARE_AUTHOR = getBundle().getString(WebActivity.PAGE_AUTHOR);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getWebViewId() {
        return R.id.article_webview;
    }

    @Override
    protected void setWebView(final ExtraWebView webView) {

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) webView.getLayoutParams();
        params.setMargins(0, getStatusBarHeight(), 0, 0);
        //webView.setLayoutParams(params);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.evaluateJavascript(StringUtil.getJsFromFile("Javascript/removeTags"), new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        mLoading.dismiss();
                    }
                });
                if (StringUtil.isNull(SHARE_IMAGE_URL)) {
                    SHARE_TITLE = webView.getTitle();
                    SHARE_SUBTITLE = webView.getTitle();
                    SHARE_IMAGE_URL = "https://raw.githubusercontent.com/lizhenxin111/ApkStore/master/ic_launcher_translution.png";
                }
                if (StringUtil.isNull(SHARE_SUBTITLE)) {
                    SHARE_SUBTITLE = SHARE_TITLE;
                }
                initToolbar(webView);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                /*if (request.isForMainFrame() == false) {        //清除iframe注入
                    view.evaluateJavascript(StringUtil.getJsFromFile("Javascript/removeTags"), new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                        }
                    });
                }*/
                if (!request.hasGesture()) {
                    return true;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString(PAGE_URL, request.getUrl().toString());
                    bundle.putString(NEED_TOOLBAR, "true");
                    bundle.putString("position", "webin");
                    newActivity(ArticleActivity.class, bundle);
                    Log.d("AUTHOR", request.getUrl().toString());

                }
                return true;
            }
        });

        webView.loadUrl(getBundle().getString(PAGE_URL));
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    private void initToolbar(final ExtraWebView webView) {
        if ("true".equals(getBundle().getString(NEED_TOOLBAR))) {
            mToolbar = findViewById(R.id.article_toolbar);
            mTitle = findViewById(R.id.article_title);
            mBack = findViewById(R.id.article_back);
            mShare = findViewById(R.id.article_share);
            Log.d("TAG", webView.getTitle());
            mTitle.setText(webView.getTitle());
            mBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            mShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("URL", SHARE_TITLE + "    " + SHARE_SUBTITLE + "      " + SHARE_AUTHOR + "    " + SHARE_IMAGE_URL + "     " + SHARE_CONTENT_URL);
                        mShareUtil.oneKeyShare(SHARE_TITLE,    SHARE_SUBTITLE,
                                SHARE_AUTHOR, SHARE_IMAGE_URL, SHARE_CONTENT_URL);
                }
            });
            webView.setOnScrollChangeCallback(new ExtraWebView.OnScrollChangeCallback() {
                @Override
                public void onScroll(int dx, int dy) {
                    if (webView.getScrollY() <= getStatusBarHeight()) {
                        hideToolbar();
                    } else {
                        if (dy > 10) {           //隐藏
                            if (isToolbarVisible)
                                hideToolbar();
                        } else if (dy < -10){
                            if (!isToolbarVisible)
                                showToolbar();
                        }
                    }
                }
            });
        }
    }

    private void hideToolbar() {
        mToolbar.setVisibility(View.INVISIBLE);
        isToolbarVisible = false;
    }

    private void showToolbar() {
        mToolbar.setVisibility(View.VISIBLE);
        isToolbarVisible = true;
    }

    @Override
    public void onBackPressed() {
        if (getWebView().canGoBack()) {
            getWebView().goBack();
        } else {
            super.onBackPressed();
        }
    }




    @Override
    protected void onResume() {
        super.onResume();
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
