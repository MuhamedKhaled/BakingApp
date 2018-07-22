package com.example.mohamed.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.adapters.StepsAdapter;
import com.example.mohamed.bakingapp.models.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsListFragment extends Fragment {
    private static final String STEPS_LIST_ARG_KEY = "stepsListArg";

    @BindView(R.id.twoPaneStepsRecyclerView)
    RecyclerView stepsRecyclerView;

    private ArrayList<Step> steps;

    private OnStepSelectedListener listener;
    private StepsAdapter adapter;

    public StepsListFragment() { }

    public static StepsListFragment newInstance(ArrayList<Step> steps) {
        StepsListFragment fragment = new StepsListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(STEPS_LIST_ARG_KEY, steps);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            steps = getArguments().getParcelableArrayList(STEPS_LIST_ARG_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steps_list, container, false);
        ButterKnife.bind(this, view);

        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new StepsAdapter(getActivity(), listener);
        stepsRecyclerView.setAdapter(adapter);

        adapter.updateSteps(steps);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStepSelectedListener) {
            listener = (OnStepSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnStepSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnStepSelectedListener {
        void onStepSelected(Step step);
    }
}
