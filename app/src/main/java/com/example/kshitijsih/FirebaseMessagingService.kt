package com.example.kshitijsih

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle incoming FCM messages
        remoteMessage.notification?.let {
            createBubbleNotification(it.title, it.body)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Send the new token to your server or backend if needed
        sendTokenToServer(token)
    }

    private fun sendTokenToServer(token: String) {
        // TODO: Implement a network request to send the token to your app server
        Log.d("FCM", "New token sent to server: $token")
    }


    private fun createBubbleNotification(title: String?, message: String?) {
        // Intent to open the bubble activity
        val bubbleIntent = Intent(this,PopAlertActivity::class.java).apply {
            putExtra("title", title)
            putExtra("message", message)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        // PendingIntent with FLAG_MUTABLE for bubbles
        val bubblePendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(this, 0, bubbleIntent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(this, 0, bubbleIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        // Bubble Metadata
        val bubbleData = NotificationCompat.BubbleMetadata.Builder(
            bubblePendingIntent,
            IconCompat.createWithResource(this, R.drawable.avatar) // Bubble Icon
        )
            .setDesiredHeight(600) // Desired height of the expanded bubble
            .setAutoExpandBubble(true) // Auto-expand when created
            .setSuppressNotification(true) // Suppress duplicate notifications
            .build()

        // Regular notification for older devices or when bubble is not active
        val notification = NotificationCompat.Builder(this, "alert_channel_id")
            .setSmallIcon(R.drawable.avatar)
            .setContentTitle(title)
            .setContentText(message)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setBubbleMetadata(bubbleData) // Attach bubble metadata
            .setAutoCancel(true)
            .build()

        // Create the notification channel
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "alert_channel_id",
                "Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for alert notifications with bubbles"
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Show the notification
        notificationManager.notify(1, notification)
    }
}