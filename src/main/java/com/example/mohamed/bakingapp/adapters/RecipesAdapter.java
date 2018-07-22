package com.example.mohamed.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private Context context;
    private OnRecipeSelectedListener listener;
    private List<Recipe> recipes;

    public RecipesAdapter(Context context, OnRecipeSelectedListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(context)
                        .inflate(R.layout.recipe_list_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bind(recipes.get(position));

    }

    @Override
    public int getItemCount() {
        if (recipes == null) return 0;
        return recipes.size();
    }

    public void updateData(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private AppCompatTextView recipeName;
        private ImageView recipeImage;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            recipeName = itemView.findViewById(R.id.recipeName);
            recipeImage = itemView.findViewById(R.id.recipeImage);
        }


        @Override
        public void onClick(View v) {
            listener.onRecipeSelected(recipes.get(getAdapterPosition()));
        }

        public void bind(Recipe recipe) {
            this.recipeName.setText(recipe.recipeName);
            Picasso.get()
                    .load(recipe.imageUrl)
                    .placeholder(R.drawable.place_holder)
                    .into(this.recipeImage);
        }
    }


    public interface OnRecipeSelectedListener {
        void onRecipeSelected(Recipe recipe);
    }

}
