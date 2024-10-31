package com.example.comp2000app.ui.holidays;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.widget.Button;
import android.widget.Toast;

import com.example.comp2000app.R;
import com.example.comp2000app.data.HolidayBooking;
import com.example.comp2000app.databinding.FragmentHolidaysBinding;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HolidaysFragment extends Fragment {




    private FragmentHolidaysBinding binding;
    private TextView debugTextView;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // create the view object so I can find the buttons by their IDs
        View view = inflater.inflate(R.layout.fragment_holidays, container, false);

        debugTextView = view.findViewById(R.id.debuggingTextView); // Ensure you have a TextView with this ID



        // create HolidayBooking objects for each of the existing holiday requests
        String startDateStr = "31/01/2025";
        String endDateStr = "06/02/2025";
        String requestDateStr = "01/11/2024";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        Date startDate = null;
        try {
            startDate = dateFormat.parse(startDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date endDate = null;
        try {
            endDate = dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date requestDate = null;
        try {
            requestDate = dateFormat.parse(requestDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Create the first of the holiday request objects
        HolidayBooking holidayRequestOne = new HolidayBooking(startDate, endDate, requestDate, HolidayBooking.ApprovalStatus.PENDING);

        startDateStr = "01/03/2025";
        endDateStr = "15/03/2025";
        requestDateStr = "01/11/2024";

        try {
            startDate = dateFormat.parse(startDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            endDate = dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            requestDate = dateFormat.parse(requestDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Create the first of the holiday request objects
        HolidayBooking holidayRequestTwo = new HolidayBooking(startDate, endDate, requestDate, HolidayBooking.ApprovalStatus.PENDING);


        // creating references to the holiday request related buttons
        Button amendHolidayButtonOne = view.findViewById(R.id.holidayRequestOneAmend);
        Button amendHolidayButtonTwo = view.findViewById(R.id.holidayRequestTwoAmend);

        Button cancelHolidayButtonOne = view.findViewById(R.id.holidayRequestOneCancel);
        Button cancelHolidayButtonTwo = view.findViewById(R.id.holidayRequestTwoCancel);


        // creating onClick listeners for the amendments of holiday requests
        amendHolidayButtonOne.setOnClickListener(v -> amendHolidayBooking(holidayRequestOne));
        amendHolidayButtonTwo.setOnClickListener(v -> amendHolidayBooking(holidayRequestTwo));

        // creating onClick listeners for the cancellations of holiday requests
        cancelHolidayButtonOne.setOnClickListener(v -> cancelHolidayBooking(holidayRequestOne));
        cancelHolidayButtonTwo.setOnClickListener(v -> cancelHolidayBooking(holidayRequestTwo));



        HolidaysViewModel slideshowViewModel =
                new ViewModelProvider(this).get(HolidaysViewModel.class);

        binding = FragmentHolidaysBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Set up click listener for the Request Holiday button
        binding.requestHolidayButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(root);
            navController.navigate(R.id.action_holidaysFragment_to_requestHolidayFragment);
        });

        return root;
    }

    // function to handle the amendments of holiday bookings
    private void amendHolidayBooking(HolidayBooking bookingObject) {
        debugTextView.setText(bookingObject.exportAsString());
    }

    // function to handle the cancellation of holiday bookings
    private void cancelHolidayBooking(HolidayBooking bookingObject) {
        debugTextView.setText(bookingObject.exportAsString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
