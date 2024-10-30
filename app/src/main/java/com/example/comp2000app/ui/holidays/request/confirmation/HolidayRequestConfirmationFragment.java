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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HolidayRequestConfirmationFragment extends Fragment {

    private HolidayRequestConfirmationViewModel mViewModel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holiday_request_confirmation, container, false);

        // Retrieve and display the start and end dates
        Bundle args = getArguments();
        if (args != null) {
            String startDateStr = args.getString("startDate");
            String endDateStr = args.getString("endDate");

            TextView confirmationTextView = view.findViewById(R.id.confirmationTextView);
            confirmationTextView.setText("Holiday request submitted:\nStart Date: " + startDateStr + "\nEnd Date: " + endDateStr);

            // Calculate and display the number of days off
            TextView numberDaysOffTextView = view.findViewById(R.id.numberDaysOff);
            try {
                Date startDate = dateFormat.parse(startDateStr);
                Date endDate = dateFormat.parse(endDateStr);

                if (startDate != null && endDate != null) {
                    long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
                    long daysOff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1; // Add 1 to include the start day
                    numberDaysOffTextView.setText(daysOff + " Days Off");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                numberDaysOffTextView.setText("Error calculating days off");
            }
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
