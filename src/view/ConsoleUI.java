package view;

import model.Produkt;
import service.LagerService;

import java.util.Scanner;

public class ConsoleUI {

    private LagerService lagerService;
    private Scanner scanner;

    // Constructor
    public ConsoleUI(LagerService lagerservice) {
        this.lagerService = lagerservice;
        this.scanner = new Scanner(System.in);
    }
    // Starts the console application loop
    public void start() {

        boolean running = true;

        while (running) {

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
            System.out.println("8. Sort by price (low → high)");
            System.out.println("9. Sort by quantity");
            System.out.println("10. Filter + Sort");
            System.out.println("11. Show all products");
            System.out.println("0. Exit");

            System.out.print("\nChoose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear input buffer

            switch (choice) {

                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> searchByName();
                case 4 -> searchByBrand();
                case 5 -> filterByCategory();
                case 6 -> filterByPrice();
                case 7 -> filterByQuantity();
                case 8 -> sortByPrice();
                case 9 -> sortByQuantity();
                case 10 -> filterAndSort();
                case 11 -> showAllProducts();
                case 0 -> {
                    running = false;
                    System.out.println("Application closed.");
                }
                default -> System.out.println("Invalid option! Please, choose from the given ones.");
            }
        }
    }

    // =========================
    // SHOW ALL PRODUCTS
    // =========================
    private void showAllProducts() {

        System.out.println("\n=== ALL PRODUCTS ===");

        int count = 1;

        for (Produkt p : lagerService.getAllProducts()) {
            System.out.println(count + ".");
            System.out.println("Name: " + p.getName());
            System.out.println("Brand: " + p.getBrand());
            System.out.println("Category: " + p.getCategory());
            System.out.println("Price: " + p.getPrice() + "€");
            System.out.println("Quantity: " + p.getQuantity());
            System.out.println("--------------------");
            count++;
        }

        System.out.println("====================");
        System.out.println("Total products: " + lagerService.getAllProducts().size());
    }

    // =========================
    // ADD PRODUCT
    // =========================
    private void addProduct() {

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

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
    // PLACEHOLDER METHODS (ALGORITHMS LATER)
    // =========================

    private void searchByName() {
        System.out.println("Search by name not implemented yet.");
    }

    private void searchByBrand() {
        System.out.println("Search by brand not implemented yet.");
    }

    private void filterByCategory() {
        System.out.println("Filter by category not implemented yet.");
    }

    private void filterByPrice() {
        System.out.println("Filter by price not implemented yet.");
    }

    private void filterByQuantity() {
        System.out.println("Filter by quantity not implemented yet.");
    }

    private void sortByPrice() {
        System.out.println("Sort by price not implemented yet.");
    }

    private void sortByQuantity() {
        System.out.println("Sort by quantity not implemented yet.");
    }

    private void filterAndSort() {
        System.out.println("Filter + sort not implemented yet.");
    }
}


