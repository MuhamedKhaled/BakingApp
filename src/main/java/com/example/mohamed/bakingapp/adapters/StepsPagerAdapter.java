package com.example.mohamed.bakingapp.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mohamed.bakingapp.fragments.StepFragment;
import com.example.mohamed.bakingapp.models.Step;

import java.util.List;


public class StepsPagerAdapter extends FragmentStatePagerAdapter {
    private List<Step> steps;

    public StepsPagerAdapter(FragmentManager fm, List<Step> steps) {
        super(fm);
        this.steps = steps;
    }

    @Override
    public StepFragment getItem(int position) {
        return StepFragment.newInstance(steps.get(position));
    }

    @Override
    public int getCount() {
        return steps.size();
    }
}
