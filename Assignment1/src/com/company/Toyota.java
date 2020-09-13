package com.company;

public class Toyota extends Car {
    public Toyota(int id, String model, int price){
        this.id = id;
        this.model = model;
        this.price = price;
        this.resalevalue = price * 0.8;
    }
}
