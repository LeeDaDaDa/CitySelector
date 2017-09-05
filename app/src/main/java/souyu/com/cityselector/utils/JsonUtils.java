package souyu.com.cityselector.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import souyu.com.cityselector.Bean.Country_City;


public class JsonUtils {
	
	private static String[] cityTag = {"A","B","C","D","E","F","G","H","J","K","L","M","N",
		   "P","Q","R","S","T","W","X","Y","Z"};
	//用于解析出看房项目的全国各个城市的城市名称
	public static List<Object> getJsonDataFromByte(String json){
		List<Object> list1 = new ArrayList<Object>();
		//开始解析json数据
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONObject jsonObject2 = jsonObject.getJSONObject("cities");
		    
			//开始解析每个城市
			
			//获取热门城市
			JSONArray jsonArray_hotcities = jsonObject2.getJSONArray("hotcities");
			for(int i = 0 ; i < jsonArray_hotcities.length() ; i++  ){
				
				//HotCity hotCity = new HotCity();
				Country_City country_City = new Country_City();
				JSONObject jsonObject3 = jsonArray_hotcities.getJSONObject(i);
				country_City.setTitle("热门城市");
				country_City.setCityid(jsonObject3.getString("cityid"));
				country_City.setCityname(jsonObject3.getString("cityname"));
				//存入list集合
				list1.add(country_City);
			}
			
			//解析A城市的数据(获取字段：cityname，cityid)
			for (int j = 0; j < cityTag.length; j++) {
				JSONArray jsonArray_a = jsonObject2.getJSONArray(cityTag[j]);
				for(int i = 0 ; i < jsonArray_a.length() ; i++  ){
					Country_City country_City = new Country_City();
					JSONObject jsonObject4 = jsonArray_a.getJSONObject(i);
					country_City.setTitle(cityTag[j]);
					country_City.setCityid(jsonObject4.getString("cityid"));
					country_City.setCityname(jsonObject4.getString("cityname"));
					//存入list集合
					list1.add(country_City);
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("=======eww========"+list1.size());
		
		return list1;
		
	}
	
	
	
   
}
