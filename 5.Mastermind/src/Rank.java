/*******************************************************************************
 *
 * Group Project
 *
 * Name ordered by the initial of the last names
 *
 * Code by Tengchao Zhou
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
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.FlowLayout;

public class Rank extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Rank() {

		setLayout(new FlowLayout());

		final JTextArea jtaDisplay = new JTextArea("");
		JButton jbt1 = new JButton("Sort First Name");
		JButton jbt2 = new JButton("Sort Last Name");
		JButton jbt3 = new JButton("Sort User Name");
		JButton jbt4 = new JButton("Sort Score");

		add(jbt1);
		add(jbt2);
		add(jbt3);
		add(jbt4);
		add(jtaDisplay);
		jtaDisplay.setText(showScore());

		jbt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtaDisplay.setText(sortFirst());
			}
		});
		jbt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtaDisplay.setText(sortLast());
			}
		});
		jbt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtaDisplay.setText(sortUser());
			}
		});
		jbt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtaDisplay.setText(showScore());
			}
		});
	}

	public static String sortFirst() {
		return sortName(1);
	}

	public static String sortUser() {
		return sortName(2);
	}

	public static String sortLast() {
		return sortName(3);
	}

	public static String sortName(int index) {
		List<PlayerName> ts1 = new ArrayList<PlayerName>();
		FileStringReader input = new FileStringReader("rank.txt");
		String line = new String();
		for (int i = 0; i < 10; i++) {
			line = input.readLine();
			int score = Integer.parseInt(line.split(":")[0]);
			String username = line.split(":")[1];
			String firstname = line.split(":")[2];
			String lastname = line.split(":")[3];
			ts1.add(new PlayerName(score, username, firstname, lastname, index));
		}
		Collections.sort(ts1);
		Iterator<PlayerName> itr = ts1.iterator();
		String output = "firstname  lastname  username  score\n";
		while (itr.hasNext()) {
			Object element = itr.next();
			output += (element + "\n");

		}
		return output;
	}

	public static void update(String name, int score) {
		List<String> listScore = new ArrayList<String>();
		List<String> listNewScore = new ArrayList<String>();
		List<String> listName = new ArrayList<String>();
		FileStringReader input = new FileStringReader("rank.txt");
		String line = new String();
		for (int i = 0; i < 10; i++) {
			line = input.readLine();
			listScore.add(line.split(":")[0]);
			listNewScore.add(line.split(":")[0]);
			listName.add(line.split(":")[1]);
		}
		System.out.println(listScore);
		for (String x : listScore) {
			if (Integer.parseInt(x) < score) {
				listNewScore.add(listScore.indexOf(x), Integer.toString(score));
				listNewScore.remove(listNewScore.size() - 1);
				listName.add(listScore.indexOf(x), name);
				listName.remove(listName.size() - 1);
				score = 0;
			}
		}

		try {
			PrintWriter output = new PrintWriter("rank.txt");
			for (int i = 0; i < 10; i++) {
				output.println(listNewScore.get(i) + ":" + listName.get(i));
			}
			output.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File does not exist");
		}
	}

	public static String showScore() {
		FileStringReader input = new FileStringReader("rank.txt");
		String line = new String();
		String output = "High Score Rankings:\nRank Score Name\n";
		for (int i = 0; i < 10; i++) {
			line = input.readLine();
			output += ("No." + Integer.toString(i + 1) + "  " + line + "\n");
		}
		return output;

	}
}
