package com.example.mohamed.bakingapp.activities;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.adapters.StepsPagerAdapter;
import com.example.mohamed.bakingapp.fragments.StepFragment;
import com.example.mohamed.bakingapp.fragments.StepsListFragment;
import com.example.mohamed.bakingapp.models.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class StepsActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener,StepsListFragment.OnStepSelectedListener {

    public static final String STEPS_EXTRA = "stepsExtra";
    public static final String RECIPE_NAME_EXTRA = "recipeName";

    @Nullable
    @BindView(R.id.stepsPager)
    ViewPager stepsPager;

    @Nullable
    @BindView(R.id.stepShortDescription)
    TextView stepShortDescription;

    @Nullable
    @BindView(R.id.toolbarRecipeName)
    TextView toolbarRecipeName;

    @Nullable
    @BindView(R.id.activityStepsToolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.stepDetailsContainer)
    FrameLayout stepDetailContainer;

    private StepsPagerAdapter stepsAdapter;

    private ArrayList<Step> steps;

    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);

        steps = getIntent().getParcelableArrayListExtra(STEPS_EXTRA);

        if (toolbarRecipeName != null)
            toolbarRecipeName.setText(getIntent().getStringExtra(RECIPE_NAME_EXTRA));

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        isTwoPane = findViewById(R.id.stepsListContainer) != null;

        if (isTwoPane) {
            setupTwoPaneLayout();
        } else {
            setupPager();
        }
    }

    private void setupTwoPaneLayout() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.stepsListContainer, StepsListFragment.newInstance(steps))
                .commit();

        onStepSelected(steps.get(0));
    }


    private void setupPager() {
        stepsAdapter = new StepsPagerAdapter(getSupportFragmentManager(), steps);
        stepsPager.addOnPageChangeListener(this);
        stepsPager.setAdapter(stepsAdapter);
    }

    @Optional
    @OnClick(R.id.nextButton)
    public void nextStep(View view) {
        stepsPager.arrowScroll(View.FOCUS_RIGHT);
    }

    @Optional
    @OnClick(R.id.previousButton)
    public void previousStep(View view) {
        stepsPager.arrowScroll(View.FOCUS_LEFT);
    }

    @Override
    public void onPageSelected(int position) {
        if (stepShortDescription != null)
            stepShortDescription.setText(String.valueOf(steps.get(position).id));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

    @Override
    public void onPageScrollStateChanged(int state) { }

    @Override
    public void onStepSelected(Step step) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.stepDetailsContainer, StepFragment.newInstance(step))
                .commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
