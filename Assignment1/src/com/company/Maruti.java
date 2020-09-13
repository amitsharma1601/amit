package com.company;

public class Maruti extends Car {
    public Maruti(int id, String model, int price){
        this.id = id;
        this.model = model;
        this.price = price;
        this.resalevalue = price * 0.8;
    }
}
