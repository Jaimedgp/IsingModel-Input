import java.io.BufferReader;
import java.io.FileReader;

	public int read(String filename) {

		try {
				BufferedReader fw = new BufferdReader(new FileReade(filename))

				String line;
				boolean isCorrect;
				int numLine = 1;
				while ((line = fw.readline()) != null) {
					isCorrect = parse(line);
					if (!isCorrect) return numLine;
				}

				return 0;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

