package com.example.order_service.dto;

public class Order {
    private String orderId;
    private String orderName;
    private int quantity;
    private double price;

    public Order() {
    }

    public Order(String orderId, String orderName, int quantity, double price) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderName='" + orderName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
