package com.muratcangzm.trcomics.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.LoginFragmentLayoutBinding;
import com.muratcangzm.trcomics.screens.MainActivity;

public class LoginFragment extends Fragment {


    private LoginFragmentLayoutBinding binding;
    private FirebaseAuth mAuth;

    public LoginFragment(){

        //Empty Constructor

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = LoginFragmentLayoutBinding.inflate(getLayoutInflater(), container, false);
        mAuth = FirebaseAuth.getInstance();

        binding.loginButton.setOnClickListener(v ->{

            v.setClickable(false);
            binding.loginProgress.setVisibility(View.VISIBLE);

            signIn(binding.loginMail.getText().toString(),
                    binding.loginPassword.getText().toString());

        });

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

    private void signIn(final String email, final String password){

        if(!email.isEmpty() && !password.isEmpty()){

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            binding.loginProgress.setVisibility(View.GONE);
                            binding.loginButton.setClickable(true);

                            if (task.isSuccessful()) {

                                MainActivity.login.setVisible(false);
                                MainActivity.register.setVisible(false);
                                MainActivity.logout.setVisible(true);
                                MainActivity.profile.setVisible(true);


                                NavController controller = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
                                controller.navigate(R.id.successLogin);

                                Toast.makeText(requireContext(), "Giriş Başarılı.",
                                        Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();

                            } else {


                                Toast.makeText(requireContext(), "Giriş Başarısız.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }


    }
}
