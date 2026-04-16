import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // UC7: Sort Bogies by Capacity
    public void sortBogiesByCapacity() {
        List<Bogie> passengerBogies = new ArrayList<>();
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("First Class", 24));
        passengerBogies.add(new Bogie("AC Chair", 60));
        
        System.out.println("Before Sorting (Insertion Order):");
        for (Bogie bogie : passengerBogies) {
            System.out.println(bogie);
        }
        
        // Sort using Comparator
        passengerBogies.sort(Comparator.comparingInt(Bogie::getCapacity));
        
        System.out.println("\nAfter Sorting (By Capacity - Ascending):");
        for (Bogie bogie : passengerBogies) {
            System.out.println(bogie);
        }
    }

    // UC8: Filter Passenger Bogies Using Streams
    public void filterBogiesByCapacity() {
        List<Bogie> passengerBogies = new ArrayList<>();
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("First Class", 24));
        passengerBogies.add(new Bogie("AC Chair", 60));
        passengerBogies.add(new Bogie("General", 90));
        
        System.out.println("Original Passenger Bogies List: " + passengerBogies);
        
        // Filter Bogies with capacity > 60 using Streams
        List<Bogie> filteredBogies = passengerBogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());
                
        System.out.println("Filtered High Capacity Bogies (> 60 seats): " + filteredBogies);
    }

    // UC9: Group Bogies by Type
    public void groupBogiesByType() {
        List<Bogie> passengerBogies = new ArrayList<>();
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("First Class", 24));
        passengerBogies.add(new Bogie("AC Chair", 60));
        passengerBogies.add(new Bogie("Sleeper", 72)); // Duplicate type to show grouping
        passengerBogies.add(new Bogie("General", 90));
        
        System.out.println("Original Passenger Bogies List: " + passengerBogies);
        
        // Group bogies by their name/type using Streams
        Map<String, List<Bogie>> groupedBogies = passengerBogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));
                
        System.out.println("Grouped Bogies by Type:");
        groupedBogies.forEach((type, list) -> {
            System.out.println(type + " -> " + list);
        });
    }

    // UC10: Count Total Seats in Train
    public void countTotalSeats() {
        List<Bogie> passengerBogies = new ArrayList<>();
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("First Class", 24));
        passengerBogies.add(new Bogie("AC Chair", 60));
        passengerBogies.add(new Bogie("General", 90));
        
        System.out.println("Passenger Bogies in Train: " + passengerBogies);
        
        // Calculate total seats using Streams (map and reduce)
        int totalSeats = passengerBogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);
                
        System.out.println("Total Seating Capacity in Train: " + totalSeats + " seats");
    }

    // UC11: Validate Train ID & Cargo Codes
    public void validateInputFormats() {
        String[] trainIds = {"TRN-1234", "TRAIN12", "TRN12A", "1234-TRN", "TRN-123", "TRN-12345", ""};
        String[] cargoCodes = {"PET-AB", "PET-ab", "PET123", "AB-PET", ""};

        Pattern trainIdPattern = Pattern.compile("^TRN-\\d{4}$");
        Pattern cargoCodePattern = Pattern.compile("^PET-[A-Z]{2}$");

        System.out.println("Train ID Validation:");
        for (String id : trainIds) {
            Matcher m = trainIdPattern.matcher(id);
            if (m.matches()) {
                System.out.println(id + " -> VALID");
            } else {
                System.out.println(id + " -> INVALID");
            }
        }

        System.out.println("\nCargo Code Validation:");
        for (String code : cargoCodes) {
            Matcher m = cargoCodePattern.matcher(code);
            if (m.matches()) {
                System.out.println(code + " -> VALID");
            } else {
                System.out.println(code + " -> INVALID");
            }
        }
    }

    // UC12: Safety Compliance Check for Goods Bogies
    public void checkSafetyCompliance() {
        List<GoodsBogie> goodsTrain = new ArrayList<>();
        goodsTrain.add(new GoodsBogie("Rectangular", "Coal"));
        goodsTrain.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsTrain.add(new GoodsBogie("Open", "Grain"));
        
        System.out.println("Validating Goods Train Formation: " + goodsTrain);
        
        // Safety Rule: Cylindrical bogies must ONLY carry Petroleum.
        boolean isSafe = goodsTrain.stream()
                .allMatch(b -> !b.getShape().equals("Cylindrical") || b.getCargo().equals("Petroleum"));
                
        if (isSafe) {
            System.out.println("Safety Check: COMPLIANT. Train is safe to dispatch.");
        } else {
            System.out.println("Safety Check: FAILED. Rule violation detected in consist!");
        }

        // Simulating a rule violation
        goodsTrain.add(new GoodsBogie("Cylindrical", "Coal")); 
        System.out.println("\nAfter adding violating bogie (Cylindrical carrying Coal):");
        
        boolean isSafeAfterViolation = goodsTrain.stream()
                .allMatch(b -> !b.getShape().equals("Cylindrical") || b.getCargo().equals("Petroleum"));
                
        if (isSafeAfterViolation) {
            System.out.println("Safety Check: COMPLIANT. Train is safe to dispatch.");
        } else {
            System.out.println("Safety Check: FAILED. Rule violation detected in consist!");
        }
    }

    static class Bogie {
        private String name;
        private int capacity;

        public Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public String getName() {
            return name;
        }

        public int getCapacity() {
            return capacity;
        }

        @Override
        public String toString() {
            return name + " (" + capacity + " seats)";
        }
    }

    static class GoodsBogie {
        private String shape;
        private String cargo;

        public GoodsBogie(String shape, String cargo) {
            this.shape = shape;
            this.cargo = cargo;
        }

        public String getShape() {
            return shape;
        }

        public String getCargo() {
            return cargo;
        }

        @Override
        public String toString() {
            return shape + " (" + cargo + ")";
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
        
        System.out.println("\n--- UC7: Sort Bogies by Capacity (Comparator) ---");
        app.sortBogiesByCapacity();
        
        System.out.println("\n--- UC8: Filter Bogies Using Streams ---");
        app.filterBogiesByCapacity();
        
        System.out.println("\n--- UC9: Group Bogies by Type (Collectors.groupingBy) ---");
        app.groupBogiesByType();
        
        System.out.println("\n--- UC10: Count Total Seats in Train (reduce) ---");
        app.countTotalSeats();
        
        System.out.println("\n--- UC11: Validate Train ID & Cargo Codes (Regex) ---");
        app.validateInputFormats();

        System.out.println("\n--- UC12: Safety Compliance Check for Goods Bogies ---");
        app.checkSafetyCompliance();
    }
}
