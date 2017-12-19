package com.lzx.onematerial.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * 适配器模式封装通知管理类
 */

public class NotificationUtil {

    public static void showToast(Context context, String message, int duration){
        Toast.makeText(context, message, duration).show();
    }

    private Notification.Builder builder;
    private NotificationManager notificationManager;

    public NotificationUtil(Context context){
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new Notification.Builder(context);
    }

    private void setContentText(String contentText){
        builder.setContentText(contentText);
    }
    private void setSmallIcon(int resourceId){
        builder.setSmallIcon(resourceId);
    }
    private void setContentTitle(String contentTitle){
        builder.setContentTitle(contentTitle);
    }
    private void setContentIntent(PendingIntent pendingIntent){
        builder.setContentIntent(pendingIntent);
    }
    private void setPriority(int pri){
        builder.setPriority(pri);
    }
    private void notify(int id){
        notificationManager.notify(id, builder.build());
    }
    private void setContentView(RemoteViews remoteViews){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setCustomContentView(remoteViews);
        }
    }
    private void setContent(RemoteViews remoteViews){
        builder.setContent(remoteViews);
    }
    public void cancle(int id){
        notificationManager.cancel(id);
    }

    public static class Builder{
        NotificationUtil notificationUtil;
        public Builder (Context context){
            notificationUtil = new NotificationUtil(context);
        }
        public Builder setContentTitle(String contentTitle){
            notificationUtil.setContentTitle(contentTitle);
            return this;
        }
        public Builder setContentText(String contentText){
            notificationUtil.setContentText(contentText);
            return this;
        }
        public Builder setSmallIcon(int resourceId){
            notificationUtil.setSmallIcon(resourceId);
            return this;
        }
        public Builder setContentIntent(PendingIntent pendingIntent){
            notificationUtil.setContentIntent(pendingIntent);
            return this;
        }
        public Builder setPriority(int pri){
            notificationUtil.setPriority(pri);
            return this;
        }
        public Builder setContentRemoteViews(RemoteViews remoteViews){
            notificationUtil.setContentView(remoteViews);
            return this;
        }
        public Builder setContent(RemoteViews remoteViews){
            notificationUtil.setContent(remoteViews);
            return this;
        }
        public NotificationUtil build(int id){
            notificationUtil.notify(id);
            return notificationUtil;
        }
    }
}
