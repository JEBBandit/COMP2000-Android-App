package com.example.comp2000app.ui.holidays;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.comp2000app.R;
import com.example.comp2000app.databinding.FragmentHolidaysBinding;

public class HolidaysFragment extends Fragment {

    private FragmentHolidaysBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
