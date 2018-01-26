package com.trust.tnighttalk.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Trust on 2018/1/26.
 */

public class TrustServer extends Service {
    private Binder trustBinder = new TrustBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return trustBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public class TrustBinder extends Binder{
        public TrustServer getService(){
            return TrustServer.this;
        }
    }
}
