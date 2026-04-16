import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementApp {
    private List<String> consist;

    public TrainConsistManagementApp() {
        consist = new ArrayList<>();
        // UC1: Initialize Train with an Engine
        consist.add("Engine");
    }

    public void displayConsistSummary() {
        System.out.println("Consist Summary: " + String.join(" - ", consist));
    }

    // UC2: Add Passenger Bogies to Train
    public void addPassengerBogie() {
        consist.add("Passenger Bogie");
        System.out.println("Passenger Bogie added to the consist.");
    }

    public static void main(String[] args) {
        TrainConsistManagementApp app = new TrainConsistManagementApp();
        app.displayConsistSummary();
        
        System.out.println("\nAdding passenger bogies...");
        app.addPassengerBogie();
        app.addPassengerBogie();
        
        app.displayConsistSummary();
    }
}
