
package SAM.SamTestMain;

import java.io.IOException;

import generic.RoverThreadHandler;
import SAM.server.SamClient;
import SAM.server.SamServer;
import json.Constants;

public class samTestMain {
    public static void main(String[] args) {
        int port_one = Constants.SAM_PORT;
        
        try {
            // create a thread for module one
            SamServer serverOne = new SamServer(port_one);
            Thread sam_server = RoverThreadHandler.getRoverThreadHandler()
            .getNewThread(serverOne);
            
            // server begins listening
            sam_server.start();
            // client one server sending messages to server
            SamClient clientOne = new SamClient(port_one, null); // notice
            
            // start the thread which communicates through sockets
            Thread sam_client = RoverThreadHandler.getRoverThreadHandler()
            .getNewThread(clientOne);
            
            // start the thread which communicates through sockets
            sam_client.start();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
