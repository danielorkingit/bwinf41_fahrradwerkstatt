package bwinf41_fahrradwerkstatt;

import java.util.ArrayList;
import java.util.Collections;

public class SecondMethod {
		
		int time = 0;
		Time dayTime = new Time();
		
		public SecondMethod () {
			dayTime.startFirstDay();
		}
		
		public void work(ArrayList<Auftrag> orders, int index, int remainingTime){

			if (index != orders.size()) {
				
				int tmpStart = orders.get(index).getStart(); // ändern
				int tmpDuration = orders.get(index).getDuration(); // ändern
				
				if (tmpStart < dayTime.endOfDay &&  (dayTime.currentTime + tmpDuration - remainingTime) < dayTime.endOfDay) { // Auftrag kann an einem Tag gemacht werden
					dayTime.currentTime = dayTime.currentTime+tmpDuration-remainingTime; // Zeitpunkt beim Abschließen des Aufrags
					Auftrag tmp = orders.get(index);
					tmp.setData((dayTime.currentTime-tmpStart), dayTime.currentTime);
					orders.set(index, tmp);
					work(orders, index+1, 0);
				
				} else if (tmpStart < dayTime.endOfDay){ // Auftrag braucht länger als einen Tag
					if (remainingTime == 0 && dayTime.currentTime < tmpStart) remainingTime = remainingTime + (dayTime.endOfDay-tmpStart);
					else if (remainingTime == 0) remainingTime = dayTime.endOfDay - dayTime.currentTime;
					else remainingTime = remainingTime + 480;
					dayTime.nextDay();
					work(orders, index, remainingTime);
					
				} else {
					dayTime.nextDay();
					work(orders, index, 0);
				}
			} else {
				System.out.println("average: " + calculateAverage(orders));
				System.out.println("highest: " + calculateHighest(orders));
			}
				
		}

		private int calculateHighest(ArrayList<Auftrag> orders) {
			int highestWarteZeit = 0;
			for (Auftrag order : orders) {
				if (order.warteZeit > highestWarteZeit) highestWarteZeit = order.warteZeit;
			}
			return highestWarteZeit;
		}

		private int calculateAverage(ArrayList<Auftrag> orders) {
			int totalWarteZeit = 0;
			for (Auftrag order : orders) {
				totalWarteZeit = totalWarteZeit + order.warteZeit;
			}
			return totalWarteZeit/orders.size();
		}

		public ArrayList<Auftrag> sortInput(ArrayList<Auftrag> orders) {
			Collections.sort(orders, Auftrag.StuDuration);
			return orders;
		};
		

}
