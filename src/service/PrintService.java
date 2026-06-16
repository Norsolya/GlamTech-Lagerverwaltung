package service;

import datastructure.CustomArrayList;
import model.Produkt;

/**
 * Service class responsible for all console output related to products.
 * Keeps all print/display logic in one place, other classes just call the right print method
 * PrintService handles all DATA output
 * (product details, search results, sorted lists).
 * ConsoleUI handles all USER INTERACTION output
 * (menu, prompts like "Enter name:", confirmations like "Product added!").
 * This separation means: if you ever want to change how products look on screen,
 * you only touch PrintService, not ConsoleUI or LagerService.
 */

public class PrintService {

    // Prints every product in the inventory with full details and a counter
    // Menü option 11 "Show all products"
    public void printAllProducts(CustomArrayList<Produkt> list) {
        System.out.println("\n=== ALL PRODUCTS ===");
        int count = 1;
        for (Produkt p : list) {
            System.out.println(count++ + "."); // numbered counter for each product
            printProductDetail(p); // delegates detail formatting to shared helper
            System.out.println("--------------------");
        }
        System.out.println("Total products: " + list.size()); // summary at the end
    }

    // Prints products after Insertion Sort, shows name, brand, category and price
    // Only price is shown (not quantity) because that is the sort criterion here
    // Menü option 8 "Sort by price"
    public void printSortedByPrice(CustomArrayList<Produkt> list) {
        System.out.println("\n=== PRODUCTS SORTED BY PRICE ===");
        int count = 1;
        for (Produkt p : list) {
            System.out.println(count++ + ". " + p.getName() + " - " + p.getBrand() + " (" + p.getCategory() + ")");
            System.out.println("Price: " + formatPrice(p.getPrice())); // formatted to 2 decimal places
            System.out.println("--------------------");
        }
    }

    // Prints products after Selection Sort: shows name, brand, category and quantity
    // Only quantity is shown because that is the sort criteria here
    // Menü option 9 "Sort by quantity"
    public void printSortedByQuantity(CustomArrayList<Produkt> list) {
        System.out.println("\n=== PRODUCTS SORTED BY QUANTITY ===");
        int count = 1;
        for (Produkt p : list) {
            System.out.println(count++ + ". " + p.getName() + " - " + p.getBrand() + " (" + p.getCategory() + ")");
            System.out.println("Available quantity: " + p.getQuantity());
            System.out.println("--------------------");
        }
    }

    // Prints unique brand names after Merge Sort (A-Z)
    // Skips duplicate brands by comparing each brand to the previous one
    // works because the list is already sorted alphabetically
    // Menü option 10 "Sort by brand name"
    public void printUniqueBrands(CustomArrayList<Produkt> list) {
        System.out.println("\n=== SORTED BRANDS ===");
        String lastBrand = null; // tracks the previously printed brand
        for (Produkt p : list) {
            String brand = p.getBrand();
            if (!brand.equalsIgnoreCase(lastBrand)) { // only print if brand has changed
                System.out.println(brand);
                lastBrand = brand; // update the last printed brand
            }
        }
    }

    // Prints the results of a search by name or brand
    // Shows a "not found" message if the result list is empty
    // Reused for both searchByName and searchByBrand results.
    public void printSearchResults(CustomArrayList<Produkt> list, String query) {
        if (list.isEmpty()) {
            System.out.println("No products found for: " + query); // early exit if nothing matched
            return;
        }
        System.out.println("\n=== SEARCH RESULTS FOR: " + query + " ===");
        for (Produkt p : list) {
            printProductDetail(p); // product details for each match
            System.out.println("--------------------");
        }
        System.out.println("Products found: " + list.size()); // total match count
    }

    // Prints the results of a filter operation (by category, price or quantity)
    // Shows a "not found" message if no products matched the filter condition
    // Separate from printSearchResults to allow different header
    public void printFilterResults(CustomArrayList<Produkt> list, String filterDescription) {
        if (list.isEmpty()) {
            System.out.println("No products found for filter: " + filterDescription);
            return;
        }
        System.out.println("\n=== FILTER RESULTS: " + filterDescription + " ===");
        for (Produkt p : list) {
            printProductDetail(p);  // product details for each match
            System.out.println("--------------------");
        }
        System.out.println("Found " + list.size() + " product(s)."); // summary
    }

    // helper that prints all fields of a single product in a consistent format
    // Used by printAllProducts, printSearchResults and printFilterResults
    // so the product layout is always identical
    private void printProductDetail(Produkt p) {
        System.out.println("Name: " + p.getName());
        System.out.println("Brand: " + p.getBrand());
        System.out.println("Category: " + p.getCategory());
        System.out.println("Price: " + formatPrice(p.getPrice())); // formatted to two decimals
        System.out.println("Quantity: " + p.getQuantity());
    }

    // helper that formats a price double to always show 2 decimal places
    // E.g. 28.0 becomes "28.00€"
    private String formatPrice(double price) {
        return String.format("%.2f€", price);
    }
}