package com.muratcangzm.trcomics.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.RegistrationFragmentLayoutBinding;

public class RegistrationFragment extends Fragment {



    private RegistrationFragmentLayoutBinding binding;

    public RegistrationFragment(){

        //Empty Constructor

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = RegistrationFragmentLayoutBinding.inflate(getLayoutInflater(), container, false);


        binding.toLoginText.setOnClickListener(v ->{

            NavController controller = Navigation.findNavController(requireActivity() ,R.id.fragmentContainerView);
            controller.navigate(R.id.toLogin);

        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
