package com.example.mohamed.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.mohamed.bakingapp.models.Ingredient;
import com.github.vipulasri.timelineview.TimelineView;
import com.example.mohamed.bakingapp.databinding.IngredientListItemBinding;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    private Context context;
    private List<Ingredient> ingredients;

    public IngredientsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        IngredientListItemBinding itemBinding = IngredientListItemBinding.inflate(inflater, parent, false);
        return new IngredientViewHolder(itemBinding, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.bind(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        if (ingredients == null) return 0;
        return ingredients.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }
    public void updateIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        private TimelineView timelineView;
        private IngredientListItemBinding itemBinding;

        public IngredientViewHolder(IngredientListItemBinding itemBinding, int viewType) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;

            timelineView = itemBinding.ingredientTimeline;
            timelineView.initLine(viewType);
        }

        public void bind(Ingredient ingredient) {
            itemBinding.setIngredient(ingredient);
            itemBinding.executePendingBindings();
        }
    }


}
