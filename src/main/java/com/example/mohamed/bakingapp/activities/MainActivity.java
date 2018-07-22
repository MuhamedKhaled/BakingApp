package com.example.mohamed.bakingapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.Retrofit.RetrofitInterface;
import com.example.mohamed.bakingapp.Retrofit.ServiceGenerator;
import com.example.mohamed.bakingapp.adapters.RecipesAdapter;
import com.example.mohamed.bakingapp.models.Recipe;
import com.example.mohamed.bakingapp.models.RecipeImage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.OnRecipeSelectedListener {

    @BindView(R.id.recipesRecyclerView)
    RecyclerView recipesRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private RecipesAdapter adapter;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new RecipesAdapter(this,this);
        recipesRecyclerView.setHasFixedSize(true);

        boolean useGridLayout = getResources().getBoolean(R.bool.useGridLayout);
        if (useGridLayout) {
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            recipesRecyclerView.setLayoutManager(manager);
        } else {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recipesRecyclerView.setLayoutManager(manager);
        }

        recipesRecyclerView.setAdapter(adapter);
        updateRecipes();
    }

    private void updateRecipes() {
        final RetrofitInterface service
                = new ServiceGenerator(this).createService(RetrofitInterface.class);

        Call<List<Recipe>> call = service.getRecipes();
        Log.d("URL ", " "+call.request().url());
        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    List<Recipe>recipes = response.body();

                    List<RecipeImage>recipeImages=getAllRecipesImages();

                    for (int i = 0; i < recipeImages.size(); i++){
                        recipes.get(i).imageUrl = recipeImages.get(i).imageUrl;

                    }
                adapter.updateData(recipes);
                }
            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d("Failure---------->", t.getMessage());
                t.printStackTrace();
            }

        });

    }


   private List<RecipeImage> getAllRecipesImages(){

       Type type = new TypeToken<ArrayList<RecipeImage>>() {}.getType();
       InputStream inputStream = null;
       try {
           this.gson = new GsonBuilder().create();
           inputStream = this.getAssets().open("recipes_images.json");
           JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
           List<RecipeImage> list = gson.fromJson(reader, type);

           return list;

       } catch (IOException e) {
           e.printStackTrace();
       }

    return null;
   }

    @Override
    public void onRecipeSelected(Recipe recipe) {
        Intent intent = new Intent(this, RecepeDetailsActivity.class);
        intent.putExtra(RecepeDetailsActivity.RECIPE_EXTRA, recipe);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

