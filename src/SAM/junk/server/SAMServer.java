package SAM.junk.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;
import SAM.junk.other.SamController;

import org.json.simple.JSONObject;


import callback.CallBack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import SAM.SamMain.SAM;
import generic.RoverServerRunnable;

public class SAMServer extends RoverServerRunnable{

	public SAMServer(int port) throws IOException {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		SAM sam = new SAM();
		CallBack cb = new CallBack();
		
		String objectToClean = "FILTER_ROCK"; 
		
		try {
			while (true) {
				System.out.println("");
				System.out.println("SAM Server: Waiting for client request");
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("");
				System.out.println("SAM Server: Message Received from Client - "+ message.toUpperCase());
				
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				
				if (message.equalsIgnoreCase("exit")){
					break;
					}
				
				//One of event which are going to performed by our instrument
				//In order to perform this event we are going to execute couple of 
				//commands in order to complete one task
				
				//All commands are written in PadsController.java class
				
				else if(message.equalsIgnoreCase("FILTER_STATUS")) {
					SamController controller = new SamController();
					sam= controller.action("SA_FILTER_STATUS");
					String jsonString = gson.toJson(sam); 
					outputToAnotherObject.writeObject(jsonString);
					
					Thread.sleep(3000); 
											
				}else if(message.equalsIgnoreCase("PRT_STATUS")) {
					SamController controller = new SamController();
					sam = controller.action("SAM_PRT_STATUS");
					String jsonString = gson.toJson(sam); 
					outputToAnotherObject.writeObject(jsonString);
					
					Thread.sleep(3000); 
											
				}
				else if(message.equalsIgnoreCase("FILTER_START")) {
					
					SamController controller = new SamController();
					controller.action("SAM_SET_POSITION");
					Thread.sleep(5000);
					controller.action("SAM_FILTER_START");
					Thread.sleep(5000);
					sam = controller.action("SAM_FILTER_STOP");
					
					MyWriter JSONWriter = new MyWriter(sam, Constants.ONE); 
					String jsonString = gson.toJson(sam); 
					outputToAnotherObject.writeObject(jsonString);
					
					Thread.sleep(5000); 
											
				}
				else if(message.equalsIgnoreCase("PRT_START")) {
					SamController controller = new SamController();
					Thread.sleep(3000);
					controller.action("SAM_PRT_SET_MODE");
					Thread.sleep(5000);
					controller.action("SAM_PRT_START");
					Thread.sleep(5000);
					sam = controller.action("SAM_PRT_STOP");
					
					String jsonString = gson.toJson(sam); 
					outputToAnotherObject.writeObject(jsonString);
					Thread.sleep(5000);
					        
				}else if(message.equalsIgnoreCase("BITS_STUCK")) {
					SamController controller = new SamController();
					Thread.sleep(3000);
					controller.action("SAM_DIS_ENGAGE_BITS");
					Thread.sleep(5000);
					sam= controller.action("SAM_LOAD_BITS");
					String jsonString = gson.toJson(sam); 
					outputToAnotherObject.writeObject(jsonString);
					
				}
				
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
			}
			cb.done();
			System.out.println("Server: Shutting down Socket server 1!!");
			closeAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception error) {
			System.out.println("Server: Error:" + error.getMessage());
		} 
	}

}