package com.company;

public class Hyundai extends Car {
    public Hyundai(int id, String model, int price){
        this.id = id;
        this.model = model;
        this.price = price;
        this.resalevalue = price * 0.8;
    }
}
