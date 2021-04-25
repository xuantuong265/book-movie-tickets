package model;

import java.io.Serializable;

public class Products implements Serializable {

    private String key, name, img, price_current, price_change;
    private int amount;

    public Products() {
    }

    public Products(String key, String name, String img, String price_current, String price_change, int amount) {
        this.key = key;
        this.name = name;
        this.img = img;
        this.price_current = price_current;
        this.price_change = price_change;
        this.amount = amount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice_current() {
        return price_current;
    }

    public void setPrice_current(String price_current) {
        this.price_current = price_current;
    }

    public String getPrice_change() {
        return price_change;
    }

    public void setPrice_change(String price_change) {
        this.price_change = price_change;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
