package LABS;


import java.util.Arrays;
import java.util.Random;

public class GreyWolfOptimizer {
    private int dim;
    private double lb;
    private double ub;
    private int populationSize;
    private int iterations;
    private ObjectiveFunction objFunction;
    private Random random = new Random();

    public GreyWolfOptimizer(ObjectiveFunction objFunction, int dim, double lb, double ub, int populationSize, int iterations) {
        this.objFunction = objFunction;
        this.dim = dim;
        this.lb = lb;
        this.ub = ub;
        this.populationSize = populationSize;
        this.iterations = iterations;
    }

    public interface ObjectiveFunction {
        double compute(double[] x);
    }

    public double[][] initializePopulation() {
        double[][] population = new double[populationSize][dim];
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < dim; j++) {
                population[i][j] = lb + (ub - lb) * random.nextDouble();
            }
        }
        return population;
    }

    public double[] updatePosition(double[] position, double[] alpha, double[] beta, double[] delta, double a) {
        double[] newPosition = new double[dim];
        for (int j = 0; j < dim; j++) {
            double r1 = random.nextDouble();
            double r2 = random.nextDouble();
            double A1 = 2 * a * r1 - a;
            double C1 = 2 * r2;
            double D_alpha = Math.abs(C1 * alpha[j] - position[j]);
            double X1 = alpha[j] - A1 * D_alpha;

            r1 = random.nextDouble();
            r2 = random.nextDouble();
            double A2 = 2 * a * r1 - a;
            double C2 = 2 * r2;
            double D_beta = Math.abs(C2 * beta[j] - position[j]);
            double X2 = beta[j] - A2 * D_beta;

            r1 = random.nextDouble();
            r2 = random.nextDouble();
            double A3 = 2 * a * r1 - a;
            double C3 = 2 * r2;
            double D_delta = Math.abs(C3 * delta[j] - position[j]);
            double X3 = delta[j] - A3 * D_delta;

            newPosition[j] = (X1 + X2 + X3) / 3;
            newPosition[j] = Math.max(lb, Math.min(ub, newPosition[j]));
        }
        return newPosition;
    }

    public double[] optimize() {
        double[][] population = initializePopulation();
        double[] alphaPosition = new double[dim];
        double[] betaPosition = new double[dim];
        double[] deltaPosition = new double[dim];
        double alphaScore = Double.POSITIVE_INFINITY;
        double betaScore = Double.POSITIVE_INFINITY;
        double deltaScore = Double.POSITIVE_INFINITY;

        for (int iteration = 0; iteration < iterations; iteration++) {
            for (int i = 0; i < populationSize; i++) {
                double fitness = objFunction.compute(population[i]);

                if (fitness < alphaScore) {
                    alphaScore = fitness;
                    alphaPosition = Arrays.copyOf(population[i], dim);
                } else if (fitness < betaScore) {
                    betaScore = fitness;
                    betaPosition = Arrays.copyOf(population[i], dim);
                } else if (fitness < deltaScore) {
                    deltaScore = fitness;
                    deltaPosition = Arrays.copyOf(population[i], dim);
                }
            }

            double a = 2 - iteration * (2.0 / iterations);

            for (int i = 0; i < populationSize; i++) {
                population[i] = updatePosition(population[i], alphaPosition, betaPosition, deltaPosition, a);
            }

            System.out.println("Iteration " + (iteration + 1) + ": Alpha Score = " + alphaScore);
        }

        return alphaPosition;
    }

    public static void main(String[] args) {
        int dim = 5;  // Dimension of the problem
        double lb = -10;  // Lower bound
        double ub = 10;  // Upper bound
        int populationSize = 20;  // Number of search agents
        int iterations = 100;  // Number of iterations

        ObjectiveFunction objFunction = x -> {
            double sum = 0.0;
            for (double xi : x) {
                sum += xi * xi;
            }
            return sum;
        };

        GreyWolfOptimizer gwo = new GreyWolfOptimizer(objFunction, dim, lb, ub, populationSize, iterations);
        double[] bestPosition = gwo.optimize();
        double bestScore = objFunction.compute(bestPosition);

        System.out.println("Best Position: " + Arrays.toString(bestPosition));
        System.out.println("Best Score: " + bestScore);
    }
}
