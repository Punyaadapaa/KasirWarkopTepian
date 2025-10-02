/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADVAN
 */
public class CartItem {
    private final MenuItem item;
    private int qty;

    public CartItem(MenuItem item, int qty) {
        this.item = item;
        this.qty = qty;
    }

    public MenuItem getItem() { return item; }
    public int getQty() { return qty; }
    public void addQty(int add) { this.qty += add; }
    public double getSubtotal() { return item.getPrice() * qty; }
}
