////package com.example.bizflash_login_firestore;
////
////////package com.example.bizflash_login_firestore;
////////
////////import androidx.appcompat.app.AppCompatActivity;
////////
////////public class Shop extends AppCompatActivity {
////////    private int counter=0;
////////    private String name;
////////    private String location;
////////    private String status;
////////    private String offer;
////////
////////    // Default constructor required for calls to DataSnapshot.getValue(Shop.class)
////////    public Shop() {
////////    }
////////
////////    public Shop(String name, String location, String status, String offer) {
////////        this.name = name;
////////        this.location = location;
////////        this.status = status;
////////        this.offer = offer;
////////    }
////////
////////    public Shop(String shopName, String shopAddress, String s, String number, String grocery, boolean b, String currentOffer) {
////////    }
////////
////////    public String getName() {
////////        return name;
////////    }
////////
////////    public void setName(String name) {
////////        this.name = name;
////////    }
////////
////////    public String getLocation() {
////////        return location;
////////    }
////////
////////    public void setLocation(String location) {
////////        this.location = location;
////////    }
////////
////////    public String getStatus() {
////////        return status;
////////    }
////////
////////    public void setStatus(String status) {
////////        this.status = status;
////////    }
////////
////////    public String getOffer() {
////////        return offer;
////////    }
////////
////////    public void setOffer(String offer) {
////////        this.offer = offer;
////////    }
////////
////////    public boolean isLive() {
////////        if (status.equals("Live")){
////////            return true;
////////        }else{
////////            return false;
////////        }
////////        }
////////        public String getShopId() {
////////        return ("#"+(counter++));
////////
////////    }
////////
////////    public String getAddress() {
////////        return location;
////////    }
////////
////////    public String getAnnounceOffer() {
////////        return offer;
////////    }
////////}
//////package com.example.bizflash_login_firestore;
//////
//////public class Shop {
//////    private String shopId;
//////    private String name;
//////    private String address;
//////    private String location;
//////    private String phoneNumber;
//////    private String shopType;
//////    private boolean isLive;
//////    private String currentOffer;
//////
//////    // Default constructor required for calls to DataSnapshot.getValue(Shop.class)
//////    public Shop() {}
//////
//////    public Shop(String shopId, String name, String address, String location, String phoneNumber, String shopType, boolean isLive, String currentOffer) {
//////        this.shopId = shopId;
//////        this.name = name;
//////        this.address = address;
//////        this.location = location;
//////        this.phoneNumber = phoneNumber;
//////        this.shopType = shopType;
//////        this.isLive = isLive;
//////        this.currentOffer = currentOffer;
//////    }
//////
//////    // Getters and setters
//////    public String getShopId() { return shopId; }
//////    public void setShopId(String shopId) { this.shopId = shopId; }
//////
//////    public String getName() { return name; }
//////    public void setName(String name) { this.name = name; }
//////
//////    public String getAddress() { return address; }
//////    public void setAddress(String address) { this.address = address; }
//////
//////    public String getLocation() { return location; }
//////    public void setLocation(String location) { this.location = location; }
//////
//////    public String getPhoneNumber() { return phoneNumber; }
//////    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
//////
//////    public String getShopType() { return shopType; }
//////    public void setShopType(String shopType) { this.shopType = shopType; }
//////
//////    public boolean isLive() { return isLive; }
//////    public void setLive(boolean live) { isLive = live; }
//////
//////    public String getCurrentOffer() { return currentOffer; }
//////    public void setCurrentOffer(String currentOffer) { this.currentOffer = currentOffer; }
//////
//////    public boolean getStatus() {
//////        return (isLive);
//////    }
//////
//////    public String getOffer() {
//////        return currentOffer;
//////    }
//////}
//////public class Shop {
//////    // ... other fields ...
//////    private String status;
//////
//////    // Update constructor
//////    public Shop(String shopId, String name, String address, String location, String phoneNumber, String shopType, boolean status, String currentOffer) {
//////        // ... other assignments ...
//////        this.status = status;
//////    }
//////
//////    // Update getter and setter
//////    public String getStatus() { return status; }
//////    public void setStatus(String status) { this.status = status; }
//////
//////    // Remove isLive() method if it exists
//////}
////public class Shop {
////    private String shopId;
////    private String name;
////    private String address;
////    private String location;
////    private String phoneNumber;
////    private String shopType;
////    private boolean isLive;
////    private String currentOffer;
////    private String status;
////
////    public Shop(String shopId, String name, String address, String location, String phoneNumber, String shopType, boolean isLive, String currentOffer) {
////        this.shopId = shopId;
////        this.name = name;
////        this.address = address;
////        this.location = location;
////        this.phoneNumber = phoneNumber;
////        this.shopType = shopType;
////        this.isLive = isLive;
////        this.currentOffer = currentOffer;
////        this.status = isLive ? "Live" : "Offline";  // Assuming status is derived from isLive
////    }
////
////    public String getShopId() {
////        return shopId;
////    }
////
////    public void setShopId(String shopId) {
////        this.shopId = shopId;
////    }
////
////    public String getName() {
////        return name;
////    }
////
////    public void setName(String name) {
////        this.name = name;
////    }
////
////    public String getAddress() {
////        return address;
////    }
////
////    public void setAddress(String address) {
////        this.address = address;
////    }
////
////    public String getLocation() {
////        return location;
////    }
////
////    public void setLocation(String location) {
////        this.location = location;
////    }
////
////    public String getPhoneNumber() {
////        return phoneNumber;
////    }
////
////    public void setPhoneNumber(String phoneNumber) {
////        this.phoneNumber = phoneNumber;
////    }
////
////    public String getShopType() {
////        return shopType;
////    }
////
////    public void setShopType(String shopType) {
////        this.shopType = shopType;
////    }
////
////    public boolean isLive() {
////        return isLive;
////    }
////
////    public void setLive(boolean live) {
////        isLive = live;
////        setStatus(live ? "Live" : "Offline");
////    }
////
////    public String getCurrentOffer() {
////        return currentOffer;
////    }
////
////    public void setCurrentOffer(String currentOffer) {
////        this.currentOffer = currentOffer;
////    }
////
////    public String getStatus() {
////        return status;
////    }
////
////    public void setStatus(String status) {
////        this.status = status;
////    }
////}
//package com.example.bizflash_login_firestore;
//
//public class Shop {
//    private String shopId;
//    private String name;
//    private String address;
//    private String location;
//    private String phoneNumber;
//    private String shopType;
//    private boolean isLive;private String currentOffer;
//
//    // Default constructor required for calls to DataSnapshot.getValue(Shop.class)
//    public Shop() {}
//
//    public Shop(String shopId, String name, String address, String location,
//                String phoneNumber, String shopType, boolean isLive, String currentOffer) {
//        this.shopId = shopId;
//        this.name = name;
//        this.address = address;
//        this.location = location;
//        this.phoneNumber = phoneNumber;
//        this.shopType = shopType;
//        this.isLive = isLive;this.currentOffer = currentOffer;
//    }
//
//    // Getters and setters
//    public String getShopId() { return shopId; }
//    public void setShopId(String shopId) { this.shopId = shopId; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getAddress() { return address; }
//    public void setAddress(String address) { this.address = address; }
//
//    public String getLocation() { return location; }
//    public void setLocation(String location) { this.location = location; }
//
//    public String getPhoneNumber() { return phoneNumber; }
//    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
//
//    public String getShopType() { return shopType; }
//    public void setShopType(String shopType) { this.shopType = shopType; }
//
//    public boolean isLive() { return isLive; }
//    public void setLive(boolean live) { isLive = live; }
//
//    public String getCurrentOffer() { return currentOffer; }
//    public void setCurrentOffer(String currentOffer) { this.currentOffer = currentOffer; }
//
//    // Calculate status dynamically based on isLive
//    public String getStatus() {
//        return isLive ? "Live" : "Offline";
//    }
//}
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