package com.muratcangzm.trcomics.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.LoginFragmentLayoutBinding;
import com.muratcangzm.trcomics.screens.MainActivity;
import com.muratcangzm.trcomics.utils.NetworkUtils;

public class LoginFragment extends Fragment {


    private LoginFragmentLayoutBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference docRef;
    private Animation fade_in;


    public LoginFragment() {

        //Empty Constructor

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = LoginFragmentLayoutBinding.inflate(getLayoutInflater(), container, false);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        SharedPreferences preferences = getContext().getSharedPreferences("rememberMe", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String getEmailRemembrance = preferences.getString("email", null);
        String getPassRemembrance = preferences.getString("password", null);
        boolean getCheckRemembrance = preferences.getBoolean("isChecked", false);

        if (getEmailRemembrance != null && getPassRemembrance != null && getCheckRemembrance != false) {

            binding.loginMail.setText(getEmailRemembrance);
            binding.loginPassword.setText(getPassRemembrance);
            binding.remember.setChecked(getCheckRemembrance);

        }

        fade_in = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);

        binding.loginButton.setOnClickListener(v -> {

            v.setClickable(false);
            binding.loginProgress.setVisibility(View.VISIBLE);
            binding.loginButton.setAnimation(fade_in);
            binding.loginButton.setText("Yükleniyor...");


            if (!binding.loginMail.getText().toString().trim().isEmpty() &&
                    !binding.loginPassword.getText().toString().isEmpty()) {
                if (binding.remember.isChecked()) {

                    editor.putString("email", binding.loginMail.getText().toString().trim()).apply();
                    editor.putString("password", binding.loginPassword.getText().toString().trim()).apply();
                    editor.putBoolean("isChecked", true).apply();

                } else {
                    editor.putString("email", null).apply();
                    editor.putString("password", null).apply();
                    editor.putBoolean("isChecked", false).apply();
                }
            }

            binding.remember.setClickable(false);

            signIn(binding.loginMail.getText().toString().trim(),
                    binding.loginPassword.getText().toString().trim());

        });

        binding.toRegisterScreenText.setOnClickListener(v -> {

            NavController controller = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
            controller.navigate(R.id.toRegister);


        });

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void signIn(final String email, final String password) {


        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            if (!email.isEmpty() && !password.isEmpty()) {

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @SuppressLint({"ResourceAsColor", "SetTextI18n"})
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                binding.loginProgress.setVisibility(View.GONE);
                                binding.loginButton.setClickable(true);
                                binding.remember.setClickable(true);
                                binding.loginButton.setText("Giriş Yap");

                                if (task.isSuccessful()) {

                                    MainActivity.login.setVisible(false);
                                    MainActivity.register.setVisible(false);
                                    MainActivity.logout.setVisible(true);
                                    MainActivity.profile.setVisible(true);


                                    binding.loginButton.setAnimation(fade_in);
                                    binding.loginButton.setText("Tamamlandı");

                                    NavController controller = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
                                    controller.navigate(R.id.successLogin);

                                    Toast.makeText(requireContext(), "Giriş Başarılı.",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    docRef = firebaseFirestore.collection("users").document(user.getEmail());

                                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                            if (task.isSuccessful()) {

                                                DocumentSnapshot document = task.getResult();
                                                String username = document.getString("username");
                                                String ImageUrl = document.getString("profilePicUrl");

                                                if (!ImageUrl.matches("boş"))
                                                    Glide.with(binding.getRoot())
                                                            .load(ImageUrl)
                                                            .centerCrop()
                                                            .error(R.drawable.not_found)
                                                            .into(MainActivity.bannerProfilePicture);

                                                MainActivity.bannerUserName.setText(username);


                                            }
                                        }
                                    });

                                    MainActivity.materialCardView.setVisibility(View.VISIBLE);
                                    MainActivity.bannerUserName.setVisibility(View.VISIBLE);
                                    MainActivity.bannerStatusText.setVisibility(View.VISIBLE);
                                    MainActivity.bannerVerification.setVisibility(View.VISIBLE);

                                    if (user.isEmailVerified()) {
                                        MainActivity.bannerVerification.setText("Onaylı");
                                        MainActivity.bannerVerification.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
                                    } else {
                                        MainActivity.bannerVerification.setText("Onaylanmadı");
                                        MainActivity.bannerVerification.setTextColor(ContextCompat.getColor(requireContext(), R.color.rose));
                                    }


                                } else {

                                    Toast.makeText(requireContext(), "Giriş Başarısız.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        } else {
            Toast.makeText(requireContext(), "İnternet bağlantısı yok",
                            Toast.LENGTH_SHORT).show();
            binding.loginProgress.setVisibility(View.GONE);
            binding.loginButton.setClickable(true);
            binding.remember.setClickable(true);
            binding.loginButton.setText("Giriş Yap");

        }

    }
}
