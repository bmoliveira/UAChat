package vc.faber.uachat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by bruno on 28/03/16.
 */
public class UAChatApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    // Init firebase
    // Before any call to firebase you must set the context
    // here is the safest place
    Firebase.setAndroidContext(this);
  }
}
