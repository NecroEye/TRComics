package com.muratcangzm.trcomics.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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
import com.muratcangzm.trcomics.utils.NotificationHelper;
import com.squareup.picasso.Picasso;

public class LoginFragment extends Fragment {


    private LoginFragmentLayoutBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference docRef;
    private Animation fade_in;

    public LoginFragment(){

        //Empty Constructor

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = LoginFragmentLayoutBinding.inflate(getLayoutInflater(), container, false);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        fade_in = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);

        binding.loginButton.setOnClickListener(v ->{

            v.setClickable(false);
            binding.loginProgress.setVisibility(View.VISIBLE);
            binding.loginButton.setAnimation(fade_in);
            binding.loginButton.setText("Yükleniyor...");

            signIn(binding.loginMail.getText().toString().trim(),
                    binding.loginPassword.getText().toString().trim());

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
                        @SuppressLint({"ResourceAsColor", "SetTextI18n"})
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            binding.loginProgress.setVisibility(View.INVISIBLE);
                            binding.loginButton.setClickable(true);
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

                                        if(task.isSuccessful()){

                                            DocumentSnapshot document = task.getResult();
                                            String username = document.getString("username");
                                            String ImageUrl = document.getString("profilePicUrl");

                                            if(!ImageUrl.matches("boş")) Picasso.get().load(ImageUrl).into(MainActivity.bannerProfilePicture);

                                            MainActivity.bannerUserName.setText(username);

                                        }
                                    }
                                });

                                MainActivity.materialCardView.setVisibility(View.VISIBLE);
                                MainActivity.bannerUserName.setVisibility(View.VISIBLE);
                                MainActivity.bannerStatusText.setVisibility(View.VISIBLE);
                                MainActivity.bannerVerification.setVisibility(View.VISIBLE);

                                if(user.isEmailVerified()){
                                    MainActivity.bannerVerification.setText("Onaylı");
                                    MainActivity.bannerVerification.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
                                }
                                else{
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


    }
}
