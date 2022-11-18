package bwinf41_fahrradwerkstatt;

import java.util.Comparator;

public class Auftrag {
		
	public int startTime;
	public int duration;
	public int warteZeit;
	public int fertigStellung;
	public int indexInAL;
	public int key;
		
	public Auftrag(int startTime, int duration, int key) {
		this.startTime = startTime;
		this.duration = duration;
		this.key = key;
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
	
	public static Comparator<Auftrag> StuDuration = new Comparator<Auftrag>() {
		  
        public int compare(Auftrag a1, Auftrag a2) {
  
            int auftrag1 = a1.duration;
            int auftrag2 = a2.duration;
  
            return auftrag1 - auftrag2;
        }
    };
}
