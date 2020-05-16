package FileManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class ImportGradeBoundaries {
	
	private static String[][] gradeBoundaries;
	
	public ImportGradeBoundaries(String filename) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("res/" + filename));
			
			String line = br.readLine();
			LinkedList<String> temp = new LinkedList<String>();
			
			//headings = line.split(",");
			int i = 0;
			while (line != null) {
				line = br.readLine();
				if(line != null) {
					i++;
					temp.add(line);
				}
			}
			
			gradeBoundaries = new String[i][2];
			i = 0;
			for (String s : temp) {
				gradeBoundaries[i] = s.split(",");
				i++;
			}
			
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static String[][] getGradeBoundaries() {
		return gradeBoundaries;
	}

	public void setGradeBoundaries(String[][] gradeBoundaries) {
		this.gradeBoundaries = gradeBoundaries;
	}
	
	
}
