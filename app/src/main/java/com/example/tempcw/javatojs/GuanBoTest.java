package com.example.tempcw.javatojs;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by TempCw on 2016/7/26.
 */
public class GuanBoTest extends Activity {
    private  final String MY_GUANBO ="ljc";
    private Button btn;
    private LocalBroadcastManager localBroadcastManager;
    Intent intent;
    private IService iService=null;
//    A a = new A();
//    A.B ab = a.new B();
//    MyService myService=new MyService();
//    IService iService=myService.new Mybinder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go_test_layout);
        btn= (Button) findViewById(R.id.btn);
 //       registerBoradcastReceiver();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动服务发送广播
                //第一种方式
//                Intent intent=new Intent(GuanBoTest.this,MyService.class);
//                startService(intent);
//                //第二种方式
//                Intent intent=new Intent(GuanBoTest.this,MyService.class);
//                bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
                //第三种方式  混合方式
                 intent=new Intent(GuanBoTest.this,MyService.class);
                startService(intent);
                bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
               // iService.method();
//                Intent intent=new Intent(MY_GUANBO);  //动态广播
//                intent.putExtra("ljc","bill jean not my lover动态广播");
//             //   Intent intent=new Intent("static_gb");
//             //   intent.putExtra("ljc","bill jean not my lover静态广播");
//                // sendBroadcast(intent);
//                localBroadcastManager.sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onDestroy() {
      //  unbindService(serviceConnection);

        //混合方式停止服务
        unbindService(serviceConnection);  //解除绑定
//        stopService(intent);
//        serviceConnection=null;
//        intent=null;

        super.onDestroy();
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
             iService= (IService) service;
            iService.method();

//            MyService.Mybinder mybinder= (MyService.Mybinder) service;
//            MyService myService=mybinder.getService();
//            myService.TestMethod();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



    //1.定义一个广播接受类
    private  BroadcastReceiver myBroadCastReceiver =new  BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if (action.equals(MY_GUANBO)){
                String lover=intent.getStringExtra("ljc");
                Toast.makeText(GuanBoTest.this,lover,Toast.LENGTH_SHORT).show();
            }
        }
    };
    //2、注册该广播
    public void registerBoradcastReceiver(){
        IntentFilter mIntentFilter=new IntentFilter();
        mIntentFilter.addAction(MY_GUANBO);
        //注册广播
        registerReceiver(myBroadCastReceiver,mIntentFilter);
        //注册应用内广播接收器
//         localBroadcastManager=LocalBroadcastManager.getInstance(this);
//        localBroadcastManager.registerReceiver(myBroadCastReceiver,mIntentFilter);
        //取消注册
        //localBroadcastManager.unregisterReceiver(myBroadCastReceiver);
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
