package SAM.server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;

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
	File datafile;

	public SAMServer(int port) throws IOException {
		super(port);
		this.filePath = "7.json";
		this.datafile = new File("data.txt");
		this.sam = new SAM(datafile);

	}

	@Override
	public void run() {

		try {
			while (true) {

				System.out.println("SAM Server: Waiting for client request");

				// read the JSON file
				readJson();

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
					System.out
		            .println("=================================================");
					sam.pyrolysis();

				} else if (message.equals("S-DER")) {
					System.out.println("initiating derivatization");
					System.out
		            .println("=================================================");
					sam.derivatization();

					message = "SAM Data:" + sam.getData();

				} else if (message.equals("S-CMB")) {
					System.out.println("initiating combustion");
					System.out
		            .println("=================================================");
					sam.combustion();

				} else if (message.equals("A-DIR")) {
					System.out.println("initiating direct atmospheric measurement");
					System.out
		            .println("=================================================");
					sam.atmosphericMeasurement();

				} else if (message.equals("A-ENR")) {
					System.out.println("initiating atmospheric enrichment");
					System.out
		            .println("=================================================");
					sam.atmosphericEnrichment();

				} else if (message.equals("A-MET")) {
					System.out.println("initiating methane enrichment");
					System.out
		            .println("=================================================");
					sam.methaneEnrichment();

				} else if (message.equals("A-NGE")) {
					System.out.println("initiating noble gas enrichment");
					System.out
		            .println("=================================================");
					sam.nobleGasEnrichment();

				} else if (message.equals("CAL-GAS")) {
					System.out.println("initiating in situ gas calibration");
					System.out
		            .println("=================================================");
					sam.inSituGasCalibration();

				} else if (message.equals("CAL-SOL")) {
					System.out.println("initiating solid sample in situ gas calibration");
					System.out
		            .println("=================================================");

				} else if (message.equals("SAM_ON")) {
					sam.turnOn();
					System.out
		            .println("=================================================");

				} else if (message.equals("SAM_OFF")) {
					sam.turnOff();
					System.out
		            .println("=================================================");

				} else if (message.equals("SAM_GET_POWER")) {

					message = "SAM power consumption is: " + sam.getPowerConsumption();
				}

				writeJson();

				// write object to Socket
				outputToAnotherObject.writeObject("SAM Server response - " + message);

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
		JSONObject obj = greader.getJSONObject();
		sam.setData(obj);

	}

}
