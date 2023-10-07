package pck;

import java.io.File;

import javax.swing.SwingUtilities;

public class App {
	
	private static final int SCREEN_WIDTH = 1440;
	private static final int SCREEN_HEIGHT = 810;

	public static void main(String[] args) {
		System.out.println("Directory: " + new File(".").getAbsolutePath());
		SwingUtilities.invokeLater(() -> {
			new MainFrame(SCREEN_WIDTH, SCREEN_HEIGHT);
		});

	}

}
