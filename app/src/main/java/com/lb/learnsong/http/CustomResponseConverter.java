package com.lb.learnsong.http;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class CustomResponseConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private TypeAdapter<T> adapter;
    private Type mType;

    CustomResponseConverter(Gson gson, TypeAdapter<T> mAdapter, Type mType) {
        this.gson = gson;
        this.adapter = mAdapter;
        this.mType = mType;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        try {
            String body = value.string();
            JSONObject json = new JSONObject(body);
            int code = json.getInt("status");

            if (code != 0) {
                Map<String, String> map = gson.fromJson(body, new TypeToken<Map<String, String>>() {
                }.getType());
                map.put("list", null);
                body = gson.toJson(map);
            }
            return gson.fromJson(body, mType);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }

    }
}
