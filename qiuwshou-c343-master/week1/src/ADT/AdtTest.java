package ADT;

import java.util.ArrayList;

public class AdtTest{
	
	public static void main(String[] args) {
	
	Measure Arraymaker = new Measure();
    Arraymaker.reinitialize(4,4);
    Arraymaker.insert(1, 1, 1);
    Arraymaker.refer(2,1);
    
    System.out.println(Arraymaker.getarray());
    
   
	


	

	}

}
