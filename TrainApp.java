import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrainConsistManagementApp {
    private List<String> consist;
    private Set<String> bogieIds;

    public TrainConsistManagementApp() {
        consist = new ArrayList<>();
        bogieIds = new HashSet<>();
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

    // UC3: Track Unique Bogie IDs
    public void addBogieId(String id) {
        if (bogieIds.add(id)) {
            System.out.println("Bogie ID " + id + " added.");
        } else {
            System.out.println("Duplicate ignored for Bogie ID: " + id);
        }
    }

    public void displayUniqueBogieIds() {
        System.out.println("Unique Bogie IDs: " + bogieIds);
    }

    // UC4: Maintain Ordered Bogie IDs
    public void manageOrderedConsist() {
        LinkedList<String> trainChain = new LinkedList<>();
        // Add bogies sequentially
        trainChain.add("Engine");
        trainChain.add("Sleeper");
        trainChain.add("AC");
        trainChain.add("Cargo");
        trainChain.add("Guard");
        
        System.out.println("Initial Train Consist: " + trainChain);

        // Insert Pantry Car at position 2
        trainChain.add(2, "Pantry Car");
        System.out.println("After inserting Pantry Car at index 2: " + trainChain);

        // Remove first and last bogie
        trainChain.removeFirst();
        trainChain.removeLast();
        
        System.out.println("Final Ordered Train Consist (after removing first & last): " + trainChain);
    }

    // UC5: Preserve Insertion Order of Bogies
    public void preserveInsertionOrderConsist() {
        LinkedHashSet<String> formation = new LinkedHashSet<>();
        
        System.out.println("Attaching bogies to formation...");
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        
        System.out.println("Attempting to attach duplicate bogie: Sleeper");
        boolean isAdded = formation.add("Sleeper");
        if (!isAdded) {
            System.out.println("Duplicate ignored for bogie: Sleeper");
        }
        
        System.out.println("Final Train Formation (LinkedHashSet): " + formation);
    }

    // UC6: Map Bogie to Capacity
    public void mapBogieToCapacity() {
        Map<String, Integer> bogieCapacities = new HashMap<>();
        
        // Use put() method to map bogie to capacity
        bogieCapacities.put("Sleeper", 72);
        bogieCapacities.put("AC Chair", 60);
        bogieCapacities.put("First Class", 24);
        
        System.out.println("Bogie Capacity Details:");
        // Iterate using entrySet()
        for (Map.Entry<String, Integer> entry : bogieCapacities.entrySet()) {
            System.out.println("Bogie Type: " + entry.getKey() + " | Capacity: " + entry.getValue() + " seats");
        }
    }

    public static void main(String[] args) {
        TrainConsistManagementApp app = new TrainConsistManagementApp();
        app.displayConsistSummary();
        
        System.out.println("\nAdding passenger bogies...");
        app.addPassengerBogie();
        app.addPassengerBogie();
        
        app.displayConsistSummary();
        
        System.out.println("\n--- UC3: Track Unique Bogie IDs ---");
        app.addBogieId("BG101");
        app.addBogieId("BG102");
        app.addBogieId("BG101"); // Intentional duplicate
        
        app.displayUniqueBogieIds();
        
        System.out.println("\n--- UC4: Maintain Ordered Bogie IDs (LinkedList) ---");
        app.manageOrderedConsist();
        
        System.out.println("\n--- UC5: Preserve Insertion Order (LinkedHashSet) ---");
        app.preserveInsertionOrderConsist();
        
        System.out.println("\n--- UC6: Map Bogie to Capacity (HashMap) ---");
        app.mapBogieToCapacity();
    }
}
