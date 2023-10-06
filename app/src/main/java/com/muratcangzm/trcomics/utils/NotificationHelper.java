package com.muratcangzm.trcomics.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.muratcangzm.trcomics.R;

public class NotificationHelper {


    private static final String CHANNEL_NAME = "main_channel";

    private static final int NOTIFICATION_ID = 1;


    @SuppressLint("MissingPermission")
    public static void showNotification(Context context, String title, String content) {

        createNotificationChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_NAME)
                .setSmallIcon(R.drawable.foreground_icon)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }


    private static void createNotificationChannel(Context context) {


        CharSequence name = "Registration Notification Channel";
        String description = "Please Check your E-Mail out for Verification";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_NAME, name, importance);
        channel.setDescription(description);

        NotificationManager manager = context.getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

    }
}
