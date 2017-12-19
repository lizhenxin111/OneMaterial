package androidlib.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by lizhenxin on 17-11-19.
 */

public class ExtraWebView extends WebView {

    private OnScrollChangeCallback mOnScrollChangeCallback;


    public ExtraWebView(Context context) {
        super(context);
    }

    public ExtraWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtraWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ExtraWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangeCallback != null) {
            mOnScrollChangeCallback.onScroll(l - oldl, t - oldt);
        }
    }

    public void setOnScrollChangeCallback(OnScrollChangeCallback onScrollChangeCallback) {
        this.mOnScrollChangeCallback = onScrollChangeCallback;
    }

    public interface OnScrollChangeCallback{
        void onScroll(int dx, int dy);
    }
}
