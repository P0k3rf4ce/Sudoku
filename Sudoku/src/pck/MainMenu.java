package pck;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
	
	private static final long serialVersionUID = 1067713944250728247L;
	
	private static final int BUTTON_WIDTH = 200, BUTTON_HEIGHT = 50;
	
	private static final int V_GAP = 5;
	
	private JButton continueButton, playButton, settingsButton, quitButton;
	
	public MainMenu(){
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		initButtons();
	}
	
	private void initButtons() {
		
		continueButton = new JButton("Continue");
		continueButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		continueButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		continueButton.setAlignmentX(CENTER_ALIGNMENT);
		
		playButton = new JButton("Play");
		playButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		playButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		playButton.setAlignmentX(CENTER_ALIGNMENT);
		
		settingsButton = new JButton("Settings");
		settingsButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		settingsButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		settingsButton.setAlignmentX(CENTER_ALIGNMENT);
		
		quitButton = new JButton("Quit");
		quitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		quitButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		quitButton.setAlignmentX(CENTER_ALIGNMENT);
		
		
		if (!loadSavedGame()) continueButton.setEnabled(false);
		
		add(Box.createVerticalGlue());
		
		add(continueButton);
		insertVerticalGap();
		add(playButton);
		insertVerticalGap();
		add(settingsButton);
		insertVerticalGap();
		add(quitButton);
		
		
		add(Box.createVerticalGlue());
	}
	
	private boolean loadSavedGame() {
		return false;
	}
	
	private void insertVerticalGap() {
		add(Box.createRigidArea(new Dimension(BUTTON_WIDTH, V_GAP)));
	}
	
	public void setSettingsButtonListener(ActionListener listener) {
		settingsButton.addActionListener(listener);
	}
	
	public void setQuitButtonListener(ActionListener listener) {
		quitButton.addActionListener(listener);
	}
	
	public void setPlayButtonListener(ActionListener listener) {
		playButton.addActionListener(listener);
	}
	
	public void setContinueButtonListener(ActionListener listener) {
		continueButton.addActionListener(listener);
	}

}
