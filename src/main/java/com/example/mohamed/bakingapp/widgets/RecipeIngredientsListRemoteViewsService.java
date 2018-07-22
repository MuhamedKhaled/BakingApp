package com.example.mohamed.bakingapp.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;


public class RecipeIngredientsListRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeIngredientsListRemoteViewsFactory(this.getApplicationContext());
    }
}
