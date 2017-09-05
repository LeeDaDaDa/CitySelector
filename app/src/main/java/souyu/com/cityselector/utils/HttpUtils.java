package souyu.com.cityselector.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import android.R.integer;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.StaticLayout;
import android.util.Log;

public class HttpUtils {

	//1锛岃幏鍙栫綉缁滄槸鍚﹂摼鎺�
	public static boolean isConnection(Context context){
		//鑾峰彇绯荤粺閾炬帴鏈嶅姟
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		//鑾峰彇缃戠粶閾炬帴鐨勪俊鎭�
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		
		if(networkInfo != null){
			return networkInfo.isConnected();
		}
		return false;
	} 
	
	//2锛屽紑濮嬭幏鍙栫綉缁滄暟鎹�===========================杩欐槸HttpURLConnection绫荤殑涓�绉嶈姹傛柟寮忥紙鎺ㄨ崘浣跨敤锛�==================================
	public static byte[] getDataFromInternet(String baseUrl){
		try {
			//璁剧疆閾炬帴
			 HttpURLConnection connection =  (HttpURLConnection) new URL(baseUrl).openConnection();
			 //璁剧疆鍩烘湰鐨勯摼鎺ヤ俊鎭�
			 connection.setRequestMethod("GET");
			 connection.setConnectTimeout(5000);
			 connection.setDoInput(true);
			 //鍒ゆ柇璇锋眰鐮�
			 if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
				 //閫氳繃娴佽鍙栨暟鎹�
				 InputStream inputStream = connection.getInputStream();
				 ByteArrayOutputStream bos = new ByteArrayOutputStream();
				 byte[] buffer = new byte[1024];
				 int len = 0 ;
				 while((len = inputStream.read(buffer)) != -1){
					 bos.write(buffer, 0, len);
				 }
				return bos.toByteArray();
			 }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	//==========================================杩欐槸涓�绉岺ttpClient绫诲瀷鐨勮姹�==========================================
//	public static byte[] getHttpResult(String imagePath){
//		byte[] buff=null;
//		HttpClient httpClient=new DefaultHttpClient();
//		HttpGet httpGet=new HttpGet(imagePath);
//		try {
//			HttpResponse response=httpClient.execute(httpGet);
//			if(response.getStatusLine().getStatusCode()==200){
//				HttpEntity entity=response.getEntity();
//				buff=EntityUtils.toByteArray(entity);
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return buff;
//	}
}
