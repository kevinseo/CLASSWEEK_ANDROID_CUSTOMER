package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.payment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.blackpigstudio.classweek.R;

import org.apache.http.util.EncodingUtils;

import java.net.URISyntaxException;

public class PaymentWebViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_web_view);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new SampleWebView());
        myWebView.getSettings().setJavaScriptEnabled(true);

        String postData =
                "P_MID=CAEblac956&" +
                        "P_OID=20140417-0000184&" +
                        "P_AMT=123&" +
                        "P_UNAME=ing&" +
                        "P_NOTI=asdjlas&" +
                        "P_NEXT_URL=http://m.classweek.kr/Pay/Recv/inicis/SmartPhonePayReceivePage.php&" +
//                        "P_NOTI_URL=http://www.classweek.kr/pay_result.html&" +
//                        "P_RETURN_URL=http://m.classweek.kr/api/shop/pgsuccess&" +
                        "P_GOODS=asdasd&" +
                        "P_MOBILE=010-4527-9272&" +
                        "P_EMAIL=continueing@gmail.com&" +
                        "P_HPP_METHOD=1&" +
                        "paymethod=wcard&" +
                        "inipaymobile_type=web" ;
        String url = "https://mobile.inicis.com/smart/wcard/";
//        myWebView.loadUrl("http://naver.com");
        myWebView.postUrl(url, EncodingUtils.getBytes(postData, "BASE64"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.payment_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class SampleWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {


            /*
	    	 * URL별로 분기가 필요합니다. 어플리케이션을 로딩하는것과
	    	 * WEB PAGE를 로딩하는것을 분리 하여 처리해야 합니다.
	    	 * 만일 가맹점 특정 어플 URL이 들어온다면
	    	 * 조건을 더 추가하여 처리해 주십시요.
	    	 */
            if( !url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("javascript:") )
            {
                Log.e("if", url);
                Intent intent;

                try{
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    Log.e("<INICIS_TEST>", "intent getDataString : " + intent.getDataString());
                } catch (URISyntaxException ex) {
                    Log.e("<INICIS_TEST>", "URI syntax error : " + url + ":" + ex.getMessage());
                    return false;


                }

                Uri uri = Uri.parse(intent.getDataString());
                intent = new Intent(Intent.ACTION_VIEW, uri);

                try{

                    startActivity(intent);

                    /*가맹점의 사정에 따라 현재 화면을 종료하지 않아도 됩니다.
	    			    삼성카드 기타 안심클릭에서는 종료되면 안되기 때문에
	    			    조건을 걸어 종료하도록 하였습니다.*/
                    if( url.startsWith("ispmobile://"))
                    {
                        finish();
                    }

                }catch(ActivityNotFoundException e)
                {
                /* ISP어플이 현재 폰에 없다면 아래 if 구문에서 처리 합니다.
	    			 * 삼성카드 및 기타 안심클릭에서는
	    			 * 카드사 웹페이지에서 알아서 처리하기때문에
	    			 * WEBVIEW에서는 별다른 처리를 하지 않아도 처리됩니다.
	    			 */
                    if( url.startsWith("ispmobile://"))
                    {
                        return false;
                    }
                }

            }
            else
            {
                Log.e("else ",  url  );
                view.loadUrl(url);
                return false;
            }

            return true;
        }





        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.e("onReceivedError",  "error code : "+errorCode+ "\n description: " + description + "\n failingUrl: "+ failingUrl );

        }
    }

}
