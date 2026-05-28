package model;

public class Produkt {

    private String name;
    private String category; // For example: Make-Up, Skin-Care,Perfume,Hair-Care
    private String brand; // For example: Maybelline, NYX, Mac, Armani
    private double price;
    private int quantity;

    // Constructor

    public Produkt(String name, String category, String brand, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter


    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters for mutable fields

    // Quantity can change
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Price can change
    public void setPrice(double price) {
        this.price = price;
    }

    // toString method for console output
    @Override
    public String toString() {
        return "Product{" +
                "Name='" + name + '\'' +
                ", Category='" + category + '\'' +
                ", Brand='" + brand + '\'' +
                ", Price=" + price +
                "€, Quantity=" + quantity +
                '}';
    }
}
