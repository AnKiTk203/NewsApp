package com.codingblocks.notifications


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            nm.createNotificationChannel(NotificationChannel("first",
            "default",NotificationManager.IMPORTANCE_DEFAULT))
        }
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val simpleNotification = NotificationCompat.Builder(this,"first")
                .setContentTitle("Simple Title")
                .setContentText("This is Sample description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(1,simpleNotification)
        }
        val btn2 = findViewById<Button>(R.id.button2)
        btn2.setOnClickListener {
            val it = Intent()
            it.action = Intent.ACTION_VIEW
            it.data = Uri.parse("https://www.google.com")
            val pi = PendingIntent.getActivity(this,123,it,PendingIntent.FLAG_IMMUTABLE)
            val clickableNotification = NotificationCompat.Builder(this,"first")
                .setContentTitle("Simple Title")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setContentText("This is Sample description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(2,clickableNotification)
        }
        val btn3  = findViewById<Button>(R.id.button3)
        btn3.setOnClickListener {
            val it = Intent()
            it.action = Intent.ACTION_VIEW
            it.data = Uri.parse("https://www.google.com")
            val pi = PendingIntent.getActivity(this,123,it,PendingIntent.FLAG_IMMUTABLE)
            val clickableNotification = NotificationCompat.Builder(this,"first")
                .setContentTitle("Simple Title")
                .addAction(R.drawable.ic_launcher_foreground,"Click Me",pi)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setContentText("This is Sample description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(3,clickableNotification)
        }
    }
}