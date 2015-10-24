package other;

import SAM.SAM;

public class SamController{
	
	SAM sam = new SAM();
	public SamController() {
		super();
	}
	
	public void setPosition() {
		// TODO Auto-generated method stub
		System.out.println("");
		sam.setPosition_set(true); 
		System.out.println("SAM_SET_POSITION: Setting up filter position..");  
	}
	public void startFilter() {
		// TODO Auto-generated method stub
	sam.setFilter_status(1); 
		System.out.println("SAM_FILTER_START: Filter has been started.."); 
		
	}
	public void getStatus() {
		// TODO Auto-generated method stub
		if(sam.getFilter_status()==1){
			System.out.println("Filter is currently functioning..");
		}else {
			System.out.println("Filter is sleeping..");
		}
		 
	}
	public void stopFilter() {
		// TODO Auto-generated method stub
		System.out.println("SAM_FILTER_STOP: Filter has been stoped.."); 
		sam.setFilter_status(0); 
		sam.setSample_exist(true); 
		sam.setPosition_set(false); 
	}
	public void disEngageBits() {
		// TODO Auto-generated method stub
		
			System.out.println("DisEngaging Filter bits.."); 
			
	}
	public void loadBits() {
		// TODO Auto-generated method stub
		if(sam.getSpare_bits()!=0){
			System.out.println("Loading Filter bits.."); 
			sam.setSpare_bits(sam.getSpare_bits()-1);
		}else{
			System.out.println("No spare bits in bit box.."); 

		}
	}
	
	public void replaceBits() {
		// TODO Auto-generated method stub
		
		if(sam.getSpare_bits() != 0){
			System.out.println("Replacing bits.."); 
			
		}else{
			System.out.println("No spare bits in bit box.."); 
		}
	}
	public void setPrtMode(String objectToClean) {
		// TODO Auto-generated method stub
		System.out.println(""); 
		
		if(objectToClean.equals("surface")){
			
			System.out.println("sam_PRT_SET_MODE: Setting up to clean mars surface.."); 
		
		}else{
			
			System.out.println("sam_PRT_SET_MODE: Setting up to clean observation tray on the rover.."); 
		
		}
		
		
	}
	public void startDrt() {
		// TODO Auto-generated method stub
		System.out.println("sam_PRT_START: PRT has been started.."); 
		sam.setFilter_status(1);
	}
	public void getDrtStatus() {
		// TODO Auto-generated method stub
		if(sam.getFilter_status()==1){
			System.out.println("PRT is currently functioning..");
		}else {
			System.out.println("PRT is sleeping..");
		}
	}
	public void stopPrt() {
		// TODO Auto-generated method stub
		System.out.println("sam_PRT_STOP: PRT has been stopped.."); 
		sam.setCleaning_completed(true);  
		sam.setFilter_status(0);
	}
 
	public SAM action(String command) { 
		// TODO Auto-generated method stub
		
	 if(command.equals("sam_FILTERL_STATUS")){ //Is it currently running??

			 this.getStatus();
			
		}else if(command.equals("sam_PRT_STATUS")){	//Is it currently running??
			
			 this.getPrtStatus();
			
		}else if(command.equals("sam_SET_POSITION")){		//set position on target object for filtering
			
			this.setPosition();
			
		}else if(command.equals("sam_FILTER_START")){	// Start filtering on target 
			
			 this.startFilter();
			
		}else if(command.equals("sam_FILTER_STOP")){	//Stop filtering
			
			 this.stopFilter();
			
		}else if(command.equals("sam_PRT_SET_MODE")){		//Decide what to clean
			
			
			//Dirt Removal Tool is used to clean two object
			//1. surface = mars surface 
			//2. tray = observation tray of contact instrument(APXS and Mahli)
			String objectToClean = "surface";
			
			this.setPrtMode(objectToClean);
			
		}else if(command.equals("sam_PRT_START")){		//Start Dirt Removal Tool (PRT)
			
			this.stopPrt();
			
		}else if(command.equals("sam_PRT_STOP")){	// Stop Dirt Removal Tool (PRT)
			
			this.stopPrt();
			
		}else if(command.equals("sam_REPLACE_BITS")){	// Replace bit in case of bit worn out
			
			this.replaceBits();
			
		}else if(command.equals("sam_DIS_ENGAGE_BITS")){	// DisEngage bit in case of bit got stuck in stone
			
			this.disEngageBits();
			
		}else if(command.equals("sam_LOAD_BITS")){	// Load new bit from bit box
			
			this.loadBits();
			
		}
	return sam; 
		
	}

	private void getPrtStatus() {
		// TODO Auto-generated method stub
		
	}

}