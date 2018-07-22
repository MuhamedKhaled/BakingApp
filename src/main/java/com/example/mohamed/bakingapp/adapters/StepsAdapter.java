package com.example.mohamed.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.fragments.StepsListFragment;
import com.example.mohamed.bakingapp.models.Step;

import java.util.ArrayList;
import java.util.Locale;


public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {
    private Context context;
    private StepsListFragment.OnStepSelectedListener listener;
    private ArrayList<Step> steps;

    public StepsAdapter(Context context, StepsListFragment.OnStepSelectedListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void updateSteps(ArrayList<Step> newSteps) {
        this.steps = newSteps;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.steps_list_item, parent, false);
        return new StepViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        holder.bind(steps.get(position));
    }

    @Override
    public int getItemCount() {
        if (steps == null) return 0;
        return steps.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView stepShortDescription;

        public StepViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.stepShortDescription = itemView.findViewById(R.id.stepItemShortDescription);
        }

        public void bind(Step step) {
            this.stepShortDescription.setText(String.format(Locale.US, "%d. %s", step.id, step.shortDescription));
        }

        @Override
        public void onClick(View v) {
            listener.onStepSelected(steps.get(getAdapterPosition()));
        }
    }
}
