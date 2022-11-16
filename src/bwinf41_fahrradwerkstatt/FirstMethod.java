package bwinf41_fahrradwerkstatt;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class FirstMethod {
	
	Time dayTime = new Time();
	
	public FirstMethod () {
		dayTime.startFirstDay();
	}
	
	public void work(ArrayList<Auftrag> orders, int index, int remainingTime){

		if (index != orders.size()) {
			
			int tmpStart = orders.get(index).getStart();
			int tmpDuration = orders.get(index).getDuration();
			
			if (tmpStart < dayTime.endOfDay &&  (dayTime.currentTime + tmpDuration - remainingTime) < dayTime.endOfDay && tmpStart <= dayTime.currentTime) { // Auftrag kann an einem Tag gemacht werden
				dayTime.currentTime = dayTime.currentTime+tmpDuration-remainingTime; // Zeitpunkt beim Abschließen des Aufrags
				Auftrag tmp = orders.get(index);
				tmp.setData((dayTime.currentTime-tmpStart), dayTime.currentTime);
				orders.set(index, tmp);
				work(orders, index+1, 0);
			
			} else if (tmpStart < dayTime.endOfDay && tmpStart <= dayTime.currentTime){ // Auftrag braucht länger als einen Tag
				if (remainingTime == 0 && dayTime.currentTime < tmpStart) remainingTime = remainingTime + (dayTime.endOfDay-tmpStart);
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

	private ArrayList<Auftrag> calculatePOrders(ArrayList<Auftrag> orders, int currentTime) {
			ArrayList<Auftrag> result = new ArrayList<>();
			int index = 0;
			for (Auftrag order : orders) {
				if (order.startTime <= currentTime) {
					order.indexInAL = index;
					result.add(order);
				}
				index += 1;
			}
			return result;
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
	};
	
}
