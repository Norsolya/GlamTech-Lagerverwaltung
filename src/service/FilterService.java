package service;

import datastructure.CustomArrayList;
import model.Produkt;

/**
 * Responsible for filtering the product list.
 * Each method performs a linear search O(n) through the CustomArrayList
 * and returns a new list containing only the elements that match the condition.
 */

public class FilterService {

    // Filters the list and returns only products that match the given category
    // Uses case-insensitive comparison so "make-up" and "Make-up" both work
    // Time complexity: O(n): every element is checked exactly once
    public CustomArrayList<Produkt> filterByCategory(CustomArrayList<Produkt> list, String category) {
        CustomArrayList<Produkt> result = new CustomArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Produkt p = list.get(i);
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    // Filters the list and returns only products whose price is
    // less than or equal to the given maximum price
    // Time complexity: O(n): every element is checked exactly once
    public CustomArrayList<Produkt> filterByMaxPrice(CustomArrayList<Produkt> list, double maxPrice) {
        CustomArrayList<Produkt> result = new CustomArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Produkt p = list.get(i);
            if (p.getPrice() <= maxPrice) {
                result.add(p);
            }
        }
        return result;
    }

    // Filters the list and returns only products whose quantity is
    // less than or equal to the given maximum quantity
    // Thought to be used for finding low-stock products
    // Time complexity: O(n): every element is checked exactly once
    public CustomArrayList<Produkt> filterByMaxQuantity(CustomArrayList<Produkt> list, int maxQuantity) {
        CustomArrayList<Produkt> result = new CustomArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Produkt p = list.get(i);
            if (p.getQuantity() <= maxQuantity) {
                result.add(p);
            }
        }
        return result;
    }
}