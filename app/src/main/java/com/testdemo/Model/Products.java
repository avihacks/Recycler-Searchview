package com.testdemo.Model;

import com.google.gson.annotations.SerializedName;

public class Products {

    @SerializedName("productName")
    private String product_name;
    @SerializedName("price")
    private int price;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("unit")
    private String unit;
    @SerializedName("imageUrl")
    private String image_url;

    public Products(String product_name, int price, int quantity, String unit, String image_url) {
        this.product_name = product_name;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.image_url = image_url;
    }

    public Products() {
    }


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
