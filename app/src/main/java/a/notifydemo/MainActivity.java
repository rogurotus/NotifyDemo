package a.notifydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel("a.notifydemo.news",
                "NotifyDemo News", "Example News Channel");
    }

    protected void createNotificationChannel(String id, String name, String description) {
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel channel = new NotificationChannel(id, name, importance);

        channel.setDescription(description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(channel);
    }

    public void sendNotification(View view) {

        int notificationID = 101;
        Intent resultIntent = new Intent(this, ResultActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        String channelID = "a.notifydemo.news";

        Notification notification =  new Notification.Builder(MainActivity.this, channelID)
                .setContentTitle("New Message")
                .setContentText("You've received new messages.")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setNumber(10)
                .setContentIntent(pendingIntent)
                .setChannelId(channelID)
                .build();
        notificationManager.notify(notificationID, notification);
    }
}