package pck;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PreviewPanel extends JPanel {

	private static final long serialVersionUID = -6186519328853417112L;
	
	private static final int BUTTON_WIDTH = 200, BUTTON_HEIGHT = 50;
	
	private JLabel noSelectionLabel;
	
	private JButton playButton;
	
	public PreviewPanel() {
		super();
		
		setLayout(new BorderLayout());
		
		noSelectionLabel = new JLabel("Please Select a Level");
		
		noSelectionLabel.setHorizontalAlignment(JLabel.CENTER);
		
		add(noSelectionLabel, BorderLayout.CENTER);
		
		initPlayButton();
	}
	
	public void unloadPreview() {
		removeAll();
		add(noSelectionLabel, BorderLayout.CENTER);
		add(playButton, BorderLayout.PAGE_END);
		playButton.setEnabled(false);
		revalidate();
	}
	
	public void loadPreview(String fileName) {
		removeAll();
		
		JLabel label = new JLabel("Could not load the preview");
		label.setHorizontalAlignment(JLabel.CENTER);
		
		JScrollPane scrollPanel = new JScrollPane(label);
		
		add(scrollPanel, BorderLayout.CENTER);
		add(playButton, BorderLayout.PAGE_END);
		
		playButton.setEnabled(true);
		
		revalidate();
	}
	
	public void setPlayButtonListener(ActionListener listener) {
		playButton.addActionListener(listener);
	}
	
	private void initPlayButton() {
		playButton = new JButton("Play");
		playButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		playButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		playButton.setAlignmentX(CENTER_ALIGNMENT);
		
		playButton.setEnabled(false);
		
		add(playButton, BorderLayout.PAGE_END);
	}

}
