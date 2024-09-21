//package com.example.bizflash_login_firestore;
//
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
//    private List<Shop> shopList;
//
//    public ShopAdapter(List<Shop> shopList) {
//        this.shopList = shopList;
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
//        Shop shop = shopList.get(position);
//        holder.shopNameTextView.setText(shop.getName());
//        holder.shopLocationTextView.setText(shop.getAddress());
//        holder.shopOfferTextView.setText(shop.getAnnounceOffer());
//
//        holder.itemView.setOnClickListener(v -> {
//            // Open detailed view of the shop
//            Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
//            intent.putExtra("shopId", shop.getShopId());
//            v.getContext().startActivity(intent);
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return shopList.size();
//    }
//
//    static class ShopViewHolder extends RecyclerView.ViewHolder {
//        TextView shopNameTextView, shopLocationTextView, shopOfferTextView;
//
//        ShopViewHolder(View itemView) {
//            super(itemView);
//            shopNameTextView = itemView.findViewById(R.id.shopNameTextView);
//            shopLocationTextView = itemView.findViewById(R.id.shopLocationTextView);
//            shopOfferTextView = itemView.findViewById(R.id.shopOfferTextView);
//        }
//    }
//}
package com.example.bizflash_login_firestore;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private List<Shop> shopList;

    public ShopAdapter(List<Shop> shopList) {
        this.shopList = shopList;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Shop shop = shopList.get(position);
        holder.bind(shop);
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public void updateShops(List<Shop> newShops) {
        this.shopList.clear();
        this.shopList.addAll(newShops);
        notifyDataSetChanged();
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {
        TextView shopNameTextView, shopLocationTextView, shopOfferTextView;

        ShopViewHolder(View itemView) {
            super(itemView);
            shopNameTextView = itemView.findViewById(R.id.shopNameTextView);
            shopLocationTextView = itemView.findViewById(R.id.shopLocationTextView);
            shopOfferTextView = itemView.findViewById(R.id.shopOfferTextView);
        }

        void bind(Shop shop) {
            shopNameTextView.setText(shop.getName());
            shopLocationTextView.setText(shop.getAddress());
            shopOfferTextView.setText(shop.getCurrentOffer());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                intent.putExtra("shopId", shop.getShopId());
                v.getContext().startActivity(intent);
            });
        }
    }
}