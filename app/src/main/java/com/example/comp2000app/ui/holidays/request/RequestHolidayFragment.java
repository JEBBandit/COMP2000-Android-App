package com.example.comp2000app.ui.holidays.request;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.comp2000app.R;

import java.util.Calendar;

public class RequestHolidayFragment extends Fragment {

    private RequestHolidayViewModel mViewModel;
    private EditText editTextDate;

    public static RequestHolidayFragment newInstance() {
        return new RequestHolidayFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_holiday, container, false);

        // Initialize the EditText for the date picker
        editTextDate = view.findViewById(R.id.editTextDate);

        // Set up click listener to show DatePickerDialog
        editTextDate.setOnClickListener(v -> showDatePickerDialog());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RequestHolidayViewModel.class);
        // TODO: Use the ViewModel
    }

    private void showDatePickerDialog() {
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
                    editTextDate.setText(date);
                },
                year, month, day);

        datePickerDialog.show();
    }
}
