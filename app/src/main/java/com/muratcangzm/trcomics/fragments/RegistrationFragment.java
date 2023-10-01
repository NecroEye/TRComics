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
import com.muratcangzm.trcomics.databinding.RegistrationFragmentLayoutBinding;

public class RegistrationFragment extends Fragment {



    private RegistrationFragmentLayoutBinding binding;
    private FirebaseAuth mAuth;

    public RegistrationFragment(){

        //Empty Constructor

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = RegistrationFragmentLayoutBinding.inflate(getLayoutInflater(), container, false);
        mAuth = FirebaseAuth.getInstance();


        binding.registerButton.setOnClickListener(v ->{

            v.setClickable(false);
            binding.registerProgress.setVisibility(View.VISIBLE);

            createUser(binding.registerUserName.getText().toString(), binding.registerMail.getText().toString(),
                    binding.registerPassword.getText().toString(), binding.registerTwicePassword.getText().toString());



        });

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


  private void createUser(final String userName, final String email, final String password, final String passwordTwice){

        if(!userName.isEmpty() && !email.isEmpty()
                && !password.isEmpty() && !passwordTwice.isEmpty() && password.equals(passwordTwice)){

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            binding.registerButton.setClickable(true);
                            binding.registerProgress.setVisibility(View.GONE);

                            if (task.isSuccessful()) {

                                Toast.makeText(requireContext(), "Hesap Oluşturuldu.",
                                        Toast.LENGTH_SHORT).show();

                                NavController controller = Navigation.findNavController(requireActivity() ,R.id.fragmentContainerView);
                                controller.navigate(R.id.toLogin);

                                FirebaseUser user = mAuth.getCurrentUser();

                            } else {

                                Toast.makeText(requireContext(), "Hesap Oluşturulamadı.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }



  }

}
