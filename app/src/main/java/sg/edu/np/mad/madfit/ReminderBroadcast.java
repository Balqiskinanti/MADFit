package sg.edu.np.mad.madfit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {

    //Set the notification broadcast
    @Override
    public void onReceive(Context context, Intent intent){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyExercise")
                .setSmallIcon(R.drawable.bg_splash)
                .setContentTitle("Exercise Reminder")
                .setContentText("A reminder to do your daily exercise :)")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }
}
