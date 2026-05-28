package service;

import model.Produkt;

import java.util.ArrayList;
import java.util.List;

public class LagerService {

    // DATA STRUCTURE (ArrayList)
    private List<Produkt> products = new ArrayList<>();

    // INITIAL DATA
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
        products.add(new Produkt("Bronze Goddess Eau Fraîche", "Parfum", "Estée Lauder", 85.00, 6));
        products.add(new Produkt("Lost Cherry", "Parfum", "Tom Ford", 240.00, 4));
        products.add(new Produkt("Coco Mademoiselle", "Parfum", "Chanel", 130.00, 7));
        products.add(new Produkt("Donna Born in Roma", "Parfum", "Valentino", 110.00, 9));
        products.add(new Produkt("Gabrielle Essence", "Parfum", "Chanel", 140.00, 8));
        products.add(new Produkt("Allure Homme Sport", "Parfum", "Chanel", 120.00, 10));
        products.add(new Produkt("Valentino Born in Roma Intense", "Parfum", "Valentino", 125.00, 7));
        products.add(new Produkt("Valentino Voce Viva", "Parfum", "Valentino", 115.00, 6));

        // Haircare
        products.add(new Produkt("Nutritive Shampoo", "Haircare", "Kérastase", 28.00, 20));
        products.add(new Produkt("No.3 Hair Perfector", "Haircare", "Olaplex", 32.00, 14));
        products.add(new Produkt("Blond Absolu Shampoo", "Haircare", "Kérastase", 32.00, 16));
        products.add(new Produkt("Nutritive Hair Mask", "Haircare", "Kérastase", 45.00, 12));
        products.add(new Produkt("Genesis Defense Serum", "Haircare", "Kérastase", 48.00, 11));
        products.add(new Produkt("Hydration Mask", "Haircare", "Moroccanoil", 39.00, 15));
    }

    // Add product
    public void addProduct(Produkt product) {
        products.add(product);
    }

    // Remove product
    public void removeProduct(Produkt produkt) {
        products.remove(produkt);
    }

    //Get all products
    public List<Produkt> getAllProducts() {
        return products;
    }

}
