import java.util.ArrayList;

public class Polynomial {
	double[] array;
	
	public Polynomial(){
		this.array = new double[]{0}; //this is used to refer local variable
	}
	
	public Polynomial(double[] array) {
		this.array = new double[array.length];
		for(int i = 0; i < array.length; i++) {
			this.array[i] = array[i];
		}
	}
	
	public Polynomial add(Polynomial polynomial2) { 
		int maxLength = Math.max(this.array.length, polynomial2.array.length); 
    	double[] combinedArray = new double[maxLength]; 
    
    	for (int i = 0; i < this.array.length; i++) {
        	combinedArray[i] = this.array[i]; 
    	}
    
    	for (int i = 0; i < polynomial2.array.length; i++) {
        	combinedArray[i] = combinedArray[i] + polynomial2.array[i]; 
    	}
    
    	return new Polynomial(combinedArray); 
		
	}
	
	public double evaluate(double x) {
		double sum = 0;
		for(int i = 0; i < array.length; i++) {
			sum = sum + array[i] * Math.pow(x,i);
		}
		return sum;
	}
	
	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}
} 