
package com.example.bizflash_login_firestore;

public class Shop {
    private String shopId;
    private String name;
    private String address;
    private String location;
    private String phoneNumber;
    private String type;  // Changed from shopType to type
    private boolean isLive;
    private String currentOffer;

    // Default constructor required for calls to DataSnapshot.getValue(Shop.class)
    public Shop() {}

    public Shop(String shopId, String name, String address, String location,
                String phoneNumber, String type, boolean isLive, String currentOffer) {
        this.shopId = shopId;
        this.name = name;
        this.address = address;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.type = type;  // Changed from shopType to type
        this.isLive = isLive;
        this.currentOffer = currentOffer;
    }

    // Getters and setters
    public String getShopId() { return shopId; }
    public void setShopId(String shopId) { this.shopId = shopId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getType() { return type; }  // Changed from getShopType to getType
    public void setType(String type) { this.type = type; }  // Changed from setShopType to setType

    public boolean isLive() { return isLive; }
    public void setLive(boolean live) { isLive = live; }

    public String getCurrentOffer() { return currentOffer; }
    public void setCurrentOffer(String currentOffer) { this.currentOffer = currentOffer; }

    // Calculate status dynamically based on isLive
    public String getStatus() {
        return isLive ? "Live" : "Offline";
    }
}