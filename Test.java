package LABS;

public class Test {
//This is test class for Solution class!
	public static void main(String[] args) {

		Solution[] sList = new Solution[10];
		
		for(int i=0;i<10;i++) {
			Solution s= new Solution(10);
			sList[i]=s;
			System.out.println(s.toString() + " Fitness = " + s.FitnessValue());
		}
		System.out.println("--------------------");
		for(int i=0;i<10;i=i+2) {
			Solution offspring = sList[i].Crossover(sList[i+1]);
			System.out.println(offspring.toString() + 
					" Fitness = " + offspring.FitnessValue());
		}
		
	}

}