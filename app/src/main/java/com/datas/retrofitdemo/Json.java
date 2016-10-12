package com.datas.retrofitdemo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by user on 2016/9/13.
 */
public class Json {

    public static void paresData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data =  jsonObject.getJSONObject("data");
           Iterator<String> iterator = data.keys();
            while (iterator.hasNext()){
                String key = iterator.next();
                JSONArray array = data.getJSONArray(key);
                for (int i = 0;i<array.length();i++){
                   JSONObject goods = array.getJSONObject(i);
                    Log.v("tag","goodsName:"+goods.getString("goods_name"));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
