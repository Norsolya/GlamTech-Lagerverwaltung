package view;

import model.Produkt;
import service.LagerService;

import java.util.Scanner;

/**
 * The user interface class, handles all interaction between the user and the application.
 * Displays the menu, reads user input and calls the appropriate methods in LagerService.
 *
 * ConsoleUI only handles USER INTERACTION
 * prompts, menü display, reading input, and confirmation messages.
 * It never contains business logic or data formatting.
 * All actual work is delegated to LagerService which coordinates the other services.
 *
 * parsePrice() is a helper that handles both "." and "," as decimal separators.
 */

public class ConsoleUI {

    private final LagerService lagerService; // the service layer that manages all product data
    private final Scanner scanner; // reads user input from the console

    // Constructor
    /**
     * Constructor — receives LagerService via dependency injection.
     * This means ConsoleUI does not create its own LagerService,
     * it receives one from Main keeping classes loosely coupled.
     */
    public ConsoleUI(LagerService lagerservice) {
        this.lagerService = lagerservice;
        this.scanner = new Scanner(System.in);
    }
    // Starts the console application loop
    // Each iteration prints the menü and waits for user input
    public void start() {

        boolean running = true; // controls the main loop, set to false on exit

        while (running) {
            // menü header print
            System.out.println("\n======================================");
            System.out.println("GlamTech Beauty Shop Inventory System");
            System.out.println("============ Welcome! ================");
            System.out.println("======================================");
            System.out.println(" ");

            System.out.println("Please select an option:");
            System.out.println("1. Add product");
            System.out.println("2. Delete product");
            System.out.println("3. Search by name");
            System.out.println("4. Search by brand");
            System.out.println("5. Filter by category");
            System.out.println("6. Filter by price");
            System.out.println("7. Filter by quantity");
            System.out.println("8. Sort by price (low to high)"); //Insertion Sort
            System.out.println("9. Sort by quantity (low to high)"); //Selection Sort
            System.out.println("10. Sort by brand name (A-Z)"); //Merge Sort
            System.out.println("11. Show all products");
            System.out.println("12. Change product price");
            System.out.println("13. Change product quantity");
            System.out.println("0. Exit");

            System.out.print("\nChoose option: ");
            int choice = scanner.nextInt(); // read the menu choice as integer
            scanner.nextLine(); // Clear input buffer

            // route the user's choice to the correct method
            switch (choice) {

                case 1 -> addProduct(); //d
                case 2 -> removeProduct(); //d
                case 3 -> searchByName();
                case 4 -> searchByBrand();
                case 5 -> filterByCategory();
                case 6 -> filterByPrice();
                case 7 -> filterByQuantity();
                case 8 -> lagerService.sortByPrice();
                case 9 -> lagerService.sortByQuantity();
                case 10 -> lagerService.sortByBrand();
                case 11 -> lagerService.printAllProducts();
                case 12 -> changeProductPrice();
                case 13 -> changeProductQuantity();
                case 0 -> {
                    running = false; // exit the loop
                    System.out.println("Application closed.");
                }
                default -> System.out.println("Invalid option! Please, choose from the given ones.");
            }
        }
    }

    // =========================
    // ADD PRODUCT
    // =========================
    private void addProduct() {

        System.out.print("Enter name: ");
        String name = scanner.nextLine();


        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();

        System.out.print("Enter category (Make-up / Skincare / Perfume / Haircare): ");
        String category = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = parsePrice(scanner.nextLine());

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        lagerService.addProduct(new Produkt(name, category, brand, price, quantity));

        System.out.println("Product successfully added!");
    }

    // =========================
    // DELETE PRODUCT
    // =========================
    private void removeProduct() {

        System.out.print("Enter product name to remove: ");
        String name = scanner.nextLine();

        boolean removed = lagerService.getAllProducts()
                .removeIf(p -> p.getName().equalsIgnoreCase(name));

        if (removed) {
            System.out.println("Product '" + name + "' was removed successfully.");
        } else {
            System.out.println("Product '" + name + "' not found.");
        }
    }

    // =========================
    // SEARCH BY NAME
    // =========================
    // Reads a name search term then delegates to --> LagerService --> PrintService
    private void searchByName() {

        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        lagerService.searchByName(name); // results printed inside LagerService → PrintService
    }

    // =========================
    // SEARCH BY BRAND
    // =========================
    // Reads a brand search term and delegates to --> LagerService --> PrintService
    private void searchByBrand() {

        System.out.print("Enter brand to search: ");
        String brand = scanner.nextLine();

        lagerService.searchByBrand(brand); // results printed inside LagerService → PrintService
    }

    // =========================
    // CHANGE PRICE
    // =========================

    // Finds a product by name and updates its price
    // Displays current product info before asking for the new price
    // Uses parsePrice() so both "." and "," are accepted as decimal separator
    private void changeProductPrice() {

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        boolean found = false;

        for (Produkt p : lagerService.getAllProducts()) { // iterate through all products

            if (p.getName().equalsIgnoreCase(name)) { // case-insensitive name match

                // Display product information
                System.out.println("\nProduct found:");
                System.out.println("Name: " + p.getName());
                System.out.println("Brand: " + p.getBrand());
                System.out.println("Current price: " + p.getPrice() + "€");

                System.out.print("Enter new price: ");
                double newPrice = parsePrice(scanner.nextLine());

                p.setPrice(newPrice);

                System.out.println("Price updated successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }

    // =========================
    // CHANGE QUANTITY
    // =========================
    // Finds a product by name and updates its quantity
    // Displays current product info before asking for the new quantity
    private void changeProductQuantity() {

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        boolean found = false;

        for (Produkt p : lagerService.getAllProducts()) {

            if (p.getName().equalsIgnoreCase(name)) {

                // Display product information
                System.out.println("\nProduct found:");
                System.out.println("Name: " + p.getName());
                System.out.println("Brand: " + p.getBrand());
                System.out.println("Current quantity: " + p.getQuantity());


                System.out.print("Enter new quantity: ");
                int newQuantity = Integer.parseInt(scanner.nextLine()); // parse as integer

                p.setQuantity(newQuantity); // update quantity on the object

                System.out.println("Quantity updated successfully.");
                found = true;
                break; // stop searching after the first match
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }

    // =========================
    // FILTER
    // =========================

    // Reads a category and delegates filtering to LagerService
    private void filterByCategory() {
        System.out.print("Enter category (Make-up / Skincare / Perfume / Haircare): ");
        String category = scanner.nextLine();
        lagerService.filterByCategory(category);
    }

    // Reads a maximum price and delegates filtering to LagerService
    private void filterByPrice() {
        System.out.print("Enter maximum price: ");
        double maxPrice = parsePrice(scanner.nextLine());
        lagerService.filterByMaxPrice(maxPrice);
    }

    // Reads a maximum quantity and delegates filtering to LagerService
    private void filterByQuantity() {
        System.out.print("Enter maximum quantity: ");
        int minQuantity = Integer.parseInt(scanner.nextLine());
        lagerService.filterByMaxQuantity(minQuantity);
    }

    // =========================
    // HELPER: PARSE PRICE
    // =========================

    // Converts a price string to a double, accepting both "." and "," as decimal separator
    // E.g. "5,99" --> 5.99 / "5.99" --> 5.99
    private double parsePrice(String input) {
        input = input.replace(",", ".");
        return Double.parseDouble(input);
    }
}
