package souyu.com.cityselector.ui;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import souyu.com.cityselector.R;

/**
 * 这个类使用类使用欢迎界面的一个展示页面的
 * @author Administrator
 *
 */
public class WelcomeActivity extends Activity {
    //创建handle对象，用于定时处理
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				//调用意图跳转的界面，跳到要操作的主的activity类正
				welcomeFunction();
				break;

			default:
				break;
			}
		}

		;
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//获取当前窗体
		final Window window = getWindow();
		//隐藏状态栏
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		//调用方法，展示程序的页面2秒钟
		welcomeUI();
	}

	private void welcomeFunction() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
		
	}
	
	
	private void welcomeUI() {
		//创建一个子线程
		new Thread(new Runnable() {
			
			
			public void run() {
				try {
					Thread.sleep(2000);
					handler.sendEmptyMessage(1); //睡眠两秒，发送一个空的消息
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

}
