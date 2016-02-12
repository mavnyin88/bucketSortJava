/**
 * @author Michael Avnyin

 * This program reads in a text file determines the largest(max) number in that text file and dynamically allocates the array to maxSize + 1
 * We initialize each index with zero. For each number in our text file we increment our index to 1. If we have multiple of the same number
 * we increment as many of that number in our index. We then print our array with the amount in each index in order. 
 * 
 */
import java.io.*; 
import java.util.Scanner;

public class BucketSort{
	
	int numberFromFile; // number for input text 
	int numberForIndex; // number for index or array 
	int max = 0; // stores max number in array 
	int [] hashArray; // our array 
	Scanner infile; // for text file
	FileWriter fWriter; // to send/write to output file 
	PrintWriter pWriter; // to print to output file
	 
	/**
	 * empty constructor 
	 */
	BucketSort(){
		
	} //end BucketSort
	
	/**
	 * findMax searches through our text file and find the largest number
	 * @param file reads in our args[0] text file
	 */
	void findMax(File file){
		
		try{
			infile  = new Scanner(file);
				while(infile.hasNext()){  // while file is not empy 
					numberFromFile = infile.nextInt(); // set variable to next number in file 
						if(numberFromFile >= max){ // if new number from file  is greater then current max 
							max = numberFromFile; // set max to equal new number from file 
						}
				}
	
		}	
		catch (FileNotFoundException e) {
			e.printStackTrace();
    }
		
	// System.out.println("Max number is " + max);
	infile.close();  // close file
	setArraySize(max); // call method and send max number 
	} // END find max 
	
	/**
	 * dynamically allocates our array size 
	 * @param max is our largest number in our text file used to dynamically allocate array + 1
	 */
	void setArraySize(int max){
		
		hashArray = new int[max+1]; // dynamically allocate array 
			for(int k = 0; k <= max; k++){  
				hashArray[k] = 0;  // initialize indexes with 0
			} // END for loop
	} // END setArraySize
	
	/**
	 * hashForBucketSort reads in our input text file. the while loop takes the number from the text 
	 * @param file is our input text file 
	 */
	void hashForBucketSort(File file){
		
		try{
			infile = new Scanner(file);  // input text file 
				while(infile.hasNext()){  // while file is not empty
					numberForIndex = infile.nextInt();   // set numberForIndex to current number of text 
					hashArray[numberForIndex]++;  // increment number in index for each time it appears in out text 
				} // END while
		} // END try
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		infile.close();  // close file 
		
	} // END hashForBucketSort
	
	/**
	 * Prints out our hashArray 
	 * @param file2 is our output to text file 
	 */
	void printHash(File file2){
		
		try{
			fWriter = new FileWriter(file2);  // write to file
			pWriter = new PrintWriter(fWriter); // print to file 
		} // END try
		catch (IOException e) {
	        e.printStackTrace();
	    }

		for(int j = 0; j <= max; j++){
			
			if(hashArray[j] > 0){  // if our indexes number is greater then 0
				while(hashArray[j] != 0){ // while our index number doesnt equal 0
					System.out.println(j);  // print to console
					pWriter.println(j); // print to file 
					hashArray[j]--; // decrement 
				} // END while
			} // END if
		} // END for
		pWriter.close();	// close file 
	} // end printHash
	
	public static void main(String [] args){
		
		BucketSort bucketSort = new BucketSort(); // creates a variable of type BucketSort so we can use "BucketSorts" methods 
		File file = new File(args[0]);	// for our text file
		File file2 = new File(args[1]); // for our output text file
		bucketSort.findMax(file); // send our text file to findMax method to determine the largest number 
		bucketSort.hashForBucketSort(file); // inserts the numbers of our text file into our array 
		bucketSort.printHash(file2); // prints our array in ascending order
		
	} // END main
 	
} // END BucketSort