package LABS;
import java.util.List;

public class TestClass {

	public static void main(String[] args) {
		
		//Test class for ant colony
		int[][] distances = {
				{0, 5, 8, 3, 7, 1},
				{5, 0, 2, 10, 8, 4},
				{8, 2, 0, 3, 10, 5},
				{3, 10, 3, 0, 2, 7},
				{7, 8, 10, 2, 0, 6},
				{1, 4, 5, 7, 6, 0}
				};

		char[] cities = {'A', 'B', 'C', 'D', 'E', 'F'};
		
		AntColony antColony = new AntColony(distances, cities, 10, 100, 0.1, 1, 2);
		List<Character> shortestPath = antColony.run();
		double shortestDistance = antColony.calculateDistance(shortestPath);
		System.out.println("Shortest Path Found: " + shortestPath);
		System.out.println("Shortest Distance: " + shortestDistance);
		
	}

}
