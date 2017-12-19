package androidlib.utils;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by lizhenxin on 17-11-14.
 */

public class ActivityUtils {

    public ActivityUtils() {
    }

    public static void newActivity(Context packageContext, Class<?> targetActivity, Bundle content) {
        Intent intent = new Intent(packageContext, targetActivity);
        if (content != null) {
            intent.putExtras(content);
        }
        packageContext.startActivity(intent);
    }

    public static void newActivity(Context packageContext, Class<?> targetActivity, 
                                     @NonNull Bundle content, ActivityOptions options) {
        Intent intent = new Intent(packageContext, targetActivity);
        intent.putExtras(content);
        packageContext.startActivity(intent, options.toBundle());
    }

    public static void newActivity(Context packageContext, Class<?> targetActivity) {
        newActivity(packageContext, targetActivity, null);
    }

}
