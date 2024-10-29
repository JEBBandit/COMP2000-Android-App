package com.example.comp2000app.ui.holidays.request.confirmation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.comp2000app.R;

public class HolidayRequestConfirmationFragment extends Fragment {

    private HolidayRequestConfirmationViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holiday_request_confirmation, container, false);

        // Retrieve and display the start and end dates
        Bundle args = getArguments();
        if (args != null) {
            String startDate = args.getString("startDate");
            String endDate = args.getString("endDate");

            TextView confirmationTextView = view.findViewById(R.id.confirmationTextView);
            confirmationTextView.setText("Holiday request submitted:\nStart Date: " + startDate + "\nEnd Date: " + endDate);
        }

        // Set up the "Home" button to navigate back to HomeFragment
        Button homeButton = view.findViewById(R.id.holidayRequestConfirmationToHome);
        homeButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.nav_home);
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HolidayRequestConfirmationViewModel.class);
    }
}
