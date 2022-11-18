package bwinf41_fahrradwerkstatt;

import java.util.ArrayList;
import java.util.Collections;

public class SecondMethod {
		
		int time = 0;
		Time dayTime = new Time();
		
		public SecondMethod () {
			dayTime.startFirstDay();
		}
		
		public void work(ArrayList<Auftrag> blueOrders, int remainingTime, Auftrag[] orders, int index, ArrayList<Integer> warteZeit){
			try {
				
				if (!blueOrders.isEmpty()) {
				
					ArrayList<Auftrag> possibleOrders = calculatePOrders(blueOrders, dayTime.endOfDay);
					if (possibleOrders.isEmpty() && index == 0) {
						dayTime.nextDay();
						work(blueOrders, 0, orders, 0, warteZeit);
					} 
					
					if (remainingTime == 0) index = possibleOrders.get(0).indexInAL;
									
					int tmpStart = blueOrders.get(index).getStart(); // ändern
					int tmpDuration = blueOrders.get(index).getDuration(); // ändern
					
					if (tmpStart < dayTime.endOfDay &&  (dayTime.currentTime + tmpDuration - remainingTime) < dayTime.endOfDay) { // Auftrag kann an einem Tag gemacht werden
						if (dayTime.currentTime < tmpStart) dayTime.currentTime = tmpStart+tmpDuration-remainingTime; // Zeitpunkt beim Abschließen des Aufrags
						else dayTime.currentTime = dayTime.currentTime+tmpDuration-remainingTime; // Zeitpunkt beim Abschließen des Aufrags
						warteZeit.add(dayTime.currentTime-tmpStart);
						blueOrders.remove(index);
						work(blueOrders, 0, orders, 0, warteZeit);
					
					} else if (tmpStart < dayTime.endOfDay){ // Auftrag braucht länger als einen Tag
						if (remainingTime == 0 && dayTime.currentTime != (dayTime.endOfDay-480)) remainingTime = (dayTime.endOfDay-dayTime.currentTime);
						else if (remainingTime == 0 && tmpStart > dayTime.endOfDay-480) remainingTime = dayTime.endOfDay-tmpStart;
						else remainingTime = remainingTime + 480;
						dayTime.nextDay();
						work(blueOrders, remainingTime, orders, index, warteZeit);
						
					} else {
						dayTime.nextDay();
						work(blueOrders, 0, orders, 0, warteZeit);
					}
				} else {
					System.out.println("\nDurchschnittliche Wartezeit: " + calculateAverage(warteZeit, orders.length) + " Minuten \n");
					System.out.println("Höchste Wartezeit: " + calculateHighest(warteZeit) + " Minuten");
				}
			} catch (Exception e) {
				return;
			}
		}

		private ArrayList<Auftrag> calculatePOrders(ArrayList<Auftrag> orders, int currentTime) {
			ArrayList<Auftrag> result = new ArrayList<>();
			int index = 0;
			for (Auftrag order : orders) {
				if (order.startTime < currentTime) {
					order.indexInAL = index;
					result.add(order);
				}
				index += 1;
			}
			result = sortInput(result);
			return result;
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

		public ArrayList<Auftrag> sortInput(ArrayList<Auftrag> orders) {
			Collections.sort(orders, Auftrag.StuDuration);
			return orders;
		};
		

}
