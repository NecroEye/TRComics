package com.muratcangzm.trcomics.fragments;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.RegistrationFragmentLayoutBinding;
import com.muratcangzm.trcomics.utils.NotificationHelper;

public class RegistrationFragment extends Fragment {


    private RegistrationFragmentLayoutBinding binding;
    private FirebaseAuth mAuth;
    private Animation fade_in;

    public RegistrationFragment() {

        //Empty Constructor

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = RegistrationFragmentLayoutBinding.inflate(getLayoutInflater(), container, false);
        mAuth = FirebaseAuth.getInstance();

        fade_in = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);

        binding.registerButton.setOnClickListener(v -> {

            v.setClickable(false);
            binding.registerProgress.setVisibility(View.VISIBLE);
            binding.registerButton.setAnimation(fade_in);
            binding.registerButton.setText("Yükleniyor...");

            createUser(binding.registerUserName.getText().toString().trim(),
                    binding.registerMail.getText().toString().trim(),
                    binding.registerPassword.getText().toString().trim(),
                    binding.registerTwicePassword.getText().toString().trim());


        });

        binding.toLoginText.setOnClickListener(v -> {

            NavController controller = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
            controller.navigate(R.id.toLogin);

        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void createUser(final String userName, final String email, final String password, final String passwordTwice) {

         if (!userName.isEmpty() && !email.isEmpty()
                && !password.isEmpty() && !passwordTwice.isEmpty()) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                if(password.equals(passwordTwice)){

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    binding.registerButton.setClickable(true);
                                    binding.registerProgress.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {

                                        binding.registerButton.setAnimation(fade_in);
                                        binding.registerButton.setText("Tamamlandı");

                                        Toast.makeText(requireContext(), "Hesap Oluşturuldu.",
                                                Toast.LENGTH_SHORT).show();

                                        NotificationHelper.showNotification(requireContext(), "Hesap Onayı", "E-Postanıza onay mesajı gönderilmiştir.");

                                        NavController controller = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
                                        controller.navigate(R.id.toLogin);

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Log.d("Deneme", "onComplete Verification: " + user.isEmailVerified());
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Log.d("Deneme", "onComplete Verification: " + user.isEmailVerified());

                                            }
                                        });

                                    } else {

                                        Toast.makeText(requireContext(), "Hesap Oluşturulamadı.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(requireContext(), "Şifre Uyuşmuyor", Toast.LENGTH_SHORT).show();
                }



            } else {
                Toast.makeText(requireContext(), "Geçersiz E-posta", Toast.LENGTH_SHORT).show();
            }


        }
         else{
             Toast.makeText(requireContext(), "Boş alan Bıraktınız", Toast.LENGTH_SHORT).show();
         }


    }

}
