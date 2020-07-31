//package com.example.myapplication.Activity;
//
//import android.app.Notification;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Build;
//import android.support.v4.media.session.MediaSessionCompat;
//
//import androidx.core.app.NotificationCompat;
//import androidx.core.app.NotificationManagerCompat;
//
//import com.example.myapplication.Model.Baihat;
//import com.example.myapplication.R;
//
//public class CreatNotification {
//    public static final String CHANNEL_ID ="channell";
//    public static final String ACTIONPREVIUOS ="actionprevious";
//    public static final String CHANNEL_PLAY ="actionplay";
//    public static final String CHANNEL_NEXT ="actionnext";
//
//    public static Notification notification;
//    public static void creatNotification(Context context, Baihat baihat, int playbutton, int pos, int size){
//
//        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
//            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
//            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context,"tag");
//
//            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), Integer.parseInt(baihat.getHinhbaihat()));
//            notification = new NotificationCompat.Builder(context,CHANNEL_ID)
//                    .setSmallIcon(R.drawable.songs)
//                    .setContentTitle(baihat.getTenbaihat())
//                    .setLargeIcon(icon)
//                    .setOnlyAlertOnce(true)
//                    .setShowWhen(false)
//                    .setPriority(NotificationCompat.PRIORITY_LOW)
//                    .build();
//
//            notificationManagerCompat.notify(1,notification);
//
//        }
//    }
//
//
//
//}
