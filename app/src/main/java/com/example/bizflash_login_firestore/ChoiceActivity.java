//package com.example.bizflash_login_firestore;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class ChoiceActivity extends AppCompatActivity {
//    private Button Shop_owner;
//    private Button Customer;
//
//    FirebaseAuth auth;
//    Button button;
//    TextView textView;
//    FirebaseUser user;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        auth = FirebaseAuth.getInstance();
//        button = findViewById(R.id.logout);
//        textView = findViewById(R.id.user_details);
//        Shop_owner = findViewById(R.id.button_Shop_owner);
//        Customer = findViewById(R.id.button_customer);
//        user = auth.getCurrentUser();
//        if (user == null) {
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//            finish();
//        } else {
//            textView.setText("Welcome " + user.getEmail().substring(0, user.getEmail().indexOf('@')) + "! Choose to be a Cutomer or a Shop-Owner");
//        }
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        Customer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
//                startActivity(intent);
//
//            }
//        });
//        Shop_owner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ChoiceActivity.this, ShopOwnerActivity.class);
//            }
//        });
//    }
//}
package com.example.bizflash_login_firestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChoiceActivity extends AppCompatActivity {
    private Button shopOwner;
    private Button customer;

    FirebaseAuth auth;
    Button logoutButton;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        auth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        shopOwner = findViewById(R.id.button_Shop_owner);
        customer = findViewById(R.id.button_customer);
        user = auth.getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText("Welcome " + user.getEmail().substring(0, user.getEmail().indexOf('@')) + "! Choose to be a Customer or a Shop-Owner");
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        shopOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, ShopOwnerActivity.class);
                startActivity(intent);
            }
        });
    }
}
