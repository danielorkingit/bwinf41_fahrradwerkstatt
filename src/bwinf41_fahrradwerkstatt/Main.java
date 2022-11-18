package bwinf41_fahrradwerkstatt;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
		
		// Pro Tag 480 Minuten arbeit
		
		// WarteZeit Prinzip 1
		
		// Liste mit: Dauer Aufträge der davor + Dauer Auftrag selber
		
		ArrayList<Auftrag> orders1 = new ArrayList<>();
						
		try {
			Scanner scanner = new Scanner(Paths.get("data.txt"));
			while (scanner.hasNextLine()) {
				orders1.add(new Auftrag(scanner.nextInt(), scanner.nextInt()));
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		
		System.out.println("Erste Methode: \n");
		FirstMethod firstMethod = new FirstMethod();
		firstMethod.work(orders1, 0, 0, new ArrayList<Integer>());
		
		System.out.println("\nZweite Methode: \n");
		SecondMethod secondMethod = new SecondMethod();
		secondMethod.work(orders1, 0, orders1.toArray(new Auftrag[orders1.size()]), 0, new ArrayList<Integer>());
	}

}