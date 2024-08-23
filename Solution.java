package LABS;

public class Solution {
	int[] x;
	int maxW = 40;
	int[] w= {7,5,10,15,5,3,8,4,9,12};
	int[] v= {10,15,5,8,20,10,7,17,5,3};
	
	public Solution(int m) {
		x = new int[m];
		for(int i=0;i<x.length;i++) {
			if (Math.random()<0.5) {
				this.x[i] =0;
			} else {
				this.x[i] =1;
			}
		}
	}
	
	public Solution(int[] x) {
		this.x = x;
	}
	
	Solution Crossover(Solution p2) {
		int[] c = new int[this.x.length];
		
		int p = 2 + (int)(Math.random() * 7);
		
		for(int i=0;i<p;i++) {
			c[i]= this.x[i];
		}
		
		for(int i=p;i<this.x.length;i++) {
			c[i]= p2.x[i];
		}
		
		return new Solution(c);
	}

	int FitnessValue() {
		int s_v=0;
		int s_w=0;
		for(int i=0;i<x.length;i++) {
			s_v= s_v + (x[i]* v[i]);
			s_w= s_w + (x[i]* w[i]);
		}
		if (s_w>maxW) {
			s_v=1000;
		}
		return s_v;
	}
	
	public String toString() {
		String s ="";
		for(int i=0;i<x.length;i++) {
			s= s + x[i];
		}
		return s;
	}
}