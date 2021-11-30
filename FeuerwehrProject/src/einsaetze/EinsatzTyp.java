package einsaetze;

public class EinsatzTyp {
	public final int minMitarbeiter;
	public final int minEinsatzfahrzeug;
	public final int minTankLoeschfahrzeug;
	public final int minManschaftstransporter;
	public final int minLeiterwagen;
	
	public EinsatzTyp(int minMA, int minEI, int minTL, int minMT, int minLT) {
		minMitarbeiter = minMA;
		minEinsatzfahrzeug = minEI;
		minTankLoeschfahrzeug = minTL;
		minManschaftstransporter = minMT;
		minLeiterwagen = minLT;
	}
}
