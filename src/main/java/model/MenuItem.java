/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADVAN
 */
public class MenuItem {
    private final String code;
    private final String name;
    private final double price;
    private final char category;

    public MenuItem(String code, String name, double price, char category) {
        this.code = code.toUpperCase();
        this.name = name;
        this.price = price;
        this.category = Character.toUpperCase(category);
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public char getCategory() { return category; }

    @Override
    public String toString() {
        return String.format("%-6s | %-20s | %7.0f | %s",
                code, name, price, categoryToText(category));
    }

    public static String categoryToText(char c) {
        c = Character.toUpperCase(c);
        if (c == 'F') return "FOOD";
        if (c == 'D') return "DRINK";
        if (c == 'S') return "SNACK";
        return "OTHER";
    }
}
