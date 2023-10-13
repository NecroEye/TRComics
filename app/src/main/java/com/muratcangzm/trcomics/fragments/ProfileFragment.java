package com.muratcangzm.trcomics.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.muratcangzm.trcomics.databinding.ProfileFragmentLayoutBinding;
import com.muratcangzm.trcomics.models.UserModel;
import com.muratcangzm.trcomics.utils.FetchingWorker;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class ProfileFragment extends Fragment {


    private ProfileFragmentLayoutBinding binding;
    private Uri ImageData;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private DocumentReference docRef;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ActivityResultLauncher<String> permissionLauncher;

    public ProfileFragment() {

        //Empty Constructor

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = ProfileFragmentLayoutBinding.inflate(getLayoutInflater(), container, false);


        currentUser = auth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

         docRef = firebaseFirestore.collection("users")
                .document(currentUser.getEmail());

         docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
             @Override
             public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                 if(task.isSuccessful()){

                     String imageUrl = null;

                      for(UserModel user : FetchingWorker.userModel){

                          if(auth.getCurrentUser().getEmail().matches(user.getEmail())){
                              binding.userName.setText(user.getUsername());

                              SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                              String formattedDate = sdf.format(user.getRegisterDate());
                              binding.dateHolder.setText(formattedDate);
                              imageUrl = user.getProfilePicUrl();

                          }
                      }

                     if(!imageUrl.matches("boş")){
                         try {

                             Picasso.get().load(imageUrl).into(binding.profilePic);

                         }
                         catch (Exception e){
                             Log.d("Hata", "onComplete: " + e.getMessage());
                         }
                     }
                 }
             }
         });

        binding.mailHolder.setText(currentUser.getEmail());
        registrationActivityLaunchers();

        binding.profilePicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (requireActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (requireActivity().shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {

                        Snackbar.make(binding.getRoot(), "İzinleri Gerekli", Snackbar.LENGTH_INDEFINITE)
                                .setAction("İzinleri Yönet", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        //beforehand denied then ask permission different way
                                       permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                                    }
                                }).show();
                    } else {
                        //ask permission
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);

                    }
                } else {
                       //permission granted
                      Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                      activityResultLauncher.launch(intentToGallery);

                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void registrationActivityLaunchers(){

        UUID uuid = UUID.randomUUID();

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {

                if (o.getResultCode() == Activity.RESULT_OK){

                  Intent intentFromResult = o.getData();
                  if(intentFromResult != null){

                    ImageData = intentFromResult.getData();

                      try {

                          ImageDecoder.Source source = ImageDecoder.createSource(requireActivity().getContentResolver(), ImageData);
                          Bitmap selectedBitmap = ImageDecoder.decodeBitmap(source);
                          binding.profilePic.setImageBitmap(selectedBitmap);

                          int atIndex = currentUser.getEmail().indexOf("@");
                          String getHalfOfEmail = currentUser.getEmail().substring(0, atIndex);
                          String path = "users/userProfilePic/" + getHalfOfEmail + ".jpg";

                          storageReference.child(path).putFile(ImageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                              @Override
                              public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                  StorageReference newReference = firebaseStorage.getReference(path);
                                  newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                      @Override
                                      public void onSuccess(Uri uri) {

                                          String downloadUrl = uri.toString();



                                          HashMap<String, Object> updateProfilePic = new HashMap<>();
                                          updateProfilePic.put("profilePicUrl", downloadUrl);

                                          docRef.update(updateProfilePic)
                                                  .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                      @Override
                                                      public void onSuccess(Void unused) {
                                                          Log.d("ProfilePic", "onSuccess: Başarılı");
                                                      }
                                                  })
                                                  .addOnFailureListener(new OnFailureListener() {
                                                      @Override
                                                      public void onFailure(@NonNull Exception e) {

                                                          Log.d("ProfilePic", "onFailure: Başarısız " + e.getMessage());

                                                      }
                                                  });

                                      }
                                  });

                              }
                          });

                      } catch (IOException e) {
                          throw new RuntimeException(e);
                      }


                  }

                }
            }
        });

        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean o) {

                if(o){

                    Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentToGallery);

                }
                else{

                }

            }
        });

    }

}
