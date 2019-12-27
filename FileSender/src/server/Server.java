package server;

import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(1234);
			while(true) {
				Socket client = ss.accept();
				ClientThread ct = new ClientThread(client);
				if(ct.client.isConnected()) {
					ct.start();
				}
			
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void run() {
		
		
	}
	
	
}
