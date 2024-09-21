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

public class SaloonParlour extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        // You can add custom logic here if needed,
        // otherwise the default behavior will handle navigating back.
        super.onBackPressed();
    }

    private FirebaseFirestore db;
    private com.example.bizflash_login_firestore.SaloonParlourAdapter saloonParlourAdapter;
    private List<Shop> shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloonparlour);

        db = FirebaseFirestore.getInstance();
        shopList = new ArrayList<>();

        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerSaloonParlour);
        saloonParlourAdapter = new SaloonParlourAdapter(shopList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(saloonParlourAdapter);

        // Fetch grocery shops data
        fetchSaloonParlour();
    }

    private void fetchSaloonParlour() {
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
                        saloonParlourAdapter.notifyDataSetChanged();
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
