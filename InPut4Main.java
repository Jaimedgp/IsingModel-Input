import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.lang.NumberFormatException;

/**
 * Container of all the information needed to perform the simulation. It reads the data 
 * from the text file passed as parameter.
 * 
 * @author Jaime Díez González-Pardo
 * @author Pablo Lavín Pellón
 * @author Inés Sánchez de Movellán Sáiz
 * 
 */

public class InPut4Main {

/**
 * Array of strings in which each element is the name of one variable
 */
private String[] names = {"temperature", "latticeLength", "J", "mcs", "therm", "skip", "H", "nJ"};

/**
 * Temperature of the system expressed in reduced units.
 */       
public double temperature = 0.9;

/**
 * An integer value with the length of the side of the lattice square.
 */
public int latticeLength = 40;

/**
 * Number of different interaction parameters that correspond to the levels (number of distances) 
 * at which the magnetization of the spins of the particles there located will be considered as 
 * able to interact which the one of the position under analysis. 
 */
public int nJ = 2;

/**
 * An array with as many interaction constants as levels of neighbors to 
 * consider, as indicated by the nJ attribute.
 * 
 * This is the module of the interaction parameter of Heisenberg Hamiltonian H = sumij ( -J Si Sj)
 */
public double[] J = {1.0, 2.0};

/**
 * Number of "Montecarlo" sweeps to do, i.e. the number of iterations
 */
public int mcs = 100000;

/**
 * Number of iteration that will be considered as needed for the system to 
 * be in thermal equilibrium. Also called number of thermalization sweeps.
 * It is the number of sweeps to perform before starting taking samples.
 */
public int therm = 25000;

/**
 * This number minus one corresponds to the number of Montecarlo 
 * sweeps to skip from sample to sample. The simulation will take 
 * one sample every this number of sweeps.
 */
public int skip = 1000;

/**
 * This is the external magnetic field that will affect the lattice. It is 
 * unidimentional since it is considered to be in the direction of the spins analyzed.
 */
public double H = 0.0;


/**
 * Constructor of the class InPut
 */
public InPut4Main() {
 
}

  /**
   * This constructor creates an InputData object with the given parameters.
   * 
   * @param latticeSize is the number of spins that are in each of the sides of the 
   * square that will hold the magnetic surface to simulate
   * @param temperature the value of the temperature in reduced units
   * @param externalMagneticField the external magnetic field that will affect the surface
   * @param nJ the number of concentric levels of neighbors to consider
   * @param J a vector with as many interaction constants as levels of neighbors to consider
   * @param mcs number of montecarlo sweeps to do
   * @param therm the number of sweeps to perform before starting taking samples
   * @param skip number of sweeps to do in order to take one sample 
   */
  public  InPut4Main(int latticeLength, double temperature, double H, int nJ, double[] J, int mcs, int therm, int skip) {

  this.latticeLength = latticeLength;
  this.temperature = temperature;
  this.H = H;
  this.nJ = nJ;
  this.J = new double[nJ];
  this.J = J;
  this.mcs = mcs;
  this.therm = therm;
  this.skip = skip;
  }

/**
  * This method reads the string provided and add the values that can be 
  * understood from it into the object used to invoke it.
  * 
  * @param line a string to be analyzed, read and the values written in it stored
  * 
  * @returns boolean true if the value can be understood, false in other case
  */
public boolean parse (String line){  
 // It creates an array of two string, one for the name of the variable (temperature, 
 // latticeLength etc.) and the other one for the value of the this variable)
 //
 // For this version we suppose that every variable have the same units

 String lineArray[] = new String[2];
 // It separate the strings of the line by tabulator
 lineArray = line.split("\t");
 
 String nameVar = lineArray[0];
 String valueVar = lineArray[1];
 // String unitsVar = lineArray[2];
 
 int variable;
 for (variable = 0; variable < names.length; variable++){
  if (nameVar.equalsIgnoreCase(names[variable])) break; 
 } 
 
 // With switch-case loop, it assigns the value of the variable to the appropriate attribute
 
 // For this version we suppose that there are not errors in the input file
 switch(variable) {
  case 0:
   this.temperature = Double.parseDouble(valueVar);
   break;
   
  case 1:
   this.latticeLength = Integer.parseInt(valueVar);
   if(latticeLength < 0) return false;
   break;
   
  case 2:
   try {
    
    String[] valuesJ = new String[J.length];
    valuesJ = valueVar.split(" ");
    
    // The first value of array of interaction parameters is an integer which is the 
    // number of J, nJ
    //
    // For this version we suppose that the interactions are only for first neighbours
    this.nJ = (int) Double.parseDouble(valuesJ[0]);
    
    // create the new J array with the new dimension
    this.J = new double[nJ];
   
    // read all J values
    for(int k = 0; k < J.length; k++) {
     this.J[k] = Double.parseDouble(valuesJ[k+1]);
     if(J[k] < 0) return false;
     if(J[k-1] > J[k]) return false;
    }
    
   } catch (NumberFormatException nfe) {
    return false;
   }
   
   break;
   
  case 3:
   this.mcs = Integer.parseInt(valueVar);
   if(mcs < 0) return false;
   break;
   
  case 4:
   this.therm = Integer.parseInt(valueVar);
   if(therm < 0) return false;
   break;
   
  case 5:
   this.skip = Integer.parseInt(valueVar);
   if(skip < 0) return false;
   break;
   
  case 6:
   this.H = Double.parseDouble(valueVar);
   break;
   
  case 7:
   this.nJ = Integer.parseInt(valueVar);
   if(nJ < 0) return false;
   this.J = new double[nJ];
   
  default: return false; 
 }
 
 return true;
  }


/**
 * Fill the attributes of the InputData object with those read from the file given as 
 * argument.  It returns zero if it could read every data from the file, the number of
 * the line that it could not read if there is any, or a negative number if the file does not 
 * exist or could not be open for reading.
 * 
 * @param fileFullName a string with the complete path an name of a file to be read.
 * @return it returns zero if it could read every data from the file, the number of
 * the line that it could not read if there is any, or a negative number if the file does not 
 * exist or could not be open for reading.
 */
public int read(String fileFullName) {

 try { 
         // It uses the class BufferedReader to read the input data file
   FileReader file = new FileReader(fileFullName);
   BufferedReader fr = new BufferedReader(file);
   
   // It defines the line, a boolean which indicates the result of applied the parse 
   // method and the number of the line what is reading
   String line;
   boolean isCorrect;
   int numLine = 0;
   
   while ((line = fr.readLine()) != null) {
    numLine++;
    isCorrect = parse(line);
    // It returns the number of the line which has a mistake (in this case 
    // isCorrect = false)
    if (!isCorrect) return numLine;
   
   }
   
   
 } catch (FileNotFoundException e) {
  e.printStackTrace();
  return -1;
 } catch (IOException e) {
  e.printStackTrace();
  return -1;
 }
 return 0;
}


/**
 * It writes the values stored in the input data object into the file given as argument
 * 
 * @param fileFullName a string with the name of the file in which the data object will be printed
 */
public void write(String fileFullName) {
   
   try {
        // It uses the class BufferedWriter to write the values of the input data objects
        // in a file 
        BufferedWriter fw = new BufferedWriter(new FileWriter(fileFullName, true));
        
        fw.write("Temperature: " + temperature + "\n");
        fw.write("Lattice Length: " + latticeLength + "\n");
        fw.write("Number of interaction parameters: " + nJ + "\n");
        for(int k = 0; k < J.length; k++){
         fw.write("J["+k+"]: "+J[k] + "\n");
        }
      
        fw.write("MCS: " + mcs + "\n");
        fw.write("Therm: " + therm + "\n");
        fw.write("Skip: " + skip + "\n");
        fw.write("H: " + H + "\n");
        
        fw.close();
      
        } catch (FileNotFoundException e) {
         e.printStackTrace();
         System.out.println("The file has not been found");
        }catch (IOException e) {
         e.printStackTrace();
         System.out.println("The input/output operation has failed");
        }
    }
}