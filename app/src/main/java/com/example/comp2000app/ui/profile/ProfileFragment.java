package com.example.comp2000app.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.comp2000app.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private String originalName, originalJobTitle, originalEmail, originalMobile, originalAddress;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Save original values to allow canceling edits
        originalName = binding.name.getText().toString();
        originalJobTitle = binding.jobTitle.getText().toString();
        originalEmail = binding.email.getText().toString();
        originalMobile = binding.mobile.getText().toString();
        originalAddress = binding.addressLine1.getText().toString();

        // Set up Edit button listener
        binding.editButton.setOnClickListener(v -> {
            setEditable(true);
            binding.editButton.setVisibility(View.GONE);
            binding.confirmButton.setVisibility(View.VISIBLE);
            binding.cancelButton.setVisibility(View.VISIBLE);
        });

        // Set up Confirm button listener
        binding.confirmButton.setOnClickListener(v -> {
            // Save edited values
            originalName = binding.name.getText().toString();
            originalJobTitle = binding.jobTitle.getText().toString();
            originalEmail = binding.email.getText().toString();
            originalMobile = binding.mobile.getText().toString();
            originalAddress = binding.addressLine1.getText().toString();

            setEditable(false);
            binding.editButton.setVisibility(View.VISIBLE);
            binding.confirmButton.setVisibility(View.GONE);
            binding.cancelButton.setVisibility(View.GONE);
        });

        // Set up Cancel button listener
        binding.cancelButton.setOnClickListener(v -> {
            // Restore original values
            binding.name.setText(originalName);
            binding.jobTitle.setText(originalJobTitle);
            binding.email.setText(originalEmail);
            binding.mobile.setText(originalMobile);
            binding.addressLine1.setText(originalAddress);

            setEditable(false);
            binding.editButton.setVisibility(View.VISIBLE);
            binding.confirmButton.setVisibility(View.GONE);
            binding.cancelButton.setVisibility(View.GONE);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Helper method to toggle editability of fields
    private void setEditable(boolean isEditable) {
        binding.name.setEnabled(isEditable);
        binding.jobTitle.setEnabled(isEditable);
        binding.email.setEnabled(isEditable);
        binding.mobile.setEnabled(isEditable);
        binding.addressLine1.setEnabled(isEditable);
    }
}
