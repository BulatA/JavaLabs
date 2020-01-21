package calcserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientThread extends Thread{
	Socket client;
	String session = "user";
	ClientThread(Socket sock) throws IOException{
		client = sock;
	}
	public void run() {
		while(true) {
			try {
				client.setSoTimeout(60000);
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String s = "";
				OutputStream out = client.getOutputStream();
				PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF8"), true);
				if ((s = br.readLine()) != null) {
					System.out.println(s);
					
					try {
						s.split(":");
					} catch (Exception e) {
						String err = "Error format of message! Use : between command and data";
						out.write(err.getBytes());
						continue;
					}
					String[] message = s.split(":");

					
					String result = "";
					
					if(message[0].equals("get_log")) {
						System.out.println(session);
						List<String> logs = new ArrayList<String>();
						String log_data = "";
						FileReader fr = new FileReader(session+"_log.txt");
						Scanner sc = new Scanner(fr);
						while(sc.hasNextLine()) {
							logs.add(sc.nextLine());
						}
						if(Integer.parseInt(message[1]) >= logs.size()) {
							for (String log : logs) {
								log_data += log+"\n";
							}
							log_data += "end";
							out.write(log_data.getBytes());
						}
						else {
							int start = logs.size()-Integer.parseInt(message[1]);
							int end = logs.size()-1;
							List<String> subLogs = logs.stream().skip(start).collect(Collectors.toList());
							for (String log : subLogs) {
								log_data += log+"\n";
							}
							log_data += "end"+"\n";
							System.out.println(log_data);
							out.write(log_data.getBytes());
						}
						sc.close();
						fr.close();
						
					}
					
					else if (message[0].equals("login")) {
						session = message[1];
						String ans = "Welcome, "+session+"!";
						writer.println(ans);
					}
					
					else if (message[0].equals("calc"))	
					{
						Date date = new Date();
						FileWriter fw = new FileWriter(session+"_log.txt", true);
						BufferedWriter bf = new BufferedWriter(fw);
						result = new Calculator().Calc(message[1]);
						
						bf.write(date + " - Message:" + s +"\n");
						bf.write(date + " - Result:" + result +"\n");
						bf.flush();
						fw.close();
						writer.println(result);
					}
					
					else {
						String err = "Wrong command:"+message[0];
						writer.println(err);
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
					FileWriter fw = new FileWriter(session+"_log.txt", true);
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
