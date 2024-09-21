package com.example.bizflash_login_firestore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class ClothingActivity extends AppCompatActivity {


    private FirebaseFirestore db;
    private com.example.bizflash_login_firestore.ClothingAdapter clothingAdapter;
    private List<Shop> shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        db = FirebaseFirestore.getInstance();
        shopList = new ArrayList<>();

        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerClothing);
        clothingAdapter = new ClothingAdapter(shopList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(clothingAdapter);

        // Fetch grocery shops data
        fetchClothing();
    }

    private void fetchClothing() {
        db.collection("AllShops")
                .whereEqualTo("category", "Grocery")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot result) {
                        for (QueryDocumentSnapshot document : result) {
                            Shop shop = document.toObject(Shop.class);
                            shopList.add(shop);
                        }
                        clothingAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("GroceryActivity", "Error getting documents: ", e);
                    }
                });
    }
}
