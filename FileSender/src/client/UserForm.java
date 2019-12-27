package client;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.awt.event.ActionEvent;

public class UserForm extends JFrame implements Runnable{

	JFileChooser fileChooser;
	public UserForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(327, 413, 85, 21);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendFile();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnSend);
		
		fileChooser = new JFileChooser();
		fileChooser.setBounds(28, 10, 652, 393);
		contentPane.add(fileChooser);
	}
	
	@Override
	public void run() {
		try {

		}
		catch(Exception e)
		{
			
		}
		
	}
	
	public void sendFile() {
		if(!fileChooser.isFileSelectionEnabled()) {
			System.out.println("No File!");
		}
		try
		{
			File file = fileChooser.getSelectedFile();
			Socket s = new Socket("127.0.0.1",1234);
			
			byte [] bytes = new byte[(int)file.length()];
			bytes = Files.readAllBytes(file.toPath());
			OutputStream out = s.getOutputStream();
			FileData info = new FileData((int)file.length(),file.getName(),bytes);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(info);
			oos.close();
			System.out.println("File "+file.getName()+" send");
			out.close();
			s.close();
			
		}
		catch(Exception e) 
		{
			
		}
		
	}
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserForm frame = new UserForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
}
