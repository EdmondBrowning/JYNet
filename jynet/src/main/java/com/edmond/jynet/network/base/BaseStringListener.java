package com.edmond.jynet.network.base;

import android.util.Log;

import com.edmond.jynet.implement.StringListenerInterface;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

/**
 * Created by edmond on 16-9-27.
 */

public abstract class BaseStringListener<T> implements StringListenerInterface<T> {
    @Override
    public void format(int what,String response) {
        Log.d("TAg",response);
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        Class<T> tClass= (Class<T>)(parameterizedType.getActualTypeArguments()[0]);
        Gson gson = new Gson();
        T object = gson.fromJson(response,tClass);
        onSucceed(what,object);
    }
}
