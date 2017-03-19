package HW9;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.*;
import java.net.URL;
import java.util.Vector;
import java.util.Scanner; 
import java.util.Hashtable;

public class Hash {

	public static void main(String[] args) throws IOException {

		 URL url = new URL("http://mendel.informatics.indiana.edu/~yye/lab/teaching/spring2014-C343/docu.txt");
		 InputStream in = url.openStream();
		 Scanner scan = new Scanner(in);
		 
		 Vector<String> Vec = new Vector<String>(10,2);
		 Vector<String> Vec2 = new Vector<String>(10,2);//restore all the words in a vector
		 Hashtable<String, String> words= new Hashtable<String, String>();
		 
    
		 while (scan.hasNext())//Import the context of the text file into a vector line by line
		 {
		     String str = scan.nextLine();
		     Vec.add(str);
		 }
		scan.close();
		
		
		
		for (int i= 0; i<Vec.size();i++ ){ //for each word create the value and corresponding key in the hash table
			Scanner scan2= new Scanner(Vec.get(i));
			while(scan2.hasNext()){
				String str = scan2.next();
				Vec2.add(str);
				int temp = i+1;  
				String str2 = Integer.toString(temp);//restore the line as a string instead of a integer
				if(words.get(str) == null)  
				{
				words.put(str,str2);}
				else{
				int n = words.get(str).length();
				String temp2 = words.get(str);
				    if(temp2.substring(n-1,n).equals(str2)) // to chech if the word appears multiple times in the same line
				     {
				    String temp3=words.get(str);
				    words.put(str, temp3);
				     }
				     else
				     {
				     String temp4=words.get(str)+","+str2;
				     words.put(str, temp4);
				}
			}
		}
		}
		
		
		try {
		      PrintStream out = new PrintStream(new FileOutputStream(
		          "OutFile.txt"));
		      for(int a = 0; a<Vec2.size(); a++){
		    	  if(words.get(Vec2.get(a)) ==null)
		    	  {out.println("the word "+ Vec2.get(a)+" is not in the document.");}
		    	  else
		    	  {out.println("["+words.get(Vec2.get(a))+"]");}  
		      }
		      out.close();

	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
		
}

      
}
