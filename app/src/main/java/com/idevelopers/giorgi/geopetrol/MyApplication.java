package com.idevelopers.giorgi.geopetrol;

import android.app.Application;

import com.idevelopers.giorgi.geopetrol.internetConnection.ConnectivityReceiver;

/**
 * Created by Giorgi on 2/28/2017.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
