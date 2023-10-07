package pck;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

public class LevelsPanel extends JPanel {

	private static final long serialVersionUID = 2507730009707110871L;
	
	private static class InvalidPackInfoFormatting extends Exception {
		private static final long serialVersionUID = 4541822634919612013L;
	}
	
	private static final String PACK_INFO = "/pack_info.ref";
	
	private String PACKS_FOLDER_PATH = "src/appdata";
	
	private JList<String> levelsList;
	
	private JLabel noSelectionLabel;
	
	private String oldPackFolder;
	
	private String[] levelNames;
	private String[] levelFileNames;
	
	private ListSelectionListener listener;
	
	public LevelsPanel(String packFolder) {
		super();
		
		oldPackFolder = "";
		
		setLayout(new BorderLayout());
		
		PACKS_FOLDER_PATH += packFolder;
		
		noSelectionLabel = new JLabel("Please Select a Pack");
		
		noSelectionLabel.setHorizontalAlignment(JLabel.CENTER);
		
		add(noSelectionLabel, BorderLayout.CENTER);
	}
	
	public void setListListener(ListSelectionListener listener) {
		this.listener = listener;
	}
	
	public String getLevelFileNameByIndex(int index) {
		return levelNames[index];
	}
	
	public void unloadList() {
		removeAll();
		add(noSelectionLabel, BorderLayout.CENTER);
		revalidate();
	}
	
	public void loadList(String packFolder) {
		if (oldPackFolder.equals(packFolder)) return;
		
		oldPackFolder = packFolder;
		
		removeAll();
		
		File f = new File(PACKS_FOLDER_PATH + packFolder + PACK_INFO);
		FileReader fr;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String line;
			
			ArrayList<String> names = new ArrayList<>();
			ArrayList<String> paths = new ArrayList<>();
			
			while ((line = br.readLine()) != null) {
				String[] split = line.split(",");
				if (split.length != 2) throw new InvalidPackInfoFormatting();
				names.add(split[0].trim());
				paths.add(split[1].trim());
			}
			
			levelNames = new String[names.size()];
			levelFileNames = new String[paths.size()];
			
			levelNames = names.toArray(levelNames);
			levelFileNames = paths.toArray(levelFileNames);
			
			levelsList = new JList<>(levelNames);
			levelsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			levelsList.addListSelectionListener(listener);
			
			JScrollPane scrollPanel = new JScrollPane(levelsList);
			
			add(scrollPanel, BorderLayout.CENTER);
			
			revalidate();
		}
		catch (FileNotFoundException e) {
			System.out.printf("File \"%s\" was not found\n", PACKS_FOLDER_PATH);
		}
		catch (IOException e) {
			System.out.printf("Could not read the file \"%s\"\n", PACKS_FOLDER_PATH);
		}
		catch (InvalidPackInfoFormatting e) {
			System.out.printf("Invalid \"%s\" line format. There must be 2 comma separated fields per line, no new lines\n", PACKS_FOLDER_PATH);
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.printf("Could not close the file \"%s\"\\n", PACKS_FOLDER_PATH);
				}
			}
		}
	}

}
