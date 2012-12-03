import java.util.Random;
import java.util.Scanner;


public class main {

	public static void main(String[] args){

		//set up every outside class that we need
		Scanner scan = new Scanner(System.in);
		Questions quest = new Questions();
		Personality personality = new Personality();
		evaluation eval = new evaluation();
		Random random = new Random();

		//user's info
		String namef;
		String namel;
		String email;
		String phonenumber;

		//answer stuff
		String answer;
		String temp = "";
		int points = 0, count = 0, num;

		//personality stuff
		String perstype;
		int pers1 = 0, pers2 = 0, pers3 = 0, pers4 = 0, place = 4;
		String[] pType = new String[4];

		//make question arrays
		String[] gen = new String[20];
		String[] tech = new String[20];
		String[] pers = new String[10];
		gen = quest.getGeneral();
		tech = quest.getTechnical();
		pers = quest.getPersonality();

		//evaluation stuff
		boolean dectype = false, dectype2 = false;
		int decision;



		//ask for general information from 
		System.out.println("Please input the personality type that you are looking for. (blue, gold, green or orange)");
		perstype = scan.nextLine();
		System.out.println("Please input your first name.");
		namef = scan.nextLine();
		System.out.println("Please input your last name.");
		namel = scan.nextLine();
		System.out.println("Please input your email address.");
		email = scan.nextLine();
		System.out.println("Please input your phone number.");
		phonenumber = scan.nextLine();

		//start interview
		System.out.println("***************************************************************************************");
		System.out.println("***************************************************************************************");
		System.out.println("THE INTERVIEW WILL BEGIN NOW");
		System.out.println("***************************************************************************************");
		System.out.println("***************************************************************************************");



		//always ask these questions
		System.out.println(namef + ", could you please list off some of your best attributes?");
		System.out.println("Please press enter twice when you are done,");
		answer = " ";
		while(!answer.equalsIgnoreCase("")){
			answer = scan.nextLine();	
			points = points + eval.EvalWord(answer);
			count = count+1;
			temp = answer;
		}
		if(count == 0 || count > 4){
			points = points - 1;
		}else{
			points = points + 2;
		}
		System.out.println("Can you please elaborate on " + temp + ".");
		answer = scan.nextLine();
		points = points + eval.Evaluate(answer);

		count = 0;
		System.out.println("What are some of your faults?");
		answer = " ";
		while(!answer.equalsIgnoreCase("")){
			answer = scan.nextLine();
			count = count + 1;
		}
		if(count == 0 || count > 4){
			points = points - 1;
		}else{
			points = points + 2;
		}

		//ask some general questions
		for(int i = 0; i < 5; i++){
			//get a random integer in [0, 19]
			num = random.nextInt(20);
			//if that location in the question array is not empty use it!
			if(gen[num] != null){
				//ask question
				System.out.println(gen[num]);
				//read answer
				answer = scan.nextLine();
				//update the points
				points = points + eval.Evaluate(answer);
				//set that question location to null now that we have asked it in order to not repeat questions
				gen[num] = null;			
			}
			else{
				//if the question is null, set counter back by one so that we always ask 5 questions here
				i = i-1;			
			}
		}//end general for

		//ask personality questions
		System.out.println("For the following 10 questions, please choose which of the sets of words best apply to yourself. Indicate your answer with the appropriate letter (a/b/c/d).");
		for(int i=0; i<10; i++){
			System.out.println(pers[i]);
			answer = scan.nextLine();
			//increases the count for each of the personality types
			switch(answer){
			case "a": 
				pers1 = pers1 + 1;
				break;
			case "b":
				pers2 = pers2 + 1;
				break;
			case "c": 
				pers3 = pers3 + 1;
				break;
			case "d": 
				pers4 = pers4 + 1;
				break;
			default:
				break;	
			}
		}
		//using the above answers, get the personality type using the method in the Personality class
		pType = personality.getType(pers1, pers2, pers3, pers4);

		// some technical questions
		//same methodology as above in the first set of general questions
		for(int i = 0; i < 8; i++){
			num = random.nextInt(20);
			if(tech[num] != null){
				System.out.println(tech[num]);
				answer = scan.nextLine();
				points = points + eval.Evaluate(answer);
				tech[num] = null;			
			}
			else{
				i = i-1;			
			}
		}

		//ask more general questions
		//again, same method as described above
		for(int i = 0; i < 5; i++){
			num = random.nextInt(20);
			if(gen[num] != null){
				System.out.println(gen[num]);
				answer = scan.nextLine();
				points = points + eval.Evaluate(answer);
				gen[num] = null;			
			}
			else{
				i = i-1;			
			}
		}

		//look at the personality type that is desired
		//figure out where in the personality spectrum 
		for(int i = 0; i < 4; i++){
			if(pType[i].equalsIgnoreCase(perstype)){
				place = i;
			}
		}
		//if it is in the top two great!
		if(place < 2){
			dectype = true;
		}
		//if it's in the third spot... ok
		if(place == 2){
			dectype2 = true;
		}
		//both are initialized to false so if it is in the last spot then we leave both as false


		//evaluate!!

		//if the personality is in the top two spots 
		if(dectype = true){
			//if we have less than 0 points (only gone down from initial) we set decision to -1
			if(points < 0){
				decision = -1;
			}
			//if it has greater than 10 points then we are happy and accept it because it's personality is quite desirable 
			else if(points > 10){
				decision = 1;
			}
			//if it's between 0 and 10 then we set for a second interview
			else{
				decision = 0;
			}
		}
		//if it has the desired personality in the third location
		else if(dectype2 = true){
			//if it's less than zero then no hire
			if(points < 0){
				decision = -1;
			}
			//if it's above 15 here... we need it to be higher because of the less desirable personality type
			else if(points > 15){
				decision = 1;
			}
			//else return for a second interview
			else{
				decision = 0;
			}
		}
		//if the personality is NOT what we are looking for then do not hire them
		else{
			decision = -1;
		}



		//output to user
		//if we want to hire them then great!
		if(decision == 1){
			System.out.println("Congratulations " + namef + "! We are very happy to offer you the position!");
			System.out.println("When are you available to start?");
			System.out.println("We will send you all of the starting information to " + email + ".");
		}
		//if we want them to return for a second interview
		if(decision == 0){
			System.out.println("Thank you " + namef + " for coming in today. We still have some other candidates to consider. Would you be interested in coming back for a second interview?");
			System.out.println("We will contact you at "+ email + "or at" + phonenumber + " when we have more information for you.");
		}
		// if we never want to see them again
		if(decision == -1){
			System.out.println("I'm sorry " + namef + " " + namel + ", but I do not think that this is going to work out for us. Have a nice day");
		}
		
		//feedback output
		System.out.println("***************************************************************************************");
		System.out.println("***************************************************************************************");
		System.out.println("FEEDBACK");
		System.out.println("***************************************************************************************");
		System.out.println("***************************************************************************************");
		System.out.println("");
		System.out.println("Your score was: " + points);
		System.out.println("");

		//first if they were just not the personality type that we were looking for then tell them that! it may not have been their fault
		if(!dectype && !dectype2){
			System.out.println("I'm sorry "+ namel + " , but we are looking for a differnt personality type for our position.");
		}
		
		// output some good stuff that they said
		if(eval.EGood[0] != null){
			System.out.println("Here are some of the places where we really liked what you said!");
			for(int i = 0; i < eval.EGood.length; i++){
				System.out.println(eval.EGood[i]);
				if(eval.EGood[i+1] == null){
					break;
				}
			}
		}
		
		//output some places that they might need to work on
		System.out.println("");
		if(eval.EBad[0] != null){
			System.out.println("Here are some places that you should work on.");
			for(int i = 0; i < eval.EBad.length; i++){
				System.out.println(eval.EBad[i]);
				if(eval.EBad[i+1] == null){
					break;
				}
			}
		}
		//you swore and so you didn't get the job.. this is what you said...
		System.out.println("");
		if(eval.EVulgar[0] != null){
			System.out.println("You did not get the position because you said the following:");
			for(int i = 0; i < eval.EVulgar.length; i++){
				System.out.println(eval.EVulgar[i]);
				if(eval.EVulgar[i+1] == null){
					break;
				}
			}

		}










	}
}





