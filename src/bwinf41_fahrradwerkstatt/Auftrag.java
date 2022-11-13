package bwinf41_fahrradwerkstatt;

import java.util.Comparator;

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
	
	public static Comparator<Auftrag> StuDuration = new Comparator<Auftrag>() {
		  
        public int compare(Auftrag a1, Auftrag a2) {
  
            int auftrag1 = a1.duration;
            int auftrag2 = a2.duration;
  
            return auftrag1 - auftrag2;
        }
    };
}
