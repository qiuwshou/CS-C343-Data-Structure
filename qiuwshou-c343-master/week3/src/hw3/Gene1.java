package hw3;


import java.io.*;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Scanner;

	public  class Gene1 {
		
		public static void main(String[] args) throws IOException {
	  
			URL url = new URL("http://mendel.informatics.indiana.edu/~yye/lab/teaching/spring2014-C343/codon.txt");
			InputStream in = url.openStream();
			Scanner scan = new Scanner(in);
			String SLC ="";
			String DNA ="";
			Vector<String> codon = new Vector<String>(10,10);
			Hashtable<String, String> ToCodon= new Hashtable<String, String>();
			
			while (scan.hasNext())//Import the context of the text file into a vector line by line
			 {
			     String str = scan.nextLine();
			     codon.add(str);
			 }
			scan.close();

			for(int j = 0 ; j<codon.size() ; j++)
			{
				Scanner scan2= new Scanner(codon.get(j)); //Read each line in the vector
				while(scan2.hasNext())// Then use a next loop to distinguish nucleotide triplet and amino-acid
				{
					String str2 = scan2.next();
					if(str2.length()==1)
					{
						SLC = str2;
					}
					else{
					if(str2.length()==3)
					{
						DNA = str2;
					}
					}
					ToCodon.put(DNA,SLC);//Use hashtabe to store the all nucleotide triplet for each amino-acid
					
						
				}scan2.close();
			}
			String s= "ATTCTTGTT";//Test the code 
			for (int i = 0 ; i < s.length() ; i=i+3)
			{
				System.out.print(ToCodon.get(s.substring(i,i+3)));
				
			}
			

		}

		
	}