package com.example.comp2000app.ui.holidays.request;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.comp2000app.R;

import java.util.Calendar;

public class RequestHolidayFragment extends Fragment {

    private RequestHolidayViewModel mViewModel;
    private EditText editTextStartDate;
    private EditText editTextEndDate;

    public static RequestHolidayFragment newInstance() {
        return new RequestHolidayFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_holiday, container, false);

        // Initialize the EditTexts for the date pickers
        editTextStartDate = view.findViewById(R.id.editTextStartDate);
        editTextEndDate = view.findViewById(R.id.editTextEndDate);

        // Initialize the Submit button
        Button submitButton = view.findViewById(R.id.submitRequestButton);

        // Set up click listeners to show DatePickerDialogs
        editTextStartDate.setOnClickListener(v -> showDatePickerDialog(editTextStartDate));
        editTextEndDate.setOnClickListener(v -> showDatePickerDialog(editTextEndDate));

        // Set up the Submit button click listener
        submitButton.setOnClickListener(v -> {
            // Get the selected dates from the EditTexts
            String startDate = editTextStartDate.getText().toString();
            String endDate = editTextEndDate.getText().toString();

            // Pass the dates to the ConfirmationFragment
            NavController navController = Navigation.findNavController(view);
            Bundle bundle = new Bundle();
            bundle.putString("startDate", startDate);
            bundle.putString("endDate", endDate);
            navController.navigate(R.id.action_requestHolidayFragment_to_confirmationFragment, bundle);
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RequestHolidayViewModel.class);
        // TODO: Use the ViewModel
    }

    private void showDatePickerDialog(EditText editText) {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show the DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    // Format and display the selected date
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    editText.setText(date);
                },
                year, month, day);

        datePickerDialog.show();
    }
}
