package com.tisuela;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {


    private String name;

    private BigDecimal price;
    protected BigDecimal taxRate = new BigDecimal(0.1);
    private BigDecimal salesTax;
    private BigDecimal fullPrice;

    public Item(String name, BigDecimal price, BigDecimal taxRate){
        this.name = name;
        this.price = price;
        this.taxRate = taxRate;

        // set salesTax before fullPrice
        this.setSalesTax();

        // after setting salesTax. set fullPrice
        this.setFullPrice();
    }

    public Item(Item item){
        this(item.name, item.price, item.taxRate);
    }


    public Item(String name, double price){
        this.name = name;
        this.price = new BigDecimal(price).setScale(
                2,
                RoundingMode.HALF_UP
        );;

        // set salesTax before fullPrice
        this.setSalesTax();

        // after setting salesTax. set fullPrice
        this.setFullPrice();
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        BigDecimal decimalPrice = new BigDecimal(price).setScale(
                2,
                RoundingMode.HALF_UP
        );
        this.price = decimalPrice;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;

        // propogate changes to sales tax and final price
        this.setSalesTax();
        this.setFullPrice();
    }

    public void setSalesTax() {
        this.salesTax = this.price.multiply(this.taxRate).setScale(
                2,
                RoundingMode.HALF_UP
        );
    }

    public void setFullPrice() {
        this.fullPrice = this.price.add(this.salesTax).setScale(
                2,
                RoundingMode.HALF_UP
        );
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public String toString(){
        return name + " " + price + "\n" + "sales tax: " + salesTax + "\n------------";
    }
}