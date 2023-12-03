package com.example.bildirimcalismasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonBildir;
    private NotificationCompat.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonBildir = findViewById(R.id.buttonBildir);

        buttonBildir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                durumaBagli();

            }
        });


    }


    public void durumaBagli(){

        NotificationManager bildirimYoneticisi = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(MainActivity.this,QarsilamaActivity.class);

        PendingIntent gidilecekIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            String kanalID = "kanalID";
            String kanalAd = "kanalAd";
            String kanalTanim = "kanalTanim";

            int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel kanal = bildirimYoneticisi.getNotificationChannel(kanalID);

            if (kanal == null){
                kanal = new NotificationChannel(kanalID,kanalAd,kanalOnceligi);
                kanal.setDescription(kanalTanim);
                bildirimYoneticisi.createNotificationChannel(kanal);
            }

            builder = new NotificationCompat.Builder(this,kanalID);

            builder.setContentTitle("Isin Bitti");
            builder.setContentText("Seni tapacam");
            builder.setSmallIcon(R.drawable.resim);
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekIntent);



        }else{

            builder = new NotificationCompat.Builder(this);

            builder.setContentTitle("Isin Bitti");
            builder.setContentText("Seni tapacam");
            builder.setSmallIcon(R.drawable.resim);
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekIntent);
            builder.setPriority(Notification.PRIORITY_HIGH);

        }


        bildirimYoneticisi.notify(1,builder.build());

    }


}