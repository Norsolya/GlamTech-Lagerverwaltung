# GlamTech Lagerverwaltung

A Java console application managing a beauty shop inventory with products in four different categories. 

This project was developed as part of the Algorithms and Data Structures course.

## 1. How to use the app
Clone the repository:

git clone https://github.com/Norsolya/GlamTech-Lagerverwaltung.git

Start the application by running `Main.java`.

The application then displays a console menu with 14 different options. To select an option, enter its number and press Enter. If additional information is required, the application will prompt the user for input.

When the application starts, it automatically loads sample products from four categories:
- Make-up
- Skincare
- Perfume
- Haircare

Prices can be typed with a dot (5.99) or a comma (5,99), both work, and the app is also case-insensitive (Today=today).

## 2. Features

- Add products to inventory
- Remove products from inventory
- Update product quantities
- Search products by name or brand
- Filter products by category, price, or quantity
- Sort products by price, quantity, or brand
- Display all products
- Preloaded sample inventory
- Case-insensitive user input

## 3. Class Architecture
The project is split into different packages, and every class only does one specific job. This is based on the **Single Responsibility Principle**.

___________________________
- **model/Product.java:** data object (name, category, brand, price, quantity)

- **datastructure/CustomArrayList.java:** own implementation of a dynamic array

- **service/LagerService.java:** coordinates everything, holds the product list

- **service/SortService.java:** contains the 3 sorting algorithms

- **service/SearchService.java:** linear search by name/brand

- **service/FilterService.java:** linear filtering by category/price/quantity

- **service/PrintService.java:** handles all the console output of product data

- **view/ConsoleUI.java:** the menu and everything the user types in

- **Main.java:** Starting point
______________________________

**Main idea behind the structure:** `LagerService` does not contain any algorithm logic itself. It just holds the product list and calls the other services when needed. `ConsoleUI` only handles things the user sees and types (menu, prompts like "Enter name:"), while `PrintService` is responsible for printing the actual product data. This way, if I want to change how a product is displayed, I only have to change `PrintService` and nothing else breaks.

## 4. Data Structure: Custom ArrayList

This is my own implementation of dynamic array.

**How it works inside:**
- It uses a normal `Object[]` array internally (you can't make a generic array `T[]` directly in Java because of type erasure)
- A `size` variable keeps track of how many elements are actually stored
- It implements `Iterable<T>` so I can use normal for-each loops on it

**Resizing:**
When the array is full and we try to add another element, the capacity gets **doubled** and all old elements are copied into the new bigger array with `System.arraycopy()`.

## 5. Algorithms

| Algorithm | Used for | Runtime | Explanation                                                                                                                                                                                                   |
|---|---|---|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Insertion Sort** | Sorting by price (low to high) | O(n²) | Builds up a sorted part of the list step by step by inserting each element at the correct position. Works well on small lists or lists that are already almost sorted.                                        |
| **Selection Sort** | Sorting by quantity (low to high) | O(n²) | In every pass, it looks for the smallest element in the unsorted part and swaps it to the front. Does the fewest possible swaps (max n-1).                                                                    |
| **Merge Sort** | Sorting by brand name (A-Z) | O(n log n) | Splits the list in half over and over until single elements are left, then merges them back together in the right order (divide and conquer). Stable and faster than the other two, good for sorting strings. |
| **Linear Search** | Search by name/brand, filter by category/price/quantity | O(n) | Goes through the list one element at a time and checks if it matches (e.g. with `contains()` for the name search).                                                                                            |

**Thank you for checking out this project! Feedback and suggestions are always welcome.**
