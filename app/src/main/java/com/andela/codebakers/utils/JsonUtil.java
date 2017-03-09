package com.andela.codebakers.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ochoge on 9/21/15.
 */
public class JsonUtil {

    public static Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.addDeserializationExclusionStrategy(new SuperclassExclusionStrategy());
        builder.addSerializationExclusionStrategy(new SuperclassExclusionStrategy());
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = builder.create();
        return gson;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {

        return getGson().fromJson(json, clazz);
    }

    public static <T> String toJson(T object) {
        return getGson().toJson(object);
    }

    public static <T> List<T> fromJsonAsList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<T>>() {
        }.getType();
        ArrayList<T> list = getGson().fromJson(json, type);
        return list;
    }

    public static <T> List<T> fromJsonStringToList(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr);
    }

    public static <T> Map<String, T> fromJsonAsMap(String json, Class<T> clazz) {
        Type type = new TypeToken<HashMap<String, T>>() {
        }.getType();
        Map<String, T> map = getGson().fromJson(json, type);
        return map;
    }

    public static <T, S> T convert(S object, Class<T> tClass) {
        String json = toJson(object);
        T expected = fromJson(json, tClass);
        return expected;
    }
}


