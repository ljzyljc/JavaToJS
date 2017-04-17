package com.example.tempcw.javatojs;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button = null;
    Button screenShot = null;
    ImageView imageView;
    Bitmap bitmap = null;
    String picName = "abc.png";
    RelativeLayout myLayout;
    private int width;
    private int height;
    int statusBarHeights;
    private static final String IMAGE_FILE_LOCATION = "file:///sdcard/0ayPic/ljc.png";//temp file
    Uri imageUri = null;
    private static final int CHOOSE_SMALL_PICTURE = 123;


    //    private Handler myHandler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            jieTu();
//        }
//    };
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageUri = Uri.parse(IMAGE_FILE_LOCATION);//The Uri to store the big bitmap

        myLayout = (RelativeLayout) findViewById(R.id.myLayout);
        WindowManager wm = this.getWindowManager();
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        View viewScreen = getWindow().getDecorView();
        // 获取状态栏高度
        Rect rect = new Rect();
        viewScreen.getWindowVisibleDisplayFrame(rect);
        statusBarHeights = rect.top;
        Log.d("jc", statusBarHeights + "");

        button = (Button) findViewById(R.id.jumpto);
        imageView = (ImageView) findViewById(R.id.Img);
        button.setOnClickListener(this);
        screenShot = (Button) findViewById(R.id.screenShot);

        screenShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jieTu();
                //  第一种方案：截屏之前你可以把状态栏去掉，然后再截屏    第二种方案：截屏之前可以将状态栏的颜色设置为透明或者和当前标题栏颜色相同，然后再截屏
//                getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
//                        WindowManager.LayoutParams. FLAG_FULLSCREEN);
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);   //全屏
//                Message msg=Message.obtain();
//                msg.obj="abc";
//                myHandler.sendMessageDelayed(msg,1000);

                //打开相册来选取图片截图
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
//                intent.setType("image/*");
//                intent.putExtra("crop", "true");
//
//                intent.putExtra("aspectX", 2);
//
//                intent.putExtra("aspectY", 1);
//
//                intent.putExtra("outputX", 200);
//
//                intent.putExtra("outputY", 100);
//
//                intent.putExtra("scale", true);
//
//                intent.putExtra("return-data", true);
//
//                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//
//                intent.putExtra("noFaceDetection", true); // no face detection
//
//                startActivityForResult(intent, CHOOSE_SMALL_PICTURE);


                //   saveBitmap();
                //    saveMyBitmap("00jackey",bitmap);   //可以使用
                //      saveMyBitmap(bitmap,"0123jc");        //  可以使用

            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//    switch (requestCode) {
//        case CHOOSE_SMALL_PICTURE:
//            if(data != null){
//                Bitmap bitmap = data.getParcelableExtra("data");
//                imageView.setImageBitmap(bitmap);
//            }else{
//                Log.i("jc", "CHOOSE_SMALL_PICTURE: data = " + data);
//            }
//            break;
//        default:
//            break;
//    }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    //    private Bitmap decodeUriAsBitmap(Uri uri){
//
//        Bitmap bitmap = null;
//
//        try {
//
//            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
//
//        } catch (FileNotFoundException e) {
//
//            e.printStackTrace();
//
//            return null;
//
//        }
//
//        return bitmap;
//
//    }
    //顶部的图片
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public Bitmap createMyBitMap() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test11);      //图片宽度540左右最合适
        //  Bitmap.createBitmap(bitmap,0,0,width,bitmap.getHeight());

        return bitmap;
    }

    //拼接两张图片
    public Bitmap add2Bitmap(Bitmap one, Bitmap two) {
        int width = Math.max(0, two.getWidth());
        int height = one.getHeight() + two.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(one, 0, 0, null);

        canvas.drawBitmap(two, 0, one.getHeight(), null);
        return result;
    }
    //底部的图片
    public Bitmap ImageCrop(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽  720                     y + height must be <= bitmap.height()
        Log.d("jc", "屏幕的高度" + width + "==========图片高度" + w);
        int h = bitmap.getHeight();  //1280
        //  int wh = w > h ? h : w;// 裁切后所取的正方形区域边长
        int widthX = width;              //要截图的宽度
        int heightY = height - 61;       //要截图的高度

//    int retX = w > h ? (w - h) / 2 : 0;//基于原图，取正方形左上角x坐标
//    int retY = w > h ? 0 : (h - w) / 2;
        //下面这句是关键
//    Bitmap source：要从中截图的原始位图
//    int x:  起始x坐标
//    int y：起始y坐标
//    int width：  要截的图的宽度
//    int height：要截的图的高度
        return Bitmap.createBitmap(bitmap, 0, 61, widthX, heightY, null, false);
    }


    //截图方法       ====================第一种==========================
    public void jieTu() {
        View viewScreen = getWindow().getDecorView();
        // 获取状态栏高度
        Rect rect = new Rect();
        viewScreen.getWindowVisibleDisplayFrame(rect);
        int statusBarHeights = rect.top;

        viewScreen.setDrawingCacheEnabled(true);
        viewScreen.buildDrawingCache();
        bitmap = Bitmap.createBitmap(viewScreen.getDrawingCache(), 0, 0, width, height);
        //  add2Bitmap(createMyBitMap(),ImageCrop(bitmap));
        String path = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File dir = new File(path + "/0ayPic/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File f1 = new File(path + "/0ayPic/", "ljc.png");
            try {
                f1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileOutputStream fos = new FileOutputStream(f1);
                add2Bitmap(createMyBitMap(), ImageCrop(bitmap)).compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
                //    }

            } catch (Exception e) {
                e.printStackTrace();
            }
            //   viewScreen.destroyDrawingCache();
            imageView.setImageBitmap(add2Bitmap(createMyBitMap(), ImageCrop(bitmap)));
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //   getWindow().setFlags(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW,WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);   //在中兴手机上有点问题

            //     getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

            //   getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            //    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//            getWindow().setFlags(WindowManager.LayoutParams. FLAG_LAYOUT_NO_LIMITS ,
//                    WindowManager.LayoutParams. FLAG_LAYOUT_NO_LIMITS);
            //FIRST_APPLICATION_WINDOW   FLAG_FORCE_NOT_FULLSCREEN
        }


    }


    //飞奔的鸵鸟
    public void saveMyBitmap(Bitmap mBitmap, String bitName) {
        File f = new File("/sdcard/Note/");
        if (!f.exists()) {
            f.mkdirs();
        }
        File f1 = new File("/sdcard/Note/", bitName + ".jpg");
        try {
            f1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveMyBitmap(String bitName, Bitmap mBitmap) {
        File f = new File("/sdcard/0123f/");
        if (!f.exists()) {
            f.mkdirs();
        }

        File f1 = new File("/sdcard/0123f/", bitName + ".png");
        try {
            f1.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveBitmap() {
        FileOutputStream out = null;
        File f = new File("/sdcard/0namecard/", picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            out = new FileOutputStream(f);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, WebUIClass.class);
        intent.putExtra("url", "www.baidu.com");
        startActivity(intent);
        //var str = window.jsObj.HtmlcallJava();
    }

}
