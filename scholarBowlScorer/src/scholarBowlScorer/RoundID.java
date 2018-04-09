package scholarBowlScorer;

public class RoundID {
	String rroom;
	String rmod;
	String rrnumber;
	String rpacket;
	String rskeep;
	String rteamAName;
	String rteamBName; 
	String a1Name; 
	String a2Name; 
	String a3Name; 
	String a4Name; 
	String a5Name;
	String a6Name;
	String b1Name; 
	String b2Name;
	String b3Name; 
	String b4Name;
	String b5Name;
	String b6Name;
	double[][] rscores;
	public RoundID (String room, String mod, String rnumber, String packet, String skeep, String teamAName, String teamBName, String p0, String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, double[][] s) {
		rroom = room;
		rmod = mod;
		rrnumber = rnumber;
		rpacket = packet;
		rskeep = skeep;
		rteamAName = teamAName;
		rteamBName = teamBName;
		a1Name = p0;
		a2Name = p1;
		a3Name = p2;
		a4Name = p3;
		a5Name = p4;
		a6Name = p5;
		b1Name = p6;
		b2Name = p7;
		b3Name = p8;
		b4Name = p9;
		b5Name = p10;
		b6Name = p11;
		rscores = s;
		
	}

}
