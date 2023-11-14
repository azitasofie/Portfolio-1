public class VesselsTest {
    public static void main(String[] args) {
        Tanker tanker = new Tanker("Oil Tanker", 100);

        // Test 1: Load 50 cubic meters of cargo into the first compartment and verify
        // that
        // the loading fraction matches the expected value of 0.05.
        tanker.loadingCargo(50, "1");
        assert tanker.loadingFraction() == 0.05f : "Cargo loading failed: The tanker should be 5% full.";

        // Test 2: Attempt to load cargo into a non-existent 11th compartment. 
        try {
            tanker.loadingCargo(50, "11");
        } catch (NumberFormatException e) {
            // The exception is expected, so it's caught and ignored.
        }

        // Test 3: Attempt to load cargo into a compartment with a non-numeric
        // identifier.
        try {
            tanker.loadingCargo(50, "a");
        } catch (NumberFormatException e) {
            // The exception is expected, so it's caught and ignored.
        }

        // Test 4: Try to overload the second compartment with 150 cubic meters, which
        // exceeds
        // the capacity per compartment. The loadingFraction should still not exceed 0.1.
        tanker.loadingCargo(150, "2");
        assert tanker.loadingFraction() <= 0.1f
                : "Overloading failed: The tanker should not be able to overload a compartment.";

        // Test 5: Load cargo evenly across compartments 3, 4, and 5 with the correct
        // capacity
        // and verify that the total loading fraction is equal to 0.25.
        tanker.loadingCargo(100, "3");
        tanker.loadingCargo(100, "4");
        tanker.loadingCargo(100, "5");
        assert tanker.loadingFraction() == 0.25f
                : "Cargo loading across compartments failed: The tanker should be 25% full.";

        // Print a message: all tests have completed.
        System.out.println("All tests completed.");
    }
}
