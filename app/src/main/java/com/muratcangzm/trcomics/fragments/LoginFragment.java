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
import com.muratcangzm.trcomics.databinding.LoginFragmentLayoutBinding;

public class LoginFragment extends Fragment {


    private LoginFragmentLayoutBinding binding;

    public LoginFragment(){

        //Empty Constructor

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = LoginFragmentLayoutBinding.inflate(getLayoutInflater(), container, false);


        binding.toRegisterScreenText.setOnClickListener(v ->{

            NavController controller = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
            controller.navigate(R.id.toRegister);


        });

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
