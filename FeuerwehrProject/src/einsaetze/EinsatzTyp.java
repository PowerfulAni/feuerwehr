package einsaetze;

public class EinsatzTyp {
	public final String bezeichnung;
	public final int minMitarbeiter;
	public final int minEinsatzfahrzeug;
	public final int minTankLoeschfahrzeug;
	public final int minManschaftstransporter;
	public final int minLeiterwagen;
	
	public EinsatzTyp(String name, int minMA, int minEI, int minTL, int minMT, int minLT) {
		this.bezeichnung = name;
		minMitarbeiter = minMA;
		minEinsatzfahrzeug = minEI;
		minTankLoeschfahrzeug = minTL;
		minManschaftstransporter = minMT;
		minLeiterwagen = minLT;
	}
	
	public String getName(){
		return this.bezeichnung;
	}
}
