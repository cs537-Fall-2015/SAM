package main;

import generic.RoverThreadHandler;

import java.io.IOException;

import server.SamClient;
import server.SamServer;

public class SamMain {

	public static void main(String[] args) {

		// Each module has its own port
		int port_one = 9003;

		try {

			// create a thread for module one
			SamServer serverOne = new SamServer(port_one);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler()
					.getNewThread(serverOne);

			// server begins listening
			server_1.start();

			// client one server sending messages to server
			SamClient clientOne = new SamClient(port_one, null); // notice
																	// port_two
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler()
					.getNewThread(clientOne);

			// start the thread which communicates through sockets
			client_1.start();
			System.out.println("Client process initiated");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
