package ebs.testSuite;

import java.util.ArrayList;
class PrimeDirective {

	// Add your methods here:
	public boolean isPrime(int number){
		if(number == 2){
			return true;
		}else if(number < 2){
			return false;
		}

		for(int i = 2; i < number; i++){
			if(number %  i == 0){
				return false;
			}
		}
		return true;
	}

	public ArrayList<Integer> onlyPrimes(int[] numbers){
		ArrayList <Integer> primes = new ArrayList<>();
		for(int number : numbers){
			if(isPrime(number)){
				primes.add(number);
			}
		}
		return primes;
	}

	public void oddEvenFilter(int[] numbers){

		for(int number : numbers){
			if(number % 2 == 0){
				System.out.println(number + " Is even");
			}else{
				System.out.println(number + " Is odd");
			}
		}
	}
	/*
	 *  input the number of primes
	 *  number of primes = 2
	 *  array = {3,6,7,9,44,55,33}
	 *  
	 *  traverse throughout the array:
	 *  output: " x, y "
	 *  
	 *  find all the primes in a array
	 *  find an equal number of primes in a array to the input number
	 *  
	 * 
	 * 
	 * */

	public ArrayList<Integer> returnNPrimes(int[] numbers , int nPrimes){
		ArrayList <Integer> primes = new ArrayList<>();

		for(int i = 0; i < numbers.length; i++){
			if(isPrime(numbers[i]) && primes.size() <= nPrimes ){
				primes.add(numbers[i]);
			}
		}
		return primes;
	}

	public ArrayList<Integer> fibonachi(int n){
		ArrayList <Integer> fibonachi = new ArrayList<>(100);
		int n1= 0;
		int n2 = 1;
		int result;

		
		for(int i = 2; i < n; i++){
			result = n1 + n2;
			fibonachi.add(result);
			n1 = n2;
			n2 = result;
		}
		return fibonachi;
	}

	public static void main(String[] args) {

		PrimeDirective pd = new PrimeDirective();
		int[] numbers = {6, 29, 28, 33, 11, 100, 101, 43, 89};
		//		//System.out.println(pd.isPrime(0));
		//		System.out.println(pd.onlyPrimes(numbers));
		//		//pd.oddEvenFilter(numbers);
		//		System.out.println(pd.returnNPrimes(numbers, 5));

		System.out.println(pd.fibonachi(20));
	}  

} 