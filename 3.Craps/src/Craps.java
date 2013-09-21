/*******************************************************************************
Tengchao Zhou
V22.0101-003
Course: Introduction to Computer Science I (JAVA) 
Professor: Sana' Odeh
October 18, 2009

Craps

Brief instructions:

How to play the Craps?

	Two players take turns and one player plays each time.
	Before you roll, you bet on your outcome.
	If you win, your bet will be added to your balance.
	If you lose, your bet will be detracted from your balance.
	In two situations, the game ends.
	If one player reaches the pre-determined goal 300 dollars
	or one player runs out of money, the game will end.
	The one who reaches the goal first will be the champion and 
	one will be the champion too when the other runs out of money
	You roll two dice each time and the sum ranges from 2 to 12.
	In the first roll,
	you win, if you get 7 or 11;
	you lose, if you get 2 or 12.
	If you get the other number, the number will be YOUR NUMBER.
	You need to keep rolling until
	you win, if you get YOUR NUMBER again;
	you lose, if you get 7.
	The game will continue until the champion appears.

***************************************************************************

Some comments regarding extra credits.

1. Use "try & catch" to avoid the player put in some bets which are not
	numeric string.

2. Use a new method called referee() to decide which player to play.

	private static Dice referee(Dice player1, Dice player2){
		if(playerOrder == 1){
			playerOrder = 2;
			return player1;
		}else{
			playerOrder = 1;
			return player2;
		}
	}

3. Use ASCII Art to present the champion, which is more dramatic.

4. Use the principal of recursion to continue the game, which is realize
	by the combination of following methods.

	firstTurn() nextTurn() isWin() isLose()

 *******************************************************************************/


import javax.swing.*;

/******************************************************************
 *                                                                *
 *                                                                *
 *                         The Craps Class                        *
 *                                                                *
 *                                                                *
 ******************************************************************/
 
 
 
 

public class Craps{
	
	//something that is determined should be declared here
	
	private static final int theGoal = 300;
	
	//some varibles will be used
	private static String firstPlayerName = "Player1";
	private static String secondPlayerName = "Player2";
	
	private static int processingRoll1;
	private static int processingRoll2;
	private static int processingSum;
	
	private static int playerOrder = 1;
	
	private static int processingBet;
	
	public static void main(String[] args){
		
		/* we need to create two dice or two players here
		 * the inital value for the players are
		 * name should be asked to put in
		 * balance 100
		 * goal which is pre-determined
		 */

		//As a normal, we need to read instruction before playing the game.
		
		System.out.print(     "\n ***************************************************************"
							+ "\n ***********************INSTRUCTIONS****************************"
							+ "\n ***************************************************************"
							+ "\n ** Two players take turns and one player plays each time."
							+ "\n ** Before you roll, you bet on your outcome."
							+ "\n ** If you win, your bet will be added to your balance."
							+ "\n ** If you lose, your bet will be detracted from your balance."
							+ "\n ** In two situations, the game ends."
							+ "\n ** If one player reaches the pre-determined goal 300 dollars"
							+ "\n ** or one player runs out of money, the game will end."
							+ "\n ** The one who reaches the goal first will be the champion and "
							+ "\n ** one will be the champion too when the other runs out of money"
							+ "\n ** You roll two dice each time and the sum ranges from 2 to 12."
							+ "\n ** In the first roll,"
							+ "\n ** you win, if you get 7 or 11;"
							+ "\n ** you lose, if you get 2 or 12."
							+ "\n ** If you get the other number, the number will be YOUR NUMBER."
							+ "\n ** You need to keep rolling until"
							+ "\n ** you win, if you get YOUR NUMBER again;"
							+ "\n ** you lose, if you get 7."
							+ "\n ** The game will continue until the champion appears."
							+ "\n ***************************************************************"
							+ "\n ***************************************************************\n");
		
		
		JOptionPane.showMessageDialog(null,"Can we start the game now?","Are you ready?",JOptionPane.QUESTION_MESSAGE);

		 //ask the players to enter their names
		firstPlayerName = JOptionPane.showInputDialog(null,
			"You will be the 1st player. What's your name?",
			"Name?",
			JOptionPane.QUESTION_MESSAGE);
		
		secondPlayerName = JOptionPane.showInputDialog(null,
			"You will be the 2nd player. What's your name?",
			"Name?",
			JOptionPane.QUESTION_MESSAGE);
			
		 //now we can use the constructor to create two dice
		Dice dice1 = new Dice(firstPlayerName,100,theGoal);  //construct dice1 instance
		Dice dice2 = new Dice(secondPlayerName,100,theGoal); //construct dice2 instance
		
		//now begin the game
		
		firstTurn(dice1, dice2, referee(dice1, dice2));
		
	}
	
	/* when we have two players ready, we can start the game
	 * We know that players are play in turn. 
	 * If he roll 7 or 11, the player will win in the first turn.
	 * If he roll 2 or 12, the player will lose in the first turn.
	 * The 'win' means that the player can add the number he bet to his balance.
	 * The 'lose' means that the player should detract the number he bet from his balance.
	 * Here we should judge whether the player win finally.
	 * Another method should be applied, which test whether the balance becomes a negetive nubmer or it reaches the pre-set goal.
	 * If his balance exceeds the goal, this player wins.
	 * If his balance becomes deficit, this player loses.
	 * If the above situations do not happen, the game starts again.
	 * The game will go to another condition if the player does not win or lose at the first turn.
	 * We will need another varible to contain the so-called "your point."
	 * The following game is like a loop.
	 * You will need to roll and roll until your number meets "your point" or seven.
	 * The seven suggests that you lose.
	 * The "your number" indicates that you win.
	 * The "lose" or the "win" will have the same effect as the previous ones.
	 */
	
	
	//In order to decide which player to play, I should construct a new method called referee()
	
	private static Dice referee(Dice player1, Dice player2){
		if(playerOrder == 1){
			playerOrder = 2;
			return player1;
		}else{
			playerOrder = 1;
			return player2;
		}
	}
	
	//this method helps to judge whether the FIRST turn is 'win' or 'lose'
	
	private static void firstTurn(Dice player1, Dice player2, Dice player){
		
		System.out.println("This is " + player.getPlayerName() +"'s turn. Your balance is " + player.getPlayerBalance());
		processingBet = player.bet();
			
		JOptionPane.showMessageDialog(null,"Let's see the number...","Good luck!",JOptionPane.WARNING_MESSAGE);
		
		processingRoll1 = Dice.roll();
		processingRoll2 = Dice.roll();
		processingSum = processingRoll1 + processingRoll2;
		
		System.out.println("You roll " + processingRoll1 + " and " + processingRoll2);
		System.out.println("The sum of your dice is " + processingSum + "\n");
		
		if(processingSum == 7 || processingSum == 11)
		{
			isWin(player1, player2, player, processingBet);
		}else if(processingSum == 2 || processingSum == 12){
			isLose(player1, player2, player, processingBet);
		}else{
			
			System.out.println("In order to win, you should roll " + processingSum);
			nextTurn(player1, player2, player, processingSum);
		}
	}
	
	//to see whether is "win"
	private static void isWin(Dice player1, Dice player2, Dice player, int bet){
		player.setPlayerBalance(player.getPlayerBalance() + bet);
		
		if(player.getPlayerBalance() >= Dice.getPlayerGoal()){
			
			System.out.println(player.getPlayerName() +" wins this turn.");
			System.out.print( "\n **************************************************************"
							+ "\n *"
							+ "\n * " + player.getPlayerName() + " 's bet " + bet + " dollars are added to the balance."
							+ "\n * " + player.getPlayerName() + " 's current balance is " + player.getPlayerBalance() + " dollars"
							+ "\n *"
							+ "\n **************************************************************\n");
			
			JOptionPane.showMessageDialog(null,
			player.getPlayerName() + "! You are the champion!"
			,"Congratulations!",JOptionPane.WARNING_MESSAGE);
			
			//Declaration:
			//The ASCII Art is derivated from http://www.chris.com/ascii/
			
			System.out.print(
							 "\n88888888888888888888888888888888888888"
							+"\n88888888888888888888888888888888888888"
							+"\n888888888888888888,,,88888888888888888"
							+"\n88888888888888888 OOO 8888888888888888"
							+"\n888888888888888881OOO18888888888888888"
							+"\n8888888888888888, OOO ,888888888888888"
							+"\n88888888888888, OOOOOOO ,8888888888888"
							+"\n8888888888881 OOOOOOOOOOO 188888888888"
							+"\n8888888888, OOOOOOOOOOOOOOO ,888888888"
							+"\n88888888,1OOOOOOOOOOOOOOOOOOO7,8888888"
							+"\n888888,1OOOOOOOOOOOOOOOOOOOOOO 8888888"
							+"\n8888881OOOOOOOOO1     1OOOOOOOO1888888"
							+"\n888888 OOOOOOOOOO 88881OOOOOOOO 888888"
							+"\n888888 OOOOOOOOOOO1888 ,OOOOOOO1888888"
							+"\n8888888 ,OOOOOOOOOO ,888       8888888"
							+"\n88888888 1OOOOOOOOOOO ,888888888888888"
							+"\n8888888888 ,OOOOOOOOOOO ,8888888888888"
							+"\n888888888888 OOOOOOOOOOOO ,88888888888"
							+"\n8888888888888 1OOOOOOOOOOOO +888888888"
							+"\n888888888888888 22OOOOOOOOOOO,88888888"
							+"\n88888888,,,,,,8888 ,OOOOOOOOOO ,888888  " + "Congratulations!"
							+"\n888888, OOOOOO ,8888 ,OOOOOOOOO1888888  " + player.getPlayerName() + " is the CHAMPION!"
							+"\n8888881OOOOOOOO. 222 OOOOOOOOOO1888888"
							+"\n8888881OOOOOOOOOOOOOOOOOOOOOOO18888888"
							+"\n888888 ,OOOOOOOOOOOOOOOOOOOOO, 8888888"
							+"\n8888888 ,OOOOOOOOOOOOOOOOOOO1888888888"
							+"\n8888888881OOOOOOOOOOOOOOOO,18888888888"
							+"\n8888888888 22,OOOOOOOOOO, 888888888888"
							+"\n88888888888888   1OOO1  88888888888888"
							+"\n888888888888888881OOO18888888888888888"
							+"\n88888888888888888,OOO,8888888888888888"
							+"\n888888888888888888   88888888888888888"
							+"\n88888888888888888888888888888888888888"
							+"\n88888888888888888888888888888888888888"
							+"\n88888888888888888888888888888888888888\n");
		}else{
			
			JOptionPane.showMessageDialog(null,
			player.getPlayerName() + "! You wins this turn!"
			,"Congratulations!",JOptionPane.WARNING_MESSAGE);
			
			System.out.println(player.getPlayerName() +" wins this turn.");
			
			System.out.print( "\n **************************************************************"
							+ "\n *"
							+ "\n * " + player.getPlayerName() + " 's bet " + bet + " dollars are added to the balance."
							+ "\n * " + player.getPlayerName() + " 's current balance is " + player.getPlayerBalance() + " dollars"
							+ "\n *"
							+ "\n **************************************************************\n");
	
			firstTurn(player1, player2, referee(player1, player2));
		}	
	}
	
	//to see whether is "lose"
	
	private static void isLose(Dice player1, Dice player2, Dice player, int bet){
		
		player.setPlayerBalance(player.getPlayerBalance() - bet);
		
		if(player.getPlayerBalance() <= 0){
			
			JOptionPane.showMessageDialog(null,
			player.getPlayerName() + "! You lose this turn."
			,"Sorry...",JOptionPane.WARNING_MESSAGE);
			
			System.out.println(player.getPlayerName() +" loses this turn.");
			
			System.out.print( "\n **************************************************************"
							+ "\n *"
							+ "\n * " + player.getPlayerName() + " 's bet " + bet + " dollars are detracted from your balance."
							+ "\n * " + player.getPlayerName() + " 's current balance is " + player.getPlayerBalance() + " dollars"
							+ "\n *"
							+ "\n **************************************************************\n");
				
			JOptionPane.showMessageDialog(null,
			player.getPlayerName() + "! Your game is over."
			,"Sorry...",JOptionPane.WARNING_MESSAGE);
			
			//Declaration:
			//The ASCII Art is derivated from http://www.chris.com/ascii/
			
			System.out.print(
							 "\n88888888888888888888888888888888888888"
							+"\n88888888888888888888888888888888888888"
							+"\n888888888888888888,,,88888888888888888"
							+"\n88888888888888888 OOO 8888888888888888"
							+"\n888888888888888881OOO18888888888888888"
							+"\n8888888888888888, OOO ,888888888888888"
							+"\n88888888888888, OOOOOOO ,8888888888888"
							+"\n8888888888881 OOOOOOOOOOO 188888888888"
							+"\n8888888888, OOOOOOOOOOOOOOO ,888888888"
							+"\n88888888,1OOOOOOOOOOOOOOOOOOO7,8888888"
							+"\n888888,1OOOOOOOOOOOOOOOOOOOOOO 8888888"
							+"\n8888881OOOOOOOOO1     1OOOOOOOO1888888"
							+"\n888888 OOOOOOOOOO 88881OOOOOOOO 888888"
							+"\n888888 OOOOOOOOOOO1888 ,OOOOOOO1888888"
							+"\n8888888 ,OOOOOOOOOO ,888       8888888"
							+"\n88888888 1OOOOOOOOOOO ,888888888888888"
							+"\n8888888888 ,OOOOOOOOOOO ,8888888888888"
							+"\n888888888888 OOOOOOOOOOOO ,88888888888"
							+"\n8888888888888 1OOOOOOOOOOOO +888888888"
							+"\n888888888888888 22OOOOOOOOOOO,88888888"
							+"\n88888888,,,,,,8888 ,OOOOOOOOOO ,888888  " + "Congratulations!"
							+"\n888888, OOOOOO ,8888 ,OOOOOOOOO1888888  " + referee(player1, player2).getPlayerName() + " is the CHAMPION!"
							+"\n8888881OOOOOOOO. 222 OOOOOOOOOO1888888"
							+"\n8888881OOOOOOOOOOOOOOOOOOOOOOO18888888"
							+"\n888888 ,OOOOOOOOOOOOOOOOOOOOO, 8888888"
							+"\n8888888 ,OOOOOOOOOOOOOOOOOOO1888888888"
							+"\n8888888881OOOOOOOOOOOOOOOO,18888888888"
							+"\n8888888888 22,OOOOOOOOOO, 888888888888"
							+"\n88888888888888   1OOO1  88888888888888"
							+"\n888888888888888881OOO18888888888888888"
							+"\n88888888888888888,OOO,8888888888888888"
							+"\n888888888888888888   88888888888888888"
							+"\n88888888888888888888888888888888888888"
							+"\n88888888888888888888888888888888888888"
							+"\n88888888888888888888888888888888888888\n");
		}else{
			
			System.out.println(player.getPlayerName() +" loses this turn.");
			
			System.out.print( "\n **************************************************************"
							+ "\n *"
							+ "\n * " + player.getPlayerName() + " 's bet " + bet + " dollars are detracted from your balance."
							+ "\n * " + player.getPlayerName() + " 's current balance is " + player.getPlayerBalance() + " dollars"
							+ "\n *"
							+ "\n **************************************************************\n");
			
			firstTurn(player1, player2, referee(player1, player2));
		}	
	}

	//This is a key method to continue the game.
	
	private static void nextTurn(Dice player1, Dice player2, Dice player, int yourPoint){
		
		JOptionPane.showMessageDialog(null,"Let's see the number...","Good luck!",JOptionPane.WARNING_MESSAGE);
		
		processingRoll1 = Dice.roll();
		processingRoll2 = Dice.roll();
		processingSum = processingRoll1 + processingRoll2;
		
		System.out.println("You roll " + processingRoll1 + " and " + processingRoll2);
		System.out.println("The sum of your dice is " + processingSum + "\n");
		
		if(processingSum == yourPoint){
			isWin(player1, player2, player, processingBet);
		}else if(processingSum == 7){
			isLose(player1, player2, player, processingBet);
		}else{
			nextTurn(player1, player2, player, yourPoint);
		}
		
	}
}

/* The following codes are used to create the Dice class, which will be used to 
 * present players.*/




/******************************************************************
 *                                                                *
 *                                                                *
 *                         The Dice Class                         *
 *                                                                *
 *                                                                *
 ******************************************************************/




class Dice{
	
	//set up all the fields for the Dice
	
	private String playerName;
	private int playerBalance;
	
	/* In order to make the game fair, the player goal should be the same.
	 * Therefore I use static varible here.*/
	 
	private static int playerGoal; 
	
	
	
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
	
	//other varibles used to store processing values
	
	private static String playerBetString;
	private static int playerBetInteger;
	private static int playerDiceNumber;
	
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
			
			//convert the numberic string to integer
			playerBetInteger = Integer.parseInt(playerBetString);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,
			"Dear " + playerName + "! Your bet should be a number. Can you put in your bet again?"
			,"Oooops", JOptionPane.WARNING_MESSAGE);
			bet();
		}
		
		if(isValidBet(playerBetInteger)){
			return playerBetInteger;
		}else{
			JOptionPane.showMessageDialog(null,
			"Hey " + playerName + "! Your bet should be more than 0 dollar and less than " + playerBalance +" dollars."
			,"Ooooooops...", JOptionPane.WARNING_MESSAGE);
			
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