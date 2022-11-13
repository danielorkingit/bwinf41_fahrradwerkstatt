package bwinf41_fahrradwerkstatt;

public class Time {
	
	public int currentTime = 0;
	public int endOfDay = 0;
	
	public void startFirstDay () {
		currentTime = 540; // 9:00 Uhr
		endOfDay = currentTime + 480; // 17:00 Uhr
	}
	
	public void nextDay() {
		currentTime = endOfDay + 960;
		endOfDay = currentTime + 480;
	}
}
