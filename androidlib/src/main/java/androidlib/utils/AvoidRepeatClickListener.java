package androidlib.utils;

import android.view.View;

import java.util.Calendar;

/**
 * Created by lizhenxin on 17-11-13.
 */

public abstract class AvoidRepeatClickListener implements View.OnClickListener {
    private long lastClickTime = 0;
    private long MIN_DELAY_TIME;

    public AvoidRepeatClickListener(long MIN_DELAY_TIME) {
        this.MIN_DELAY_TIME = MIN_DELAY_TIME;
    }

    @Override
    public void onClick(View v) {
        long currentClickTime = Calendar.getInstance().getTimeInMillis();
        if (currentClickTime - lastClickTime > MIN_DELAY_TIME) {
            lastClickTime = currentClickTime;
            onAVClick(v);
        }
    }

    protected void onAVClick(View view) {

    }
}
