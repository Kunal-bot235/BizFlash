//
//package com.example.bizflash_login_firestore;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Locale;
//
//public class ShopOwnerActivity extends AppCompatActivity {
//
//    private DatabaseReference mDatabase;
//    private FirebaseAuth mAuth;
//    //private DatabaseReference shopRef;
//    private String shopId;
//    private Button goLiveButton;
//    private TextView shopStatusTextView;
//    private FusedLocationProviderClient fusedLocationClient;
//    private EditText shopName, shopAddress;
//    private EditText shopPhoneNumber;
//    private Spinner shopTypeSpinner;
//    private Geocoder geocoder;
//    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
//    FirebaseDatabase database=FirebaseDatabase.getInstance();
//    DatabaseReference shopRef=database.getReference("shops");
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shopowner);
//
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        shopId = user != null ? user.getUid() : "";
//
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        shopRef = mDatabase.child("shops").child(shopId);
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        shopPhoneNumber = findViewById(R.id.shopPhoneNumberEditText);
//        shopTypeSpinner = findViewById(R.id.shopTypeSpinner);
//        goLiveButton = findViewById(R.id.toggleStatusButton);
//        shopStatusTextView = findViewById(R.id.shopStatusTextView);
//        shopName = findViewById(R.id.shopNameEditText);
//        shopAddress = findViewById(R.id.shopAddressEditText);
//
//        // Create a shop object
//        Shop shop = new Shop(
//                shopId,
//                "Shop Name",
//                "Shop Address",
//                "Latitude,Longitude",
//                "",  // Phone number
//                "",  // Shop type
//                false,
//
//                "Current Offer"
//        );
//
//        // Add the shop to the database
//        shopRef.setValue(shop)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(ShopOwnerActivity.this, "Shop data saved successfully", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(e -> Toast.makeText(ShopOwnerActivity.this, "Failed to save shop data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//
//        goLiveButton.setOnClickListener(v -> toggleLiveStatus());
//
//        // Listen for changes in live status
//        shopRef.child("isLive").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Boolean isLive = dataSnapshot.getValue(Boolean.class);
//                updateUILiveStatus(isLive);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ShopOwnerActivity.this, "Failed to load live status.", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Button getDetails = findViewById(R.id.saveDetailsButton);
//        Button getLocation = findViewById(R.id.getLocationButton);
//
//        getLocation.setOnClickListener(view -> getLastLocation());
//
//        getDetails.setOnClickListener(view -> saveShopDetails());
//    }
//
//    private void toggleLiveStatus() {
//        shopRef.child("isLive").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Boolean currentStatus = dataSnapshot.getValue(Boolean.class);
//                shopRef.child("isLive").setValue(currentStatus == null || !currentStatus);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ShopOwnerActivity.this, "Failed to update status.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void updateUILiveStatus(Boolean isLive) {
//        if (isLive != null && isLive) {
//            shopStatusTextView.setText("Shop is live");
//            shopStatusTextView.setTextColor(Color.GREEN);
//            goLiveButton.setText("Go Offline");
//
//        } else {
//            shopStatusTextView.setText("Shop is not live");
//            shopStatusTextView.setTextColor(Color.RED);
//            goLiveButton.setText("Go Live");
//        }
//    }
//
//    private void getLastLocation() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
//            return;
//        }
//
//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener(this, location -> {
//                    if (location != null) {
//                        String locationString = location.getLatitude() + "," + location.getLongitude();
//                        shopRef.child("location").setValue(locationString);
//
//                        // Get address from location and update the EditText and database
//                        try {
//                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                            if (!addresses.isEmpty()) {
//                                Address address = addresses.get(0);
//                                String addressText = address.getAddressLine(0);
//                                shopAddress.setText(addressText);
//                                shopRef.child("address").setValue(addressText);
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        Toast.makeText(ShopOwnerActivity.this, "Location saved", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(ShopOwnerActivity.this, "Failed to retrieve location", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
////    private void saveShopDetails() {
////        String name = shopName.getText().toString().trim();
////        String address = shopAddress.getText().toString().trim();
////        String phoneNumber = shopPhoneNumber.getText().toString().trim();
////        String type = shopType.getText().toString().trim();
////
////        if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !type.isEmpty()) {
////            shopRef.child("name").setValue(name);
////            shopRef.child("address").setValue(address);
////            shopRef.child("phoneNumber").setValue(phoneNumber);
////            shopRef.child("type").setValue(type);
////            Toast.makeText(this, "Shop details saved", Toast.LENGTH_SHORT).show();
////        } else {
////            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
////        }
////    }
////private void saveShopDetails() {
////    String name = shopName.getText().toString().trim();
////    String address = shopAddress.getText().toString().trim();
////    String phoneNumber = shopPhoneNumber.getText().toString().trim();
////    String type = shopTypeSpinner.getSelectedItem().toString().toLowerCase();
////
////    if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty()) {
////        Shop shop = new Shop(shopId, name, address, "", phoneNumber, type, false, "");
////
////        // Save shop details in the main shops node
////        shopRef.setValue(shop).addOnSuccessListener(aVoid -> {
////            // Also save relevant details in the category-specific node
////            DatabaseReference categoryRef = mDatabase.child(type).child(shopId);
////            categoryRef.child("name").setValue(name);
////            categoryRef.child("address").setValue(address);
////            categoryRef.child("phoneNumber").setValue(phoneNumber);
////            categoryRef.child("type").setValue(type);
////            categoryRef.child("isLive").setValue(false);
////
////            Toast.makeText(this, "Shop details saved", Toast.LENGTH_SHORT).show();
////        }).addOnFailureListener(e -> {
////            Toast.makeText(this, "Failed to save shop details", Toast.LENGTH_SHORT).show();
////        });
////    } else {
////        Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
////    }
////}
//private void saveShopDetails() {
//    String name = shopName.getText().toString().trim();
//    String address = shopAddress.getText().toString().trim();
//    String phoneNumber = shopPhoneNumber.getText().toString().trim();
//    String type = shopTypeSpinner.getSelectedItem() != null
//            ? shopTypeSpinner.getSelectedItem().toString().toLowerCase()
//            : "";
//
//    if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !type.isEmpty()) {
//        Shop shop = new Shop(shopId, name, address, "", phoneNumber, type, false, "");
//
//        // Save shop details in the main shops node
//        shopRef.setValue(shop).addOnSuccessListener(aVoid -> {
//            // Also save relevant details in the category-specific node
//            DatabaseReference categoryRef = mDatabase.child(type).child(shopId);
//            categoryRef.child("name").setValue(name);
//            categoryRef.child("address").setValue(address);
//            categoryRef.child("phoneNumber").setValue(phoneNumber);
//            categoryRef.child("type").setValue(type);
//            categoryRef.child("isLive").setValue(false);
//
//            Toast.makeText(this, "Shop details saved", Toast.LENGTH_SHORT).show();
//        }).addOnFailureListener(e -> {
//            Toast.makeText(this, "Failed to save shop details: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//        });
//    } else {
//        Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//    }
//}
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getLastLocation();
//            } else {
//                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//}
package com.example.bizflash_login_firestore;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShopOwnerActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String shopId;
    private Button goLiveButton;
    private TextView shopStatusTextView;
    private FusedLocationProviderClient fusedLocationClient;
    private EditText shopName, shopAddress, shopPhoneNumber;
    private Spinner shopTypeSpinner;
    private Geocoder geocoder;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopowner);

        initializeFirebase();
        initializeViews();
        setupLocationServices();
        setupSpinner();
        setupListeners();
    }

    private void initializeFirebase() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        shopId = user != null ? user.getUid() : "";
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void initializeViews() {
        shopPhoneNumber = findViewById(R.id.shopPhoneNumberEditText);
        shopTypeSpinner = findViewById(R.id.shopTypeSpinner);
        goLiveButton = findViewById(R.id.toggleStatusButton);
        shopStatusTextView = findViewById(R.id.shopStatusTextView);
        shopName = findViewById(R.id.shopNameEditText);
        shopAddress = findViewById(R.id.shopAddressEditText);
    }

    private void setupLocationServices() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        geocoder = new Geocoder(this, Locale.getDefault());
    }

    private void setupSpinner() {
        List<String> shopTypes = new ArrayList<>();
        shopTypes.add("Grocery");
        shopTypes.add("Electronics");
        shopTypes.add("Clothing");
        shopTypes.add("Restaurant");
        shopTypes.add("Pharmacy");
        // Add more shop types as needed

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, shopTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shopTypeSpinner.setAdapter(adapter);
    }

    private void setupListeners() {
        goLiveButton.setOnClickListener(v -> toggleLiveStatus());
        Button getDetails = findViewById(R.id.saveDetailsButton);
        Button getLocation = findViewById(R.id.getLocationButton);
        getLocation.setOnClickListener(view -> getLastLocation());
        getDetails.setOnClickListener(view -> saveShopDetails());

        DatabaseReference shopRef = mDatabase.child("shops").child(shopId);
        shopRef.child("isLive").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean isLive = dataSnapshot.getValue(Boolean.class);
                updateUILiveStatus(isLive);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ShopOwnerActivity.this, "Failed to load live status.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toggleLiveStatus() {
        DatabaseReference shopRef = mDatabase.child("shops").child(shopId);
        shopRef.child("isLive").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean currentStatus = dataSnapshot.getValue(Boolean.class);
                shopRef.child("isLive").setValue(currentStatus == null || !currentStatus);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ShopOwnerActivity.this, "Failed to update status.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUILiveStatus(Boolean isLive) {
        if (isLive != null && isLive) {
            shopStatusTextView.setText("Shop is live");
            shopStatusTextView.setTextColor(Color.GREEN);
            goLiveButton.setText("Go Offline");
        } else {
            shopStatusTextView.setText("Shop is not live");
            shopStatusTextView.setTextColor(Color.RED);
            goLiveButton.setText("Go Live");
        }
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        String locationString = location.getLatitude() + "," + location.getLongitude();
                        DatabaseReference shopRef = mDatabase.child("shops").child(shopId);
                        shopRef.child("location").setValue(locationString);

                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (!addresses.isEmpty()) {
                                Address address = addresses.get(0);
                                String addressText = address.getAddressLine(0);
                                shopAddress.setText(addressText);
                                shopRef.child("address").setValue(addressText);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(ShopOwnerActivity.this, "Location saved", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ShopOwnerActivity.this, "Failed to retrieve location", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveShopDetails() {
        String name = shopName.getText().toString().trim();
        String address = shopAddress.getText().toString().trim();
        String phoneNumber = shopPhoneNumber.getText().toString().trim();
        String type = shopTypeSpinner.getSelectedItem().toString();

        if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !type.isEmpty()) {
            Shop shop = new Shop(shopId, name, address, "", phoneNumber, type, false, "");

            DatabaseReference shopRef = mDatabase.child("shops").child(shopId);
            shopRef.setValue(shop).addOnSuccessListener(aVoid -> {
                DatabaseReference categoryRef = mDatabase.child(type).child(shopId);
                categoryRef.child("name").setValue(name);
                categoryRef.child("address").setValue(address);
                categoryRef.child("phoneNumber").setValue(phoneNumber);
                categoryRef.child("type").setValue(type);
                categoryRef.child("isLive").setValue(false);

                Toast.makeText(this, "Shop details saved", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                Toast.makeText(this, "Failed to save shop details: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}