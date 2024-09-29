import java.util.ArrayList;
import java.util.HashMap;

public class Polynomial {
	double[] array;
	int[] expoarray; //exponent arrays
	
	public Polynomial(){
		this.array = new double[]{0}; //this is used to refer local variable
		this.expoarray = new int[]{0};
	}
	
	public Polynomial(double[] array, int[] exporarray) { //needs to be modified so that it ignores 0 values
		
		//create new array storage based on length of coefficient array without 0s

		//create a small loop to do so
		this.array = new double[array.length];
		this.expoarray = new int[exporarray.length];

		for(int i = 0; i < array.length; i++){
			this.array[i] = array[i];
			this.expoarray[i] = expoarray[i];
		}
	}

	public Polynomial add(Polynomial polynomial2) { 
		int indicator = 0;
		int minLength = Math.min(this.array.length, polynomial2.array.length); 
		if (minLength == array.length){
			indicator = 1;
		}
		int combinedLength = array.length + polynomial2.length;

		for(int i = 0; i < minLength; i++){
			if(indicator == 1){
				if(Arrays.asList(polynomial2.expoarray).contains(expoarray[i])){
					combinedLength--;
				}
			}
			else{
				if(Arrays.asList(expoarray).contains(polynomial2.expoarray[i])){
					combinedLength--;
				}
			}
		}
		
    	double[] combinedArray = new double[combinedLength];
		int[] combinedExpoarray = new int[combinedLength];
		HashMap<Integer, Double> map = new HashMap<>();
		
		for(int i = 0; i < this.array.length; i++){
			map.put(this.expoarray[i], this.array[i]);
		}

		for(int i = 0; i < polynomial2.array.length; i++){
			if (map.containsKey(polynomial2.expoarray[i])){
				combinedArray[i] = polynomial2.array[i] + maps.get(polynomial2.expoarray[i]);
				combinedExpoarray[i] = polynomial2.expoarray[i];
				map.remove(polynomial2.expoarray[i]);
			}
			else{
				combinedArray[i] = polynomial2.array[i];
				combinedExpoarray[i] = polynomial2.expoarray[i];
			}
		}

		int idx_to_start = combinedLength - map.size();
		for(Map.Entry<Integer, Double> entry : map.entrySet()){
			combinedArray[idx_to_start] = entry.getValue();
			combinedExpoarray[idx_to_start] = entry.getKey();
			idx_to_start++;
		}

		return new Polynomial(combinedArray, combinedExpoarray); 
		
	}

	public Polynomial multiply(Polynomial polynomial2){

		HashMap<Integer, Double> map = new HashMap<>();
		
		for(int i = 0; i < this.array.length; i++){
			for(int j = 0; j < polynomial2.length; j++){
				
				int expoProduct = this.expoarray[i] + polynomial2.expoarray[j];
				double coefficientProduct = this.array[i] * polynomial2.array[j];
				int coefficientSum = this.array[i] + polynomial2.array[j];

				boolean hasValue = map.containsKey(expoProduct);
				
				if(hasValue == false){
					map.put(expoProduct, coefficientProduct);
				}
				else{
					map.put(expoProduct, coefficientSum);
				}
				
			}
		}

		//getting rid of 0 coefficients
		map.entrySet().removeIf(entry -> entry.getValue() == 0.0);

		int productLength = map.size();

		set<Integer> keySet =  map.keySet();
		int[] combinedExpoarray = new int[productLength];
		double[] combinedArray = new double[productLength];

		int i = 0;
		for(Map.Entry<Integer, Double>  enntry : map.entrySet()){
			combinedExpoarray[i] =  entry.getKey();
			combinedArray[i] = entry.getValue();
			i++;
		}

		// for (int i = 0; i < productLength; i++){
		// 	int key = keySet.get(i);
		// 	combinedExpoarray[i] = key;
		// }

		// //below is converting values from dictionary into array

		// Collection<Double> values = map.values();
		

		// for (int i = 0; i < productLength; i++){
		// 	int value = values.get(i);
		// 	combinedArray[i] = value;
		// }

		return new Polynomial(combinedArray, combinedExpoarray);
	}
	
	public double evaluate(double x) { 
		//is there any need to modify evaluate? 
		//Only takes in a double and not int
		double sum = 0;
		for(int i = 0; i < array.length; i++) {
			sum = sum + array[i] * Math.pow(x, expoarray[i]); //this expoarray works?
		}
		return sum;
	}

	// public Polynomial(File file){
	// 	coefficients = new ArrayList<>();
	// 	exponents = new ArrayList<>();

	// 	Scanner scanner = new Scanner(inputFile);
    //     String polynomialString = scanner.nextLine().trim();
    //     scanner.close();

	// 	String currentTerm = "";
	// 	int sign =  1;
	// }

	
	
	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}
} 
