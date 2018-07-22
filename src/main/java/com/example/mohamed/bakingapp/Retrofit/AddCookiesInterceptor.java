package com.example.mohamed.bakingapp.Retrofit;


import android.content.Context;
import android.util.Log;



import java.io.IOException;
import java.util.HashSet;

import okhttp3.*;

/**
 * This interceptor put all the Cookies in Preferences in the Request.
 * Your implementation on how to get the Preferences MAY VARY.
 * <p>
 *
 */
public class AddCookiesInterceptor implements Interceptor {

    Context mContext;
    public final static String SHARED_PREFERENCE_NAME = "sharedPreference";
    public final static String HASH_SET_COOKIE = "cookies";

    public AddCookiesInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences
                = (HashSet) mContext.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getStringSet(HASH_SET_COOKIE, new HashSet<String>());

        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
        }

        return chain.proceed(builder.build());
    }
}