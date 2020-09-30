import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class StyledJButton extends JButton{
		StyledJButton(String string){
			super(string);
	        this.setContentAreaFilled(false);
	        this.setForeground(Color.GREEN);
	        this.setBorder(BorderFactory.createCompoundBorder(
	                BorderFactory.createLineBorder(Color.GREEN, 1),
	                BorderFactory.createLineBorder(Color.BLACK, 5)));
		}
	}