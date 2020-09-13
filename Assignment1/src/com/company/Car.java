package com.company;

public class Car {
    protected int id;
    protected String model;
    protected int price;
    protected Double resalevalue;

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public Double getResalevalue() {
        return resalevalue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setResalevalue(Double resalevalue) {
        this.resalevalue = resalevalue;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", resalevalue=" + resalevalue +
                '}';
    }
}


