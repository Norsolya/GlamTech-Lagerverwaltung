package service;
import datastructure.CustomArrayList;
import model.Produkt;

/**
 * Service class containing all three sorting algorithms.
 * Each algorithm sorts the product list by a different criteria.
 * Overview of the three algorithms:
 *   Insertion Sort: sorts by price O(n^2)
 *   Selection Sort: sorts by quantity O(n^2)
 *   Merge Sort: sorts by brand  O(n log n)
 *
 * All methods sort the original list directly
 * no copy of the list is created, the original CustomArrayList is modified.
 */

public class SortService {

    // =========================
    // INSERTION SORT (PRICE)
    // =========================

    /**
     * INSERTION SORT sorts products by price from low to high.
     * Time complexity: O(n^2) in the worst case.
     *
     * Starts at index 1 and picks one element at a time
     * then shifts all larger elements one position to the right
     * to make room, and inserts the picked element in the correct position.
     *
     * Why Insertion Sort for price?
     * It performs well on small or nearly sorted lists, and price lists
     * in a shop inventory tend to be partially ordered already.
     */

    public void insertionSortByPrice(CustomArrayList<Produkt> list) {
        for (int i = 1; i < list.size(); i++) { // start at index 1, index 0 is already "sorted"
            Produkt current = list.get(i); // pick the current element to be inserted
            double currentPrice = current.getPrice(); // store its price for comparison
            int j = i - 1; // j points to the last element of the sorted portion
            while (j >= 0 && list.get(j).getPrice() > currentPrice) { // shift right while larger
                list.set(j + 1, list.get(j)); // move element one position to the right
                j--; // move j one step to the left
            }
            list.set(j + 1, current); // insert current element in its correct position
        }
    }

    // =========================
    // SELECTION SORT (QUANTITY)
    // =========================

    /**
     * SELECTION SORT sorts products by quantity from low to high.
     * Time complexity: O(n^2) in all cases.
     *
     * Divides the list into a sorted and an unsorted portion.
     * In each pass it finds the minimum element in the unsorted portion
     * and swaps it to the end of the sorted portion.
     *
     * Why Selection Sort for quantity?
     * Selection Sort performs the minimum possible number of swaps (at most n-1).
     * This makes it a good choice when swapping elements is costly,
     * and for quantity sorting where we just need to find the smallest values.
     */

    public void selectionSortByQuantity(CustomArrayList<Produkt> list) {
        for (int i = 0; i < list.size() - 1; i++) { // outer loop: grows the sorted portion
            int minIndex = i; // assume the first unsorted element is the minimum
            for (int j = i + 1; j < list.size(); j++) { // inner loop: find the actual minimum
                if (list.get(j).getQuantity() < list.get(minIndex).getQuantity()) {
                    minIndex = j; // update minIndex if a smaller quantity is found
                }
            }
            // swap the found minimum with the first unsorted element
            Produkt temp = list.get(i); // temporarily store element at position i
            list.set(i, list.get(minIndex)); // place the minimum at position i
            list.set(minIndex, temp); // put the stored element where the minimum was
        }
    }

    // =========================
    // MERGE SORT (BRAND NAME)
    // =========================

    /**
     * MERGE SORT sorts products alphabetically by brand name (A-Z).
     * Time complexity: O(n log n) in all cases.
     *
     * Recursively splits the list in half until each part
     * has only one element (which is trivially sorted), then merges
     * the parts back together in sorted order.
     *
     * Why Merge Sort for brand names?
     * Merge Sort is stable (equal elements keep their original order)
     * and guarantees O(n log n) performance, ideal for alphabetical
     * string sorting where comparisons can be expensive.
     */
    public void mergeSortByBrand(CustomArrayList<Produkt> list) {
        if (list.size() <= 1) return; // base case: a list of 0 or 1 is already sorted
        int mid = list.size() / 2; // find the middle index to split the list

        // create two separate sub-lists for left and right halves
        CustomArrayList<Produkt> left = new CustomArrayList<>();
        CustomArrayList<Produkt> right = new CustomArrayList<>();

        for (int i = 0; i < mid; i++) left.add(list.get(i)); // fill left half
        for (int i = mid; i < list.size(); i++) right.add(list.get(i)); // fill right half

        mergeSortByBrand(left); // recursively sort the left half
        mergeSortByBrand(right); // recursively sort the right half

        merge(list, left, right); // merge the two sorted halves back into list
    }

    /**
     * Merges two sorted sub-lists (left and right) back into the result list.
     * Compares brand names from both sides and always picks the smaller one first.
     * Uses compareToIgnoreCase so e.g. "chanel" and "Chanel" are treated equally.
     */

    private void merge(CustomArrayList<Produkt> result, CustomArrayList<Produkt> left, CustomArrayList<Produkt> right) {
        int i = 0, j = 0, k = 0; // i = left index, j = right index, k = result index

        // compare elements from both halves and write the smaller one into result
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getBrand().compareToIgnoreCase(right.get(j).getBrand()) <= 0) {
                result.set(k++, left.get(i++)); // left brand comes first alphabetically
            } else {
                result.set(k++, right.get(j++)); // right brand comes first alphabetically
            }
        }
        // copy any remaining elements from the left half (if right ran out first)
        while (i < left.size()) result.set(k++, left.get(i++));
        // copy any remaining elements from the right half (if left ran out first)
        while (j < right.size()) result.set(k++, right.get(j++));
    }
}
