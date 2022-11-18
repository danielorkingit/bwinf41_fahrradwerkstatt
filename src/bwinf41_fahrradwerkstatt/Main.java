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
		
		ArrayList<Auftrag> orders1 = new ArrayList<>();
		
		int index = 0;
				
		try {
			Scanner scanner = new Scanner(Paths.get("data.txt"));
			while (scanner.hasNextLine()) {
				orders1.add(new Auftrag(scanner.nextInt(), scanner.nextInt(), index));
				index += 1;
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		
		System.out.println("First Method: \n");
		System.out.println("Wartezeiten: ");
		FirstMethod firstMethod = new FirstMethod();
		firstMethod.work(orders1, 0, 0);
		
		index = 0;
		
		ArrayList<Auftrag> orders2 = new ArrayList<>();
		
		try {
			Scanner scanner = new Scanner(Paths.get("data.txt"));
			while (scanner.hasNextLine()) {
				orders2.add(new Auftrag(scanner.nextInt(), scanner.nextInt(), index));
				index += 1;
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		
		
		System.out.println("\nSecond Method: \n");
		System.out.println("Wartezeiten: ");
		SecondMethod secondMethod = new SecondMethod();
		secondMethod.work(orders2, 0, orders2.toArray(new Auftrag[orders2.size()]), 0, new ArrayList<Integer>());
	}

}