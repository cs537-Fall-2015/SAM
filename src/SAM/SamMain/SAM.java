package SAM.SamMain;

import java.util.HashMap;

// SAM Model

public class SAM {
    private double SAM_ON;
    private double SAM_OFF;
    private double SAM_OPEN;
    private double SAM_CLOSE;
    private double SAM_CLEAN;
    private double SAM_RELEASE;
    
    private final static boolean ON = true;
    private final static boolean OFF = false;
    
    private boolean state;
    
    private HashMap<Long, Double> data = new HashMap<Long, Double>();
    
    
    public void turnOff(){
        state = OFF;
        System.out.println("SAM Module: SAM is turning off");
    }
    
    public SAM() {
        state = OFF;
    }
    
    // Change state
    public void turnOn(){
        state = ON;
        System.out.println("SAM Module: SAM is turning on");
    }
    
    public boolean isOn(){
        return state;
    }
    
    
    public double getSAM_ON() {
        return SAM_ON;
    }
    
    public void setSAM_ON(double sAM_ON) {
        SAM_ON = sAM_ON;
    }
    
    public double getSAM_OFF() {
        return SAM_OFF;
    }
    
    public void setSAM_OFF(double sAM_OFF) {
        SAM_OFF = sAM_OFF;
    }
    
    public double getSAM_OPEN() {
        return SAM_OPEN;
    }
    
    public void setSAM_OPEN(double sAM_OPEN) {
        SAM_OPEN = sAM_OPEN;
    }
    
    public double getSAM_CLOSE() {
        return SAM_CLOSE;
    }
    
    public void setSAM_CLOSE(double sAM_CLOSE) {
        SAM_CLOSE = sAM_CLOSE;
    }
    
    public double getSAM_CLEAN() {
        return SAM_CLEAN;
    }
    
    public void setSAM_CLEAN(double sAM_CLEAN) {
        SAM_CLEAN = sAM_CLEAN;
    }
    
    public double getSAM_RELEASE() {
        return SAM_RELEASE;
    }
    
    public void setSAM_RELEASE(double sAM_RELEASE) {
        SAM_RELEASE = sAM_RELEASE;
    }
    
    public void setPosition_set(boolean b) {
        // TODO Auto-generated method stub
        
    }
    
    public void setFilter_status(int i) {
        // TODO Auto-generated method stub
        
    }
    
    public int getFilter_status() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    public void setSample_exist(boolean b) {
        // TODO Auto-generated method stub
        
    }
    
    public void setCleaning_completed(boolean b) {
        // TODO Auto-generated method stub
        
    }
    
    public int getSpare_bits() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    public void setSpare_bits(int i) {
        // TODO Auto-generated method stub
        
    }

	public HashMap<Long, Double> getData() {
		return data;
	}

	public void setData(HashMap<Long, Double> data) {
		this.data = data;
	}
    
    
}
