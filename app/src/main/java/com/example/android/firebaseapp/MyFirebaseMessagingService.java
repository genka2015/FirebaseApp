package com.example.android.firebaseapp;


import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName() + "_TAG";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "onMessageReceived: From: " + remoteMessage.getFrom());
        Log.d(TAG, "onMessageReceived: Body: " + remoteMessage.getNotification().getBody());
    }
}
