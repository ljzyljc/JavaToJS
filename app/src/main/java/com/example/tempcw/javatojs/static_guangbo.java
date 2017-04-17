package com.example.tempcw.javatojs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by TempCw on 2016/7/26.
 */
public class static_guangbo extends BroadcastReceiver {
    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("static_gb")){
            String lover=intent.getStringExtra("ljc");
            Toast.makeText(context,lover+"00-",Toast.LENGTH_SHORT).show();
        }
    }
//    1 //registerReceiver(mBroadcastReceiver, intentFilter);
//            2 //注册应用内广播接收器
//            3 localBroadcastManager = LocalBroadcastManager.getInstance(this);
//    4 localBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
//    5
//            6 //unregisterReceiver(mBroadcastReceiver);
//            7 //取消注册应用内广播接收器
//            8 localBroadcastManager.unregisterReceiver(mBroadcastReceiver);
//    9
//            10 Intent intent = new Intent();
//    11 intent.setAction(BROADCAST_ACTION);
//    12 intent.putExtra("name", "qqyumidi");
//    13 //sendBroadcast(intent);
//            14 //发送应用内广播
//            15 localBroadcastManager.sendBroadcast(intent)
}
