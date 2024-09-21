//////package com.example.bizflash_login_firestore;
//////
//////import android.graphics.Color;
//////import android.os.Bundle;
//////import android.support.annotation.NonNull;
//////import android.widget.Button;
//////import android.widget.EditText;
//////import android.widget.TextView;
//////import android.widget.Toast;
//////
//////import androidx.appcompat.app.AppCompatActivity;
//////
//////import com.google.android.gms.location.FusedLocationProviderClient;
//////import com.google.android.gms.location.LocationServices;
//////import com.google.android.gms.tasks.OnFailureListener;
//////import com.google.android.gms.tasks.OnSuccessListener;
//////import com.google.firebase.auth.FirebaseAuth;
//////import com.google.firebase.auth.FirebaseUser;
//////import com.google.firebase.database.DataSnapshot;
//////import com.google.firebase.database.DatabaseError;
//////import com.google.firebase.database.DatabaseReference;
//////import com.google.firebase.database.FirebaseDatabase;
//////import com.google.firebase.database.ValueEventListener;
//////
//////////package com.example.bizflash_login_firestore;
//////////
//////////import android.Manifest;
//////////import android.content.pm.PackageManager;
//////////import android.graphics.Color;
//////////import android.location.Location;
//////////import android.os.Bundle;
//////////import android.view.View;
//////////import android.widget.Button;
//////////import android.widget.EditText;
//////////import android.widget.TextView;
//////////import android.widget.Toast;
//////////
//////////import androidx.annotation.NonNull;
//////////import androidx.appcompat.app.AppCompatActivity;
//////////import androidx.core.app.ActivityCompat;
//////////
//////////import com.google.android.gms.location.FusedLocationProviderClient;
//////////import com.google.android.gms.location.LocationServices;
//////////import com.google.android.gms.tasks.OnSuccessListener;
//////////import com.google.firebase.auth.FirebaseAuth;
//////////import com.google.firebase.auth.FirebaseUser;
//////////import com.google.firebase.database.DataSnapshot;
//////////import com.google.firebase.database.DatabaseError;
//////////import com.google.firebase.database.DatabaseReference;
//////////import com.google.firebase.database.FirebaseDatabase;
//////////import com.google.firebase.database.ValueEventListener;
//////////
//////////public class ShopOwnerActivity extends AppCompatActivity {
//////////
//////////        private DatabaseReference shopRef;
//////////        private String shopId;
//////////        private Button goLiveButton;
//////////        private TextView shopStatusTextView;
//////////
//////////        @Override
//////////        protected void onCreate(Bundle savedInstanceState) {
//////////            super.onCreate(savedInstanceState);
//////////            setContentView(R.layout.activity_shopowner);
//////////
//////////            FirebaseAuth auth = FirebaseAuth.getInstance();
//////////            FirebaseUser user = auth.getCurrentUser();
//////////            shopId = user.getUid(); // Using user ID as shop ID
//////////
//////////            shopRef = FirebaseDatabase.getInstance().getReference().child("shops").child(shopId);
//////////
//////////            goLiveButton = findViewById(R.id.toggleStatusButton);
//////////            shopStatusTextView = findViewById(R.id.shopStatusTextView);
//////////
//////////            goLiveButton.setOnClickListener(new View.OnClickListener() {
//////////                @Override
//////////                public void onClick(View v) {
//////////                    toggleLiveStatus();
//////////                }
//////////            });
//////////
//////////            // Listen for changes in live status
//////////            shopRef.child("isLive").addValueEventListener(new ValueEventListener() {
//////////                @Override
//////////                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//////////                    Boolean isLive = dataSnapshot.getValue(Boolean.class);
//////////                    updateUILiveStatus(isLive);
//////////                }
//////////
//////////                @Override
//////////                public void onCancelled(@NonNull DatabaseError databaseError) {
//////////                    // Handle error
//////////                }
//////////            });
//////////
//////////
//////////
//////////
//////////    @Override
//////////    protected void onCreate(Bundle savedInstanceState) {
//////////        super.onCreate(savedInstanceState);
//////////        setContentView(R.layout.activity_shopowner);
//////////
//////////        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//////////        shopRef = FirebaseDatabase.getInstance().getReference().child("shops");
//////////
//////////        Button getDetails = findViewById(R.id.saveDetailsButton);
//////////        Button getLocation = findViewById(R.id.getLocationButton);
//////////        Button announceButton = findViewById(R.id.announceOfferButton);
//////////        Button goLiveButton = findViewById(R.id.toggleStatusButton);
//////////        shopName = findViewById(R.id.shopNameEditText);
//////////        shopAddress = findViewById(R.id.shopAddressEditText);
//////////
//////////        getLocation.setOnClickListener(new View.OnClickListener() {
//////////            @Override
//////////            public void onClick(View view) {
//////////                getLastLocation();
//////////            }
//////////        });
//////////
//////////        getDetails.setOnClickListener(new View.OnClickListener() {
//////////            @Override
//////////            public void onClick(View view) {
//////////                saveShopDetails();
//////////            }
//////////        });
//////////    }
//////////    private void toggleLiveStatus() {
//////////        shopRef.child("isLive").get().addOnCompleteListener(task -> {
//////////            if (task.isSuccessful()) {
//////////                Boolean currentStatus = task.getResult().getValue(Boolean.class);
//////////                shopRef.child("isLive").setValue(currentStatus == null || !currentStatus);
//////////            }
//////////        });
//////////    }
//////////    }
//////////
//////////    private void updateUILiveStatus(Boolean isLive) {
//////////        if (isLive != null && isLive) {
//////////            shopStatusTextView.setText("Shop is live");
//////////            shopStatusTextView.setTextColor(Color.GREEN);
//////////            goLiveButton.setText("Go Offline");
//////////        } else {
//////////            shopStatusTextView.setText("Shop is not live");
//////////            shopStatusTextView.setTextColor(Color.RED);
//////////            goLiveButton.setText("Go Live");
//////////        }
//////////    }
//////////
//////////    // Add methods to save other shop details...
//////////
//////////
//////////    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
//////////    private FusedLocationProviderClient fusedLocationClient;
//////////
//////////    private EditText shopName, shopAddress;
//////////    private void getLastLocation() {
//////////        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//////////            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
//////////            return;
//////////        }
//////////
//////////        fusedLocationClient.getLastLocation()
//////////                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//////////                    @Override
//////////                    public void onSuccess(Location location) {
//////////                        if (location != null) {
//////////                            String locationString = location.getLatitude() + "," + location.getLongitude();
//////////                            shopRef.child(shopName.getText().toString()).child("location").setValue(locationString);
//////////                            Toast.makeText(ShopOwnerActivity.this, "Location saved", Toast.LENGTH_SHORT).show();
//////////                        }
//////////                    }
//////////                });
//////////    }
//////////
//////////    private void saveShopDetails() {
//////////        String name = shopName.getText().toString().trim();
//////////        String address = shopAddress.getText().toString().trim();
//////////
//////////        if (!name.isEmpty() && !address.isEmpty()) {
//////////            DatabaseReference currentShopRef = shopRef.child(name);
//////////            currentShopRef.child("name").setValue(name);
//////////            currentShopRef.child("address").setValue(address);
//////////            Toast.makeText(this, "Shop details saved", Toast.LENGTH_SHORT).show();
//////////        } else {
//////////            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//////////        }
//////////    }
//////////
//////////    @Override
//////////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//////////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//////////        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
//////////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//////////                getLastLocation();
//////////            } else {
//////////                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
//////////            }
//////////        }
//////////    }
//////////}
//////////package com.example.bizflash_login_firestore;
//////////
//////////import android.Manifest;
//////////import android.content.pm.PackageManager;
//////////import android.graphics.Color;
//////////import android.location.Location;
//////////import android.os.Bundle;
//////////import android.view.View;
//////////import android.widget.Button;
//////////import android.widget.EditText;
//////////import android.widget.TextView;
//////////import android.widget.Toast;
//////////
//////////import androidx.annotation.NonNull;
//////////import androidx.appcompat.app.AppCompatActivity;
//////////import androidx.core.app.ActivityCompat;
//////////
//////////import com.google.android.gms.location.FusedLocationProviderClient;
//////////import com.google.android.gms.location.LocationServices;
//////////import com.google.android.gms.tasks.OnFailureListener;
//////////import com.google.android.gms.tasks.OnSuccessListener;
//////////import com.google.firebase.auth.FirebaseAuth;
//////////import com.google.firebase.auth.FirebaseUser;
//////////import com.google.firebase.database.DataSnapshot;
//////////import com.google.firebase.database.DatabaseError;
//////////import com.google.firebase.database.DatabaseReference;
//////////import com.google.firebase.database.FirebaseDatabase;
//////////import com.google.firebase.database.ValueEventListener;
//////////
//////////public class ShopOwnerActivity extends AppCompatActivity {
//////////    private DatabaseReference mDatabase;
//////////    private FirebaseAuth mAuth;
//////////    private DatabaseReference shopRef;
//////////    private String shopId;
//////////    private Button goLiveButton;
//////////    private TextView shopStatusTextView;
//////////
//////////    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
//////////    private FusedLocationProviderClient fusedLocationClient;
//////////    private EditText shopName, shopAddress;
//////////
//////////    @Override
//////////    protected void onCreate(Bundle savedInstanceState) {
//////////        super.onCreate(savedInstanceState);
//////////        setContentView(R.layout.activity_shopowner);
//////////
//////////        FirebaseAuth auth = FirebaseAuth.getInstance();
//////////        FirebaseUser user = auth.getCurrentUser();
//////////        shopId = user != null ? user.getUid() : ""; // Ensure shopId is not null
//////////
//////////        shopRef = FirebaseDatabase.getInstance().getReference().child("shops").child(shopId);
//////////
//////////        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//////////
//////////        goLiveButton = findViewById(R.id.toggleStatusButton);
//////////        shopStatusTextView = findViewById(R.id.shopStatusTextView);
//////////        shopName = findViewById(R.id.shopNameEditText);
//////////        shopAddress = findViewById(R.id.shopAddressEditText);
//////////        mAuth = FirebaseAuth.getInstance();
//////////        mDatabase = FirebaseDatabase.getInstance().getReference();
//////////
//////////        // Get the current user's ID (assuming this is the shop ID)
//////////        String shopId = mAuth.getCurrentUser().getUid();
//////////
//////////        // Create a shop object
//////////        Shop shop = new Shop(
//////////                "Shop Name",
//////////                "Shop Address",
//////////                "Latitude,Longitude",
//////////                "1234567890",
//////////                "Grocery",
//////////                false,
//////////                "Current Offer"
//////////        );
//////////        // Add the shop to the database
//////////        mDatabase.child("shops").child(shopId).setValue(shop)
//////////                .addOnSuccessListener(new OnSuccessListener<Void>() {
//////////                    @Override
//////////                    public void onSuccess(Void aVoid) {
//////////                        Toast.makeText(ShopOwnerActivity.this, "Shop data saved successfully", Toast.LENGTH_SHORT).show();
//////////                    }
//////////                })
//////////                .addOnFailureListener(new OnFailureListener() {
//////////                    @Override
//////////                    public void onFailure(@NonNull Exception e) {
//////////                        Toast.makeText(ShopOwnerActivity.this, "Failed to save shop data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//////////                    }
//////////                });
//////////        goLiveButton.setOnClickListener(new View.OnClickListener() {
//////////            @Override
//////////            public void onClick(View v) {
//////////                toggleLiveStatus();
//////////            }
//////////        });
//////////
//////////        // Listen for changes in live status
//////////        shopRef.child("isLive").addValueEventListener(new ValueEventListener() {
//////////            @Override
//////////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//////////                Boolean isLive = dataSnapshot.getValue(Boolean.class);
//////////                updateUILiveStatus(isLive);
//////////            }
//////////
//////////            @Override
//////////            public void onCancelled(@NonNull DatabaseError databaseError) {
//////////                // Handle error
//////////                Toast.makeText(ShopOwnerActivity.this, "Failed to load live status.", Toast.LENGTH_SHORT).show();
//////////            }
//////////        });
//////////
//////////        Button getDetails = findViewById(R.id.saveDetailsButton);
//////////        Button getLocation = findViewById(R.id.getLocationButton);
//////////
//////////        getLocation.setOnClickListener(new View.OnClickListener() {
//////////            @Override
//////////            public void onClick(View view) {
//////////                getLastLocation();
//////////            }
//////////        });
//////////
//////////        getDetails.setOnClickListener(new View.OnClickListener() {
//////////            @Override
//////////            public void onClick(View view) {
//////////                saveShopDetails();
//////////            }
//////////        });
//////////    }
//////////
//////////    private void toggleLiveStatus() {
//////////        shopRef.child("isLive").get().addOnCompleteListener(task -> {
//////////            if (task.isSuccessful()) {
//////////                Boolean currentStatus = task.getResult().getValue(Boolean.class);
//////////                shopRef.child("isLive").setValue(currentStatus == null || !currentStatus);
//////////            } else {
//////////                Toast.makeText(ShopOwnerActivity.this, "Failed to update status.", Toast.LENGTH_SHORT).show();
//////////            }
//////////        });
//////////    }
//////////
//////////    private void updateUILiveStatus(Boolean isLive) {
//////////        if (isLive != null && isLive) {
//////////            shopStatusTextView.setText("Shop is live");
//////////            shopStatusTextView.setTextColor(Color.GREEN);
//////////            goLiveButton.setText("Go Offline");
//////////        } else {
//////////            shopStatusTextView.setText("Shop is not live");
//////////            shopStatusTextView.setTextColor(Color.RED);
//////////            goLiveButton.setText("Go Live");
//////////        }
//////////    }
//////////
//////////    private void getLastLocation() {
//////////        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//////////            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
//////////            return;
//////////        }
//////////
//////////        fusedLocationClient.getLastLocation()
//////////                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//////////                    @Override
//////////                    public void onSuccess(Location location) {
//////////                        if (location != null) {
//////////                            String locationString = location.getLatitude() + "," + location.getLongitude();
//////////                            shopRef.child("location").setValue(locationString);
//////////                            Toast.makeText(ShopOwnerActivity.this, "Location saved", Toast.LENGTH_SHORT).show();
//////////                        } else {
//////////                            Toast.makeText(ShopOwnerActivity.this, "Failed to retrieve location", Toast.LENGTH_SHORT).show();
//////////                        }
//////////                    }
//////////                });
//////////    }
//////////
//////////    private void saveShopDetails() {
//////////        String name = shopName.getText().toString().trim();
//////////        String address = shopAddress.getText().toString().trim();
//////////
//////////        if (!name.isEmpty() && !address.isEmpty()) {
//////////            shopRef.child("name").setValue(name);
//////////            shopRef.child("address").setValue(address);
//////////            Toast.makeText(this, "Shop details saved", Toast.LENGTH_SHORT).show();
//////////        } else {
//////////            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//////////        }
//////////    }
//////////
//////////    @Override
//////////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//////////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//////////        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
//////////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//////////                getLastLocation();
//////////            } else {
//////////                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
//////////            }
//////////        }
//////////    }
//////////}
//////
//////
//////import android.Manifest;
//////import android.content.pm.PackageManager;
//////import android.location.Location;
//////import android.view.View;
//////
//////import androidx.core.app.ActivityCompat;
//////
//////public class ShopOwnerActivity extends AppCompatActivity {
//////    private DatabaseReference mDatabase;
//////    private FirebaseAuth mAuth;
//////    private DatabaseReference shopRef;
//////    private String shopId;
//////    private Button goLiveButton;
//////    private TextView shopStatusTextView;
//////
//////    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
//////    private FusedLocationProviderClient fusedLocationClient;
//////    private EditText shopName, shopAddress;
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_shopowner);
//////
//////        mAuth = FirebaseAuth.getInstance();
//////        FirebaseUser user = mAuth.getCurrentUser();
//////        shopId = user != null ? user.getUid() : "";
//////
//////        mDatabase = FirebaseDatabase.getInstance().getReference();
//////        shopRef = mDatabase.child("shops").child(shopId);
//////
//////        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//////
//////        goLiveButton = findViewById(R.id.toggleStatusButton);
//////        shopStatusTextView = findViewById(R.id.shopStatusTextView);
//////        shopName = findViewById(R.id.shopNameEditText);
//////        shopAddress = findViewById(R.id.shopAddressEditText);
//////
//////        // Create a shop object
//////        Shop shop = new Shop(
//////                shopId,
//////                "Shop Name",
//////                "Shop Address",
//////                "Latitude,Longitude",
//////                "1234567890",
//////                "Grocery",
//////                false,
//////                "Current Offer"
//////        );
//////
//////        // Add the shop to the database
//////        shopRef.setValue(shop)
//////                .addOnSuccessListener(new OnSuccessListener<Void>() {
//////                    @Override
//////                    public void onSuccess(Void aVoid) {
//////                        Toast.makeText(ShopOwnerActivity.this, "Shop data saved successfully", Toast.LENGTH_SHORT).show();
//////                    }
//////                })
//////                .addOnFailureListener(new OnFailureListener() {
//////                    @Override
//////                    public void onFailure(@NonNull Exception e) {
//////                        Toast.makeText(ShopOwnerActivity.this, "Failed to save shop data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//////                    }
//////                });
//////        private void toggleLiveStatus() {
//////        shopRef.child("status").addListenerForSingleValueEvent(new ValueEventListener() {
//////            @Override
//////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//////                String currentStatus = dataSnapshot.getValue(String.class);
//////                String newStatus = "Live".equals(currentStatus) ? "Offline" : "Live";
//////                shopRef.child("status").setValue(newStatus);
//////            }
//////
//////            @Override
//////            public void onCancelled(@NonNull DatabaseError databaseError) {
//////                Toast.makeText(ShopOwnerActivity.this, "Failed to update status.", Toast.LENGTH_SHORT).show();
//////            }
//////
//////            )};
//////        }
//////        goLiveButton.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                toggleLiveStatus();
//////            }
//////        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////        // Listen for changes in live status
//////        shopRef.child("isLive").addValueEventListener(new ValueEventListener() {
//////            @Override
//////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//////                Boolean isLive = dataSnapshot.getValue(Boolean.class);
//////                updateUILiveStatus(isLive);
//////            }
//////
//////            @Override
//////            public void onCancelled(@NonNull DatabaseError databaseError) {
//////                Toast.makeText(ShopOwnerActivity.this, "Failed to load live status.", Toast.LENGTH_SHORT).show();
//////            }
//////        });
//////
//////        Button getDetails = findViewById(R.id.saveDetailsButton);
//////        Button getLocation = findViewById(R.id.getLocationButton);
//////
//////        getLocation.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                getLastLocation();
//////            }
//////        });
//////
//////        getDetails.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                saveShopDetails();
//////            }
//////        });
//////    }
//////
//////    private void toggleLiveStatus() {
//////        shopRef.child("isLive").addListenerForSingleValueEvent(new ValueEventListener() {
//////            @Override
//////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//////                Boolean currentStatus = dataSnapshot.getValue(Boolean.class);
//////                shopRef.child("isLive").setValue(currentStatus == null || !currentStatus);
//////            }
//////
//////            @Override
//////            public void onCancelled(@NonNull DatabaseError databaseError) {
//////                Toast.makeText(ShopOwnerActivity.this, "Failed to update status.", Toast.LENGTH_SHORT).show();
//////            }
//////        });
//////    }
//////            private void updateUILiveStatus(String status) {
//////        if ("Live".equals(status)) {
//////            shopStatusTextView.setText("Shop is live");
//////            shopStatusTextView.setTextColor(Color.GREEN);
//////            goLiveButton.setText("Go Offline");
//////        } else {
//////            shopStatusTextView.setText("Shop is not live");
//////            shopStatusTextView.setTextColor(Color.RED);
//////            goLiveButton.setText("Go Live");
//////        }
//////    }
//////
//////    // Update the ValueEventListener in onCreate()
//////    shopRef.child("status").addValueEventListener(new ValueEventListener() {
//////        @Override
//////        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//////            String status = dataSnapshot.getValue(String.class);
//////            updateUILiveStatus(status);
//////        }
//////
//////        @Override
//////        public void onCancelled(@NonNull DatabaseError databaseError) {
//////            Toast.makeText(ShopOwnerActivity.this, "Failed to load live status.", Toast.LENGTH_SHORT).show();
//////        }
//////    });
//////    private void updateUILiveStatus(Boolean isLive) {
//////        if (isLive != null && isLive) {
//////            shopStatusTextView.setText("Shop is live");
//////            shopStatusTextView.setTextColor(Color.GREEN);
//////            goLiveButton.setText("Go Offline");
//////        } else {
//////            shopStatusTextView.setText("Shop is not live");
//////            shopStatusTextView.setTextColor(Color.RED);
//////            goLiveButton.setText("Go Live");
//////        }
//////    }
//////
//////    private void getLastLocation() {
//////        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//////            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
//////            return;
//////        }
//////
//////        fusedLocationClient.getLastLocation()
//////                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//////                    @Override
//////                    public void onSuccess(Location location) {
//////                        if (location != null) {
//////                            String locationString = location.getLatitude() + "," + location.getLongitude();
//////                            shopRef.child("location").setValue(locationString);
//////                            Toast.makeText(ShopOwnerActivity.this, "Location saved", Toast.LENGTH_SHORT).show();
//////                        } else {
//////                            Toast.makeText(ShopOwnerActivity.this, "Failed to retrieve location", Toast.LENGTH_SHORT).show();
//////                        }
//////                    }
//////                });
//////    }
//////
//////    private void saveShopDetails() {
//////        String name = shopName.getText().toString().trim();
//////        String address = shopAddress.getText().toString().trim();
//////
//////        if (!name.isEmpty() && !address.isEmpty()) {
//////            shopRef.child("name").setValue(name);
//////            shopRef.child("address").setValue(address);
//////            Toast.makeText(this, "Shop details saved", Toast.LENGTH_SHORT).show();
//////        } else {
//////            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//////        }
//////    }
//////
//////    @Override
//////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//////        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
//////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//////                getLastLocation();
//////            } else {
//////                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
//////            }
//////        }
//////    }
//////}
////////public class ShopOwnerActivity extends AppCompatActivity {
////////    // ... other code ...
////////    private DatabaseReference mDatabase;
////////    private FirebaseAuth mAuth;
////////    private DatabaseReference shopRef;
////////    private String shopId;
////////    private Button goLiveButton;
////////    private TextView shopStatusTextView;
////////
////////    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
////////    private FusedLocationProviderClient fusedLocationClient;
////////    private EditText shopName, shopAddress;
////////
////////    @Override
////////    protected void onCreate(Bundle savedInstanceState) {
////////        super.onCreate(savedInstanceState);
////////        setContentView(R.layout.activity_shopowner);
////////
////////        mAuth = FirebaseAuth.getInstance();
////////        FirebaseUser user = mAuth.getCurrentUser();
////////        shopId = user != null ? user.getUid() : "";
////////
////////        mDatabase = FirebaseDatabase.getInstance().getReference();
////////        shopRef = mDatabase.child("shops").child(shopId);
////////
////////        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
////////
////////        goLiveButton = findViewById(R.id.toggleStatusButton);
////////        shopStatusTextView = findViewById(R.id.shopStatusTextView);
////////        shopName = findViewById(R.id.shopNameEditText);
////////        shopAddress = findViewById(R.id.shopAddressEditText);
////////
////////        // Create a shop object
////////        Shop shop = new Shop(
////////                shopId,
////////                "Shop Name",
////////                "Shop Address",
////////                "Latitude,Longitude",
////////                "1234567890",
////////                "Grocery",
////////                false,
////////                "Current Offer"
////////        );
////////
////////        // Add the shop to the database
////////        shopRef.setValue(shop)
////////                .addOnSuccessListener(new OnSuccessListener<Void>() {
////////                    @Override
////////                    public void onSuccess(Void aVoid) {
////////                        Toast.makeText(ShopOwnerActivity.this, "Shop data saved successfully", Toast.LENGTH_SHORT).show();
////////                    }
////////                })
////////                .addOnFailureListener(new OnFailureListener() {
////////                    @Override
////////                    public void onFailure(@NonNull Exception e) {
////////                        Toast.makeText(ShopOwnerActivity.this, "Failed to save shop data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
////////                    }
////////                });
////////
////////        private void toggleLiveStatus() {
////////            shopRef.child("status").addListenerForSingleValueEvent(new ValueEventListener() {
////////                @Override
////////                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////////                    String currentStatus = dataSnapshot.getValue(String.class);
////////                    String newStatus = "Live".equals(currentStatus) ? "Offline" : "Live";
////////                    shopRef.child("status").setValue(newStatus);
////////                }
////////
////////                @Override
////////                public void onCancelled(@NonNull DatabaseError databaseError) {
////////                    Toast.makeText(ShopOwnerActivity.this, "Failed to update status.", Toast.LENGTH_SHORT).show();
////////                }
////////            });
////////        }
////////
////////        private void updateUILiveStatus(boolean status){
////////            if ("Live".equals(status)) {
////////                shopStatusTextView.setText("Shop is live");
////////                shopStatusTextView.setTextColor(Color.GREEN);
////////                goLiveButton.setText("Go Offline");
////////            } else {
////////                shopStatusTextView.setText("Shop is not live");
////////                shopStatusTextView.setTextColor(Color.RED);
////////                goLiveButton.setText("Go Live");
////////            }
////////        }
////////
////////        // Update the ValueEventListener in onCreate()
////////        shopRef.child("status");
////////        shopRef.addValueEventListener(new ValueEventListener() {
////////            @Override
////////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////////                String status = dataSnapshot.getValue(String.class);
////////                updateUILiveStatus(status);
////////            }
////////
////////
////////            @Override
////////            public void onCancelled(@NonNull DatabaseError databaseError) {
////////                Toast.makeText(ShopOwnerActivity.this, "Failed to load live status.", Toast.LENGTH_SHORT).show();
////////            }
////////        });
////////
////////        // ... rest of the code ...
////////    }
////////
////////    private void updateUILiveStatus(String status) {
////////
////////    }
////////}
////package com.example.bizflash_login_firestore;
////import android.Manifest;
////import android.content.pm.PackageManager;
////import android.graphics.Color;
////import android.location.Location;
////import android.os.Bundle;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.TextView;
////import android.widget.Toast;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.core.app.ActivityCompat;
////
////import com.example.bizflash_login_firestore.R;
////import com.google.android.gms.location.FusedLocationProviderClient;
////import com.google.android.gms.location.LocationServices;
////import com.google.android.gms.tasks.OnSuccessListener;
////import com.google.common.net.InternetDomainName;
////import com.google.firebase.auth.FirebaseAuth;
////import com.google.firebase.auth.FirebaseUser;
////import com.google.firebase.database.DataSnapshot;
////import com.google.firebase.database.DatabaseError;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////import com.google.firebase.database.ValueEventListener;
////
////public class ShopOwnerActivity extends AppCompatActivity {
////
////    private DatabaseReference mDatabase;
////    private FirebaseAuth mAuth;
////    private DatabaseReference shop                                                                                                                                                                                                                        ;d;
////    private Button goLiveButton;
////    private TextView shopStatusTextView;
////    private FusedLocationProviderClient fusedLocationClient;
////    private EditText shopName, shopAddress;
////
////    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_shopowner);
////
////        mAuth = FirebaseAuth.getInstance();
////        FirebaseUser user = mAuth.getCurrentUser();
////        String shopId = user != null ? user.getUid() : "";
////
////        mDatabase = FirebaseDatabase.getInstance().getReference();
////        DatabaseReference shopRef = mDatabase.child("shops").child(shopId);
////
////        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
////
////        goLiveButton = findViewById(R.id.toggleStatusButton);
////        shopStatusTextView = findViewById(R.id.shopStatusTextView);
////        shopName = findViewById(R.id.shopNameEditText);
////        shopAddress = findViewById(R.id.shopAddressEditText);
////
////        // Create a shop object
////        Shop shop = new Shop(
////                shopId,
////                "Shop Name",
////                "Shop Address",
////                "Latitude,Longitude",
////                "1234567890",
////                "Grocery",
////                false,
////                "Current Offer"
////        );
////
////        // Add the shop to the database
////        shopRef.setValue(shop)
////                .addOnSuccessListener(new OnSuccessListener<Void>() {
////                    @Override
////                    public void onSuccess(Void aVoid) {
////                        Toast.makeText(ShopOwnerActivity.this, "Shop data saved successfully", Toast.LENGTH_SHORT).show();
////                    }
////                })
////                .addOnFailureListener(e -> Toast.makeText(ShopOwnerActivity.this, "Failed to save shop data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
////
////        goLiveButton.setOnClickListener(v -> toggleLiveStatus());
////
////        // Listen for changes in live status
////        shopRef.child("isLive").addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                Boolean isLive = dataSnapshot.getValue(Boolean.class);
////                updateUILiveStatus(isLive);
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////                Toast.makeText(ShopOwnerActivity.this, "Failed to load live status.", Toast.LENGTH_SHORT).show();
////            }
////        });
////
////        Button getDetails = findViewById(R.id.saveDetailsButton);
////        Button getLocation = findViewById(R.id.getLocationButton);
////
////        getLocation.setOnClickListener(view -> getLastLocation());
////
////        getDetails.setOnClickListener(view -> saveShopDetails());
////    }
////
////    private void toggleLiveStatus() {
////        InternetDomainName shopRef;
////        shopRef.child("isLive").addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                Boolean currentStatus = dataSnapshot.getValue(Boolean.class);
////                shopRef.child("isLive").setValue(currentStatus == null || !currentStatus);
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////                Toast.makeText(ShopOwnerActivity.this, "Failed to update status.", Toast.LENGTH_SHORT).show();
////            }
////        });
////    }
////
////    private void updateUILiveStatus(Boolean isLive) {
////        if (isLive != null && isLive) {
////            shopStatusTextView.setText("Shop is live");
////            shopStatusTextView.setTextColor(Color.GREEN);
////            goLiveButton.setText("Go Offline");
////        } else {
////            shopStatusTextView.setText("Shop is not live");
////            shopStatusTextView.setTextColor(Color.RED);
////            goLiveButton.setText("Go Live");
////        }
////    }
////
////    private void getLastLocation() {
////        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
////            return;
////        }
////
////        fusedLocationClient.getLastLocation()
////                .addOnSuccessListener(this, location -> {
////                    if (location != null) {
////                        String locationString = location.getLatitude() + "," + location.getLongitude();
////                        InternetDomainName shopRef;
////                        shopRef.child("location").setValue(locationString);
////                        Toast.makeText(ShopOwnerActivity.this, "Location saved", Toast.LENGTH_SHORT).show();
////                    } else {
////                        Toast.makeText(ShopOwnerActivity.this, "Failed to retrieve location", Toast.LENGTH_SHORT).show();
////                    }
////                });
////    }
////
////    private void saveShopDetails() {
////        String name = shopName.getText().toString().trim();
////        String address = shopAddress.getText().toString().trim();
////
////        if (!name.isEmpty() && !address.isEmpty()) {
////            InternetDomainName shopRef;
////            shopRef.child("name").setValue(name);
////            shopRef.child("address").setValue(address);
////            Toast.makeText(this, "Shop details saved", Toast.LENGTH_SHORT).show();
////        } else {
////            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
////        }
////    }
////
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                getLastLocation();
////            } else {
////                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
////            }
////        }
////    }
////}
//package com.example.bizflash_login_firestore;
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.location.Location;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
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
//public class ShopOwnerActivity extends AppCompatActivity {
//
//    private DatabaseReference mDatabase;
//    private FirebaseAuth mAuth;
//    private DatabaseReference shopRef;
//    private String shopId;
//    private Button goLiveButton;
//    private TextView shopStatusTextView;
//    private FusedLocationProviderClient fusedLocationClient;
//    private EditText shopName, shopAddress;
//    private EditText shopPhoneNumber;
//    private EditText shopCategory;
//    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
//
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
//        shopPhoneNumber = findViewById(R.id.shopPhoneNumberEditText);
//        shopCategory = findViewById(R.id.shopTypeEditText);
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
//                "1234567890",
//                "Grocery",
//                false,
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
//        } else {
//            shopStatusTextView.setText("Shop is not live");
//            shopStatusTextView.setTextColor(Color.RED);
//            goLiveButton.setText("Go Live");
//        }
//    }
////databaseReference.addValueEventListener(new ValueEventListener() {
////        @Override
////        public void onDataChange(@NonNull DataSnapshot snapshot) {
////            Shop shop = snapshot.getValue(Shop.class);
////            if (shop != null) {
////                String shopStatus = shop.getStatus();
////                // Use shopStatus as needed
////            }
////        }
////        // ...
////    });
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
//                        Toast.makeText(ShopOwnerActivity.this, "Location saved", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(ShopOwnerActivity.this, "Failed to retrieve location", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    private void saveShopDetails() {
//        String name = shopName.getText().toString().trim();
//        String address = shopAddress.getText().toString().trim();
//
//        if (!name.isEmpty() && !address.isEmpty()) {
//            shopRef.child("name").setValue(name);
//            shopRef.child("address").setValue(address);
//            Toast.makeText(this, "Shop details saved", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//        }
//    }
//
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
//
package com.example.bizflash_login_firestore;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ShopOwnerActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference shopRef;
    private String shopId;
    private Button goLiveButton;
    private TextView shopStatusTextView;
    private FusedLocationProviderClient fusedLocationClient;
    private EditText shopName, shopAddress;
    private EditText shopPhoneNumber;
    private EditText shopType;
    private Geocoder geocoder;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopowner);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        shopId = user != null ? user.getUid() : "";

        mDatabase = FirebaseDatabase.getInstance().getReference();
        shopRef = mDatabase.child("shops").child(shopId);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        geocoder = new Geocoder(this, Locale.getDefault());

        shopPhoneNumber = findViewById(R.id.shopPhoneNumberEditText);
        shopType = findViewById(R.id.shopTypeEditText);
        goLiveButton = findViewById(R.id.toggleStatusButton);
        shopStatusTextView = findViewById(R.id.shopStatusTextView);
        shopName = findViewById(R.id.shopNameEditText);
        shopAddress = findViewById(R.id.shopAddressEditText);

        // Create a shop object
        Shop shop = new Shop(
                shopId,
                "Shop Name",
                "Shop Address",
                "Latitude,Longitude",
                "",  // Phone number
                "",  // Shop type
                false,
                "Current Offer"
        );

        // Add the shop to the database
        shopRef.setValue(shop)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ShopOwnerActivity.this, "Shop data saved successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(ShopOwnerActivity.this, "Failed to save shop data: " + e.getMessage(), Toast.LENGTH_SHORT).show());

        goLiveButton.setOnClickListener(v -> toggleLiveStatus());

        // Listen for changes in live status
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

        Button getDetails = findViewById(R.id.saveDetailsButton);
        Button getLocation = findViewById(R.id.getLocationButton);

        getLocation.setOnClickListener(view -> getLastLocation());

        getDetails.setOnClickListener(view -> saveShopDetails());
    }

    private void toggleLiveStatus() {
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
                        shopRef.child("location").setValue(locationString);

                        // Get address from location and update the EditText and database
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
        String type = shopType.getText().toString().trim();

        if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !type.isEmpty()) {
            shopRef.child("name").setValue(name);
            shopRef.child("address").setValue(address);
            shopRef.child("phoneNumber").setValue(phoneNumber);
            shopRef.child("type").setValue(type);
            Toast.makeText(this, "Shop details saved", Toast.LENGTH_SHORT).show();
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