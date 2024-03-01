import java.util.Scanner;
import java.util.Random;

class HelloWorld {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Assuming 3 cities for simplicity
        int numCities = 3;
        int[][] distances = new int[numCities][numCities];

        // Input distances between cities
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                if (i != j) {
                    System.out.println("Enter distance between City " + (i + 1) + " and City " + (j + 1) + ": ");
                    distances[i][j] = scanner.nextInt();
                } else {
                    distances[i][j] = 0; // Distance from a city to itself is 0
                }
            }
        }

        int[] currentTour = generateRandomTour(numCities);

        boolean done = false;

        while (!done) {
            int[] best_neighbour = currentTour.clone();
            for (int i = 0; i < numCities; i++) {
                int[] neighbour = generateRandomTour(numCities);
                if (calculateTourDistance(neighbour, distances) < calculateTourDistance(best_neighbour, distances)) {
                    best_neighbour = neighbour;
                }
            }

            if (compareTours(currentTour, best_neighbour)) {
                done = true;
            } else {
                currentTour = best_neighbour;
            }
        }

        System.out.println("Best Tour:");
        for (int city : currentTour) {
            System.out.print("City " + (city + 1) + " -> ");
        }

        int objVal = calculateTourDistance(currentTour, distances);
        System.out.println("\nTotal Distance: " + objVal);
    }

    public static int[] generateRandomTour(int numCities) {
        int[] tour = new int[numCities];
        for (int i = 0; i < numCities; i++) {
            tour[i] = i;
        }
        // Shuffle the tour using Fisher-Yates algorithm
        Random rand = new Random();
        for (int i = numCities - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = tour[i];
            tour[i] = tour[j];
            tour[j] = temp;
        }
        return tour;
    }

    public static int calculateTourDistance(int[] tour, int[][] distances) {
        int distance = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            distance += distances[tour[i]][tour[i + 1]];
        }
        // Add distance from the last city back to the starting city
        distance += distances[tour[tour.length - 1]][tour[0]];
        return distance;
    }

    public static boolean compareTours(int[] tour1, int[] tour2) {
        // Compare if two tours are the same
        for (int i = 0; i < tour1.length; i++) {
            if (tour1[i] != tour2[i]) {
                return false;
            }
        }
        return true;
    }
}
