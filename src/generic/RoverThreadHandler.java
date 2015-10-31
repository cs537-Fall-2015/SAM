package generic;

import module2.ModuleTwoClient;
import module2.ModuleTwoServer;
import other.TestClientForSam;
import server.SAMServer;

public class RoverThreadHandler<ModuleOneServer, ModuleOneClient> {
	
	private static RoverThreadHandler roverThreadHandler;	
	
	private RoverThreadHandler(){
		
	}
	
	public static RoverThreadHandler getRoverThreadHandler(){
		
		if(roverThreadHandler == null){
			roverThreadHandler = new RoverThreadHandler();
		}
		
		return roverThreadHandler;		
	}
	
	public Thread getNewThread(ModuleTwoClient clientOne){		
		return RoverThreadFactory.getRoverThreadFactory().newThread(clientOne);
	}

	public Thread getNewThread1(ModuleOneClient clientOne) {
		// TODO Auto-generated method stub
		return null;
	}

	public Thread getNewThread(ModuleOneServer serverOne) {
		// TODO Auto-generated method stub
		return null;
	}

	public Thread getNewThread(ModuleTwoServer serverTwo) {
		// TODO Auto-generated method stub
		return null;
	}

	public Thread getNewThread1(ModuleTwoClient clientTwo) {
		// TODO Auto-generated method stub
		return null;
	}

	public Thread getNewThread(TestClientForSam testClient) {
		// TODO Auto-generated method stub
		return null;
	}

	public Thread getNewThread(SAMServer server12) {
		// TODO Auto-generated method stub
		return null;
	}
}