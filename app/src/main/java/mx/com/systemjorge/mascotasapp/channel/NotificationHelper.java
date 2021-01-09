package mx.com.systemjorge.mascotasapp.channel;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import mx.com.systemjorge.mascotasapp.R;

public class NotificationHelper extends ContextWrapper {

    private static final String CHANNEL_ID = "mx.com.systemjorge.mascotasapp";
    private static final String CHANNEL_NAME = "MascotasApp";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Si estamos en un celular cuyo sistema operativo sea Oreo o una versión superior...
            createChannels();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {
        NotificationChannel notificationChannel = new NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
        );
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLightColor(Color.GRAY);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(notificationChannel);
    }

    public NotificationManager getManager() {
        if (mManager == null) { // Primero preguntaremos si el objeto NO está instanciado, y esto lo hacemos pregutando si el objeto es igual a null
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    // Método para crear la notificación para versiónes iguales o superiores a Android OREO
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getNotification(String tittle, String body, PendingIntent intent, Uri soundUri) {
        return new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(tittle)
                .setContentText(body)
                .setAutoCancel(true) // Para que cuando el usuario presione sobre esta notificación, se cierrre automaticamente
                .setSound(soundUri)
                .setContentIntent(intent)
                .setSmallIcon(R.drawable.logo)
                .setStyle(new Notification.BigTextStyle().bigText(body).setBigContentTitle(tittle));
    }

    // Método para crear la notificación para versiónes inferiores a Android OREO
    public NotificationCompat.Builder getNotificationApiVieja(String tittle, String body, PendingIntent intent, Uri soundUri) {
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(tittle)
                .setContentText(body)
                .setAutoCancel(true) // Para que cuando el usuario presione sobre esta notificación, se cierrre automaticamente
                .setSound(soundUri)
                .setContentIntent(intent)
                .setSmallIcon(R.drawable.logo)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body).setBigContentTitle(tittle));
    }

}
