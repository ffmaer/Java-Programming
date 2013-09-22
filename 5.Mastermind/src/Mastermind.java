import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Mastermind extends JFrame{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public Mastermind(){
		
		setLayout(new FlowLayout());
		
		final Table table = new Table();
		add(table);
		
		ImageIcon jbtFace = new ImageIcon("peg.gif");
		
		JButton jbt1 = new JButton(null,jbtFace);
		JButton jbt2 = new JButton(null,jbtFace);
		JButton jbt3 = new JButton(null,jbtFace);
		JButton jbt4 = new JButton(null,jbtFace);
		JButton jbt5 = new JButton("Enter!");	
		
		add(jbt1);
		add(jbt2);
		add(jbt3);
		add(jbt4);
		add(jbt5);
		
		jbt1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		  	bloop4();
		  	table.setColor(0);
		}
		});
		jbt2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		  	bloop4();
		  	table.setColor(1);
		}
		});
		jbt3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		  	bloop4();
		  	table.setColor(2);
		}
		});
		jbt4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		  	bloop4();
		  	table.setColor(3);
		}
		});
		
		jbt5.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			bloop4();
			table.enter();
		}
		});
	}
	private void bloop4(){
		try {
		    java.applet.AudioClip clip = 
		    java.applet.Applet.newAudioClip(new java.net.URL("file:bloop4.wav"));
		    clip.play();
		}catch (java.net.MalformedURLException ex){}
	}
}