package service;

import datastructure.CustomArrayList;
import model.Produkt;

/**
 * Central service class that manages the product inventory.
 * Holds the main CustomArrayList of products and acts as the coordinator
 * between all other services (SortService, SearchService, FilterService, PrintService).
 * LagerService does not contain any algorithm logic itself
 * it delegates to the specialized services and passes the product list to them.
 * Single Responsibility Principle (each class has one clear job).
 */

public class LagerService {

    // DATA STRUCTURE (Custom ArrayList)
    private final CustomArrayList<Produkt> products = new CustomArrayList<>();

    // INITIAL DATA
    // Loads a predefined set of sample products into the inventory on startup
    public void loadInitialData() {

        //Make-Up
        products.add(new Produkt("Sky High Mascara", "Make-up", "Maybelline", 11.99, 25));
        products.add(new Produkt("FauxFilter Foundation", "Make-up", "Huda Beauty", 39.99, 10));
        products.add(new Produkt("Soft Matte Lip Cream", "Make-up", "NYX", 6.50, 30));
        products.add(new Produkt("Studio Fix Powder Plus Foundation", "Make-up", "MAC", 34.00, 12));
        products.add(new Produkt("Colossal Mascara", "Make-up", "Maybelline", 10.99, 28));
        products.add(new Produkt("Lifter Gloss", "Make-up", "Maybelline", 8.99, 32));
        products.add(new Produkt("Super Stay Matte Ink Lipstick", "Make-up", "Maybelline", 9.50, 26));
        products.add(new Produkt("Retro Matte Lipstick", "Make-up", "MAC", 21.00, 20));
        products.add(new Produkt("Lip Pencil Chestnut", "Make-up", "MAC", 19.00, 18));

        //Skincare
        products.add(new Produkt("Water Toner Serum", "Skincare", "Fenty Beauty", 28.00, 18));
        products.add(new Produkt("Hydra Zen Moisturizer", "Skincare", "Lancôme", 45.00, 8));
        products.add(new Produkt("Rice Toner", "Skincare", "I'm From (K-Beauty)", 22.50, 15));
        products.add(new Produkt("Dewy Skin Cream", "Skincare", "Fenty Beauty", 34.00, 14));
        products.add(new Produkt("Instant Reset Overnight Gel", "Skincare", "Fenty Beauty", 38.00, 10));
        products.add(new Produkt("Advanced Génifique Eye Cream", "Skincare", "Lancôme", 55.00, 9));
        products.add(new Produkt("Tonique Confort Toner", "Skincare", "Lancôme", 35.00, 13));

        // Perfume
        products.add(new Produkt("Bronze Goddess Eau Fraîche", "Perfume", "Estée Lauder", 85.00, 6));
        products.add(new Produkt("Lost Cherry", "Perfume", "Tom Ford", 240.00, 4));
        products.add(new Produkt("Coco Mademoiselle", "Perfume", "Chanel", 130.00, 7));
        products.add(new Produkt("Donna Born in Roma", "Perfume", "Valentino", 110.00, 9));
        products.add(new Produkt("Gabrielle Essence", "Perfume", "Chanel", 140.00, 8));
        products.add(new Produkt("Allure Homme Sport", "Perfume", "Chanel", 120.00, 10));
        products.add(new Produkt("Valentino Born in Roma Intense", "Perfume", "Valentino", 125.00, 7));
        products.add(new Produkt("Valentino Voce Viva", "Perfume", "Valentino", 115.00, 6));

        // Haircare
        products.add(new Produkt("Nutritive Shampoo", "Haircare", "Kérastase", 28.00, 20));
        products.add(new Produkt("No.3 Hair Perfector", "Haircare", "Olaplex", 32.00, 14));
        products.add(new Produkt("Blond Absolu Shampoo", "Haircare", "Kérastase", 32.00, 16));
        products.add(new Produkt("Nutritive Hair Mask", "Haircare", "Kérastase", 45.00, 12));
        products.add(new Produkt("Genesis Defense Serum", "Haircare", "Kérastase", 48.00, 11));
        products.add(new Produkt("Hydration Mask", "Haircare", "Moroccanoil", 39.00, 15));
    }
    // SERVICES
    private final SortService sortService = new SortService();
    private final SearchService searchService = new SearchService();
    private final PrintService printService = new PrintService();
    private final FilterService filterService = new FilterService();

    // =========================
    // ADD / REMOVE
    // =========================

    // Adds a new product to the inventory.
    // Delegates directly to CustomArrayList.add() which handles resizing if needed
    public void addProduct(Produkt product) {
        products.add(product);
    }

    // Removes the first product whose name matches the given string (case-insensitive).
    // Uses the custom removeIf() method implemented in CustomArrayList.
    public void removeProduct(String name) {
        boolean removed = products.removeIf(p -> p.getName().equalsIgnoreCase(name));
        if (removed) {
            System.out.println("Product '" + name + "' was removed successfully.");
        } else {
            System.out.println("Product '" + name + "' not found.");
        }
    }

    // =========================
    // GET ALL
    // =========================

    // Returns the full product list
    // Used by ConsoleUI to allow direct access for operations (remove.. etc.)
    public CustomArrayList<Produkt> getAllProducts() {
        return products;
    }

    // Prints all products to the console
    public void printAllProducts() {
        printService.printAllProducts(products);
    }

    // =========================
    // SORT
    // =========================

    // Sorts the product list by price from low to high using Insertion Sort (O(n^2))
    // Insertion Sort was chosen here because it performs well on small or nearly sorted lists
    // Delegates to SortService
    public void sortByPrice() {
        sortService.insertionSortByPrice(products);
        printService.printSortedByPrice(products);
    }

    // Sorts the product list by quantity from low to high using Selection Sort (O(n^2))
    // Selection Sort minimizes the number of swaps, which is useful for quantity comparisons
    // Delegates to SortService
    public void sortByQuantity() {
        sortService.selectionSortByQuantity(products);
        printService.printSortedByQuantity(products);
    }

    // Sorts the product list alphabetically by brand name using Merge Sort (O(n log n))
    // Merge Sort is stable and efficient, it is ideal for alphabetical string sorting
    // Delegates to SortService
    public void sortByBrand() {
        sortService.mergeSortByBrand(products);
        printService.printUniqueBrands(products);
    }

    // =========================
    // SEARCH
    // =========================

    // Searches for products whose name contains the given string (case-insensitive)
    // Delegates to SearchService which performs a linear search O(n)
    public void searchByName(String name) {
        CustomArrayList<Produkt> result = searchService.searchByName(products, name);
        printService.printSearchResults(result, name);
    }

    // Searches for products whose brand contains the given string (case-insensitive)
    // Delegates to SearchService which performs a linear search O(n)
    public void searchByBrand(String brand) {
        CustomArrayList<Produkt> result = searchService.searchByBrand(products, brand);
        printService.printSearchResults(result, brand);
    }

    // =========================
    // FIND
    // =========================

    // Returns the first product whose name matches exactly (case-insensitive)
    // Returns null if no product is found
    // Used by ConsoleUI to locate a product before updating its price or quantity
    public Produkt findByName(String name) {
        for (Produkt p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
    // =========================
    // FILTER
    // =========================

    // Filters products by category and prints the results.
    // Delegates to FilterService: linear filter O(n)
    public void filterByCategory(String category) {
        CustomArrayList<Produkt> result = filterService.filterByCategory(products, category);
        printService.printSearchResults(result, category);
    }

    // Filters products by maximum price and prints the results
    // Delegates to FilterService: linear filter O(n)
    public void filterByMaxPrice(double maxPrice) {
        CustomArrayList<Produkt> result = filterService.filterByMaxPrice(products, maxPrice);
        printService.printFilterResults(result, "max price €" + maxPrice);
    }

    // Filters products by maximum quantity and prints the results
    // Delegates to FilterService: linear filter O(n)
    public void filterByMaxQuantity(int maxQuantity) {
        CustomArrayList<Produkt> result = filterService.filterByMaxQuantity(products, maxQuantity);
        printService.printFilterResults(result, "max quantity " + maxQuantity);
    }
}