package com.example.myapp;

  import android.content.BroadcastReceiver;
  import android.content.Context;
  import android.content.Intent;
  import android.os.Vibrator;
  import android.view.Gravity;
  import android.widget.Toast;

  public class MyReceiver extends BroadcastReceiver {
      @Override
      public void onReceive(Context context, Intent intent) {
          Toast toast = Toast.makeText(context, "Hei time up!!!!.", Toast.LENGTH_LONG);
          //toast.setDuration();
          toast.setGravity(Gravity.CENTER, 0, 0);
          toast.show();

          Vibrator vibrator=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
          vibrator.vibrate(1000);


      }
  }