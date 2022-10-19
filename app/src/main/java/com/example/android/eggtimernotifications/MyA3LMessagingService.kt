package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import com.amazon.A3L.messaging.A3LMessagingService
import com.amazon.A3L.messaging.RemoteMessage
import com.example.android.eggtimernotifications.util.sendNotification

class MyA3LMessagingService : A3LMessagingService() {
    private val TAG = "MyA3LMessagingService"
    override fun onMessageReceived(context: Context, remoteMessage: RemoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${remoteMessage.from}")

        // TODO Step 3.5 check messages for data
        // Check if message contains a data payload.
        remoteMessage?.data?.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        // TODO Step 3.6 check messages for notification and call sendNotification
        // Check if message contains a notification payload.
        remoteMessage?.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(context, it.body!!)
        }
    }

    override fun onNewToken(context: Context, token: String) {
        Log.d(TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(context, token)
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(context: Context, body: String) {
        val notificationManager = ContextCompat.getSystemService(context, NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(body, context)
    }

    /**
     * Persist token to third-party servers.
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(context: Context, token: String?) {
        // TODO: Implement this method to send token to your app server.
    }
}