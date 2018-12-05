import java.io.BufferedReader;
import java.io.FileReader;

class InPut {

	private String[] names = {"temperature", "latticeLength", "J", "mcs", "therm", "skip", "H"};
	/**
	 *    * Temperature of the system expressed in reduced units.
	 *       */
	public double temperature = 0.9;

	/**
		*    * An integer value with the length of the side of the lattice square.
		*       */
	public int latticeLength = 40;

	/**
	*    * Number of different interaction parameters that correspond to the levels (numer of distances) 
	*       * at which the magnetization of the spins of the particles there located will be considered as 
	*          * able to interact which the one of the position under analysis. 
	*             */
	public int nJ = 2;

	/**
		*    * An array with as many interaction constants as levels of neighbors to 
		*       * consider, as indicated by the nJ attribute.
		*          */
	public double[] J = { 1.0 , 0.0 };

	/**
	*    * Number of "Montecarlo" sweeps to do, i.e. the number of iterations
	*       */
	public int mcs = 100000;

	/**
		*    * Number of iteration that will be considered as needed for the system to 
		*       * be in thermal equilibrium. Also called number of thermalization sweeps.
		*          * It is the number of sweeps to perform before starting taking samples.
		*             */
	public int therm = 25000;

	/**
	*    * This number minus one corresponds to the number of Montecarlo 
	*       * sweeps to skip from sample to sample. The simulation will take 
	*          * one sample every this number of  sweeps.
	*             */
	public int skip = 1000;

	/**
	*    * This is the external magnetic field that will affect the lattice. It is 
	*       * unidimentional since it is considered to be in the direction of the spins analyzed.
	*          */
	public double H = 0.0;

	public InPut() {
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
		
		int variable;
		for (variable = 0; variable < names.length(); variable++){
			if (nameVar.equalsIgnoreCase(names)) break; 
		}	
		
		switch(variable) {
			case 0:
				this.temperature = Double.parseDouble(valueVar);
				break;
				
			case 1:
				this.latticeLength = Integer.parseInt(valueVar);
				break;
				
		//  case "nJ":
		//  	this.nJ = Integer.parseInt("valueVar");
		//  	return true;
		//  	break;
							
			case 2:
				this.J[0] = Integer.parseInt(valueVar);
				int nJ = (int) J[0];
				for(int k = 1; k < J.length; k++) {
					this.J[k] = Double.parseDouble(valueVar);
				}
				break;
				
			case 3:
				this.mcs = Integer.parseInt(valueVar);
				break;
				
			case 4:
				this.therm = Integer.parseInt(valueVar);
				break;
				
			case 5:
				this.therm = Integer.parseInt(valueVar);
				break;
				
			case 6:
				this.H = Double.parseDouble(valueVar);
				break;
				
			default: return false;	
		}
		
		return true;
	  }
