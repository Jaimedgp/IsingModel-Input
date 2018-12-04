
package r2ms.inputData.modLv;

class Lv extends r2ms.common.InputData {
	
	
	
//Parse
	
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
	}
