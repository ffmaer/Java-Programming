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
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Table extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int BLACK = 1, EMPTY = 6, GRAY = 2; // colors for key pegs
	private int[][] grid_keypegs = new int[12][4]; // grid for key pegs
	private int[][] grid_smallpegs = new int[24][2]; // grid for small pegs
	private int[][] answer = new int[1][4]; // the correct code set by the game
	private boolean playing; // checks whether the game is over or not
	private int row; // mth row =mth try out of 12 times
	private int outcome; // game outcome !=0 if the game is over
	private int numBlack; // num of small black pegs
	// (both right color and right position)
	private int numGray; // num of small white pegs
	// (right color, but wrong position)
	private int numEmpty; // num of empty small pegs
	// (both wrong color and wrong pos)
	private int playerPoints = 360; // the player's points
	private int set = 0; // current set(round)

	Table() {
		setPreferredSize(new Dimension(400, 500));
		setBackground(Color.WHITE);
		addMouseListener(new GameListener());
		newGameScreen();
		playing = true;
	}

	public void enter() {
		boolean allfour = true; // check whether or not the user has left one of
								// the 4 spots empty
		for (int c = 0; c < 4; c++) {
			if (grid_keypegs[row][c] != 0 && grid_keypegs[row][c] != 1
					&& grid_keypegs[row][c] != 2 && grid_keypegs[row][c] != 3
					&& grid_keypegs[row][c] != 4 && grid_keypegs[row][c] != 5) {
				allfour = false;
			}
		}// end for col

		if (allfour == false) {
			JOptionPane.showMessageDialog(null, "you must choose all 4 pegs!");
		}

		else {
			// ///////count the small
			// pegs//////////////////////////////////////////////////////////
			List<String> temp2 = new ArrayList<String>(grid_keypegs[row].length);
			for (int i : grid_keypegs[row]) {
				temp2.add(Integer.toString(i));
			}
			List<String> answer2 = new ArrayList<String>(answer[0].length);
			for (int i : answer[0]) {
				answer2.add(Integer.toString(i));
			}

			for (int x = 0; x < 4; x++) {
				if (grid_keypegs[row][x] == answer[0][x]) {
					numBlack++;
					temp2.remove(temp2.indexOf(Integer
							.toString(grid_keypegs[row][x])));
					answer2.remove(answer2.indexOf(Integer
							.toString(answer[0][x])));
				}
			}// end for x
			for (String x : temp2) {
				if (answer2.contains(x)) {
					numGray++;
					answer2.remove(answer2.indexOf(x));
				}

			}// end for x

			// assign colors to the grid for small pegs
			for (int y = 0; y < 2; y++) {
				for (int x = 0; x < 2; x++) {
					if (numBlack > 0) {
						grid_smallpegs[2 * row + y][x] = BLACK;
						numBlack--;
					} else if (numGray > 0) {
						grid_smallpegs[2 * row + y][x] = GRAY;
						numGray--;
					} else if (numEmpty > 0) {
						grid_smallpegs[2 * row + y][x] = EMPTY;
						numEmpty--;
					}
				}// end for x
			}// end for y

			playerPoints -= 10;
			numBlack = 0;
			numGray = 0;

			outcome = gameOver();

			if (outcome != 0) {
				playing = false;
			}// end if playing
			row++;
			repaint();

		}// end else
	}// end enter()

	public void setColor(int c) {
		// System.out.println();
		if (grid_keypegs[row][c] == 6) {
			grid_keypegs[row][c] = 0;
		} else if (grid_keypegs[row][c] < 5) {
			grid_keypegs[row][c] += 1;
		} else {
			grid_keypegs[row][c] = 0;
		}
		repaint();
	} // end setColor(int c)

	private void newGameScreen() {
		for (int y = 0; y < 12; y++) {
			for (int x = 0; x < 4; x++) {
				grid_keypegs[y][x] = EMPTY;
			}
		}
		for (int y = 0; y < 24; y++) {
			for (int x = 0; x < 2; x++) {
				grid_smallpegs[y][x] = EMPTY;
			}
		}

		for (int x = 0; x < 4; x++)
			answer[0][x] = (int) (Math.random() * 6);

		row = 0;
		numBlack = 0;
		numGray = 0;
		set++;
	}// end newGameScreen()

	private class GameListener implements MouseListener {
		public void mousePressed(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
			int xcoord = e.getPoint().x;
			int ycoord = e.getPoint().y;
			if (xcoord > 0 && xcoord < 400 && ycoord > 0 && ycoord < 600) {

				if (playing == false && set < 3) {
					newGameScreen();
					playing = true;
					repaint();
					// allows score to continue tracking
					return;
				}// end if playing
				else if (playing == false && set == 3) {
					JOptionPane.showMessageDialog(null,
							"Game Over! Congratulations, "
									+ "your final score is: " + playerPoints);
					String tempname = "";
					tempname = PlayMastermind.playerName + ":"
							+ PlayMastermind.playerFirstName + ":"
							+ PlayMastermind.playerLastName;
					Rank.update(tempname, playerPoints);
				}
			}// end if x,ycoord
		}// end mouseClicked(MouseEvent e)

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}

	private int gameOver() {
		boolean same = true; // compares the answer&guess

		if (row < 12) {
			for (int x = 0; x < 4; x++) {
				if (grid_keypegs[row][x] != answer[0][x]) {
					same = false;
				}

			}// end for x
		}

		if (row < 12 && same == true) {
			return 1; // the player guessed the right code
		} else if (row == 11 && same == false) {
			return 2; // the player lost the set
		}

		else
			return 0; // still playing

	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);

		// draw the grid
		for (int y = 0; y <= 12; y++) {
			g.drawLine(0, y * 40 + 0, 0 + 40 * 4, y * 40 + 0);
		}

		for (int x = 0; x <= 4; x++) {
			g.drawLine(x * 40 + 0, 0, x * 40 + 0, 0 + 40 * 12);
		}

		// pick the chosen key peg color and draw them
		for (int y = 11; y >= 0; y--) {
			for (int x = 0; x < 4; x++) {
				if (grid_keypegs[11 - y][x] == 0) {
					g.setColor(Color.YELLOW);
					g.fillOval(4 + x * 40, 4 + y * 40, 30, 30);
				} else if (grid_keypegs[11 - y][x] == 1) {
					g.setColor(Color.BLACK);
					g.fillOval(4 + x * 40, 4 + y * 40, 30, 30);
				} else if (grid_keypegs[11 - y][x] == 2) {
					g.setColor(Color.CYAN);
					g.fillOval(4 + x * 40, 4 + y * 40, 30, 30);
				} else if (grid_keypegs[11 - y][x] == 3) {
					g.setColor(Color.GREEN);
					g.fillOval(4 + x * 40, 4 + y * 40, 30, 30);
				} else if (grid_keypegs[11 - y][x] == 4) {
					g.setColor(Color.BLUE);
					g.fillOval(4 + x * 40, 4 + y * 40, 30, 30);
				} else if (grid_keypegs[11 - y][x] == 5) {
					g.setColor(Color.RED);
					g.fillOval(4 + x * 40, 4 + y * 40, 30, 30);
				}
			}// end for x
		}// end for y

		// draw grid lines for small pegs
		for (int y = 0; y <= 24; y++) {
			g.setColor(Color.BLACK);
			g.drawLine(200, y * 20 + 0, 20 * 2 + 200, y * 20 + 0);
		}
		for (int x = 0; x <= 2; x++)
			g.drawLine(x * 20 + 200, 0, x * 20 + 200, 0 + 20 * 24);

		// draw small pegs
		for (int y = 23; y >= 0; y--) {
			for (int x = 0; x < 2; x++) {
				if (grid_smallpegs[23 - y][x] == BLACK) {
					g.setColor(Color.BLACK);
					g.fillOval(202 + x * 20, 2 + y * 20, 15, 15);
				} else if (grid_smallpegs[23 - y][x] == GRAY) {
					g.setColor(Color.GRAY);
					g.fillOval(202 + x * 20, 2 + y * 20, 15, 15);
				} else if (grid_smallpegs[23 - y][x] == EMPTY) {
					g.setColor(Color.WHITE);
					g.fillOval(202 + x * 20, 2 + y * 20, 15, 15);
				}

			}// end x
		}// end y

		g.setColor(Color.BLUE);
		if (playing) {
			g.drawString("set #: " + set, 256, 20);
			g.drawString(PlayMastermind.playerName + " " + playerPoints
					+ " points", 256, 40);
		} else if (outcome == 1) {
			applause();
			g.drawString("correct set of pegs!", 256, 20);
			g.drawString("click anywhere on ", 256, 40);
			g.drawString("the screen to continue", 256, 60);
		} else if (outcome == 2) {
			g.drawString("this set is over.....", 256, 20);
			g.drawString("click anywhere on ", 256, 40);
			g.drawString("the screen to continue", 256, 60);
		}

	}

	private void applause() {
		try {
			java.applet.AudioClip clip = java.applet.Applet
					.newAudioClip(new java.net.URL("file:applause.wav"));
			clip.play();
		} catch (java.net.MalformedURLException ex) {
		}
	}

	public int getPoints() {
		return playerPoints;
	}
}