package SAM.server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import SAM.SamModule.SAM;
import generic.RoverServerRunnable;

public class SAMServer extends RoverServerRunnable {
	SAM sam;
	String filePath;

	public SAMServer(int port) throws IOException {
		super(port);
		this.filePath = "7.json";
		sam = new SAM();
	}

	@Override
	public void run() {

		try {
			while (true) {

				System.out.println("SAM Server: Waiting for client request");

				

				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();

				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(
						getRoverServerSocket().getSocket().getInputStream());

				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("SAM Server: Message Received from Client - " + message.toUpperCase());

				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(
						getRoverServerSocket().getSocket().getOutputStream());

				if (message.equals("S-PYR")) {
					System.out.println("initiating pyrolysis with GCMS");
					sam.pyrolysis();

				} else if (message.equals("S-DER")) {
					System.out.println("initiating derivatization");
					sam.derivatization();

				} else if (message.equals("S-CMB")) {
					System.out.println("initiating combustion");
					sam.combustion();

				} else if (message.equals("A-DIR")) {
					System.out.println("initiating direct atmospheric measurement");
					sam.atmosphericMeasurement();

				} else if (message.equals("A-ENR")) {
					System.out.println("initiating atmospheric enrichment");
					sam.atmosphericEnrichment();

				} else if (message.equals("A-MET")) {
					System.out.println("initiating methane enrichment experiment");
					sam.methaneEnrichment();

				} else if (message.equals("A-NGE")) {
					System.out.println("initiating noble gas enrichment");
					sam.nobleGasEnrichment();

				} else if (message.equals("CAL-GAS")) {
					System.out.println("initiating in situ gas calibration");
					sam.inSituGasCalibration();

				} else if (message.equals("CAL-SOL")) {
					System.out.println("initiating solid sample in situ gas calibration");
				
				} else if (message.equals("SAM_ON")) {
					sam.turnOn();

				} else if (message.equals("SAM_OFF")) {
					sam.turnOff();

				} else if (message.equals("SAM_GET_POWER")) {

					message = "SAM power consumption is: " + sam.getPowerConsumption();
				}

				writeJson();

				// write object to Socket
				//outputToAnotherObject.writeObject("SAM Server response - " + message);

				// close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();

				// getRoverServerSocket().closeSocket();
				// terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
				}
			System.out.println("Server: Shutting down Socket server 1!!");
			// close the ServerSocket object
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

	void writeJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		new MyWriter(sam, 7);
	}

	@SuppressWarnings("unchecked")
	void readJson() {
		GlobalReader greader = new GlobalReader(7);
		//JSONObject obj = greader.getJSONObject();
		//sam.setData(obj);

	}

}
