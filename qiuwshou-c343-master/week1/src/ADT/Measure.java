package ADT;



public class Measure extends AdtTest {
	
	private int x,y,temp;
	int[][] aa = new int[x][y];
	
	
	public Measure(){
		x = 1;
		y = 1;
		
	};
	
    public void insert(int a, int b,int c){
       aa[a][b] = c;
       
    };
	
	public void delete(int a, int b){
		aa[a][b] = 0;
		
	};
	
	public void refer(int a, int b){
		temp = aa[a][b];
		
	};
	
	
	
	public void reinitialize(int x , int y){
		
		aa = new int[x][y];
		for(int i = 0; i < x; i++)
			for(int j = 0; j < y ; j++)
			{aa[i][j]=0;}
		
	};
	
	public int[][] getarray(){
	 
	    return  aa;
	};
	

}
