package com.giaca.antonino.ninoeat.ui.activities.adapters;

import android.arch.persistence.room.TypeConverter;

import com.giaca.antonino.ninoeat.ui.activities.Shop;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by anton on 25/02/2019.
 */

public class ProductTypeConverter {
    private static Gson gson=new Gson();



    public static List<Shop> stringToProductList(String data){

        if(data==null){
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Shop>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String productListToString(List<Shop> someObjects) {
        return gson.toJson(someObjects);
    }
}
