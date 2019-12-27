package calcserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClientThread extends Thread{
	Socket client;
	String session;
	ClientThread(Socket sock) throws IOException{
		client = sock;
	}
	public void run() {
		while(true) {
			try {
				client.setSoTimeout(20000);
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String s = "";
				if ((s = br.readLine()) != null) {
					System.out.println("ready");
					System.out.println(s);
					String[] message = s.split(":");
					session = message[0];
					
					OutputStream out = client.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(out);
					String result = "";
					if(message[1].equals("get_log")) {
						
						List<String> logs = new ArrayList<String>();
						FileReader fr = new FileReader(session+"_log.txt");
						Scanner sc = new Scanner(fr);
						while(sc.hasNextLine()) {
							logs.add(sc.nextLine());
						}
						sc.close();
						fr.close();
						oos.writeObject(logs);
					}
					
					else 
						
					{
						Date date = new Date();
						FileWriter fw = new FileWriter(session+"_log.txt", true);
						BufferedWriter bf = new BufferedWriter(fw);
						result = new Calculator().Calc(message[1]);
						
						bf.write(date + " - Message:" + s +"\n");
						bf.write(date + " - Result:" + result +"\n");
						bf.flush();
						fw.close();
						out.write(result.getBytes());
						System.out.println(result);
					}
					
				}
				if(client.isClosed()) {
					System.out.println("Client disconnect!");
				}
				
			}
			
			catch (Exception e){
				System.out.println("Connection error:" + e.getMessage());
				try {
					Date date = new Date();
					FileWriter fw = new FileWriter(session+"log.txt", true);
					BufferedWriter bf = new BufferedWriter(fw);
					bf.write(date + " - Fail to connect:" + e.getMessage()+"\n");
					
					bf.flush();
					fw.close();
				}
				catch(IOException e1) {
					System.out.println(e1.getMessage());
				}
				
				this.stop();
			}
		}
	}
}
