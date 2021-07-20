package sg.edu.np.mad.madfit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {
    public String GLOBAL_PREFS = "MyPrefs";
    public String MUTE_PUSH_NOTIFS_SETTINGS = "MutePushNotifsSettings";

    //Set the notification broadcast
    @Override
    public void onReceive(Context context, Intent intent){
        SharedPreferences sharedPreferences = context.getSharedPreferences(GLOBAL_PREFS, Context.MODE_PRIVATE);
        boolean sharedNotifsIsMuted = sharedPreferences.getBoolean(MUTE_PUSH_NOTIFS_SETTINGS,false);

        initBuilder(sharedNotifsIsMuted, context);
    }

    /*initialise notification builder*/
    private void initBuilder(Boolean isMuted, Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyExercise")
                .setContentTitle("Exercise Reminder")
                .setContentText("A reminder to do your daily exercise :)")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        // set icon : >Marshmallow & <Marshmallow
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            builder.setSmallIcon(R.mipmap.ic_launcher_foreground)
                    .setColor(Color.parseColor("#3F51B5"));
        }
        else{
            builder.setSmallIcon(R.drawable.logo_madfit);
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());

        //if muted : cancel notify & set silent
        if(isMuted){
            builder.setSilent(true);
            notificationManager.cancel(1);
        }
        else{
            builder.setSilent(false);
        }
    }
}
