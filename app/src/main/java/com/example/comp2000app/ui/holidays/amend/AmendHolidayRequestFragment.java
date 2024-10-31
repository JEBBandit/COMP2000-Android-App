package com.example.comp2000app.ui.holidays.amend;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.comp2000app.R;
import com.example.comp2000app.data.HolidayBooking;

import java.text.SimpleDateFormat;

public class AmendHolidayRequestFragment extends Fragment {

    private AmendHolidayRequestViewModel mViewModel;

    private TextView textViewStartDate;
    private TextView textViewEndDate;
    private TextView textViewRequestDate;
    private TextView textViewApprovalStatus;
    private TextView textViewApprovalOrRejectionDate;
    private TextView textViewCancellationDate;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_amend_holiday_request, container, false);

        // Initialize TextViews
        textViewStartDate = view.findViewById(R.id.textViewStartDate);
        textViewEndDate = view.findViewById(R.id.textViewEndDate);
        textViewRequestDate = view.findViewById(R.id.textViewRequestDate);
        textViewApprovalStatus = view.findViewById(R.id.textViewApprovalStatus);
        textViewApprovalOrRejectionDate = view.findViewById(R.id.textViewApprovalOrRejectionDate);
        textViewCancellationDate = view.findViewById(R.id.textViewCancellationDate);

        // Reconstruct HolidayBooking object from arguments if available
        if (getArguments() != null) {
            HolidayBooking holidayBooking = HolidayBooking.reconstruct(getArguments());
            populateUI(holidayBooking);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AmendHolidayRequestViewModel.class);
        // TODO: Use the ViewModel if needed
    }

    // Method to populate the UI with HolidayBooking data
    private void populateUI(HolidayBooking booking) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (booking.getStartDate() != null) {
            textViewStartDate.setText(dateFormat.format(booking.getStartDate()));
        }

        if (booking.getEndDate() != null) {
            textViewEndDate.setText(dateFormat.format(booking.getEndDate()));
        }

        if (booking.getRequestDate() != null) {
            textViewRequestDate.setText(dateFormat.format(booking.getRequestDate()));
        }

        textViewApprovalStatus.setText(booking.getApprovalStatus().name());

        // Check approval status before getting approval or rejection date
        if (booking.getApprovalStatus() == HolidayBooking.ApprovalStatus.APPROVED && booking.getApprovalDate() != null) {
            textViewApprovalOrRejectionDate.setText(dateFormat.format(booking.getApprovalDate()));
        } else if (booking.getApprovalStatus() == HolidayBooking.ApprovalStatus.REJECTED && booking.getRejectionDate() != null) {
            textViewApprovalOrRejectionDate.setText(dateFormat.format(booking.getRejectionDate()));
        } else {
            textViewApprovalOrRejectionDate.setText("N/A");
        }

        // Check if the booking is cancelled before calling getCancellationDate
        if (booking.getApprovalStatus() == HolidayBooking.ApprovalStatus.CANCELLED && booking.getCancellationDate() != null) {
            textViewCancellationDate.setText(dateFormat.format(booking.getCancellationDate()));
        } else {
            textViewCancellationDate.setText("N/A");
        }
    }


}
