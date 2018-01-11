package com.bestvike.mvphttpdaodemo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 * hqx
 */

public class JsonUtils {
    //类转json
    public static <T> String class2Json(T cls) {
        return new Gson().toJson(cls);
    }

    //json转类
    public static <T> T json2Class(String json, Class<T> cls) {
        Gson gson = new Gson();
        return gson.fromJson(json, cls);
    }

    //json转list
    public static <T> List<T> json2list(String json, Class<T> clazz) {
        List<T> lst = new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        Gson gson = new Gson();
        for (final JsonElement elem : array) {
            lst.add(gson.fromJson(elem, clazz));
        }
        return lst;
    }

    //list转json
    public static <T> String list2Json(List<T> list) {
        return new Gson().toJson(list);
    }
}
