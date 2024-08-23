package LABS;
import java.util.Random;
public class HillClimbing_Sphere {
	
	public static void main (String[] args) {
		int maxInt = 1000;
		int t = 0;
		double[] X = new double[10];
		for(int i=0; i<10;i++) {
			X[i] = -100 +(200* Math.random());//-100 between 100
		}
		while(t < maxInt) {
			double[] n = Movement(X);
			if( Fitness(n) < Fitness(X))
			{
				X = n;
			}
			t++;
			System.out.println("iteration: "+ t + " Fitness Value: "+ Fitness(X));
		}
		
		System.out.print("");
        System.out.println("Data:");
		
        System.out.println("Minimum values list:");
		String s = "";
		for(int i=0;i<10;i++) {
			s += "[,\n" +X[i]+"]";
		}
		
		System.out.println(s);
	}
	
	public static double Fitness(double[] X) {

		int result=0;
		for (int i = 0; i < X.length; i++) {
		result+=X[i]*X[i];
		}
		return result;
	}
	
	public static double[] Movement(double[] X) {//array göndermek gerekiyor.
		double[] n = new double[10];
		for(int i=0;i<10;i++) {
			n[i]= X[i];
		}
		int ind = (int)(Math.random()*10);//bunu niye yazdı?
		double a = -2 +Math.random()*4; //between -2  and 2
		n[ind] = n[ind] + a;
		if(n[ind] < -100) 
			n[ind] = -100;
		if(n[ind] > 100) 
			n[ind] = 100;
		return n;
	}
	

}
