package pck;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 5900576310524351760L;
	private JPanel cards;
	private Game game;
	private Menu menu;
	
	public MainFrame(int width, int height){
		super("Sudoku");
		
		cards = new JPanel();
		menu = new Menu();
		game = new Game();
		
		cards.setLayout(new CardLayout());
		
		cards.add("MENU", menu);
		cards.add("GAME", game);
		
		add(cards, BorderLayout.CENTER);
		
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		setQuitListeners();
		setPlayListeners();
	}
	
	private void setQuitListeners() {
		menu.setQuitButtonListener((ActionEvent e) -> {
			this.dispose();
			System.exit(0);
		});
	}
	
	private void setPlayListeners() {
		menu.setPlayButtonListener((ActionEvent e) -> {
			CardLayout cl = (CardLayout) cards.getLayout();
			cl.show(cards, "GAME");
		});
	}

}
