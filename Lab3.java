package newExample;

import java.util.Random;

public class Lab1 {

	public static void main(String[] args) {
		double x[] = new double[10];
		for(int i=0;i<10;i++) {
		x[i] = -100 + (200*(int)(Math.random()));
		}
		
		int maxInt=1000;
		int t=0;
		
		while(t<maxInt)
		{
			double[] n = Movement(x);
			if(Fitness(n) < Fitness(x))
			{
				x=n;
		    }
		t++;
		System.out.println("Iteration: " + t + " Fitness Value: " + Fitness(x));
		}
		
		
		System.out.println("Data:");
		String s ="";
		for(int i=0;i<10;i++) {
			s += ","+x[i];
		}
		System.out.println(s);
		
	}//main class paranthesis
	
		public static double[] Movement(double[] x) 
		{
			double[] arr=new double[10];
			//double[] x = new double[10];

			for(int i=0;i<arr.length;i++) {
				arr[i] += x[i];
			}
			int ind = (int)(Math.random()*10);
			double f = -2 + Math.random()*4; 
			
			arr[ind] = arr[ind]+f;
			if(arr[ind] > 100) 
				arr[ind] = 100;
			
			if(arr[ind] <- 100) 
				arr[ind] = -100;
			
			return arr;
				
		}//Movement parantheses
		
		public static double Fitness(double[] X) 
		{
			double result =0;
			for(int i=0;i<X.length;i++) {
				result += X[i]*X[i];
			}
			return result;
		}//Fitness parantheses
		

	}


