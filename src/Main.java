import service.LagerService;
import view.ConsoleUI;

public class Main {

    public static void main(String[] args) {

        // 1. Create the service (your "database layer")
        LagerService lagerService = new LagerService();

        // 2. Load initial sample data (fake database)
        lagerService.loadInitialData();

        // 3. Create the console UI and inject the service
        ConsoleUI ui = new ConsoleUI(lagerService);

        // 4. Start the application
        ui.start();
    }
}
