/*******************************************************************************
 *
 * Tengchao Zhou
 * 
 * V22.0101-003
 * 
 * Course: Introduction to Computer Science I (JAVA) 
 * Professor: Sana Odeh
 * 
 *
 * November 7, 2009
 * 
 * The Game of Life
 * 
 *******************************************************************************/


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;

/******************************************************************
 * * * The Life Class * * *
 ******************************************************************/

@SuppressWarnings("serial")
public class Life extends JFrame {

	static int iteration = 0;

	static JLabel iterationText = new JLabel("Iteration: " + iteration);
	static JTextPane mainDisplay = new JTextPane();
	static StyledJButton nextGenButton = new StyledJButton("Next Generation");
	static StyledJButton saveButton = new StyledJButton("Save The Pattern");
	static StyledJButton restartButton = new StyledJButton("Restart");
	static JLabel message = new JLabel("");
	
	static final int M = 25; // rows
	static final int N = 75; // columns

	// we have two arrays, because then you can create a new generation based on the previous generation
	static char array1[][] = new char[M + 2][N + 2];//+2 for better handling edge cases
	static char array2[][] = new char[M + 2][N + 2];
	
	public Life() {

		setTitle("The Game of Life");
		setSize(800, 600);
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		this.getContentPane().setBackground( Color.BLACK );
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		mainDisplay.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		mainDisplay.setBackground(Color.BLACK);
		mainDisplay.setForeground(Color.GREEN);
		add(mainDisplay);
		
		iterationText.setForeground(Color.GREEN);
		add(iterationText);
		
		nextGenButton.addActionListener(new NextGenListener());
		add(nextGenButton);

		saveButton.addActionListener(new SaveListener());
		add(saveButton);
		
		restartButton.addActionListener(new RestartListener());
		add(restartButton);
		
		message.setForeground(Color.GREEN);
		message.setText("");
		add(message);
		
	}

	
	private static void startGame() {
		
		iteration = 0; // reset
		
		// choose the game that the player wants to play
		int filenum = selectGame();

		FileStringReader kbd = new FileStringReader("life" + filenum + ".txt");

		String line;

		// put the .txt file into array
		for (int i = 1; i <= M; i++) {
			line = kbd.readLine();
			for (int j = 1; j <= N; j++) {
				if (line.charAt(j - 1) == 'X') {
					array1[i][j] = 'X';
					array2[i][j] = 'X';
				} else {
					array1[i][j] = ' ';
					array2[i][j] = ' ';
				}

			}
		}
		
		displayPattern();
	}

	public static void main(String[] args) {

		/*
		 * Empty cells are represented by dots and occupied cells with X's. Read
		 * the data and assign the data to a two-dimensional array using two
		 * nested for loops.The outer loop reads a string consisting of the data
		 * on a given line of the input file.The inner loop assigns the data to
		 * a given row of the array.
		 * 
		 * In order to read the file, you will have to use FileStringReader(it
		 * is posted directly under the link for this assignment)which has the
		 * string indicating the file you are reading as the parameter of the
		 * constructor.Thus to read the first file you would have
		 * FileStringReader kbd = new FileStringReader( "file1.txt");;and then
		 * String line = kbd.readLine() to read a string.Of course, in your
		 * program you would have a string variable representing "file1.txt"
		 * whose value you type as input to your program .
		 */

		/*
		 * Use two-dimensional arrays of type char to store the old and new
		 * generations respectively.To keep things simple, assume that M=25 and
		 * N=75, i.e. the world has 25 rows and 75 columns,and define these in a
		 * final statement before declaring your array variables.
		 */

		startGame();
		
		new Life();

		
	}

	/*
	 * Include at least two non-void methods.One should take a world and the
	 * coordinates of a cell and return the number of neighbors(organisms in
	 * neighboring cells) that the cell has.The other should take a generation
	 * array and return a Boolean value that tells whether or not the world
	 * represented by the array is empty.
	 */
	public static int selectGame() {
		int filenum = -1;
		try {
			String str = JOptionPane
			.showInputDialog(
					null,
					"Which game do you want to play?\n"
					+ "Enter a number from 0 to 6.\n"
					+ "Please try 6, which is designed by me.\n"
					+ "0 means the game saved last time.");
						
			if(str == null) System.exit(0);
							
			filenum = Integer.parseInt(str);
		} catch (Exception ex) {
			return selectGame();
		}
		if (filenum > 6 || filenum < 0) {
			return selectGame();
		} else {
			return filenum;
		}
	}

	public static void displayPattern() {
		String displayOutput = "";

		if (isTotallyEmpty()) {
			iterationText.setText("Game Over");
		} else {
			iteration++;
			iterationText.setText("Iteration: " + iteration);
			for (int i = 0; i <= M + 1; i++) {
				displayOutput += (String.copyValueOf(array1[i]) + "\n");
			}
			mainDisplay.setText(displayOutput);
			nextGen();
		}
	}

	static void nextGen() {

		// store current pattern
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				array2[i][j] = array1[i][j];
				array1[i][j] = ' ';
			}
		}
		
		// create next generation of pattern
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (neighbor(i, j) == 3) { // if it has three neighbors
					array1[i][j] = 'X';
				} else if (array2[i][j] == 'X' && neighbor(i, j) == 2) { // if it has two neighbors, and an X already exists at i,j
					array1[i][j] = 'X';
				}
			}
		}
	}

	static int neighbor(int i, int j) {
		int neighborCounter = 0;
		if (array2[i - 1][j - 1] == 'X') {
			neighborCounter++;
		}
		if (array2[i - 1][j] == 'X') {
			neighborCounter++;
		}
		if (array2[i - 1][j + 1] == 'X') {
			neighborCounter++;
		}
		if (array2[i][j - 1] == 'X') {
			neighborCounter++;
		}
		if (array2[i][j + 1] == 'X') {
			neighborCounter++;
		}
		if (array2[i + 1][j - 1] == 'X') {
			neighborCounter++;
		}
		if (array2[i + 1][j] == 'X') {
			neighborCounter++;
		}
		if (array2[i + 1][j + 1] == 'X') {
			neighborCounter++;
		}
		return neighborCounter;
	}

	static Boolean isTotallyEmpty() { // no life is left, a very sad situation
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (array2[i][j] == 'X') {
					return false;
				}
			}
		}
		return true;
	}

	private class RestartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			startGame();
		}
	}
	
	private class NextGenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			message.setText("");
			displayPattern();
		}
	}

	private class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			try {
				PrintWriter output = new PrintWriter("life0.txt");
				for (int i = 1; i <= M; i++) {
					output.println(String.copyValueOf(array1[i]).replaceAll(
							" ", "."));
				}
				output.close();
				String status = "Saved to life0.txt";
				message.setText(status);
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
	}
}
