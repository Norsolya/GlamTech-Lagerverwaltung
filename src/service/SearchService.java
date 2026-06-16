package service;

import datastructure.CustomArrayList;
import model.Produkt;

/**
 * Service class responsible for searching through the product inventory.
 * Implements a linear search algorithm O(n): every element in the list
 * is checked one by one until all matches are found.
 * Both methods use a "contains" check instead of an exact match,
 * so partial searches work too, searching "may" will find "Maybelline".
 * All comparisons are case-insensitive so "MAC" and "mac" both return results.
 */

public class SearchService {

    // Searches for all products whose name contains the given search string
    // Returns a new CustomArrayList with all matching products
    // If nothing is found, an empty list is returned
    public CustomArrayList<Produkt> searchByName(CustomArrayList<Produkt> list, String name) {

        CustomArrayList<Produkt> result = new CustomArrayList<>(); // empty list to collect matches

        for (Produkt p : list) { // iterate through every product
            if (p.getName().toLowerCase().contains(name.toLowerCase())) { // case-insensitive partial match
                result.add(p); // add to results if name matches
            }
        }

        return result; // return all matched products (can be empty)
    }

    // Searches for all products whose brand contains the given search string
    // Returns a new CustomArrayList with all matching products
    // If nothing is found, an empty list is returned
    public CustomArrayList<Produkt> searchByBrand(CustomArrayList<Produkt> list, String brand) {

        CustomArrayList<Produkt> result = new CustomArrayList<>(); // empty list to collect matches

        for (Produkt p : list) { // iterate through every product
            if (p.getBrand().toLowerCase().contains(brand.toLowerCase())) { // case-insensitive partial match
                result.add(p); // add to results if brand matches
            }
        }

        return result; // return all matched products (can be empty)
    }
}
