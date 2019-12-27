package server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import client.FileData;

public class ClientThread extends Thread{
	Socket client;
	String session;
	ClientThread(Socket sock) throws IOException{
		client = sock;
	}
	
	public void run() {
		
		while(true) {
			try {
				InputStream is = client.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
                FileData info = (FileData)ois.readObject();
                if(new File("ServerFiles/").mkdirs()==false)
                	Files.createDirectories(Paths.get("ServerFiles/"));
                File dir = new File("ServerFiles/");
                File[] find = dir.listFiles((dir1,name)->name.contains(info.name));
                if(find.length != 0) {
                	Date date = new Date();
                	SimpleDateFormat formatForDateNow = new SimpleDateFormat("EyyyyMMddhhmm");
                	String dateFormat = formatForDateNow.format(date);
                	info.name = dateFormat+info.name;
                }
                FileOutputStream fOut = new FileOutputStream("ServerFiles/"+info.name);
                fOut.write(info.file);
                fOut.close();

                if(client.isClosed()) {
					System.out.println("Client disconnect!");
				}
            } 
			catch(EOFException e) {

			}
			
			catch (Exception e) {
				System.out.println("error:"+e.getMessage());
				System.out.println(e);
                this.stop();
            }
			
		}
	}
}
