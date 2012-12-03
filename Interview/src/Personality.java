
public class Personality {
	String[] type = new String[4];
	String aa = "orange";
	String bb = "green";
	String cc = "blue";
	String dd = "gold";
	int[] temp = new int[4];
	
	int tempy;
	String temppy;
	

	
	public Personality(){
		
	}
	
	/* A method that will take the four inputted integer values from the personality questions asked
	 * and sort them into an array while mimicking the changes in order on an array
	 * that contains the string personality type values in order to create the personality spectrum
	 */
	public String[] getType(int a, int b, int c, int d){
		//set initial values
		temp[0] = a;
		type[0] = aa;
		//if the second value is greater than the first then switch them
		if(b > a){
			temp[0] = b;
			type[0] = bb;
			temp[1] = a;
			type[1] = aa;
		} else{ //else just add it in after
			temp[1] = b;
			type[1] = bb;
		}
		//if the third value is greater than the current largest
		//put it in the first and then shift eerything down
		if (c > temp[0]){
			temp[2] = temp[1];
			type[2] = type[1];
			
			temp[1] = temp[0];
			type[1] = type[0];
			
			temp[0] = c;
			type[0] = cc;

		}else { //else check if it is larger than the second value in the set,
			//if so add it in there and switch the others down
			if(c > temp[1]){
				temp[2] = temp[1];
				type[2] = type[1];
				
				temp[1] = c;
				type[1] = cc;
			} else{//else just add it in at the end
				temp[2] = c;
				type[2] = cc;
			}
		}
		//if the fourth is largest
		//add it in and shift everything else down
		if(d > temp[0]){
			temp[3] = temp[2];
			type[3] = type[2];
			
			temp[2] = temp[1];
			type[2] = type[1];
			
			temp[1] = temp[0];
			type[1] = type[0];
			
			temp[0] = d;
			type[0] = dd;
		} else {//else check if it is bigger than the second value
			//and add it in then shift
			if(d > temp[1]){
				temp[3] = temp[2];
				type[3] = type[2];
				
				temp[2] = temp[1];
				type[2] = type[1];
				
				temp[1] = d;
				type[1] = dd;
			} else {// check if it is larger tan the third value
				//shift down and add
				if(d > temp[2]){
					temp[3] = temp[2];
					type[3] = type[2];
					
					temp[2] = d;
					type[2] = dd;
				} else{//else just add it in at the end
					temp[3] = d;
					type[3] = dd;
				}
			}			
		}
		return type;
	}
}
