package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class client {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String session = br.readLine();
			Socket s = new Socket("127.0.0.1", 1919);
			System.out.println("Client connected to socket.");
			
			while(true) {
				InputStream is = s.getInputStream(); 
				String data = br.readLine();
				String input = session+":"+data;
				OutputStream outs = s.getOutputStream();
				PrintWriter writer = new PrintWriter(outs, true);
				writer.println(input);
				String line = "";
				ObjectInputStream ois = new ObjectInputStream(is);
				if(data.equals("get_log")) {
					
					List<String> logs = (List<String>)ois.readObject();
					System.out.println(logs.size());
					logs.forEach(x -> System.out.println(x));
				}
				else {
					BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
					System.out.println(br1.readLine());
				}
				
			}
			
		}
		catch(Exception e){
			
		}
		
	}
}
