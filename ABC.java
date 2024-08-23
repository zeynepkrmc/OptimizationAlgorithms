package LABS;

public class ABC extends ABCAlgorithm {

	public static void main(String[] args) {
		
		int popSize = 20; 
		int dim = 3; 
		double lowerBound = -10; 
		double upperBound = 10; 
		int maxIter = 100; 
		int limit = 20; 
		double[] bestSolution = abc(popSize, dim, lowerBound, upperBound, maxIter, limit);
		System.out.println("Best Solution: " + java.util.Arrays.toString(bestSolution));
	}

}