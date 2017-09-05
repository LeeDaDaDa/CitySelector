package souyu.com.cityselector.adapter;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import souyu.com.cityselector.Bean.Country_City;
import souyu.com.cityselector.Bean.HotCity;
import souyu.com.cityselector.R;


public class MyAdapter extends BaseAdapter{
     private List<Object> listAll ;
     private Context context ;
     public MyAdapter(List<Object> listAll,Context context){
    	 this.listAll = listAll ;
    	 this.context  = context;
     }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listAll.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listAll.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder ;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.city_list_title, null);
			holder = new ViewHolder() ;
			holder.tv_cityselect_listview_title_text = (TextView) convertView.findViewById(R.id.lv_titleCity_ListView);
		   convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		//设置数据源
		Object object = listAll.get(position);
		if (object instanceof Country_City) {
			Country_City country_City = (Country_City)object;
			holder.tv_cityselect_listview_title_text.setText(country_City.getCityname());
		}else if (object instanceof HotCity) {
			HotCity hotCity = (HotCity)object;
			holder.tv_cityselect_listview_title_text.setText(hotCity.getCityname());
		}

		
		return convertView;
	}
   
	//启动异步任务
	
	public class ViewHolder{
		TextView tv_cityselect_listview_title_text ;
	}
}
