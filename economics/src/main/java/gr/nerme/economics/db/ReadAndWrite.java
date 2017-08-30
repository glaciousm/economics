package gr.nerme.economics.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadAndWrite {

	public static void saveToFile(String title, String category, String date, double amount) throws IOException {
		String context = "~" + category + "," + date + "," + amount;
		try (FileWriter fw = new FileWriter("src/main/resources/tables/" + title + ".txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(context);
		} catch (IOException e) {
		}
	}

	public static List<String> readFromFile(String title) throws IOException {
		List<String> list = new ArrayList<>();

		File file = new File("src/main/resources/tables/" + title + ".txt");
		Scanner reader = new Scanner(file);
		reader.useDelimiter("~");
		while (reader.hasNext()) {
			String s = reader.next();
			list.add(s);
		}
		reader.close();
		
		return list;
	}
	
	public static String [][] trimString(List<String> list){
		String [][] array2;
		
		array2 = new String[list.size()][];
		for (int j = 0; j < list.size(); j++) {
			array2[j]= list.get(j).split(",");
		}
		return array2;
	}
	
	public static List<String> getYears(){
		List<String> years = new ArrayList<>();
		File file = new File("src/main/resources/tables/years.txt");
		Scanner reader = null;
		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader.useDelimiter("~");
		while (reader.hasNext()) {
			String s = reader.next();
			if (!s.equals("")) {
			}
			years.add(s);
		}
		reader.close();
		return years;
	}
	
	public static List<String> getCategories(){
		List<String> categories = new ArrayList<>();
		File file = new File("src/main/resources/tables/categories.txt");
		Scanner reader = null;
		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader.useDelimiter("~");
		while (reader.hasNext()) {
			String s = reader.next();
			if (!s.equals("")) {
			}
			categories.add(s);
		}
		reader.close();
		return categories;
	}


}
