/*******************************************************************************
 *
 * Group Project
 *
 * Name ordered by the initial of the last names
 *
 * Eric Chen
 * Wan Huh
 * Tengchao Zhou
 * 
 * V22.0101-003
 * 
 * Course: Introduction to Computer Science I (JAVA) 
 * Professor: Sana' Odeh
 *
 * December 16, 2009
 * 
 * Master Mind
 * 
 *******************************************************************************/

import javax.swing.*;

public class PlayMastermind {

	public static String playerFirstName;
	public static String playerLastName;
	public static String playerName;

	public static void main(String[] args) {
		menu();
	}// end main method

	public static void menu() {

		Object[] menuDropdown = { "About MasterMind", "Game Rules",
				"Launch Game", "View High Scores", "Exit" }; // creates array of
																// menu options

		try {
			Object userChoice = JOptionPane.showInputDialog(null, "Welcome to"
					+ " Mastermind!", "Mastermind Launch Menu",
					JOptionPane.INFORMATION_MESSAGE, null, menuDropdown,
					menuDropdown[0]);
			if (userChoice.equals(menuDropdown[0])) {
				JOptionPane
						.showMessageDialog(
								null,
								"About Mastermind:"
										+ "\nThis is a java version of the classic boardgame Mastermind"
										+ ". \nIt is meant to be played by two players alternating "
										+ "turns. \nIt has been developed by Eric Chen, Wan Huh and Tengchao Zhou\n"
										+ "(ordered by the initial of last names) \nPlease enjoy playing Mastermind!");
				menu();
			} else if (userChoice.equals(menuDropdown[1])) {
				JOptionPane
						.showMessageDialog(
								null,
								"Mastermind Rules:"
										+ "\n*One player takes turns trying to break a preset code"
										+ "\n*There are 2 types of pegs: code pegs and key pegs"
										+ "\n*Code pegs are larger and come in 6 colors: red, green, blue, yellow, black, and cyan"
										+ "\n*Key pegs are smaller and come in 2 colors: black and gray"
										+ "\n*The game is played on a board consisting of 12 rows of four holes each"
										+ "\n*The game begins with a preset pattern of 4 colored code pegs"
										+ "\n*Duplicate colors are allowed and the pattern is unknown"
										+ "\n*The player must attempt to guess the exact pattern of the code in both color and order"
										+ "\n*After each guess key pegs are placed next to each row to provide feedback on the code breaker's guess"
										+ "\n*A black key peg is placed to indicate that a code peg is both of the right color and in the right spot"
										+ "\n*A gray key peg is placed to indicate a code peg of the right color in the wrong spot"
										+ "\n*No key peg is placed for any code peg that is both of the wrong color and the wrong spot"
										+ "\n*A turn ends when either of the following conditions is true:"
										+ "\n*  -The player correctly matches preset code, or"
										+ "\n*  -The player does not match the code within 12 tries"
										+ "\n*The player begins with 360 points"
										+ "\n*For each guess the player makes 10 points are deducted from the score"
										+ "\n*The game is played for 2 sets"
										+ "\n*High scores are attained by using as few guesses as possible");
				menu();
			} else if (userChoice.equals(menuDropdown[2])) {
				playerFirstName = JOptionPane.showInputDialog(null,
						"Step 1/3\nPlease enter your first name.");
				playerLastName = JOptionPane.showInputDialog(null,
						"Step 2/3\nPlease enter your last name.");
				playerName = JOptionPane
						.showInputDialog(null,
								"Step 3/3\nPlease enter your user name.\nThe game will start soon!");
				Mastermind mastermind = new Mastermind();
				mastermind.setTitle("Mastermind");
				mastermind.setDefaultCloseOperation(3); // exits on close
				mastermind.setSize(400, 630);
				mastermind.setLocationRelativeTo(null);
				mastermind.setVisible(true);

			} else if (userChoice.equals(menuDropdown[3])) {
				Rank rank = new Rank();
				rank.setTitle("Ranking");
				rank.setDefaultCloseOperation(3); // exits on close
				rank.setSize(400, 315);
				rank.setLocationRelativeTo(null);
				rank.setVisible(true);
			} else if (userChoice.equals(menuDropdown[4])) {
				System.exit(0);
			}

		}

		catch (NullPointerException npe) {
			System.exit(0);
		}
	}
}// end Mastermind class
