package pck;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Menu extends JPanel {
	
	private static final String MAIN_MENU = "MAIN_MENU";
	private static final String SETTINGS_MENU = "SETTINGS_MENU";
	private static final String PLAY_MENU = "PLAY_MENU";

	private static final long serialVersionUID = 87770876702331379L;
	
	private CardLayout cards;
	
	private MainMenu mainMenu;
	private SettingsMenu settingsMenu;
	private PlayMenu playMenu;
	
	public Menu(){
		super();
		
		mainMenu = new MainMenu();
		settingsMenu = new SettingsMenu();
		playMenu = new PlayMenu();
		
		cards = new CardLayout();
		
		setLayout(cards);
		
		add(MAIN_MENU, mainMenu);
		setMainMenuListeners();
		
		add(SETTINGS_MENU, settingsMenu);
		setSettingsMenuListeners();
		
		add(PLAY_MENU, playMenu);
		setPlayMenuListeners();
		
	}
	
	public void setQuitButtonListener(ActionListener listener) {
		mainMenu.setQuitButtonListener(listener);
	}
	
	public void setPlayButtonListener(ActionListener listener) {
		playMenu.setPlayButtonListener(listener);
	}
	
	private void setMainMenuListeners() {
		mainMenu.setSettingsButtonListener((ActionEvent e) -> {
			cards.show(Menu.this, SETTINGS_MENU);
		});
		mainMenu.setPlayButtonListener((ActionEvent e) -> {
			cards.show(Menu.this, PLAY_MENU);
		});
	}
	
	private void setSettingsMenuListeners() {
		settingsMenu.setBackButtonListener((ActionEvent e) -> {
			cards.show(this, MAIN_MENU);
		});
	}
	
	private void setPlayMenuListeners() {
		playMenu.setBackButtonListener((ActionEvent e) -> {
			cards.show(this, MAIN_MENU);
		});
	}
 
}
