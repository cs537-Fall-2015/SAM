package callback;

import java.net.UnknownHostException;

public class CallBack {
	
	
	public CallBack() {
		
	}
	
	public void done() {
		CallBackClient client = null;
		try {
			client = new CallBackClient(9014, null);
			} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		client.run();
	}
}