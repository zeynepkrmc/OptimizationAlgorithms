package LABS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AntColony {

	private int[][] distances;
	private char[] cities;
	private int numAnts;
	private int numIterations;
	private double decay;
	private double alpha;
	private double beta;
	private int numCities;
	private double[][] pheromone;

	public AntColony(int[][] distances, char[] cities, int numAnts, int numIterations, double decay, double alpha, double beta) {
		this.distances = distances;
		this.cities = cities;
		this.numAnts = numAnts;
		this.numIterations = numIterations;
		this.decay = decay;
		this.alpha = alpha;
		this.beta = beta;
		this.numCities = distances.length;
		this.pheromone = new double[numCities][numCities];
		initializePheromone();
	}

	public void initializePheromone() {double initialPheromone = 1.0 / (numCities * numCities);
			for (int i = 0; i < numCities; i++) {
				for (int j = 0; j < numCities; j++) {
					pheromone[i][j] = initialPheromone;
					}
		}
	}

	public List<Character> run() {
		List<Character> shortestPath = null;
		double shortestDistance = Double.POSITIVE_INFINITY;

		for (int iteration = 0; iteration < numIterations; iteration++) {
			List<List<Character>> paths = generatePaths();
			updatePheromone(paths);
			for (List<Character> path : paths) {
			double distance = calculateDistance(path);
				if (distance < shortestDistance) {
					shortestDistance = distance;
					shortestPath = new ArrayList<>(path);
			}
		}
		pheromone = decayPheromone(pheromone);
	}

	return shortestPath;
	}

	public List<List<Character>> generatePaths() {
	List<List<Character>> paths = new ArrayList<>();
	Random random = new Random();

	for (int ant = 0; ant < numAnts; ant++) {
		List<Character> path = new ArrayList<>();
		List<Character> visited = new ArrayList<>();
		char startCity = cities[random.nextInt(numCities)];
		char currentCity = startCity;
		path.add(currentCity);
		visited.add(currentCity);

	while (visited.size() < numCities) {
		char nextCity = selectNextCity(currentCity, visited);
		path.add(nextCity);
		visited.add(nextCity);
		currentCity = nextCity;
	}
	path.add(startCity); // Complete the tour
	paths.add(path);
	}

	return paths;
	}

	public char selectNextCity(char currentCity, List<Character> visited) {
	double[] probabilities = calculateProbabilities(currentCity, visited);
	double randomNumber = Math.random();
	double cumulativeProbability = 0;

	for (int i = 0; i < numCities; i++) {
		cumulativeProbability += probabilities[i];
		if (randomNumber <= cumulativeProbability) {
			return cities[i];
		}
	}

	// Should not reach here
	return ' ';
	}

	public double[] calculateProbabilities(char currentCity, List<Character> visited) {
		double[] probabilities = new double[numCities];
		double total = 0;

		int currentIndex = indexOfCity(currentCity);

		for (int i = 0; i < numCities; i++) {
		char city = cities[i];
		if (!visited.contains(city)) {
			int distance = distances[currentIndex][i];
			double pheromoneLevel = pheromone[currentIndex][i];
			double heuristic = 1.0 / distance;
			double probability = Math.pow(pheromoneLevel, alpha) * Math.pow(heuristic, beta);
			probabilities[i] = probability;
			total += probability;
		}
	}

	for (int i = 0; i < numCities; i++) {
		probabilities[i] /= total;
	}
	return probabilities;
	}
	
	public void updatePheromone(List<List<Character>> paths) {
		for (int i = 0; i < numCities; i++) {
			for (int j = 0; j < numCities; j++) {
				if (i != j) {
					pheromone[i][j] *= (1 - decay);
			}
		}
	}

	for (List<Character> path : paths) {
		for (int i = 0; i < numCities; i++) {
			int fromIndex = indexOfCity(path.get(i));
			int toIndex = indexOfCity(path.get(i + 1));
			pheromone[fromIndex][toIndex] += 1.0 / calculateDistance(path);
			pheromone[toIndex][fromIndex] = pheromone[fromIndex][toIndex];
			}
		}
	}

	public double[][] decayPheromone(double[][] pheromone) {
		double[][] newPheromone = new double[numCities][numCities];
		for (int i = 0; i < numCities; i++) {
			for (int j = 0; j < numCities; j++) {
				newPheromone[i][j] = Math.max(pheromone[i][j] * decay, 0.001);
			}
		}
	return newPheromone;
	}

	public double calculateDistance(List<Character> path) {
	double distance = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			int fromIndex = indexOfCity(path.get(i));
			int toIndex = indexOfCity(path.get(i + 1));
				distance += distances[fromIndex][toIndex];
			}
		return distance;
	}

	public int indexOfCity(char city) {
		for (int i = 0; i < numCities; i++) {
		if (cities[i] == city) {
			return i;
			}
		}
		return -1;
	}
}
