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

public class PacksPanel extends JPanel {
	
	private static class InvalidPacksInfoFormatting extends Exception {
		private static final long serialVersionUID = 4541822634919612013L;
	}

	private static final long serialVersionUID = 1586657283072813858L;
	
	private static final String PACKS_INFO_PATH = "src/appdata/packs_info.ref";

	private String packsFolder;
	private String[] packNames;
	private String[] packFolderPaths;
	
	private JList<String> packList;
	
	public PacksPanel() {
		super();
		readPacksInfo();
		
		setLayout(new BorderLayout());
		
		if (packNames == null) {
			add(new JLabel("Could not load packs. Read the console"), BorderLayout.CENTER);
			return;
		}
		
		packList = new JList<>(packNames);
		packList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPanel = new JScrollPane(packList);
		
		add(scrollPanel, BorderLayout.CENTER);
	}
	
	public void setListListener(ListSelectionListener listener) {
		packList.addListSelectionListener(listener);
	}
	
	public String getPacksFolder() {
		return packsFolder;
	}
	
	public String getPackFolderByIndex(int index) {
		return packFolderPaths[index];
	}
	
	private void readPacksInfo() {
		File f = new File(PACKS_INFO_PATH);
		FileReader fr;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String line;
			packsFolder = br.readLine();
			
			ArrayList<String> names = new ArrayList<>();
			ArrayList<String> paths = new ArrayList<>();
			
			while ((line = br.readLine()) != null) {
				String[] split = line.split(",");
				if (split.length != 2) throw new InvalidPacksInfoFormatting();
				names.add(split[0].trim());
				paths.add(split[1].trim());
			}
			
			packNames = new String[names.size()];
			packFolderPaths = new String[paths.size()];
			
			packNames = names.toArray(packNames);
			packFolderPaths = paths.toArray(packFolderPaths);
		}
		catch (FileNotFoundException e) {
			System.out.printf("File \"%s\" was not found\n", PACKS_INFO_PATH);
		}
		catch (IOException e) {
			System.out.printf("Could not read the file \"%s\"\n", PACKS_INFO_PATH);
		}
		catch (InvalidPacksInfoFormatting e) {
			System.out.printf("Invalid \"%s\" line format. There must be 2 comma separated fields per line, no new lines\n", PACKS_INFO_PATH);
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.printf("Could not close the file \"%s\"\\n", PACKS_INFO_PATH);
				}
			}
		}
	}

}
