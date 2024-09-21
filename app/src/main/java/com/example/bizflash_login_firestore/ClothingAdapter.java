//package com.example.bizflash_login_firestore;
//
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.bizflash_login_firestore.R;
//import com.example.bizflash_login_firestore.Shop;
//
//import java.util.List;
//
//
//public class ClothingAdapter extends RecyclerView.Adapter<ClothingAdapter.ShopViewHolder> {
//
//
//    private List<Shop> shops;
//
//    public ClothingAdapter(List<Shop> shops) {
//        this.shops = shops;
//    }
//
//    @NonNull
//    @Override
//    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
//        return new ShopViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
//        Shop shop = shops.get(position);
//        holder.bind(shop);
//    }
//
//    @Override
//    public int getItemCount() {
//        return shops.size();
//    }
//
//    public static class ShopViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView shopNameTextView;
//        private TextView shopLocationTextView;
//        private TextView shopStatusTextView;
//        private TextView shopOfferTextView;
//
//        public ShopViewHolder(@NonNull View itemView) {
//            super(itemView);
//            shopNameTextView = itemView.findViewById(R.id.shopNameTextView);
//            shopLocationTextView = itemView.findViewById(R.id.shopLocationTextView);
//            shopStatusTextView = itemView.findViewById(R.id.shopStatusTextView);
//            shopOfferTextView = itemView.findViewById(R.id.shopOfferTextView);
//        }
//
//        public void bind(Shop shop) {
//            shopNameTextView.setText(shop.getName());
//            shopLocationTextView.setText(shop.getLocation());
//            shopStatusTextView.setText(shop.getStatus());
//            shopOfferTextView.setText(shop.getOffer());
//        }
//    }
//
//}
package com.example.bizflash_login_firestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ClothingAdapter extends RecyclerView.Adapter<ClothingAdapter.ShopViewHolder> {

    private List<Shop> shops;

    public ClothingAdapter(List<Shop> shops) {
        this.shops = shops;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Shop shop = shops.get(position);
        holder.bind(shop);
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public static class ShopViewHolder extends RecyclerView.ViewHolder {

        private TextView shopNameTextView;
        private TextView shopLocationTextView;
        private TextView shopStatusTextView;
        private TextView shopOfferTextView;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            shopNameTextView = itemView.findViewById(R.id.shopNameTextView);
            shopLocationTextView = itemView.findViewById(R.id.shopLocationTextView);
            shopStatusTextView = itemView.findViewById(R.id.shopStatusTextView);
            shopOfferTextView = itemView.findViewById(R.id.shopOfferTextView);
        }

        public void bind(Shop shop) {
            shopNameTextView.setText(shop.getName());
            shopLocationTextView.setText(shop.getLocation());
            shopStatusTextView.setText(shop.getStatus());
            shopOfferTextView.setText(shop.getCurrentOffer());
        }
    }
}
