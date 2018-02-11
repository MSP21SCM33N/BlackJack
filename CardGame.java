package library.client.classes;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import carddeck.service.classes.CardValue;
import carddeck.service.classes.CardSign;
import carddeck.service.classes.Card;


/*Task 2: Import necessary user defined classes */


class CardGame{
	Card [] userHand;
	Card [] dealerHand;
	
	

    private void generateDealerHand(){
		
		int minSuitCard = 0;
		int maxSuitCard = 3;
		int minFaceCard = 0;
		int maxFaceCard = 12;
		int randGenNum;
		int randGenNum2;
		int range1;
		int range2;
		int randVal;
		int randVal2;
		
		
		range1 = maxSuitCard - minSuitCard;
		range2 = maxFaceCard - minFaceCard;
		dealerHand = new Card[userHand.length];
		
		for(int i = 0; i < userHand.length; i++){	
			randVal = (int)(Math.random()*(range1)+minSuitCard);
			randVal2 = (int)(Math.random() * (range2)+ minFaceCard);
			dealerHand[i] = new Card(CardSign.values()[randVal], CardValue.values()[randVal2]);
		}
		
		/*Task 5: Implement the class method generateDealerHand() in CardGame class. This method will use a random generator to generate signs, and values of the cards for the dealer.*/
	}
	
	
	
	
	private int getScore(){
		// int [] array3 = new int[5];
		// int [] array4 = new int[5];
		int userScore = 0;
		int dealerScore = 0;
		int score = 0;
		int total = 0;
		// int total = 0;
		// int total2 = 0;
		// int score = 0;
		/*
		for(int i = 0; i < userHand.length; i++){
			total += userHand[i];
			if (userHand[i].ordinal() >9){
				userScore = 2;
			}
			else if ()
			if(total >= 42){
				userScore = 2;
			}
			else if(total <= 42 && total >= 5){
				userScore = 1;
			}else{
				userScore = 0;
			}
		for(int j = 0; j < dealerHand.length; i++)
			
		}
		*/
		
		if(royalFlush(userHand)){
			userScore = 2;
		}else if (flush(userHand)){
			userScore = 1;
		}else{
			userScore = 0;
		}
		
		if(royalFlush(dealerHand)){
			dealerScore = 2;
		}else if (flush(dealerHand)){
			dealerScore = 1;
		}else{
			dealerScore = 0;
		}
		
		if(userScore > dealerScore){
			score = 1;
		}
		else if (userScore == dealerScore){
			score = 0;
		}else{
			score = -1;
		}
		
		return score;
	}
	
	private boolean royalFlush(Card [] hand){
		if (!flush(hand)){
			return false;
		}
		boolean[] switches = {false,false,false,false,false};
		for(Card card : hand){
			switch (card.getValue()){
				case ACE:
				switches[0] = true;
				break;
				
				case KING:
				switches[1]= true;
				break;
				
				case QUEEN:
				switches[2] = true;
				break;
				
				case JACK:
				switches[3] = true;
				break;
				
				case TEN:
				switches[4] = true;
				break;	
			}
		}
		for(boolean sw: switches){
			if (!sw){
				return false;
			}
		}
		return true;
	}
	private boolean flush(Card [] hand){
		CardSign signCard = hand[0].getSign();
		for(Card card: hand){
			if (signCard != card.getSign()){
				return false;
			}
		}
		return true;
	}
	
	/*Task 3: Implement the class method getScore() in CardGame class. This method will compare the cards of the dealer and player and provide a score. If the score is zero, it means the game is a tie. If the score is positive, then the player has won the game. You should implement other methods as needed.*/
	private int printHand(){
		System.out.println("User's Hand");
		for(int i = 0; i < userHand.length; i++){
			System.out.println(userHand[i]); 
		}
		System.out.println("Dealer's Hand");
		for(int j = 0; j < dealerHand.length; j++){
			System.out.println(dealerHand[j]);
		}
		return 0;
	} 
	public static void main(String []args){
	//arg[0]: file containing user hand
		int flag = 0;
		CardGame game=new CardGame();
		// read the the files from text files
		int counter=0;
		Scanner scan = new Scanner(System.in);
		
		//game.getScore();
		 try {
			 Card aCard;
			Scanner foo = new Scanner(new File(args[0]));
			System.out.println("foo");
			game.userHand = new Card[5];
			int z = 0;
			while(foo.hasNextLine()){
				String str = foo.nextLine();
				String [] tok=str.split(":");
				aCard = new Card(CardSign.valueOf(tok[0]), CardValue.valueOf(tok[1]));
				game.userHand[z] = aCard;
				
				z++;
			}
				
		// 4: Implement the code in the main method of the CardGame class, that will take the values read from the text file, create Card objects and populate the array Hand. userHand is an attribute of the CardGame class.*/
				
		
		//lets play iPoker!!
		//User interactive part
		String option1;
		int score;
		while(true){
			game.generateDealerHand(); 
			System.out.println("Select an option:");
			System.out.println("Type \"P\" to play a round of iPoker");			
			System.out.println("Type \"Q\" to Quit");
			option1=scan.nextLine();
			
			switch (option1) {								 
				case "P":   game.generateDealerHand();
				            score=game.getScore();
							game.printHand();///First print out the hands
							System.out.println("\n\nCompare the two hands:");
							if(score < 0)
								System.out.println("Dealer Wins :-(");
							else if (score == 0)
								System.out.println("Its a draw");
							else if (score > 0)
								System.out.println("Congrats You win !!");
							else
								System.out.println("Somethings wrong!");
							break;
				
				case "Q":   System.out.println("Quitting program");
							System.exit(0);
							
				default:	System.out.println("Wrong option! Try again");
							break;
			
			}			
		}
		}catch(IOException ioe){ 
			System.out.println("The file can not be read");
		}catch(IllegalArgumentException ia){ 
            System.out.println(ia.getMessage());
		} catch(NullPointerException np){
			System.out.println(np.getMessage());
		}
	}	

}