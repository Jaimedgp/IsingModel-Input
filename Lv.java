package r2ms.inputData.modLv;

import r2ms.common.String;

import java.io.BufferedReader;
import java.io.FileReader;

class Lv extends r2ms.common.InputData {
	
	
	
//Parse
	

	/**
	  * This method reads the string provided and add the values that can be 
	  * understood from it into the object used to invoke it.
	  * 
	  * @param line a string to be analyzed, read and the balues written in it stored
	  * 
	  * @returns boolean true if the value can be understood, false ion other case
	  */
	
	public boolean parse (String line){
		
		String lineArray[] = new String[2];
		lineArray = line.split("\t");
		
		String nameVar = lineArray[0];
		String valueVar = lineArray[1];
		// String unitsVar = lineArray[2];
		
		
		switch(nameVar) {
			case "temperature":
				this.temperature = Double.parseDouble(valueVar);
				break;
				
			case "latticeLength":
				this.latticeLength = Integer.parseInt(valueVar);
				break;
				
		//	case "nJ":
			//	this.nJ = Integer.parseInt("valueVar");
			//	return true;
			//	break;
							
			case "J":
				this.J[0] = Integer.parseInt(valueVar);
				int nJ = (int) J[0];
				for(int k = 1; k < J.length; k++) {
					this.J[k] = Double.parseDouble(valueVar);
				}
				break;
				
			case "mcs":
				this.mcs = Integer.parseInt(valueVar);
				break;
				
			case "therm":
				this.therm = Integer.parseInt(valueVar);
				break;
				
			case "skip":
				this.therm = Integer.parseInt(valueVar);
				break;
				
			case "H":
				this.H = Double.parseDouble(valueVar);
				break;
				
			default: return false;	
		}
		
		return true;
	  }
	
	
	  /**
	   * Fill the attributes of the InputData object with those read from the file given as 
	   * argument.  It returns the number of values that could be read from the file, zero 
	   * if no data could be taken from the file, or a negative number if the file does not 
	   * exist or could not be open for reading.
	   * 
	   * @param fileFullName a string with th complete path an name of a file to be read.
	   * @return it returns the number of values that could be read from the file, zero if no 
	   * data could be taken from the file or a negative number if there is no file or t is not 
	   * possible to open it.
	   */
	
		public int read(String filename) {
	
			try {
					BufferedReader fr = new BufferdReader(new FileReade(filename))
	
					String line;
					boolean isCorrect;
					int numLine = 0;
					
					while ((line = fr.readline()) != null) {
						numLine++;
						
						isCorrect = parse(line);
						// It returns the number of the line which has a mistake (in this case 
						// isCorrect = false)
						if (!isCorrect) return numLine;
						
					}
					return 0;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	

	  /**
	   * It writes the values stored in the input data object into the file given as argument
	   * 
	   * @param fileFullName a string with the name of the file in which the data object will be printed
	   */
	  public void write(String fileFullName) {
	  }
	  

	}






