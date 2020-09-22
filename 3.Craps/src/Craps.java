/*******************************************************************************
Tengchao Zhou
V22.0101-003
Course: Introduction to Computer Science I (JAVA) 
Professor: Sana' Odeh
October 18, 2009

New Craps

How to play the New Craps?
Two players take turns. You start with a balance of 100 dollars.
You roll two dice each time and the sum ranges from 2 to 12.
Before you roll, you bet. In the first roll, if you get 7 or 11, you win;
if you get 2 or 12, you lose. If you get any other numbers,
you need to keep rolling until you get the previous sum again,
in which case you win or you get 7, in which case you lose.
Under two scenarios, the game ends.
(1) One player reaches the goal of 300 dollars
and (2) One player runs out of money.
 ***************************************************************************

Some comments.

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
 * * * The Craps Class * * *
 ******************************************************************/

public class Craps {

	// something that is determined should be declared here

	private static final int theGoal = 300;

	// some variables will be used
	private static String firstPlayerName = "Player1";
	private static String secondPlayerName = "Player2";

	private static int processingRoll1;
	private static int processingRoll2;
	private static int processingSum;

	private static int playerOrder = 1;

	private static int processingBet;
	
	private static int choice;

	public static void main(String[] args) {

		// As a norm, we need to read instruction before playing the game.

		choice = JOptionPane.showConfirmDialog(
						null,
								  "\n ***************************************************************"
								+ "\n ***********************INSTRUCTIONS************************"
								+ "\n ***************************************************************"
								+ "\n Two players take turns. You start with a balance of 100 dollars."
								+ "\n You roll two dice each time and the sum ranges from 2 to 12."
								+ "\n Before you roll, you bet. In the first roll, if you get 7 or 11, you win;"
								+ "\n if you get 2 or 12, you lose. If you get any other numbers,"
								+ "\n you need to keep rolling until you get the previous sum again,"
								+ "\n in which case you win or you get 7, in which case you lose."
								+ "\n Under two scenarios, the game ends."
								+ "\n (1) One player reaches the goal of 300 dollars"
								+ "\n and (2) One player runs out of money."
								+ "\n ***************************************************************"
								+ "\n ***************************************************************\n", "INSTRUCTIONS", JOptionPane.DEFAULT_OPTION );
		if(choice == -1) System.exit(0);
		
		choice = JOptionPane.showConfirmDialog(null, "Can we start the game now?",
				"Are you ready?", JOptionPane.DEFAULT_OPTION );
		if(choice == -1) System.exit(0);

		// ask the players to enter their names
		firstPlayerName = JOptionPane.showInputDialog(null,
				"You will be the 1st player. What's your name?", "Name?",
				JOptionPane.QUESTION_MESSAGE);
		if(firstPlayerName == null) System.exit(0);
		if(firstPlayerName.isEmpty()) firstPlayerName = "Player1";
		
		secondPlayerName = JOptionPane.showInputDialog(null,
				"You will be the 2nd player. What's your name?", "Name?",
				JOptionPane.QUESTION_MESSAGE);
		if(secondPlayerName == null) System.exit(0);
		if(secondPlayerName.isEmpty()) secondPlayerName = "Player2";

		// now we can use the constructor to create two dice
		Dice dice1 = new Dice(firstPlayerName, 100, theGoal); // construct dice1 instance
		Dice dice2 = new Dice(secondPlayerName, 100, theGoal); // construct dice2 instance


		// now begin the game

		firstTurn(dice1, dice2, referee(dice1, dice2));

	}

	// In order to decide which player to play, I should construct a new method called referee()

	private static Dice referee(Dice player1, Dice player2) {
		if (playerOrder == 1) {
			playerOrder = 2;
			return player1;
		} else {
			playerOrder = 1;
			return player2;
		}
	}

	// this method helps to judge whether the FIRST turn is 'win' or 'lose'

	private static void firstTurn(Dice player1, Dice player2, Dice player) {

		choice = JOptionPane.showConfirmDialog(null, "This is " + player.getPlayerName()
				+ "'s turn. Your balance is " + player.getPlayerBalance(), "INFO", JOptionPane.DEFAULT_OPTION);
		if(choice == -1) System.exit(0);
		processingBet = player.bet();

		choice = JOptionPane.showConfirmDialog(null, "Let's see the number...",
				"Good luck!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		if(choice == -1) System.exit(0);

		processingRoll1 = Dice.roll();
		processingRoll2 = Dice.roll();
		processingSum = processingRoll1 + processingRoll2;

		choice = JOptionPane.showConfirmDialog(null, "You roll " + processingRoll1
				+ " and " + processingRoll2, "INFO", JOptionPane.DEFAULT_OPTION);
		if(choice == -1) System.exit(0);

		choice = JOptionPane.showConfirmDialog(null, "The sum of your dice is "
				+ processingSum + "\n", "INFO", JOptionPane.DEFAULT_OPTION);
		if(choice == -1) System.exit(0);

		if (processingSum == 7 || processingSum == 11) {
			isWin(player1, player2, player, processingBet);
		} else if (processingSum == 2 || processingSum == 12) {
			isLose(player1, player2, player, processingBet);
		} else {

			choice = JOptionPane.showConfirmDialog(null,
					"In order to win, you should roll " + processingSum, "INFO", JOptionPane.DEFAULT_OPTION);
			if(choice == -1) System.exit(0);

			nextTurn(player1, player2, player, processingSum);
		}
	}

	// to see whether is "win"
	private static void isWin(Dice player1, Dice player2, Dice player, int bet) {
		player.setPlayerBalance(player.getPlayerBalance() + bet);

		if (player.getPlayerBalance() >= Dice.getPlayerGoal()) {

			choice = JOptionPane.showConfirmDialog(null, player.getPlayerName()
					+ " wins this turn.", "INFO", JOptionPane.DEFAULT_OPTION);
			if(choice == -1) System.exit(0);

			choice = JOptionPane.showConfirmDialog(
							null,
									  "\n **************************************************************"
									+ "\n *"
									+ "\n * "
									+ player.getPlayerName()
									+ " 's bet "
									+ bet
									+ " dollars are added to the balance."
									+ "\n * "
									+ player.getPlayerName()
									+ " 's current balance is "
									+ player.getPlayerBalance()
									+ " dollars"
									+ "\n *"
									+ "\n **************************************************************\n", 
									"INFO", JOptionPane.DEFAULT_OPTION);
			if(choice == -1) System.exit(0);

			choice = JOptionPane.showConfirmDialog(null, player.getPlayerName()
					+ "! You are the champion!", "Congratulations!", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if(choice == -1) System.exit(0);

			choice = JOptionPane.showConfirmDialog(null,
					dollarASCII(player.getPlayerName()), "INFO", JOptionPane.DEFAULT_OPTION);
			if(choice == -1) System.exit(0);

		} else {

			choice = JOptionPane.showConfirmDialog(null, player.getPlayerName()
					+ "! You wins this turn!", "Congratulations!", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if(choice == -1) System.exit(0);

			choice = JOptionPane.showConfirmDialog(null, player.getPlayerName()
					+ " wins this turn.", "INFO", JOptionPane.DEFAULT_OPTION);
			if(choice == -1) System.exit(0);

			choice = JOptionPane.showConfirmDialog(
							null,
									  "\n **************************************************************"
									+ "\n *"
									+ "\n * "
									+ player.getPlayerName()
									+ " 's bet "
									+ bet
									+ " dollars are added to the balance."
									+ "\n * "
									+ player.getPlayerName()
									+ " 's current balance is "
									+ player.getPlayerBalance()
									+ " dollars"
									+ "\n *"
									+ "\n **************************************************************\n", 
									"INFO", JOptionPane.DEFAULT_OPTION);
			if(choice == -1) System.exit(0);

			firstTurn(player1, player2, referee(player1, player2));
		}
	}

	// to see whether is "lose"
	private static void isLose(Dice player1, Dice player2, Dice player, int bet) {

		player.setPlayerBalance(player.getPlayerBalance() - bet);

		if (player.getPlayerBalance() <= 0) {

			choice = JOptionPane.showConfirmDialog(null, player.getPlayerName()
					+ "! You lose this turn.", "Sorry...", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if(choice == -1) System.exit(0);

			choice = JOptionPane.showConfirmDialog(null, player.getPlayerName()
					+ " loses this turn.", "INFO", JOptionPane.DEFAULT_OPTION);
			if(choice == -1) System.exit(0);

			choice = JOptionPane.showConfirmDialog(
							null,
									  "\n **************************************************************"
									+ "\n *"
									+ "\n * "
									+ player.getPlayerName()
									+ " 's bet "
									+ bet
									+ " dollars are detracted from your balance."
									+ "\n * "
									+ player.getPlayerName()
									+ " 's current balance is "
									+ player.getPlayerBalance()
									+ " dollars"
									+ "\n *"
									+ "\n **************************************************************\n", 
									"INFO", JOptionPane.DEFAULT_OPTION);
			if(choice == -1) System.exit(0);

			choice = JOptionPane.showConfirmDialog(null, player.getPlayerName()
					+ "! Your game is over.", "Sorry...", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if(choice == -1) System.exit(0);

			choice = JOptionPane.showConfirmDialog(null,
					dollarASCII(referee(player1, player2).getPlayerName()), "INFO", JOptionPane.DEFAULT_OPTION);
			if(choice == -1) System.exit(0);
		} else {

			choice = JOptionPane.showConfirmDialog(null, player.getPlayerName()
					+ " loses this turn.", "INFO", JOptionPane.DEFAULT_OPTION);
			if(choice == -1) System.exit(0);

			choice = JOptionPane.showConfirmDialog(
							null,
									  "\n **************************************************************"
									+ "\n *"
									+ "\n * "
									+ player.getPlayerName()
									+ " 's bet "
									+ bet
									+ " dollars are detracted from your balance."
									+ "\n * "
									+ player.getPlayerName()
									+ " 's current balance is "
									+ player.getPlayerBalance()
									+ " dollars"
									+ "\n *"
									+ "\n **************************************************************\n", 
									"INFO", JOptionPane.DEFAULT_OPTION);
			if(choice == -1) System.exit(0);

			firstTurn(player1, player2, referee(player1, player2));
		}
	}

	// This is a key method to continue the game.
	private static void nextTurn(Dice player1, Dice player2, Dice player,
			int yourPoint) {

		choice = JOptionPane.showConfirmDialog(null, "Let's see the number...",
				"Good luck!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		if(choice == -1) System.exit(0);

		processingRoll1 = Dice.roll();
		processingRoll2 = Dice.roll();
		processingSum = processingRoll1 + processingRoll2;

		choice = JOptionPane.showConfirmDialog(null, "You roll " + processingRoll1
				+ " and " + processingRoll2, "INFO", JOptionPane.DEFAULT_OPTION);
		if(choice == -1) System.exit(0);

		choice = JOptionPane.showConfirmDialog(null, "The sum of your dice is "
				+ processingSum + "\n", "INFO", JOptionPane.DEFAULT_OPTION);
		if(choice == -1) System.exit(0);

		if (processingSum == yourPoint) {
			isWin(player1, player2, player, processingBet);
		} else if (processingSum == 7) {
			isLose(player1, player2, player, processingBet);
		} else {
			nextTurn(player1, player2, player, yourPoint);
		}

	}

	// Declaration:
	// The ASCII Art is derived from http://www.chris.com/ascii/
	private static String dollarASCII(String winnerName) {
		return "Congratulations!\n" + winnerName + " is the CHAMPION!"
				+ "\n88888888888888888888888888888888888888"
				+ "\n88888888888888888888888888888888888888"
				+ "\n888888888888888888,,,88888888888888888"
				+ "\n88888888888888888 OOO 8888888888888888"
				+ "\n888888888888888881OOO18888888888888888"
				+ "\n8888888888888888, OOO ,888888888888888"
				+ "\n88888888888888, OOOOOOO ,8888888888888"
				+ "\n8888888888881 OOOOOOOOOOO 188888888888"
				+ "\n8888888888, OOOOOOOOOOOOOOO ,888888888"
				+ "\n88888888,1OOOOOOOOOOOOOOOOOOO7,8888888"
				+ "\n888888,1OOOOOOOOOOOOOOOOOOOOOO 8888888"
				+ "\n8888881OOOOOOOOO1     1OOOOOOOO1888888"
				+ "\n888888 OOOOOOOOOO 88881OOOOOOOO 888888"
				+ "\n888888 OOOOOOOOOOO1888 ,OOOOOOO1888888"
				+ "\n8888888 ,OOOOOOOOOO ,888       8888888"
				+ "\n88888888 1OOOOOOOOOOO ,888888888888888"
				+ "\n8888888888 ,OOOOOOOOOOO ,8888888888888"
				+ "\n888888888888 OOOOOOOOOOOO ,88888888888"
				+ "\n8888888888888 1OOOOOOOOOOOO +888888888"
				+ "\n888888888888888 22OOOOOOOOOOO,88888888"
				+ "\n88888888,,,,,,8888 ,OOOOOOOOOO ,888888"
				+ "\n888888, OOOOOO ,8888 ,OOOOOOOOO1888888"
				+ "\n8888881OOOOOOOO. 222 OOOOOOOOOO1888888"
				+ "\n8888881OOOOOOOOOOOOOOOOOOOOOOO18888888"
				+ "\n888888 ,OOOOOOOOOOOOOOOOOOOOO, 8888888"
				+ "\n8888888 ,OOOOOOOOOOOOOOOOOOO1888888888"
				+ "\n8888888881OOOOOOOOOOOOOOOO,18888888888"
				+ "\n8888888888 22,OOOOOOOOOO, 888888888888"
				+ "\n88888888888888   1OOO1  88888888888888"
				+ "\n888888888888888881OOO18888888888888888"
				+ "\n88888888888888888,OOO,8888888888888888"
				+ "\n888888888888888888   88888888888888888"
				+ "\n88888888888888888888888888888888888888"
				+ "\n88888888888888888888888888888888888888"
				+ "\n88888888888888888888888888888888888888\n";
	}
}

// This is the end of the code.