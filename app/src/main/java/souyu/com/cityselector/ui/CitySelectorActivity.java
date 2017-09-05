package souyu.com.cityselector.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import souyu.com.cityselector.Bean.Country_City;
import souyu.com.cityselector.R;
import souyu.com.cityselector.adapter.TitledListAdapter;
import souyu.com.cityselector.asynctask.MyAsyacTask;
import souyu.com.cityselector.override.TitledListView;
import souyu.com.cityselector.utils.MyURL;


//用于城市的选择
public class CitySelectorActivity extends AppCompatActivity {
	private ListView lv_titleCity_ListView;
	private List<Object> listAll = new ArrayList<Object>();
	//private MyAdapter adapter; 
	private TitledListAdapter adapter;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_selector);
		//隐藏actionBar控件
		hideActionBar() ;
		
		//用于listView控件，并设置数据,设置滑动点击监听
		initListView();
		
	}


	private void initListView() {
		//设置listView控件
		lv_titleCity_ListView = (ListView) findViewById(R.id.lv_titleCity_ListView);
		//创建数据源
		
		//创建适配器
		adapter = new TitledListAdapter(listAll, CitySelectorActivity.this);
		
		//添加到适配器
		lv_titleCity_ListView.setAdapter(adapter);
		
		//开启异步任务下载数据
		new MyAsyacTask(CitySelectorActivity.this, adapter, listAll).execute(MyURL.CHOICE_CITY);
		
		//为ListView设置点击监听
		lv_titleCity_ListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//表示开始点击事件
				String cityId = ((Country_City)(listAll.get(position))).getCityid();
				String cityname = ((Country_City)(listAll.get(position))).getCityname();
				Toast.makeText(CitySelectorActivity.this, cityId+" : "+cityname,Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("cityid", cityId);
				bundle.putString("cityname", cityname);
				intent.putExtras(bundle);

				//将数据返回上一个界面
				setResult(RESULT_OK, intent);
				
				//结束本页面
				finish();
				
			}
		});
		
		//为ListView设置滑动监听
		lv_titleCity_ListView.setOnScrollListener(new OnScrollListener() {
			
			
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
			}
			
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// 第一项与第二项标题不同，说明标题需要移动  
				if (listAll.size() > 0) {
					String firString = ((Country_City)(listAll.get(firstVisibleItem))).getTitle() ;
					String secString = ((Country_City)(listAll.get(firstVisibleItem+1))).getTitle();
			        if (!firString.equals(secString)){
			            ((TitledListView) view).moveTitle();
			        } else {
			            ((TitledListView) view).updateTitle(firString);
			        }
				}
				
			}
		});
	}

	
	private void hideActionBar() {
		//获取actionBar管理者对象，隐藏
		ActionBar bar = getSupportActionBar();
		bar.hide();
	}
	
	public void click_cancel(View v){
		//表示点击了取消按钮
		finish();
	}
}
