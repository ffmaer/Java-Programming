/*******************************************************************************
 *
 * Tengchao Zhou
 * 
 * V22.0101-003
 * 
 * Course: Introduction to Computer Science I (JAVA) 
 * Professor: Sana' Odeh
 * 
 *
 * November 7, 2009
 * 
 * The Game of Life
 * 
 *******************************************************************************/

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Font;

/******************************************************************
 * * * The Life Class * * *
 ******************************************************************/

public class Life extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static int iteration = 0;

	static JLabel jlblIteration = new JLabel("Iteration: " + iteration);
	static JTextArea jtaDisplay = new JTextArea("");
	static JButton jbtOK = new JButton("Next Generation");
	static JButton jbtSave = new JButton("Save The Pattern");
	
	static final int M = 25; // rows
	static final int N = 75; // columns

	static char array1[][] = new char[M + 2][N + 2];//+2 for better handling edge cases
	static char array2[][] = new char[M + 2][N + 2];
	
	
	public Life() {

		jtaDisplay.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		add(jlblIteration);
		add(jtaDisplay);
		add(jbtOK);
		add(jbtSave);

		ActionListener listener = new OKListener();
		jbtOK.addActionListener(listener);
		ActionListener listener1 = new SaveListener();
		jbtSave.addActionListener(listener1);
		
		setTitle("The Game of Life");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}



	public static void main(String[] args) {
		new Life();


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
		 * FileStringReader kbd = new FileStringReader( "file1.dat");;and then
		 * String line = kbd.readLine() to read a string.Of course, in your
		 * program you would have a string variable representing "file1.dat"
		 * whose value you type as input to your program .
		 */

		/*
		 * Use two-dimensional arrays of type char to store the old and new
		 * generations respectively.To keep things simple, assume that M=25 and
		 * N=75, i.e. the world has 25 rows and 75 columns,and define these in a
		 * final statement before declaring your array variables.
		 */

		// choose which game do the player want to play
		int filenum = fileNumber();

		FileStringReader kbd = new FileStringReader("life" + filenum + ".dat");

		String line;

		// put the dat file into array
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
		printAll();
	}

	/*
	 * Include at least two non-void methods.One should take a world and the
	 * coordinates of a cell and return the number of neighbors(organisms in
	 * neighboring cells) that the cell has.The other should take a generation
	 * array and return a Boolean value thattells whether or not the world
	 * represented by the array is empty.
	 */
	public static int fileNumber() {
		int filenum = 1;
		try {
			filenum = Integer
					.parseInt(JOptionPane
							.showInputDialog(
									null,
									"Which game do you want to play?\nEnter a number from 0 to 6.\nPlease try 6, which is designed by me.\n0 means the game saved last time."));
		} catch (Exception ex) {
			return fileNumber();
		}
		if (filenum > 6 || filenum < 0) {
			return fileNumber();
		} else {
			return filenum;
		}
	}

	public static void printAll() {
		String tempOutLine = "";

		if (isEmpty()) {
			jlblIteration.setText("Game Over");
		} else {
			iteration++;
			jlblIteration.setText("Iteration: " + iteration);
			for (int i = 0; i <= M + 1; i++) {
				tempOutLine += (String.copyValueOf(array1[i]) + "\n");
			}
			jtaDisplay.setText(tempOutLine);
			nextGen();
		}
	}

	static void nextGen() {

		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				array2[i][j] = array1[i][j];
				array1[i][j] = ' ';
			}
		}
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (numNei(i, j) == 3) {
					array1[i][j] = 'X';
				} else if (array2[i][j] == 'X' && numNei(i, j) == 2) {
					array1[i][j] = 'X';
				}
			}
		}
	}

	static int numNei(int i, int j) {
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

	static Boolean isEmpty() {
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (array2[i][j] == 'X') {
					return false;
				}
			}
		}
		return true;
	}

	private class OKListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			printAll();
		}
	}

	private class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				PrintWriter output = new PrintWriter("life0.dat");
				for (int i = 1; i <= M; i++) {
					output.println(String.copyValueOf(array1[i]).replaceAll(
							" ", "."));
				}

				output.close();
			} catch (FileNotFoundException ex) {
				System.out.println("File  does not exist");
			}
		}
	}
}
