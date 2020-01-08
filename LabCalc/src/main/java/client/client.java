package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

import java.util.List;


public class client {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			Socket s = new Socket("127.0.0.1", 1122);
			System.out.println("Client connected to socket.");
			
			while(true) {
				InputStream is = s.getInputStream();
				System.out.println("Input action");
				String action = br.readLine();
				System.out.println("Input data");
				String data = br.readLine();
				String input = action+":"+data;
				OutputStream outs = s.getOutputStream();
				PrintWriter writer = new PrintWriter(outs, true);
				writer.println(input);

				if(action.equals("get_log")) {		
					ObjectInputStream ois = new ObjectInputStream(is);
					List<String> logs = (List<String>)ois.readObject();
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
