package com.example.realmquerytest;

import android.app.Application;
import android.util.Log;

import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;

public class MyApplication extends Application {

    //Authentication variables
    Realm realm;
    SyncConfiguration config;

    private static final String INSTANCE_ADDRESS = "exercisesdb.us1a.cloud.realm.io";
    public static final String AUTH_URL = "https://" + INSTANCE_ADDRESS + "/auth";
    public static final String REALM_URL = "realms://" + INSTANCE_ADDRESS + "/ProgettoS3_v1";
    public static final String username = "Gabriele";
    public static final String password = "password";
    public static final boolean createUser = false;


    @Override
    public void onCreate(){
        super.onCreate();

        realm.init(this);

    }
}

