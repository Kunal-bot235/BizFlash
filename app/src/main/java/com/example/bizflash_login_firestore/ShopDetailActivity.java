//package com.example.bizflash_login_firestore;
//
//import static android.content.Intent.getIntent;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class ShopDetailActivity extends AppCompatActivity {
//    private DatabaseReference shopRef;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_detail);
//
//        String shopId = getIntent().getStringExtra("shopId");
//        shopRef = FirebaseDatabase.getInstance().getReference().child("shops").child(shopId);
//
//        shopRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Shop shop = dataSnapshot.getValue(Shop.class);
//                if (shop != null) {
//                    updateUI(shop);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle error
//            }
//        });
//    }
//
//    private void updateUI(Shop shop) {
//        // Update UI elements with shop details
//        // For example:
//        // ((TextView) findViewById(R.id.shopNameTextView)).setText(shop.getName());
//        // ... set other details
//    }
//}
package com.example.bizflash_login_firestore;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShopDetailActivity extends AppCompatActivity {
    private DatabaseReference shopRef;
    private TextView shopNameTextView, shopAddressTextView, shopOfferTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        shopNameTextView = findViewById(R.id.shopNameTextView);
        shopAddressTextView = findViewById(R.id.shopAddressTextView);
        shopOfferTextView = findViewById(R.id.shopOfferTextView);

        String shopId = getIntent().getStringExtra("shopId");
        shopRef = FirebaseDatabase.getInstance().getReference().child("shops").child(shopId);

        shopRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Shop shop = dataSnapshot.getValue(Shop.class);
                if (shop != null) {
                    updateUI(shop);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void updateUI(Shop shop) {
        shopNameTextView.setText(shop.getName());
        shopAddressTextView.setText(shop.getAddress());
        shopOfferTextView.setText(shop.getCurrentOffer());
    }
}