package pck;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;

public class PlayMenu extends JPanel {

	private static final long serialVersionUID = -6928481603603225067L;
	
	private static final int BUTTON_WIDTH = 200, BUTTON_HEIGHT = 50;
	private static final int V_GAP = 100;
	
	private JPanel topPanel;
	private JButton backButton;
	
	private PacksPanel packsPanel;
	private LevelsPanel levelsPanel;
	private PreviewPanel previewPanel;
	
	public PlayMenu(){
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		
		packsPanel = new PacksPanel();
		levelsPanel = new LevelsPanel(packsPanel.getPacksFolder());
		previewPanel = new PreviewPanel();
		
		setPacksListListener();
		setLevelsListListener();
		
		packsPanel.setBorder(BorderFactory.createTitledBorder("Packs"));
		levelsPanel.setBorder(BorderFactory.createTitledBorder("Levels"));
		previewPanel.setBorder(BorderFactory.createTitledBorder("Preview"));
		
		topPanel.add(packsPanel);
		topPanel.add(levelsPanel);
		topPanel.add(previewPanel);
		
		insertVerticalGap(V_GAP);
		add(topPanel);
		insertVerticalGap(V_GAP);
		initBackButton();
		add(backButton);
		insertVerticalGap(V_GAP);
	}
	
	public void setPlayButtonListener(ActionListener listener) {
		previewPanel.setPlayButtonListener(listener);
	}
	
	private void setPacksListListener() {
		packsPanel.setListListener((ListSelectionEvent e) -> {
			@SuppressWarnings("unchecked")
			JList<String> jList = (JList<String>)e.getSource();
			
			int index = jList.getSelectedIndex();
			
			if (index == -1) levelsPanel.unloadList();
			else levelsPanel.loadList(packsPanel.getPackFolderByIndex(index));
		});
	}
	
	private void setLevelsListListener() {
		levelsPanel.setListListener((ListSelectionEvent e) -> {
			@SuppressWarnings("unchecked")
			JList<String> jList = (JList<String>)e.getSource();
			
			int index = jList.getSelectedIndex();
			
			if (index == -1) previewPanel.unloadPreview();
			else previewPanel.loadPreview(levelsPanel.getLevelFileNameByIndex(index));
		});
	}
	
	private void initBackButton() {
		backButton = new JButton("Back to Main Menu");
		backButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		backButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		backButton.setAlignmentX(CENTER_ALIGNMENT);
	}
	
	private void insertVerticalGap(int v_gap) {
		add(Box.createRigidArea(new Dimension(BUTTON_WIDTH, v_gap)));
	}
	
	public void setBackButtonListener(ActionListener listener) {
		backButton.addActionListener(listener);
	}

}
