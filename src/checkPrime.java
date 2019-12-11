import java.util.ArrayList;

public class checkPrime implements Runnable {
	
	//A and B are a subrange of the total range to check for the thread
	private int start = 0, end = 0;
	
	//the array list containing primes in the subrange
	private ArrayList<Integer> primes;

	// A - Begin at number
	// range - number of Integer to check, including A
	public checkPrime(int start, int range) {
		this.start = start;
		this.end = start + range - 1;
		primes = new ArrayList<Integer>();
	}

	@Override
	public void run() {
		for (int i = start; i <= end; i++) {
			if (isPrime(i)) {
				primes.add(i);
			}
		}
	}

	private boolean isPrime(int n) {
		// check if n is a multiple of 2

		if (n % 2 == 0)
			return false;
		// if not, then just check the odds
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	
	public ArrayList<Integer> getPrimes() {
		return primes;
	}

	public void setPrimes(ArrayList<Integer> primes) {
		this.primes = primes;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int x) {
		this.start = x;
	}

	public int getRange() {
		return start-end;
	}

	public void setRange(int range) {
		this.end = start+range;
	}
}