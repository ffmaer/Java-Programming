import javax.swing.JOptionPane;

/* The following codes are used to create the Dice class, which will be used to 
 * present players.*/

/******************************************************************
 *                                                                *
 *                                                                *
 *                         The Dice Class                         *
 *                                                                *
 *                                                                *
 ******************************************************************/




public class Dice{
	
	//set up all the fields for the Dice
	
	private String playerName;
	private int playerBalance;
	
	/* In order to make the game fair, the player goal should be the same.
	 * Therefore I use static variable here.*/
	 
	private static int playerGoal; 
	
	//other variables used to store processing values
	
	private static String playerBetString;
	private static int playerBetInteger;
	private static int playerDiceNumber;
	
	private static int choice;
	
	//We will access these private fields outside the class, so I will set up the "setField" and the "getField".
	
	//the get part
	
	String getPlayerName(){
		return playerName;
	}
	int getPlayerBalance(){
		return playerBalance;
	}
	static int getPlayerGoal(){
		return playerGoal;
	}
	
	//the set part
	
	void setPlayerBalance(int balance){
		playerBalance = balance;
	}	

	//set up the constructor for the Dice
	
	//just in case no initialization for the dice, thought it won't happen in this program
	
	Dice(){
		this("newplayer", 100, 500);
	}
	
	//give value for the fields of Dice
	
	Dice(String name, int balance, int goal){
		playerName = name;
		playerBalance = balance;
		playerGoal = goal;
	}
	
	//set up other methods for the Dice
	
	/* the steps are as follows:
	 * first: bet
	 * second: check the bet
	 * third: create the random number from 1 to 6
	 * forth: return the bet and the number */
	 
	//the random number generated in roll() is essential to the game Craps
	static int roll(){

		 playerDiceNumber = (int) (Math.random() * 6 + 1);
		 return playerDiceNumber;
		 
	}

	int bet(){
		//in case the player's input is not an integer
		try{
			playerBetString = JOptionPane.showInputDialog(null,
			"How much do you want to bet, " + playerName + " ?",
			"Wow!", JOptionPane.QUESTION_MESSAGE);
			if(playerBetString == null) {
				System.exit(0); // quit the game
			}
			System.out.println(playerBetString);
			//convert the numeric string to integer
			playerBetInteger = Integer.parseInt(playerBetString);
		}catch(Exception e){
			System.out.println(e);
			choice = JOptionPane.showConfirmDialog(null,
			"Dear " + playerName + "! Your bet should be a number. Can you put in your bet again?"
			, "Oooops", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			if(choice == -1) System.exit(0);
			
			bet();
		}
		
		if(isValidBet(playerBetInteger)){
			return playerBetInteger;
		}else{
			choice = JOptionPane.showConfirmDialog(null,
			"Hey " + playerName + "! Your bet should be more than 0 dollar and less than " + (playerBalance + 1)  +" dollars."
			,"Ooooooops...", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			if(choice == -1) System.exit(0);
			
			return bet();
		}
		
	}
	
	//The following method will be used to check whether the bet is valid or not.
	
	private boolean isValidBet(int bet){
		if(bet > 0 & bet <= playerBalance){
			return true;
		}else{
			return false;
		}
		
	}
}

//This is the end of the code.