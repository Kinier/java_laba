package com.example.kyrsach;

public class ProductItem {
    private String id;
    private String user_id;
    private String manufacturer_id;

    private String name;
    private String amount;
    private String price;

    public ProductItem(String id, String user_id, String manufacturer_id, String name, String amount, String price){
        this.id = id;
        this.user_id = user_id;
        this.manufacturer_id = manufacturer_id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public String getManufacturer_id() {
        return manufacturer_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getPrice() {
        return price;
    }
}
