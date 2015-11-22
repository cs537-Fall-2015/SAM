package SAM.SamTestMain;

import generic.RoverClientRunnable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;

public class SAMClient extends RoverClientRunnable{
    
    public SAMClient(int port, InetAddress host) throws UnknownHostException {
        super(port, host);
    }
    
    public String readCommand() throws IOException, FileNotFoundException {
    	File file = new File("commands.txt");
    	FileInputStream fis = new FileInputStream(file);
    	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
    	String line = null;
    	String cmd = null;
    	while ((line = br.readLine()) != null) {
    		cmd = line + "" ;
    		sendMessage(cmd);
    	}
    	br.close();
    	return cmd;
    	
    }
    
    @Override
    public void run() {
       
    	try {
			readCommand();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
        try {
            closeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sendMessage(String msg){
        try {
            ObjectOutputStream outputToAnotherObject = null;
            ObjectInputStream inputFromAnotherObject = null;
            Thread.sleep(1000);
            
            // write to socket using ObjectOutputStream
            outputToAnotherObject = new ObjectOutputStream(getRoverSocket()
                                                           .getNewSocket().getOutputStream());
            
            System.out
            .println("=================================================");
            System.out
            .println("SAM Testing Framework: SAM Client: Sending request to Socket Server");
            System.out
            .println("=================================================");
            
            outputToAnotherObject.writeObject(msg);
            
            // read the server response message
            inputFromAnotherObject = new ObjectInputStream(getRoverSocket()
                                                           .getSocket().getInputStream());
            String message = (String) inputFromAnotherObject.readObject();
           // System.out.println("SAM Testing Framework received: "
                  //             + message.toUpperCase());
            
            // close resources
            inputFromAnotherObject.close();
            outputToAnotherObject.close();
            Thread.sleep(300);
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception error) {
            //System.out.println("Testing Framework: Error:" + error.getMessage());
        }
        
    }
    
}
