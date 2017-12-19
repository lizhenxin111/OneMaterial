package androidlib.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lzx.androidlib.R;

import java.util.Stack;

import androidlib.utils.ActivityUtils;

/**
 * Created by lizhenxin on 17-11-13.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private int isDoubleClickExit = 0;      //是否双击退出App。0为不退出

    private static Stack<Activity> BASE_ACTIVITY_STACK = new Stack<>();     //记录所有已经打开的Activity

    private ActivityUtils mActivityUtils = new ActivityUtils();

    private Bundle bundle;      //从上一个Activity传递过来的数据
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        initViews(savedInstanceState);
        initData();
        initListeners();
    }

    protected abstract void initViews(Bundle saveBundleInstance);
    protected abstract void initData();
    protected abstract void initListeners();

    /*****************数据恢复********************/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        saveBundleState(outState);
        super.onSaveInstanceState(outState);
    }

    protected void saveBundleState(Bundle state) {

    }


    /*****************Toast********************/
    protected void toastShort(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    protected void toastShort(int resID) {
        Toast.makeText(this, getString(resID), Toast.LENGTH_SHORT).show();
    }

    protected void toastLong(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
    protected void toastLong(int resID) {
        Toast.makeText(this, getString(resID), Toast.LENGTH_LONG).show();
    }


    /*****************Activity操作********************/
    protected void newActivity(Class<?> targetActivity, @NonNull Bundle content) {
        ActivityUtils.newActivity(this, targetActivity, content);
        BASE_ACTIVITY_STACK.push(this);
    }

    protected void newActivity(Class<?> targetActivity, @NonNull Bundle content, ActivityOptions options) {
        ActivityUtils.newActivity(this, targetActivity, content, options);
        BASE_ACTIVITY_STACK.push(this);
    }

    protected void newActivity(Class<?> targetActivity) {
        newActivity(targetActivity, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BASE_ACTIVITY_STACK.contains(this)) {
            BASE_ACTIVITY_STACK.remove(this);
        }
    }

    protected void finishAll() {
        for (Activity activity:
             BASE_ACTIVITY_STACK) {
            activity.finish();
        }

    }

    protected void exitApp() {
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    /*****************加载动画********************/


    /*****************获取资源********************/
    /** @param resId
     * @return String字符串
     * */
    protected String getStringResource(int resId) {
        return getResources().getString(resId);
    }

    /**
     * @param resId
     * @return int尺寸值
     */
    protected int getDimenResource(int resId) {
        return (int) getResources().getDimension(resId);
    }

    /**
     * @return 从上一个Activity传递过来的数据的bundle
     */
    protected Bundle getBundle() {
        if (bundle == null) {
            bundle = getIntent().getExtras();
        }
        return bundle;
    }


    /********************双击退出*******************/
    protected void setDoubleClickExit(int ms) {
        isDoubleClickExit = ms;
    }

    private boolean isBackButtonClicked = false;

    @Override
    public void onBackPressed() {
        if (isDoubleClickExit != 0) {
            if (isBackButtonClicked) {
                exitApp();
                return;
            }
            isBackButtonClicked = true;
            toastShort(R.string.double_clikc_exit);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBackButtonClicked = false;
                }
            }, isDoubleClickExit);
        } else {
            super.onBackPressed();
        }
    }



    //滑动返回
    private boolean mSlideBack = false;
    private boolean isBackArrowShow = false;
    private boolean isFirstShow = true;
    public void setSlideBack(boolean slideBack){
        mSlideBack = slideBack;
        if (slideBack == true) {
            initBackArrow();
        }
    }
    private float fx, fy;       //手指按下的坐标
    private ImageView mBackArrow;
    private void initBackArrow(){
        mBackArrow = new ImageView(this);
        mBackArrow.setImageDrawable(getResources().getDrawable(R.drawable.ic_motion_back));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        params.leftMargin =  16;
        mBackArrow.setLayoutParams(params);
        //mBackArrow.setBackgroundColor(getColor(R.color.colorPrimary));
        ViewGroup mContentView = findViewById(android.R.id.content);
        mContentView.addView(mBackArrow);
        mBackArrow.setVisibility(View.GONE);
    }
    private void showArrow() {
        if (isFirstShow) {
            mBackArrow.setVisibility(View.VISIBLE);
            isBackArrowShow = true;
        }
    }
    private void removeArrow() {
        mBackArrow.setVisibility(View.GONE);
        isBackArrowShow = false;
        isFirstShow = false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mSlideBack) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    fx = event.getX();
                    fy = event.getY();
                    //Log.d("MOTION", "按下" + event.getX() + "  " + event.getY());
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (fx < 100) {
                        if (Math.abs(event.getY() - fy) < 900) {
                            showArrow();
                        } else {
                            removeArrow();
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d("MOTION", "松开" + event.getX() + "  " + event.getY());
                    Log.d("MOTION", "距离" + Math.abs(event.getX() - fx ) + "  " + Math.abs(event.getY() - fy));
                    if (isBackArrowShow) {
                        this.finish();
                    }
                    //手指松开，恢复原样
                    removeArrow();
                    isFirstShow = true;
                    break;
                default:break;
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
