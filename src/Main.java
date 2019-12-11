import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	public static Object primes(int start, int end, int N) throws InterruptedException {
		System.out.println("Checking primes within the range [" + start + ", " + end + "] with " + N + " threads.\n");
		ArrayList<Integer> primes = new ArrayList<Integer>();  // array of primes
		Thread[] threads = new Thread[N]; // N threads
		checkPrime[] method = new checkPrime[N]; // N checkPrime objects 
		int range = ((end - start + 1) / N); // the size of one subrange 
		int subrangeA;

		for (int i = 0; i < N; i++) {
			subrangeA = start + i * range; // current end of range 
			if (i == (N - 1)) // last range 
			// makes sure the last thread will calculate the rest of the
			// integers if they don't divide to N threads
			{
				range = end - subrangeA + 1;
			}

			method[i] = new checkPrime(subrangeA, range); // current checkPrime object
			// creates a job for thread i with subrange of A-B
			threads[i] = new Thread(method[i]); // current thread 
			threads[i].start(); // runs method on current thread

		}
			for (int i = 0; i < N; i++) {
				//makes sure all threads finish before moving on
				threads[i].join();
			}


		for (int i = 0; i < N; i++) // adds all primes found in all threads to one array list
		{
			primes.addAll(method[i].getPrimes());
		}
		if (primes.isEmpty())
			return "No Prime in range";
		
		Collections.sort(primes); // sorts list
		return primes;
	}
	public static void main(String[]args) throws InterruptedException {
		int left = 0, right = 0, N = 1; // range between left to right, N - number of threads
		try {
			if (args.length >= 3)
			{
				left = Integer.parseInt(args[0]);
				right = Integer.parseInt(args[1]);
				N = Integer.parseInt(args[2]);
				System.out.println("The primes are : ");
				System.out.println(primes(left, right, N));
			} else {
				System.out.println("Not enough arguments passed");
			}
		}catch (NumberFormatException ex){
			System.out.println("invalid arguments passed." + ex.getMessage());
        }
	}
}


