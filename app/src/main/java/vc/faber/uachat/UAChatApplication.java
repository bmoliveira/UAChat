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
    Firebase.setAndroidContext(this);
  }
}
