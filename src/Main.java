import service.LagerService;
import view.ConsoleUI;

/**
 * Entry point of the GlamTech Beauty Shop Inventory System.
 * Responsible for creating and wiring together the application components.
 *
 * Main only does three things
 * create the service
 * load the data
 * start the UI.
 *
 * This pattern is called "composition root"
 * (one single place where all dependencies are created and connected to each other.
 */

public class Main {

    public static void main(String[] args) {

        // 1. create the central service that manages the product inventory
        LagerService lagerService = new LagerService();

        // 2. load the predefined sample products into the inventory
        lagerService.loadInitialData();

        // 3. create the console UI and inject LagerService into it
        // dependency injection
        ConsoleUI ui = new ConsoleUI(lagerService);

        // 4. Start the application (Main Menü loop)
        ui.start();
    }
}
