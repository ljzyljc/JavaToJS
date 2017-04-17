package com.example.tempcw.javatojs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

/**
 * Created by TempCw on 2016/7/22.
 */
@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
public class WebUIClass extends AppCompatActivity {
    private String url;
    private WebView webView;
    private IWXAPI api;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        setContentView(R.layout.web_layout);
        setupView();
        api = WXAPIFactory.createWXAPI(this, "wx71d182065bbc7dd0");
     //   webView.loadUrl("javascript: showFromHtml()");
        Toast.makeText(this, "clickBtn", Toast.LENGTH_SHORT).show();
    }

    private void setupView() {
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
       // webView.loadUrl(url);
        webView.addJavascriptInterface(new DemoJavaScripteInterface(),"andrroid");
        webView.loadUrl("file:///android_asset/indexx.html");
      //  webView.loadUrl("https://zhfw.cyga.gov.cn/Paypage_H5/PayPage_H5/Index?ID=8aaa6180-162e-4618-b669-3b8cfebd947e");
     //   webView.addJavascriptInterface(getHtmlObject(), "jsObj");

    }
     class DemoJavaScripteInterface{
         DemoJavaScripteInterface(){};
        @JavascriptInterface
        public void clickOnAndroid(){
            String url = "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";

            Toast.makeText(WebUIClass.this, "获取订单中...", Toast.LENGTH_SHORT).show();
            try{
                byte[] buf = Util.httpGet(url);
                if (buf != null && buf.length > 0) {
                    String content = new String(buf);
                    //    Log.e("get server pay params:",content);
                    JSONObject json = new JSONObject(content);
                    if (null != json && !json.has("retcode")) {
                        PayReq req = new PayReq();
                        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                        req.appId = json.getString("appid");
                        req.partnerId = json.getString("partnerid");
                        req.prepayId = json.getString("prepayid");
                        req.nonceStr = json.getString("noncestr");
                        req.timeStamp = json.getString("timestamp");
                        req.packageValue = json.getString("package");
                        req.sign = json.getString("sign");
                        req.extData = "app data"; // optional
                        Toast.makeText(WebUIClass.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                        api.sendReq(req);
                    }
                }
                    }catch(Exception e){

                    }


//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                   // webView.loadUrl("javascript:showFromHtml()");
//                    Toast.makeText(WebUIClass.this,"调用安卓方法",Toast.LENGTH_SHORT).show();
//                }
//            });

        }
    }

//    private Object getHtmlObject(){
//        Object insertObj = new Object(){
//            @JavascriptInterface
//            public String HtmlcallJava(){
//                return "Html call Java";
//            }
//            @JavascriptInterface
//            public String HtmlcallJava2(final String param){
//                return "Html call Java : " + param;
//            }
//            public void JavacallHtml(){
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        webView.loadUrl("javascript: showFromHtml()");
//                        Toast.makeText(WebUIClass.this, "clickBtn", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//            public void JavacallHtml2(){
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        webView.loadUrl("javascript: showFromHtml2('IT-homer blog')");
//                        Toast.makeText(WebUIClass.this, "clickBtn2", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        };
//
//        return insertObj;
//    }
}