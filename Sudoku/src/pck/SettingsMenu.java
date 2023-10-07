package pck;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingsMenu extends JPanel {

	private static final long serialVersionUID = -1890339414083499141L;
	
	private static final int BUTTON_WIDTH = 200, BUTTON_HEIGHT = 50;
	
	private JButton backButton;
	
	public SettingsMenu(){
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		initButtons();
	}
	
	private void initButtons() {
		backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		backButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		
		add(Box.createVerticalGlue());
		
		add(backButton, BorderLayout.CENTER);
		
		add(Box.createVerticalGlue());
	}
	
	public void setBackButtonListener(ActionListener listener) {
		backButton.addActionListener(listener);
	}

}
