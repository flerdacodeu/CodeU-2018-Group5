import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grid {

	private List<String[]> content;

	public Grid(String filename) {
		try {
			this.content = Grid.parseFile(filename);
		} catch (IOException | BadGridFileException e) {
			e.printStackTrace();
		}
	}

	/**
	 * builds a grid filled with Characters out of a file
	 * @param filename - the path to a file
	 * @return - the grid built from the file
	 * @throws IOException - error reading the file
	 * @throws BadGridFileException - the file was malformed (lines of different width)
	 */
	public static List<String[]> parseFile(String filename) throws IOException, BadGridFileException {

		List<String[]> finalGrid = new ArrayList<String[]>();

		try {
			/* open the file */
			File f = new File(filename);
			BufferedReader b = new BufferedReader(new FileReader(f));

			/* the line length must be the same for all lines */
			String line =  b.readLine();
			if (line == null) throw new FileNotFoundException();
			String[] arrayedLine = line.split(" ");
			int gridWidth = arrayedLine.length;
			
			while ((line = b.readLine()) != null) {
				arrayedLine = line.split(" ");
				int currentWidth = arrayedLine.length;
				if (currentWidth != gridWidth) throw new BadGridFileException("line width:"+currentWidth+", but must be: "+gridWidth);
				finalGrid.add(arrayedLine);
			}
			
			b.close();
			return finalGrid;

		} catch (IOException e) {
			e.printStackTrace();
			return finalGrid;
		}

	}
}