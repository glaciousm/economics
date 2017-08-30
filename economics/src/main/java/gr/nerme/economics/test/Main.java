package gr.nerme.economics.test;

import java.io.IOException;
import java.util.Date;

import gr.nerme.economics.db.ReadAndWrite;

public class Main {
	
	public static void main(String[] args) {
		try {
			ReadAndWrite.saveToFile("2017_06", "super market", (new Date()).toString(),12.8);
			ReadAndWrite.readFromFile("2017_06");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
