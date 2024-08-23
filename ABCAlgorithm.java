package LABS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ABCAlgorithm {

	// 1. İşçi arılar fazı
private static void employedBeesPhase(List<double[]> population, double[] objectiveValues, int[] trialCounter, double lowerBound, double upperBound, double phi) {
	Random rand = new Random();
	for (int i = 0; i < population.size(); i++) {
		int k = rand.nextInt(population.size());
		while (k == i) {
			k = rand.nextInt(population.size());
		}
		double[] newSolution = updateSolution(population.get(i), lowerBound, upperBound, phi);
		double newObjective = objectiveFunction(newSolution);
		if (newObjective < objectiveValues[i]) {
			population.set(i, newSolution);
			objectiveValues[i] = newObjective;
			trialCounter[i] = 0;
		} else {
			trialCounter[i]++;
		}
	}
}
// 2. Gözcü arılar fazı

private static void onlookerBeesPhase(List<double[]> population, double[] objectiveValues, int[] trialCounter, double lowerBound, double upperBound, double phi) {

	Random rand = new Random();
	double totalFitness = 0;
	for (double value : objectiveValues) {

		totalFitness += 1.0 / (1.0 + value);
	}

	double[] fitness = new double[population.size()];

	for (int i = 0; i < population.size(); i++) {

		fitness[i] = (1.0 / (1.0 + objectiveValues[i])) / totalFitness;

	}

	for (int i = 0; i < population.size(); i++) {

		if (rand.nextDouble() < fitness[i]) {

			int k = rand.nextInt(population.size());

			while (k == i) {

				k = rand.nextInt(population.size());

			}

			double[] newSolution = updateSolution(population.get(i), lowerBound, upperBound, phi);

			double newObjective = objectiveFunction(newSolution);

			if (newObjective < objectiveValues[i]) {

				population.set(i, newSolution);

				objectiveValues[i] = newObjective;

				trialCounter[i] = 0;

			} else {

				trialCounter[i]++;

			}

		}

	}
}

// 3. Keşif arıları fazı

private static void scoutBeesPhase(List<double[]> population, double[] objectiveValues, int[] trialCounter, double lowerBound, double upperBound, int limit) {

	Random rand = new Random();

	for (int i = 0; i < population.size(); i++) {

		if (trialCounter[i] >= limit) {

			double[] newSolution = new double[population.get(i).length];

			for (int j = 0; j < newSolution.length; j++) {

				newSolution[j] = rand.nextDouble() * (upperBound - lowerBound) + lowerBound;

			}

			population.set(i, newSolution);

			objectiveValues[i] = objectiveFunction(newSolution);

			trialCounter[i] = 0;

		}

	}
}
// Amaç fonksiyonunu hesaplamak için yardımcı fonksiyon
private static double objectiveFunction(double[] solution) {

	return Math.sqrt(2 * Math.pow((10 - solution[0]), 2) + Math.pow((5 - solution[1]), 2) + Math.pow((8 - solution[2]), 2));
}
// Bir çözümü güncellemek için yardımcı fonksiyon
private static double[] updateSolution(double[] solution, double lowerBound, double upperBound, double phi) {

	double[] newSolution = new double[solution.length];

	Random rand = new Random();

	for (int i = 0; i < solution.length; i++) {

		double delta = phi * (rand.nextDouble() - 0.5);

		double newX = solution[i] + delta;

		newX = Math.min(Math.max(newX, lowerBound), upperBound);

		newSolution[i] = newX;

	}

	return newSolution;
}
public static double[] abc(int popSize, int dim, double lowerBound, double upperBound, int maxIter, int limit) {

	List<double[]> population = initializePopulation(popSize, dim, lowerBound, upperBound);

	double[] objectiveValues = new double[popSize];

	for (int i = 0; i < popSize; i++) {

		objectiveValues[i] = objectiveFunction(population.get(i));

	}

	int[] trialCounter = new int[popSize];

	double[] bestSolution = population.get(0);

	double bestValue = objectiveFunction(bestSolution);

	for (int iteration = 0; iteration < maxIter; iteration++) {

		double phi = new Random().nextDouble() * 2 - 1;
// 1. İşçi arılar fazı

		employedBeesPhase(population, objectiveValues, trialCounter, lowerBound, upperBound, phi);
// 2. Gözcü arılar fazı

		onlookerBeesPhase(population, objectiveValues, trialCounter, lowerBound, upperBound, phi);
// 3. Keşif arıları fazı

		scoutBeesPhase(population, objectiveValues, trialCounter, lowerBound, upperBound, limit);

		double currentBestValue = Double.MAX_VALUE;

		double[] currentBestSolution = null;

		for (double[] solution : population) {

			double value = objectiveFunction(solution);

			if (value < bestValue) {

				bestSolution = solution;

				bestValue = value;

			}

			if (value < currentBestValue) {

				currentBestSolution = solution;

				currentBestValue = value;

			}

		}
		System.out.println("Iteration " + (iteration + 1) + ": Best Value = " + bestValue);

	}
return bestSolution;
}

// Rastgele bir başlangıç popülasyonu oluşturmak için yardımcı fonksiyon
private static List<double[]> initializePopulation(int popSize, int dim, double lowerBound, double upperBound) {

	List<double[]> population = new ArrayList<>();

	Random rand = new Random();

	for (int i = 0; i < popSize; i++) {

		double[] solution = new double[dim];

		for (int j = 0; j < dim; j++) {
			solution[j] = rand.nextDouble() * (upperBound - lowerBound) + lowerBound;
		}
		population.add(solution);
	}
	return population;
}
}