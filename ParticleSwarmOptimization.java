package LABS;

public class ParticleSwarmOptimization {
	
	public static void main(String[] args) {
		 int maxIt = 100;
		 int dim = 5;
		 int np = 10;
		 float c1=2, c2=2;
		 
		 double[][] p= new double[np][];
		 double[][] pBest = new double[np][];
		 
		 double[] gBest= new double[dim];
		 double gFit = Double.MAX_VALUE;
		 
		 double[][] v =new double[np][];
		 
		 for(int i =0; i<np; i++) {
			 v[i] = new double[dim];
			 p[i] = new double[dim];
			 pBest[i] = new double[dim];
			 
			 
			 for(int j=0;j<dim;j++) {
				 p[i][j] = (Math.random() * 10 )-5;
				 pBest[i][j] = p[i][j];
				 v[i][j] =0;
			 }
		 }
		 
		 for(int t=0;t<maxIt; t++) {
			 for(int i=0;i<np;i++) {
				 double pb = fitness(pBest[i]);
				 double fb = fitness(p[i]);
				 
				 //update pBest
				 if(pb > fb) {
					 for(int j=0;j<dim; j++) {
						 pBest[i][j] = p[i][j];
						 
					 }
				 }
				 //update gBest
				 if(gFit > fb) {
					 for(int j=0; j<dim; j++) {
						 gBest[j] = p[i][j];
					 }
					 gFit =fb;
				 }
			 }
			 System.out.println(t + "  Iteration " + gFit);
			 
			 for(int i=0;i<np;i++) {
				 for(int j=0; j< dim; j++) {
					 v[i][j] = v[i][j] + (c1 *Math.random()* (pBest[i][j] - p[i][j])) + (c2 *Math.random()* (gBest[j] - p[i][j]));
					p[i][j] = p[i][j] + v[i][j];
				 }
			 }
		 }
		 System.out.println("Fitness Evaluation Number: " + fitness(gBest));
		 for(int j=0; j<dim; j++) {
			 System.out.println("gBest[i] : " + gBest[j]);
		 } 
	}
	
	static double fitness(double[] x) {
		double retVal =0;
		for(int i=0; i<x.length-1;i++) {
			retVal = retVal + 100 * (x[i+1]- Math.pow(x[i], 2)) + Math.pow(x[i]-1, 2);
		}
		return retVal;
	}
}
