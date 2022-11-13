package bwinf41_fahrradwerkstatt;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class FirstMethod {
	
	int time = 0;
	Time dayTime = new Time();
	
	public FirstMethod () {
		dayTime.startFirstDay();
	}
	
	public void work(ArrayList<Auftrag> orders, int index, int remainingTime){
		
		int tmpStart = orders.get(index).getStart();
		int tmpDuration = orders.get(index).getDuration();
		
		if (tmpStart < dayTime.endOfDay &&  (dayTime.currentTime + tmpDuration - remainingTime) < dayTime.endOfDay) { // Auftrag kann an einem Tag gemacht werden
			dayTime.currentTime = dayTime.currentTime+tmpDuration-remainingTime; // Zeitpunkt beim Abschließen des Aufrags
			Auftrag tmp = orders.get(index);
			tmp.setData((dayTime.currentTime-tmpStart), dayTime.currentTime);
			orders.set(index, tmp);
			
			work(orders, index+1, 0);
		
		} else if (tmpStart < dayTime.endOfDay){ // Auftrag braucht länger als einen Tag
			if (remainingTime == 0) remainingTime = remainingTime + (dayTime.endOfDay-tmpStart);
			else remainingTime = remainingTime + 480;
			dayTime.nextDay();
			work(orders, index, remainingTime);
			
		} else {
			dayTime.nextDay();
			work(orders, index, 0);
		}
			
	};
	
}
