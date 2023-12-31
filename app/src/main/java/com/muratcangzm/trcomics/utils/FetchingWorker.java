package com.muratcangzm.trcomics.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.muratcangzm.trcomics.models.ComicModel;
import com.muratcangzm.trcomics.models.UserModel;
import java.util.ArrayList;

public class FetchingWorker extends Worker {


    private FirebaseFirestore firebaseFirestore;
    private final Context context;
    public static ArrayList<ComicModel> comicModel = new ArrayList<>();
    public static ArrayList<UserModel> userModel = new ArrayList<>();


    public FetchingWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        this.context = context;
        firebaseFirestore = FirebaseFirestore.getInstance();


    }

    @NonNull
    @Override
    public Result doWork() {

        fetchingComics();
        fetchingUsers();

        return Result.success();
    }

    private void fetchingComics() {

        firebaseFirestore.collection("comic")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null)
                            Toast.makeText(context, "Hata", Toast.LENGTH_SHORT).show();

                        if (value != null) {

                            comicModel.clear();
                            for (DocumentSnapshot documentSnapshot : value.getDocuments()) {

                                ArrayList<String> episodes = (ArrayList<String>) documentSnapshot.get("episodes");
                                ArrayList<String> genres = (ArrayList<String>) documentSnapshot.get("genres");

                                comicModel.add(new ComicModel(
                                        documentSnapshot.getString("author"),
                                        documentSnapshot.getString("coverUrl"),
                                        documentSnapshot.getDate("date"),
                                        documentSnapshot.getString("description"),
                                        episodes,
                                        documentSnapshot.getBoolean("favorite"),
                                        genres,
                                        documentSnapshot.getString("title")
                                ));
                            }

                            for (ComicModel comic : comicModel) {
                                Log.d("Veri", "onEvent: " + comic.getTitle());
                            }
                            Log.d("Veri", "onEvent: " + comicModel.size());


                        }
                    }
                });

    }

    private void fetchingUsers() {

        firebaseFirestore.collection("users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null)
                            Toast.makeText(context, "Hata", Toast.LENGTH_SHORT).show();

                        if (value != null) {


                            for (DocumentSnapshot document : value.getDocuments()) {

                                userModel.add(new UserModel(
                                        document.getString("username"),
                                        document.getString("email"),
                                        document.getString("profilePicUrl"),
                                        document.getDate("registerDate")
                                ));


                            }
                        }


                    }
                });

    }


}
