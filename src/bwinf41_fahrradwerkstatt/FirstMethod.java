package bwinf41_fahrradwerkstatt;

import java.util.ArrayList;

public class FirstMethod {
	
	Time dayTime = new Time();
	
	public FirstMethod () {
		dayTime.startFirstDay();
	}
	
	public void work(ArrayList<Auftrag> orders, int index, int remainingTime, ArrayList<Integer> warteZeit){

		if (index != orders.size()) {
			
			int tmpStart = orders.get(index).getStart();
			int tmpDuration = orders.get(index).getDuration();
			
			if (tmpStart < dayTime.endOfDay &&  (dayTime.currentTime + tmpDuration - remainingTime) < dayTime.endOfDay && tmpStart <= dayTime.currentTime) { 
				// Auftrag kann an einem Tag gemacht werden
				dayTime.currentTime = dayTime.currentTime+tmpDuration-remainingTime; // Zeitpunkt beim Abschließen des Aufrags
				warteZeit.add(dayTime.currentTime-tmpStart);
				work(orders, index+1, 0, warteZeit);
			
			}else if (tmpStart < dayTime.endOfDay){ // Auftrag braucht länger als einen Tag
				// Mitten am Tag
				if (remainingTime == 0 && dayTime.currentTime != (dayTime.endOfDay-480)) remainingTime = (dayTime.endOfDay-dayTime.currentTime);
				// Auftrag kommt mitten am Tag rein
				else if (remainingTime == 0 && tmpStart > dayTime.endOfDay-480) remainingTime = dayTime.endOfDay-tmpStart;
				// Während der Auftrag erledigt wird -> mehr als ein Tag Arbeit
				else remainingTime = remainingTime + 480;
				dayTime.nextDay();
				work(orders, index, remainingTime, warteZeit);
				
			} else {
				dayTime.nextDay();
				work(orders, index, 0, warteZeit);
			}
		} else {
			System.out.println("\nDurchschnittliche Wartezeit: " + calculateAverage(warteZeit, orders.size()) + " Minuten \n");
			System.out.println("Höchste Wartezeit: " + calculateHighest(warteZeit) + " Minuten");
		}
			
	}

	private int calculateHighest(ArrayList<Integer> warteZeit) {
		int highestWarteZeit = 0;
		for (int order : warteZeit) {
			if (order > highestWarteZeit) highestWarteZeit = order;
		}
		return highestWarteZeit;
	}

	private int calculateAverage(ArrayList<Integer> warteZeit, int total) {
		int totalWarteZeit = 0;
		for (int order : warteZeit) {
			totalWarteZeit += order;
		}
		return totalWarteZeit/total;
	}
	
}
