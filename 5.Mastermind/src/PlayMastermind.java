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
								"About Mastermind:\n"
										+ "This is a java version of the classic boardgame Mastermind.\n"
										+ "It is meant to be played by two players alternating tunrs.\n"
										+ "It has been developed by Eric Chen, Wan Huh and Tengchao Zhou\n"
										+ "(ordered by the initial of last names)\n"
										+ "Please enjoy playing Mastermind!");
				menu();
			} else if (userChoice.equals(menuDropdown[1])) {
				JOptionPane
						.showMessageDialog(
								null,
								"Mastermind Rules: Mastermind is a single player game. The goal is to break a code. "
										+ "\nThe code is made of four big code-pegs. Each code-peg can have either of the 6 colors. "
										+ "\nEach turn you make a guess about the code. 4 small key-pegs are used to indicate the "
										+ "\ncorrectness of your guess. A black key-peg indicates that a code-peg has both right color "
										+ "\nand right spot. A gray key-peg indicates that a code-peg has the right color but wrong spot. "
										+ "\nYou win if you break the code within 12 attempts. The player begins with 360 points. "
										+ "\n10 points are deducted for each guess. Use as few guesses as possible to achieve high scores.");
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
}// end Master Mind class
