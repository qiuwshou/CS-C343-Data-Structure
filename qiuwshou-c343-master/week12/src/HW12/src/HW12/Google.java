package HW12;


import java.util.Vector;
import java.util.Scanner;
import java.io.*;
import java.net.URL;


public class Google {
	
	private Vector<String> words;
	private int[][]Degree;
	private Vector<String> list; //a vector contains all the words that need to be excluded
	private Vector<String> lines;//import the tweets by lines
    private Vector<String> keys;//contains all the keywords after extraction 
    private Vector<Integer> vertices;// vertices indicate the index of each corresponding keyword
    private int k,n,col,row;
	

	
	Google() throws IOException {
		k = 0;
		n = 0;
		Degree= new int[0][0];
		vertices = new Vector<Integer>(100, 1);
		keys = new Vector<String>(100, 1);
		list = new Vector<String>(100, 1);
		words = new Vector<String>(100, 1);
		lines = new Vector<String>(100, 1);
		
		
	}
	
	public void readFile() throws IOException {
	    URL url = new URL("http://mendel.informatics.indiana.edu/~yye/lab/teaching/spring2014-C343/common100.txt"); // the file that contains all the words that need to be extracted
	    URL testurl = new URL("http://mendel.informatics.indiana.edu/~yye/lab/teaching/spring2014-C343/google.txt");
		InputStream in = url.openStream();
		InputStream in2 = testurl.openStream();
		Scanner scan = new Scanner(in);
		Scanner scan2 = new Scanner(in2);
		
		while(scan.hasNext()) {
			words.add(scan.next());
			}
			scan.close();
		
		 for(int i = 0; i < words.size(); i++ ){
			 if(i%2 == 1 ){
				 list.add(words.get(i));
			 }
		 }
		 
		 while(scan2.hasNextLine()){
			 String temp = scan2.nextLine();
			 Scanner scan3 = new Scanner(temp);
			 lines.add(temp);
			 while(scan3.hasNext()){
				 String key = scan3.next();
				 if(list.contains(key) == true || key==null){}
				 else{
					 if(keys.contains(key) == true){ //make sure the same keyword will not be restored multiple times
					 }
					 else{
						 keys.add(key);
					     vertices.add(k);
					     k++;
					     } 
			         }
			
			 }
		   }
		 n = vertices.size();
	}

     
    public void degree(){  // to get the degree of the vertices and present in a 2-D array
    	Degree = new int[n][n];//initialize the 2-d array
    	for (int q=0; q< n;q++)
    		for (int w=0; w<n ; w++){
    			Degree[q][w]=0;
    		}
    	 for(int i = 0; i < lines.size(); i++){
    	Scanner scan = new Scanner(lines.get(i));	 
    	Vector<String> temp = new Vector<String>(10,1);
    	while(scan.hasNext()){
    		String t = scan.next();
    		temp.add(t);
    	}
    	int size = temp.size();
    	for(int j = 0; j < size; j++) //to build the edges and weight
    		for (int l = j+1; l < size;  l++){
    			 col = keys.indexOf(temp.get(l));
    			 row = keys.indexOf(temp.get(j));
    		if( col != -1 && row != -1){
    			Degree[col][row]++;
    			
    		}
    	}
    	scan.close();
    	 }
    	 
    }
    
    public void getkeys(){ //to show the result
   	     //System.out.println(vertices);
		 System.out.println("The keywords of tweets are "+ keys);
		 System.out.println("Show the file in lines "+ lines);
	     System.out.println("there are "+ keys.size()+" keywords");
	     System.out.println("The weight between "+keys.get(0)+" and "+keys.get(1)+" is "+ Degree[1][0]);
	     
	 }
	

}
