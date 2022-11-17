package bwinf41_fahrradwerkstatt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SecondMethod {
		
		int time = 0;
		Time dayTime = new Time();
		
		public SecondMethod () {
			dayTime.startFirstDay();
		}
		
		public void work(ArrayList<Auftrag> blueOrders, int remainingTime, Auftrag[] orders, int index){
			
			if (!blueOrders.isEmpty() || index != 0) {
			
				ArrayList<Auftrag> possibleOrders = calculatePOrders(blueOrders, dayTime.endOfDay);
				if (possibleOrders.isEmpty() && index == 0) {
					dayTime.nextDay();
					work(blueOrders, 0, orders, 0);
				} 
				
				if (index == 0) index = possibleOrders.get(0).indexInAL;
								
				int tmpStart = orders[index].getStart(); // ändern
				int tmpDuration = orders[index].getDuration(); // ändern
								
				blueOrders.remove(index);
				
				if (tmpStart < dayTime.endOfDay &&  (dayTime.currentTime + tmpDuration - remainingTime) < dayTime.endOfDay) { // Auftrag kann an einem Tag gemacht werden
					if (dayTime.currentTime < tmpStart) dayTime.currentTime = tmpStart+tmpDuration-remainingTime; // Zeitpunkt beim Abschließen des Aufrags
					else dayTime.currentTime = dayTime.currentTime+tmpDuration-remainingTime; // Zeitpunkt beim Abschließen des Aufrags
					Auftrag tmp = orders[index];
					tmp.setData((dayTime.currentTime-tmpStart), dayTime.currentTime);
					orders[index] = tmp;
					work(blueOrders, 0, orders, 0);
				
				} else if (tmpStart < dayTime.endOfDay){ // Auftrag braucht länger als einen Tag
					if (remainingTime == 0 && dayTime.currentTime != (dayTime.endOfDay-480)) remainingTime = (dayTime.endOfDay-dayTime.currentTime);
					else if (remainingTime == 0 && tmpStart > dayTime.endOfDay-480) remainingTime = dayTime.endOfDay-tmpStart;
					else remainingTime = remainingTime + 480;
					dayTime.nextDay();
					work(blueOrders, remainingTime, orders, index);
					
				} else {
					dayTime.nextDay();
					work(blueOrders, 0, orders, 0);
				}
			} else {
				System.out.println("average: " + calculateAverage(new ArrayList<>(Arrays.asList(orders))));
				System.out.println("highest: " + calculateHighest(new ArrayList<>(Arrays.asList(orders))));
				return;
			}
		}

		private ArrayList<Auftrag> removeUsedOrder(ArrayList<Auftrag> orders1, ArrayList<Auftrag> possibleOrders) {
			for (Auftrag order : possibleOrders) {
				orders1.remove(order.indexInAL);
				System.out.print(orders1);
			}
			return orders1;
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
