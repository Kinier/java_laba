package com.example.kyrsach;

public class ManufacturerItem {
    private String id;
    private String name;
    private String city;

    public ManufacturerItem(String id, String name, String city){
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
