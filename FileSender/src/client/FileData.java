package client;

import java.io.Serializable;

public class FileData implements Serializable {
	public int size;
	public String name;
	public byte[] file;
	public FileData(int s, String n, byte[] f) {
		size = s;
		name = n;
		file = f;
	}
}
