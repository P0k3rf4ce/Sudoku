package pck;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {

	private static final long serialVersionUID = -1158889307921392039L;
	
	private String levelTitle;
	private String[] grid;
	
	public Game() {
		super();
		
		setLayout(new BorderLayout());
		
		JLabel sike = new JLabel("Sike! You thought");
		
		sike.setHorizontalAlignment(JLabel.CENTER);
		
		add(sike);
	}
	
	public void loadGame(String filePath) { 
		File f = new File(filePath);
		FileReader fr;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String line;
			levelTitle = br.readLine().trim();
			br.readLine();
			
			while (!(line = br.readLine().trim()).equals("")) {
				
			}

		}
		catch (FileNotFoundException e) {
			System.out.printf("File \"%s\" was not found\n", filePath);
		}
		catch (IOException e) {
			System.out.printf("Could not read the file \"%s\"\n", filePath);
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.printf("Could not close the file \"%s\"\\n", filePath);
				}
			}
		}
	}

}
