import java.util.Random;
class sumOfSquares {
    public static void main(String[] args) {
        int[] arr = new int[5];
        
        for(int i=0; i<5; i++){
            Random rand = new Random();
            int rand1 = rand.nextInt(10)-5;
            arr[i] = rand1;
             // Print random integers
            System.out.println("Random Integers: "+ arr[i]);
        }
        
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            sum += arr[i]*arr[i];
        }
        System.out.println("Sum = " + sum);
    }
}