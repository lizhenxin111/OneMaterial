package androidlib.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

/**
 * Created by lizhenxin on 17-12-15.
 */

public class SimpleLoading extends Dialog {
    private Context mContext;
    public SimpleLoading(@NonNull Context context) {
        super(context);
        mContext = context;
        initProgress(context);
    }

    public SimpleLoading(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        initProgress(context);
    }

    protected SimpleLoading(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
        initProgress(context);
    }

    private void initProgress(Context context) {
        ProgressBar progressBar = new ProgressBar(context);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(android.R.color.transparent)));
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(true);
        this.getWindow().setDimAmount(0);
        this.setContentView(progressBar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ((Activity)mContext).finish();
    }
}
