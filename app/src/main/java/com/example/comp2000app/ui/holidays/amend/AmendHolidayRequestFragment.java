package com.example.comp2000app.ui.holidays.amend;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comp2000app.R;

public class AmendHolidayRequestFragment extends Fragment {

    private AmendHolidayRequestViewModel mViewModel;

    public static AmendHolidayRequestFragment newInstance() {
        return new AmendHolidayRequestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_amend_holiday_request, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AmendHolidayRequestViewModel.class);
        // TODO: Use the ViewModel
    }

}