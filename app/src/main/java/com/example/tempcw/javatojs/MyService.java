package com.example.tempcw.javatojs;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by TempCw on 2016/7/27.
 */
public class MyService extends Service {
    // LocalBroadcastManager localBroadcastManager;
    private Context context;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        //  localBroadcastManager.sendBroadcast(intent1);
        return new Mybinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "服务一启动", Toast.LENGTH_SHORT).show();


//        IntentFilter mIntentFilter = new IntentFilter();
//        mIntentFilter.addAction("static_gb");
//        static_guangbo myBroadCastReceiver=new static_guangbo();
//        //注册广播
//        registerReceiver(myBroadCastReceiver, mIntentFilter);

//        Intent intent1 = new Intent("static_gb");  //动态广播
//        intent1.putExtra("ljc", "bill jean not my lover服务启动的广播另一个0");
//        //   Intent intent=new Intent("static_gb");
//        //   intent.putExtra("ljc","bill jean not my lover静态广播");
//        sendBroadcast(intent1);
    }
   public class Mybinder extends Binder implements IService {
//       Context mContext;
//        public MyService getService(){
//            return MyService.this;
//        }

        public void method() {
           TestMethod();

        }

    }
    public void TestMethod(){
        Toast.makeText(this,"调用服务里的方法",Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public boolean onUnbind(Intent intent) {
//        return super.onUnbind(intent);
//    }
    //    class Mycon implements ServiceConnection{
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder binder) {
//
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    }


    //1.定义一个广播接受类
//    private BroadcastReceiver myBroadCastReceiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (action.equals("ljc")) {
//                String lover = intent.getStringExtra("ljc");
//                Toast.makeText(MyService.this, lover, Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    };
}