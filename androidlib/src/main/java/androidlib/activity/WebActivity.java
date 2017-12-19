package androidlib.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by lizhenxin on 17-11-22.
 */

public abstract class WebActivity extends BaseActivity {
    public static final String NEED_TOOLBAR = "NEEDTOOLBAR";
    public static final String PAGE_URL = "PAGEURL";
    public static final String PAGE_TITLE = "PAGETITLE";
    public static final String PAGE_SUBTITLE = "PAGESUBTITLE";
    public static final String PAGE_IMAGEURL = "PAGEIMAGEURL";
    public static final String PAGE_AUTHOR = "PAGEAUTHOR";



    private ExtraWebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = findViewById(getWebViewId());
        setWebView(mWebView);
        Log.d("ACTIVITYLIFECIRCLE", "WebActivity onCreate");
    }

    @Override
    protected abstract void initViews(Bundle saveBundleInstance);

    @Override
    protected abstract void initData();

    @Override
    protected abstract void initListeners();

    protected abstract int getWebViewId();

    protected abstract void setWebView(ExtraWebView webView);

    protected ExtraWebView getWebView() {
        return mWebView;
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
        mWebView = null;
    }
}
