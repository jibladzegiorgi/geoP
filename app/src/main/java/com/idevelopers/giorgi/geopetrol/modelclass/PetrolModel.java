package com.idevelopers.giorgi.geopetrol.modelclass;

import com.idevelopers.giorgi.geopetrol.R;

/**
 * Created by Giorgi on 2/14/2017.
 */

public class PetrolModel {
    private int id;
    private String company;
    private String product;
    private String price;
    private String updated;
    private String category;
    private int imagAdress;

    public PetrolModel(int id, String company, String product, String price, String updated, String category) {
        this.id = id;
        this.company = company;
        this.product = product;
        this.price = price;
        this.updated = updated;
        this.category = category;
        this.imagAdress= R.drawable.exaimage_new;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImagAdress() {
        return imagAdress;
    }

    public void setImagAdress(int imagAdress) {
        this.imagAdress = imagAdress;
    }
}
