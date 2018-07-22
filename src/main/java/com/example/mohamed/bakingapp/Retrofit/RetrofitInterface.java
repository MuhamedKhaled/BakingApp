package com.example.mohamed.bakingapp.Retrofit;

import com.example.mohamed.bakingapp.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mohamed.
 */

public interface RetrofitInterface {



    @GET("android-baking-app-json")
    Call<List<Recipe>> getRecipes();

}
