package SAM;

import generic.RoverThreadHandler;
import json.Constants;

import java.io.IOException;

import other.TestClientForSam;
import server.SAMServer;


public class SamMain {
public static void main(String[] args) {
		
		try {
			
			//Our module server
			SAMServer server12 = new SAMServer(Constants.PORT_FOURTEEN);
			Thread server_12 = RoverThreadHandler.getRoverThreadHandler().getNewThread(server12);
			server_12.start(); 
			
			//Testing client from which we are getting command to drill on the surface of the mars
			TestClientForSam testClient = new TestClientForSam(); 
			Thread test = RoverThreadHandler.getRoverThreadHandler().getNewThread(testClient);
			test.start();
			
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}