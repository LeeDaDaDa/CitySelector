package souyu.com.cityselector.asynctask;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import souyu.com.cityselector.adapter.TitledListAdapter;
import souyu.com.cityselector.utils.HttpUtils;
import souyu.com.cityselector.utils.JsonUtils;


public class MyAsyacTask extends AsyncTask<String, Void, List<Object>>{
	
	private Context context ;
	private TitledListAdapter adapter;
	private List<Object> listAll;
    public MyAsyacTask(Context context,TitledListAdapter adapter,List<Object> listAll){
    	this.context = context ;
    	this.adapter = adapter ;
    	this.listAll = listAll ;
    }
	
	protected List<Object> doInBackground(String... params) {
		//开始网络下载数据
		if (HttpUtils.isConnection(context)) { //表示网络连通
			byte[] dataFromInternet = HttpUtils.getDataFromInternet(params[0]);
			String json = new String(dataFromInternet);
			
			List<Object> list2 = new ArrayList<Object>();
			list2 = JsonUtils.getJsonDataFromByte(json);
			return list2 ;
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(List<Object> result) {
		//表示获取到了数据
	    if(result != null){
	    	listAll.addAll(result);
	    	adapter.notifyDataSetChanged() ;
	    }else {
			Toast.makeText(context, "没有获取到数据", Toast.LENGTH_SHORT).show() ;
		} 	
	
	}

}
