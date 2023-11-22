abstract class Vessel {
    protected String name; 
    protected int capacity;

    // Constructor for Vessel. 
    public Vessel(String name, int capacity) {
        this.name = name; 
        this.capacity = capacity; 
    }

    // These are abstract methods. Any class that extends Vessel
    // needs to provide these methods.
    public abstract void loadingCargo(int amount, String type);
    public abstract float loadingFraction();
}

class Container extends Vessel {
    private int containersLoaded; // Tracks how many containers are currently loaded.

    // Constructor for a Container
    public Container(String name, int capacity) {
        super(name, capacity);
    }

    // Method to load containers. Only loads if the unit type is "TEU".
    public void loadingCargo(int containers, String unit) {
        if ("TEU".equals(unit)) { 
            this.containersLoaded = Math.min(containers, this.capacity);
        }
    }

    // Calculates what fraction of the vessel's capacity is used.
    public float loadingFraction() {
        return (float) this.containersLoaded / this.capacity; 
    }
}

class Tanker extends Vessel {
    private float[] compartments; // An array to hold how much each compartment is filled.
    private int capacityPerCompartment; // The capacity of each compartment.

    // Constructor for a Tanker.
    public Tanker(String name, int capacityPerCompartment) {
        super(name, capacityPerCompartment * 10); 
        compartments = new float[10]; 
        this.capacityPerCompartment = capacityPerCompartment; // Sets the compartment capacity.
    }

    // Tries to load cargo into a compartment.
    public void loadingCargo(int amount, String compartment) {
        try {
            // Tries to convert the string compartment number into an integer.
            int compartmentNumber = Integer.parseInt(compartment);
            if (compartmentNumber >= 1 && compartmentNumber <= compartments.length) {
                compartments[compartmentNumber - 1] = Math.min(amount, capacityPerCompartment);
            } else {
                // If the compartment number is out of range, prints an error message.
                System.out.println("Error: Compartment number must be between 1 and " + compartments.length);
            }
        } catch (NumberFormatException e) {
            // If the string can't be converted to an integer, prints an error message.
            System.out.println("Error: Compartment number must be an integer.");
        }
    }

    // Calculates the fraction of the tanker's capacity that's filled.
    public float loadingFraction() {
        float total = 0;
        for (int i = 0; i < compartments.length; i++) {
            total += compartments[i];
        }
        return total / this.capacity; // Divides the filled capacity by the tanker's total capacity.
    }
}

class RoRo extends Vessel {
    private int laneMetersOccupied; // Keeps track of how many lane meters are occupied.

    // Constructor for RoRo. 
    public RoRo(String name, int totalLaneMeters) {
        super(name, totalLaneMeters);
        laneMetersOccupied = 0; // Starts with no lane meters occupied.
    }

    // Loads vehicles onto the RoRo vessel.
    public void loadingCargo(int vehicleCount, String type) {
        int vehicleLength;
        // Checks the type of vehicle and sets the length.
        if ("car".equals(type)) {
            vehicleLength = 8; // A car occupies 8 meters of lane space.
        } else if ("truck".equals(type)) {
            vehicleLength = 30; // A truck occupies 30 meters of lane space.
        } else {
            vehicleLength = 0; // If the type is unknown, it thinks that the vehicle length is equal to zero.
        }

        // Adds the total length of vehicles being loaded to laneMetersOccupied, without exceeding capacity.
        this.laneMetersOccupied = Math.min(this.laneMetersOccupied + (vehicleCount * vehicleLength), this.capacity);
    }

    // Calculates the fraction of lane meters occupied compared to the vessel's total capacity.
    public float loadingFraction() {
        return (float) this.laneMetersOccupied / this.capacity; // The occupied lane meters divided by total capacity.
    }
}


