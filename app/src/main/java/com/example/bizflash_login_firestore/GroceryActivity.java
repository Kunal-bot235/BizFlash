////package com.example.bizflash_login_firestore;
////
////import android.os.Bundle;
////import android.support.annotation.NonNull;
////import android.util.Log;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////
////import com.google.android.gms.tasks.OnFailureListener;
////import com.google.android.gms.tasks.OnSuccessListener;
////import com.google.firebase.firestore.FirebaseFirestore;
////import com.google.firebase.firestore.QueryDocumentSnapshot;
////import com.google.firebase.firestore.QuerySnapshot;
////import java.util.ArrayList;
////import java.util.List;
////
////public class GroceryActivity extends AppCompatActivity {
////    @Override
////    public void onBackPressed() {
////        // You can add custom logic here if needed,
////        // otherwise the default behavior will handle navigating back.
////        super.onBackPressed();
////    }
////
////    private FirebaseFirestore db;
////    private com.example.bizflash_login_firestore.GroceryAdapter groceryAdapter;
////    private List<Shop> shopList;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_grocery);
////
////        db = FirebaseFirestore.getInstance();
////        shopList = new ArrayList<>();
////
////        // Setup RecyclerView
////        RecyclerView recyclerView = findViewById(R.id.recyclerViewGrocery);
////        groceryAdapter = new GroceryAdapter(shopList);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        recyclerView.setAdapter(groceryAdapter);
////
////        // Fetch grocery shops data
////        fetchGroceryShops();
////    }
////
////    private void fetchGroceryShops() {
////        db.collection("AllShops")
////                .whereEqualTo("category", "Grocery")
////                .get()
////                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
////                    @Override
////                    public void onSuccess(QuerySnapshot result) {
////                        for (QueryDocumentSnapshot document : result) {
////                            Shop shop = document.toObject(Shop.class);
////                            shopList.add(shop);
////                        }
////                        groceryAdapter.notifyDataSetChanged();
////                    }
////                })
////                .addOnFailureListener(new OnFailureListener() {
////                    @Override
////                    public void onFailure(@NonNull Exception e) {
////                        Log.e("GroceryActivity", "Error getting documents: ", e);
////                    }
////                });
////    }
////}
//package com.example.bizflash_login_firestore;
//
//import android.os.Bundle;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class GroceryActivity extends AppCompatActivity {
//    private DatabaseReference shopsRef;
//    private ShopAdapter shopAdapter;
//    private List<Shop> shopList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_grocery);
//
//        shopsRef = FirebaseDatabase.getInstance().getReference().child("shops");
//        RecyclerView recyclerView = findViewById(R.id.recyclerViewGrocery);
//        shopList = new ArrayList<>();
//        shopAdapter = new ShopAdapter(shopList);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(shopAdapter);
//
//        fetchLiveGroceryShops();
//    }
//
//    private void fetchLiveGroceryShops() {
//        shopsRef.orderByChild("shopType").equalTo("Grocery").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                shopList.clear();
//                for (DataSnapshot shopSnapshot : dataSnapshot.getChildren()) {
//                    Shop shop = shopSnapshot.getValue(Shop.class);
//                    if (shop != null && shop.isLive()) {
//                        shopList.add(shop);
//                    }
//                }
//                shopAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle error
//                Log.e("GroceryActivity", "Error fetching grocery shops: ", databaseError.toException());
//            }
//        });
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//}
package com.example.bizflash_login_firestore;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroceryActivity extends AppCompatActivity {
    private DatabaseReference shopsRef;
    private ShopAdapter shopAdapter;
    private List<Shop> shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        shopsRef = FirebaseDatabase.getInstance().getReference().child("shops");
        RecyclerView recyclerView = findViewById(R.id.recyclerViewGrocery);
        shopList = new ArrayList<>();
        shopAdapter = new ShopAdapter(shopList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shopAdapter);

        fetchLiveGroceryShops();
    }

    private void fetchLiveGroceryShops() {
        shopsRef.orderByChild("shopType").equalTo("Grocery").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                shopList.clear();
                for (DataSnapshot shopSnapshot : dataSnapshot.getChildren()) {
                    Shop shop = shopSnapshot.getValue(Shop.class);
                    if (shop != null && shop.isLive()) {
                        shopList.add(shop);
                    }
                }
                shopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Log.e("GroceryActivity", "Error fetching grocery shops: ", databaseError.toException());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}