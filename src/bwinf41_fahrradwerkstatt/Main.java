package bwinf41_fahrradwerkstatt;

import java.io.IOException;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Iterator;


public class Main {
	
	public static void main(String[] args) {
		
		// Pro Tag 480 Minuten arbeit
		
		// WarteZeit Prinzip 1
		
		// Liste mit: Dauer Aufträge der davor + Dauer Auftrag selber
		
		ArrayList<Auftrag> orders = new ArrayList<>();
		
		try {
			Scanner scanner = new Scanner(Paths.get("data.txt"));
			while (scanner.hasNextLine()) {
				orders.add(new Auftrag(scanner.nextInt(), scanner.nextInt()));
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		System.out.println("First Method: \n");
		FirstMethod firstMethod = new FirstMethod();
		firstMethod.work(orders, 0, 0);
		System.out.println("\nSecond Method: \n");
		SecondMethod secondMethod = new SecondMethod();
		secondMethod.work(orders, 0, 0);
		
	}

}