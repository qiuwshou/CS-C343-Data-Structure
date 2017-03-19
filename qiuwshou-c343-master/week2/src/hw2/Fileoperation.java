package hw2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.*;
import java.net.URL;
import java.util.Vector;
import java.util.Scanner; 
public class Fileoperation {

	public static void main(String[] args) throws IOException {
	
		URL url = new URL("http://mendel.informatics.indiana.edu/~yye/lab/teaching/spring2014-C343/tweet-candy-Jan20.txt");
		 InputStream in = url.openStream();
		 Scanner scan = new Scanner(in);
		 
		 
		 Vector<String> FirstVector = new Vector<String>(10,2);//declare a vector to store all the tweets
		 while (scan.hasNext())
		 {
			 String str = scan.next();
			 FirstVector.add(str);
			 
		 }
		 scan.close();
		 System.out.println(FirstVector);
		 int size = FirstVector.size();

	
		 int[] count = new int[size];// declare a array to record the times of repeats of each words
		 int temp;
		 
		 for (int i = 0 ; i < size; i++)//use a for loop to count how many times each word repeats
		 {
			 int s=1;
			 for(int j = 0; j < size; j++)
			 {
				 
				 if ((FirstVector.get(i)).equals(FirstVector.get(j)) )
				 {
					s++;
				    
				 }
				 temp =s;
				count[i]=temp;
			 }
			 
			 
		 }
		 for (int a =0; a<size; a++)
		 {
			 System.out.println("The word "+FirstVector.get(a)+ " repeats " + count[a] +" times.");
			 
		 }// to examine if the loop works and it works well
		 
		 try {
		      PrintStream out = new PrintStream(new FileOutputStream(
		          "OutFile.txt"));
		      for (int a =0; a<size; a++)
		    out.println("The word "+FirstVector.get(a)+ " repeats " + count[a] +" times.");

		      out.close();

		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    }//output as a text file
	
		 
	}

}
