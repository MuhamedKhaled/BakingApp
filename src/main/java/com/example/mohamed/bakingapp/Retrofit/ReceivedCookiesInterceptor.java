package com.example.mohamed.bakingapp.Retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

import static com.example.mohamed.bakingapp.Retrofit.AddCookiesInterceptor.HASH_SET_COOKIE;
import static com.example.mohamed.bakingapp.Retrofit.AddCookiesInterceptor.SHARED_PREFERENCE_NAME;

/**
 * This Interceptor add all received Cookies to the app DefaultPreferences.
 * Your implementation on how to save the Cookies on the Preferences MAY VARY.
 * <p>
 *
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    Context mContext;

    public ReceivedCookiesInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());


        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
                Log.d("header: ",header);
            }

            final SharedPreferences sharedPref = mContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putStringSet(HASH_SET_COOKIE, cookies);
            editor.commit();
        }
        return originalResponse;
    }
}