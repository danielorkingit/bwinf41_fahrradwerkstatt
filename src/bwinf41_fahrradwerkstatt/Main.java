package bwinf41_fahrradwerkstatt;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) throws IOException {
		
		// Pro Tag 480 Minuten arbeit
		
		// WarteZeit Prinzip 1
		
		// Liste mit: Dauer Aufträge der davor + Dauer Auftrag selber
		
		ArrayList<Auftrag> orders1 = new ArrayList<>();
		
		try {
			Scanner scanner = new Scanner(Paths.get(new java.io.File(".").getCanonicalPath() + "/data.txt"));
			while (scanner.hasNextLine()) {
				orders1.add(new Auftrag(scanner.nextInt(), scanner.nextInt()));
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println("Keine Datei gefunden.");
			Scanner scanner = new Scanner(Paths.get("data.txt"));
			while (scanner.hasNextLine()) {
				Auftrag tmp = new Auftrag(scanner.nextInt(), scanner.nextInt());
				tmp.dringlich = scanner.nextBoolean();
				orders1.add(tmp);
			}
			scanner.close();
			System.out.println("\nDritte Methode: \n");
			ThirdMethod thirdMethod = new ThirdMethod();
			thirdMethod.work(orders1, 0, orders1.toArray(new Auftrag[orders1.size()]), 0, new ArrayList<Integer>());
			return;
			
		}
		
		System.out.println("Erste Methode: \n");
		FirstMethod firstMethod = new FirstMethod();
		firstMethod.work(orders1, 0, 0, new ArrayList<Integer>());
		
		System.out.println("\nZweite Methode: \n");
		SecondMethod secondMethod = new SecondMethod();
		secondMethod.work(orders1, 0, orders1.toArray(new Auftrag[orders1.size()]), 0, new ArrayList<Integer>());
		
		orders1.clear();
		
		System.out.println("\nGeben Sie Boolean-Werte ein, um die Dritte Methode zu testen.");
		
	}

}