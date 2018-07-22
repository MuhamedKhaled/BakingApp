package com.example.mohamed.bakingapp.widgets;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.models.Ingredient;
import com.example.mohamed.bakingapp.models.Recipe;
import com.google.gson.Gson;


public class RecipeIngredientsListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    public static final String WIDGET_PREFS_NAME = "WidgetPrefs";
    public static final String WIDGET_RECIPE_KEY = "widgetRecipeKey";

    private Context context;
    private Recipe recipe;

    public RecipeIngredientsListRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() { }

    @Override
    public void onDataSetChanged() {
        String json = context.getSharedPreferences(WIDGET_PREFS_NAME, Context.MODE_PRIVATE)
                .getString(WIDGET_RECIPE_KEY, null);
        Gson gson = new Gson();
        this.recipe = gson.fromJson(json, Recipe.class);
    }

    @Override
    public void onDestroy() { }

    @Override
    public int getCount() {
        if (recipe == null) return 0;
        return recipe.ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ingredient_list_item);
        Ingredient ingredient = recipe.ingredients.get(position);

        views.setTextViewText(R.id.widgetIngredientName, ingredient.ingredient);
        views.setTextViewText(R.id.widgetIngredientQuantity, ingredient.getFormattedQuantity());
        views.setTextViewText(R.id.widgetIngredientMeasure, ingredient.measure);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
