import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Input inputData = new Input();

		String fileName = "inputData.txt";
		
		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));
	
			fw.write("latticeLength"+"\t"+"40"+"\n");
			fw.write("temperature"+"\t"+"0.9"+"\t"+"K"+"\n");
			fw.write("therm"+"\t"+"25000"+"\n");
			fw.write("H"+"\t"+"0.0"+"\t"+"A/m"+"\n");
			fw.write("skip"+"\t"+"1000"+"\n");
			fw.write("mcs"+"\t"+"100000"+"\n");
			fw.write("J"+"\t"+"3.0 4.0 5.0 6.0"+"\t"+ "J" +"\n");
			fw.close();
		} catch (FileNotFoundException e) {
	   		   e.printStackTrace();
	   		   System.out.println("The file has not been found");
	   	}catch (IOException e) {
	   	   e.printStackTrace();
	   	   System.out.println("The input/output operation has failed");
	   	}

		int numline = inputData.read(fileName);

		inputData.write("outPutData.txt");
	}

}
