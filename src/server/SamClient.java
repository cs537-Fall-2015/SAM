package server;

import generic.RoverClientRunnable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;

public class SAMClient extends RoverClientRunnable{

	public SAMClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
		// TODO Auto-generated constructor stub
	}
 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
