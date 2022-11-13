package bwinf41_fahrradwerkstatt;

public class Auftrag {
		
	public int startTime;
	public int duration;
	public int warteZeit;
	public int fertigStellung;
		
	public Auftrag(int startTime, int duration) {
		this.startTime = startTime;
		this.duration = duration;
	
	}
	
	public int getStart() {
		return this.startTime;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public void setData(int warteZeit, int fertigStellung) {
		this.warteZeit = warteZeit;
		this.fertigStellung = fertigStellung;
	}
	
}
