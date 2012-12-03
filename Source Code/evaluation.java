
public class evaluation {
	private String[] Good = new String[24];
	private String[] Bad = new String[25];
	private String[] Vulgar = new String[31];
	private String[] ReallyGood = new String[11];
	private String[] ReallyBad = new String[10];

	public String[] EGood = new String[50];
	public String[] EBad = new String[50];
	public String[] EVulgar = new String[50];

	private int countg = 0, countb = 0, countv = 0;

	
	
	//Constructor that creates the arrays of Vulgar, Good, Bad, Really Good and Really Bad words
	// Note: had to continuously update these in the realization that my friends were able to find a ton of vulgar words that I did not take into consideration
	public evaluation() {

		Vulgar[0] = "fuck";
		Vulgar[1] = "fucking";
		Vulgar[2] = "fucked";
		Vulgar[3] = "shit";
		Vulgar[4] = "bitch";
		Vulgar[5] = "nigger";
		Vulgar[6] = "retarded";
		Vulgar[7] = "cunt";
		Vulgar[8] = "ass";
		Vulgar[9] = "asshole";
		Vulgar[10] = "jew";
		Vulgar[11] = "alcohol";
		Vulgar[12] = "drug";
		Vulgar[13] = "drugs";
		Vulgar[14] = "sex";
		Vulgar[15] = "fucker";
		Vulgar[16] = "mothafucka";
		Vulgar[17] = "pussy";
		Vulgar[18] = "panties";
		Vulgar[19] = "panty";
		Vulgar[20] = "bra";
		Vulgar[21] = "underwear";
		Vulgar[22] = "penis";
		Vulgar[23] = "boobs";
		Vulgar[24] = "boobies";
		Vulgar[25] = "boob";
		Vulgar[26] = "vagina";
		Vulgar[25] = "dick";
		Vulgar[26] = "dicks";
		Vulgar[27] = "whore";
		Vulgar[28] = "slut";
		Vulgar[29] = "skeet";
		Vulgar[30] = "sexy";
		

		Bad[0] = "stupid";
		Bad[1] = "jesus";
		Bad[2] = "dumb";
		Bad[3] = "idiot";
		Bad[4] = "rude";
		Bad[5] = "mean";
		Bad[6] = "hated";
		Bad[7] = "church";
		Bad[8] = "inability";
		Bad[9] = "jerk";
		Bad[10] = "late";
		Bad[11] = "promoted";
		Bad[12] = "lol";
		Bad[13] = "haha";
		Bad[14] = "crime";
		Bad[15] = "jail";
		Bad[16] = "disorganized";
		Bad[17] = "incompetent";
		Bad[18] = "canned";
		Bad[19] = "fired";
		Bad[20] = "laid";
		Bad[21] = "religion";
		Bad[22] = "mad";
		Bad[23] = "meh";
		Bad[24] = "blah";

		Good[0] = "organized";
		Good[1] = "polite";
		Good[2] = "pro-active";
		Good[3] = "innovative";
		Good[4] = "drive";
		Good[5] = "communication";
		Good[6] = "communicating";
		Good[7] = "proficient";
		Good[8] = "efficient";
		Good[9] = "cooperate";
		Good[10] = "cooperation";
		Good[11] = "analysing";
		Good[12] = "analytical";
		Good[13] = "solutions";
		Good[14] = "achieve";
		Good[15] = "goals";
		Good[16] = "goal";
		Good[17] = "strategic";
		Good[18] = "skill";
		Good[19] = "team";
		Good[20] = "initiative";
		Good[21] = "effective";
		Good[22] = "hard worker";
		Good[23] = "collaborate";

		ReallyBad[0] = "suck";
		ReallyBad[1] = "sucked";
		ReallyBad[2] = "promotions";
		ReallyBad[3] = "hell";
		ReallyBad[4] = "bonuses";
		ReallyBad[5] = "anger";
		ReallyBad[6] = "hate";
		ReallyBad[7] = "vacation";
		ReallyBad[8] = "promotion";
		ReallyBad[9] = "vacation";

		ReallyGood[0] = "flexible";
		ReallyGood[1] = "energetic";
		ReallyGood[2] = "positive";
		ReallyGood[3] = "experience";
		ReallyGood[4] = "team";
		ReallyGood[5] = "motivated";
		ReallyGood[6] = "learning";
		ReallyGood[7] = "collaborative";
		ReallyGood[8] = "ethic";
		ReallyGood[9] = "contribute";
		ReallyGood[10] = "communicate";

	}

	/* for the case where we want to evaluate a sentence that is an answer
	 * we first make everything into lower case and then use split and split the reply into an array of words broken apart by 
	 * punctuation, spaces and such
	 * Then we scroll thorugh each word and compare it to every word in the above arrays..
	 * if it is in that list we update the count of the current score appropriately and move on! 
	 * this took a while to do!!:)
	 */
	
	public int Evaluate(String answer) {
		String use = answer.toLowerCase();
		int count = 0;
		String[] result = use.split("[\\s \\p{P} \\t \\n \\r]");

		//for every word in the reply
		for(int i = 0; i < result.length; i++){
			
			//check for vulgar words
			for(int j = 0; j < Vulgar.length; j++){
				//if there is a vulgar word match
				if(result[i].equalsIgnoreCase(Vulgar[j])){
					//decrement score by 500 so that they can never hit the positive numbers again
					count = count - 500;
					//put example away for later
					EVulgar[countv] = use;
					//increment count for later use
					countv = countv + 1;
				}
			}//end vulgar
			
			//check for bad words
			for(int j = 0; j < Bad.length; j++){
				if(result[i].equalsIgnoreCase(Bad[j])){
					count = count - 1;
					EBad[countb] = use;
					countb = countb + 1;
				}
			}//end bad
			
			//check for good words
			for(int j = 0; j < Good.length; j++){
				if(result[i].equalsIgnoreCase(Good[j])){
					count = count + 1;
					EGood[countg] = use;
					countg = countg + 1;
				}
			}//end good
			
			//check for really bad words (weighted more)
			for(int j = 0; j < ReallyBad.length; j++){
				if(result[i].equalsIgnoreCase(ReallyBad[j])){
					count = count - 3;
					EBad[countb] = use;
					countb = countb + 1;
				}
			}//end really bad words
			
			//check for really good words
			for(int j = 0; j < ReallyGood.length; j++){
				if(result[i].equalsIgnoreCase(ReallyGood[j])){
					count = count + 3;
					EGood[countg] = use;
					countg = countg + 1;
				}
			}//end really good

		}//end result for loop
		
		//return the score for this reply
		return count;

	}//end Evaluate
	
	
	//for the case where we have single listed inputs
	//only difference from above is that we do not split the inputs
	//see above for details on each line
	public int EvalWord(String answer){
		answer = answer.toLowerCase();
		int count = 0;

		for(int j = 0; j < Vulgar.length; j++){
			if(answer.equalsIgnoreCase(Vulgar[j])){
				count = count-500;	
				EVulgar[countv] = answer;
				countv = countv + 1;
			}
		}
		for(int j = 0; j < Good.length; j++){
			if(answer.equalsIgnoreCase(Good[j])){
				count = count + 1;
				EGood[countg] = answer;
				countg = countg + 1;
			}
		}
		for(int j = 0; j < Bad.length; j++){
			if(answer.equalsIgnoreCase(Bad[j])){
				count = count -1;
				EBad[countb] = answer;
				countb = countb + 1;
			}
		}
		for(int j = 0; j < ReallyGood.length; j++){
			if(answer.equalsIgnoreCase(ReallyGood[j])){
				count = count + 3;
				EGood[countg] = answer;
				countg = countg + 1;
			}
		}
		for(int j = 0; j < ReallyBad.length; j++){
			if(answer.equalsIgnoreCase(ReallyBad[j])){
				count = count -3;
				EBad[countb] = answer;
				countb = countb + 1;
			}
		}
		return count;
	}//end evalword

}
