package SAM.SamModule;

import java.io.File;
import java.util.HashMap;

import org.json.simple.JSONObject;

// SAM instrument suite

public class SAM {
	
    private final static boolean ON = true;
    private final static boolean OFF = false;
    private boolean state;
    File sample;
    
    public SAM(File sample) {
        state = OFF;
        this.sample = sample;
    }
    
    // Turning on the SAM instrument suite for experiment
    public void turnOn(){
        state = ON;
        System.out.println("SAM Module: SAM is turning on");
    } 
    
    // turning off the instrument suite
    public void turnOff(){
        state = OFF;
        System.out.println("SAM Module: SAM is turning off");
    } 
    public boolean isOn(){
        return state;
    }
    
    public void pyrolysis() {
		//Seq #4
		//get data from dummy data files
		//analyse data
		//show in graph
	}
  
 	public void derivatization(){
 		//Seq #2
 		//get data from data files
		//analyse data
		//show in graph
    }
    
    public void combustion() {
    	//Seq #8
    	//get data from data files
		//analyse data
		//show in graph
    }
    
    public void atmosphericMeasurement() {
    	//Seq #4
    	//get data from data files
		//analyse data
		//show in graph
    }
    
    public void atmosphericEnrichment() {
    	//Seq #5
    	//get data from data files
		//analyse data
		//show in graph
    }
    
    public void methaneEnrichment() {
    	//Seq #6
    	//get data from dummy data files
		//analyse data
		//show in graph
    }
    
    public void nobleGasEnrichment() {
    	//Seq #6
    	//get data from dummy data files
		//analyse data
		//show in graph
    }
    
    public void inSituGasCalibration() {
    	//Seq #9
    	//get data from dummy data files
		//analyse data
		//show in graph
    }
    
    public void solidSampleInSituCalibration() {
    	//Seq #3
    	//get data from dummy data files
		//analyse data
		//show in graph
    }

	public void setData(JSONObject obj) {
		// TODO Auto-generated method stub
		
	}

	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPowerConsumption() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
