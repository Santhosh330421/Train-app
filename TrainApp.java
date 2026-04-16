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
    public void sortBogiesByCapacity() throws InvalidCapacityException {
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
    public void filterBogiesByCapacity() throws InvalidCapacityException {
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
    public void groupBogiesByType() throws InvalidCapacityException {
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
    public void countTotalSeats() throws InvalidCapacityException {
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

    // UC13: Performance Comparison (Loops vs Streams)
    public void comparePerformance() throws InvalidCapacityException {
        List<Bogie> largeBogieList = new ArrayList<>();
        // Generate a large dataset
        for (int i = 0; i < 100000; i++) {
            largeBogieList.add(new Bogie("Sleeper", 72));
            largeBogieList.add(new Bogie("First Class", 24));
            largeBogieList.add(new Bogie("AC Chair", 60));
            largeBogieList.add(new Bogie("General", 90));
        }

        System.out.println("Benchmarking with dataset of size: " + largeBogieList.size());

        // Baseline: Loop-Based Filtering
        long loopStartTime = System.nanoTime();
        List<Bogie> loopFiltered = new ArrayList<>();
        for (Bogie b : largeBogieList) {
            if (b.getCapacity() > 60) {
                loopFiltered.add(b);
            }
        }
        long loopEndTime = System.nanoTime();
        long loopDuration = loopEndTime - loopStartTime;

        // Alternative: Stream-Based Filtering
        long streamStartTime = System.nanoTime();
        List<Bogie> streamFiltered = largeBogieList.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());
        long streamEndTime = System.nanoTime();
        long streamDuration = streamEndTime - streamStartTime;

        System.out.println("Loop-based filtered count: " + loopFiltered.size() + " | Time: " + loopDuration + " ns");
        System.out.println("Stream-based filtered count: " + streamFiltered.size() + " | Time: " + streamDuration + " ns");
        
        if (loopFiltered.size() == streamFiltered.size()) {
            System.out.println("Verification: Both filtering methods produced identical results.");
        } else {
            System.out.println("Verification FAILED: Results differ!");
        }
    }

    // UC14: Handle Invalid Bogie Capacity (Custom Exception)
    public void validatePassengerBogieCapacity() {
        System.out.println("Attempting to create valid passenger bogie...");
        try {
            Bogie validBogie = new Bogie("Sleeper", 72);
            System.out.println("Success: " + validBogie);
        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nAttempting to create invalid passenger bogie (Negative Capacity)...");
        try {
            Bogie invalidBogie = new Bogie("AC Chair", -10);
            System.out.println("Success: " + invalidBogie);
        } catch (InvalidCapacityException e) {
            System.out.println("Validation Error Caught: " + e.getMessage());
        }

        System.out.println("\nAttempting to create invalid passenger bogie (Zero Capacity)...");
        try {
            Bogie zeroBogie = new Bogie("General", 0);
            System.out.println("Success: " + zeroBogie);
        } catch (InvalidCapacityException e) {
            System.out.println("Validation Error Caught: " + e.getMessage());
        }
    }

    // UC15: Safe Cargo Assignment Using try-catch-finally
    public void assignCargoSafely() {
        System.out.println("Processing Safe Assignment:");
        try {
            GoodsBogie safeBogie = new GoodsBogie("Cylindrical", "None");
            assignCargo(safeBogie, "Petroleum");
            System.out.println("Result: " + safeBogie.getCargo() + " successfully assigned to " + safeBogie.getShape() + " bogie.");
        } catch (CargoSafetyException e) {
            System.out.println("Error Caught: " + e.getMessage());
        } finally {
            System.out.println("Assignment check complete for safe bogie.\n");
        }

        System.out.println("Processing Unsafe Assignment:");
        try {
            GoodsBogie unsafeBogie = new GoodsBogie("Rectangular", "None");
            assignCargo(unsafeBogie, "Petroleum");
            System.out.println("Result: " + unsafeBogie.getCargo() + " successfully assigned to " + unsafeBogie.getShape() + " bogie.");
        } catch (CargoSafetyException e) {
            System.out.println("Error Caught: " + e.getMessage());
        } finally {
            System.out.println("Assignment check complete for unsafe bogie.");
        }
    }

    private void assignCargo(GoodsBogie bogie, String cargo) {
        if (bogie.getShape().equals("Rectangular") && cargo.equals("Petroleum")) {
            throw new CargoSafetyException("Unsafe assignment: Petroleum cannot be assigned to a Rectangular bogie.");
        }
        bogie.setCargo(cargo);
    }

    // UC16: Sort Passenger Bogies by Capacity (Bubble Sort)
    public void sortBogiesBubbleSort() {
        int[] capacities = {72, 56, 24, 70, 60};
        System.out.print("Original Capacities: ");
        for (int c : capacities) {
            System.out.print(c + " ");
        }
        System.out.println();

        int n = capacities.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (capacities[j] > capacities[j + 1]) {
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }

        System.out.print("Sorted Capacities (Bubble Sort): ");
        for (int c : capacities) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    // UC17: Sort Bogie Names Using Arrays.sort()
    public void sortBogiesUsingArraysSort() {
        String[] bogieNames = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};
        System.out.println("Original Bogie Names: " + java.util.Arrays.toString(bogieNames));
        
        java.util.Arrays.sort(bogieNames);
        
        System.out.println("Sorted Bogie Names (Arrays.sort): " + java.util.Arrays.toString(bogieNames));
    }

    // UC18: Linear Search for Bogie ID (Array-Based Searching)
    public void searchBogieLinear() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        System.out.println("Bogie IDs: " + java.util.Arrays.toString(bogieIds));
        
        String targetFound = "BG309";
        linearSearch(bogieIds, targetFound);
        
        String targetNotFound = "BG999";
        linearSearch(bogieIds, targetNotFound);
    }
    
    private void linearSearch(String[] array, String target) {
        boolean found = false;
        
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                found = true;
                System.out.println("Linear Search: Bogie ID " + target + " FOUND at index " + i + ".");
                break;
            }
        }
        
        if (!found) {
            System.out.println("Linear Search: Bogie ID " + target + " NOT FOUND.");
        }
    }

    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) {
            super(message);
        }
    }

    static class Bogie {
        private String name;
        private int capacity;

        public Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
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

        public void setCargo(String cargo) {
            this.cargo = cargo;
        }

        @Override
        public String toString() {
            return shape + " (" + cargo + ")";
        }
    }

    public static void main(String[] args) throws InvalidCapacityException {
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

        System.out.println("\n--- UC13: Performance Comparison (Loops vs Streams) ---");
        app.comparePerformance();

        System.out.println("\n--- UC14: Handle Invalid Bogie Capacity (Custom Exception) ---");
        app.validatePassengerBogieCapacity();

        System.out.println("\n--- UC15: Safe Cargo Assignment Using try-catch-finally ---");
        app.assignCargoSafely();

        System.out.println("\n--- UC16: Sort Passenger Bogies by Capacity (Bubble Sort) ---");
        app.sortBogiesBubbleSort();

        System.out.println("\n--- UC17: Sort Bogie Names Using Arrays.sort() ---");
        app.sortBogiesUsingArraysSort();

        System.out.println("\n--- UC18: Linear Search for Bogie ID (Array-Based Searching) ---");
        app.searchBogieLinear();
    }
}
