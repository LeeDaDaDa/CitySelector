package souyu.com.cityselector.ui;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import souyu.com.cityselector.R;

public class MainActivity extends AppCompatActivity {


    private TextView tv_main_city;
    private static final int REQUESTCODE = 1;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//隐藏ActionBar
        hideActionBar();

        //初始化控件
        initView();


    }

    public void click_city(View v) {

        //为tv_main_city控件添加点击事件,进行意图的跳转，
        Intent intent = new Intent(MainActivity.this, CitySelectorActivity.class);
        startActivityForResult(intent, REQUESTCODE);

    }
    private void initView() {
        tv_main_city = (TextView) findViewById(R.id.tv_main_textView_city);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUESTCODE&& resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String cityname = bundle.getString("cityname");
            tv_main_city.setText(cityname);
        }

    }


    private void hideActionBar() {
        //获取actionBar管理者对象，隐藏

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.hide();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
