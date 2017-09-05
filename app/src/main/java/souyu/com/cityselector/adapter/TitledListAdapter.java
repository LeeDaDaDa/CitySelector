package souyu.com.cityselector.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import souyu.com.cityselector.Bean.Country_City;
import souyu.com.cityselector.R;


public class TitledListAdapter extends BaseAdapter {
		
	 private List<Object> listAll ;
     private Context context ;
     public TitledListAdapter(List<Object> listAll,Context context){
    	 this.listAll = listAll ;
    	 this.context  = context;
     }

	    @Override
	    public int getCount() {
	        return listAll.size();
	    }

	    @Override
	    public Object getItem(int position) {
	        return listAll.get(position);
	    }

	    @Override
	    public long getItemId(int position) {
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        LayoutInflater inflater = LayoutInflater.from(context);
	        View view = inflater.inflate(R.layout.city_list_item, null);
	        TextView content = (TextView) view.findViewById(R.id.tv_cityselect__listview_title_content);
	        TextView title = (TextView) view.findViewById(R.id.tv_cityselect__listview_title);
	        
	        
	        Object object = listAll.get(position);
	        
	        
			Country_City country_City = (Country_City)object;
			title.setText(country_City.getTitle());
			content.setText(country_City.getCityname());
			

	        //第一项和前后不同的项需要显示标题，否则隐藏
	        if (position == 0) {
	            title.setVisibility(View.VISIBLE);
	        } else if (position < getCount() && !country_City.getTitle().equals(((Country_City)(listAll.get(position - 1))).getTitle())) {
	            title.setVisibility(View.VISIBLE);
	        } else {
	            title.setVisibility(View.GONE);
	        }
	        
	        return view;
	    }

	}

