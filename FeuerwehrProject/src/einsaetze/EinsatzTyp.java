package einsaetze;

/**
 * Dient zum Speichern von Vorgegebenen Einsätzen
 */
public class EinsatzTyp {
	public final String bezeichnung;
	public final int minMitarbeiter;
	public final int minEinsatzfahrzeug;
	public final int minTankLoeschfahrzeug;
	public final int minManschaftstransporter;
	public final int minLeiterwagen;
	
	/**
	 * Erstelle einen neuen EinsatzTypen
	 * @param name des Einsatzes
	 * @param minMA Minimum an Mitarbeitern
	 * @param minEI Minimum an Einsatz-Leitfahrzeugen
	 * @param minTL Minimum an Tank-Löschfahrzeugen
	 * @param minMT Minimum an Mannschaftstransporter
	 * @param minLT Minimum an Leiterwagen
	 */
	public EinsatzTyp(String name, int minMA, int minEI, int minTL, int minMT, int minLT) {
		this.bezeichnung = name;
		minMitarbeiter = minMA;
		minEinsatzfahrzeug = minEI;
		minTankLoeschfahrzeug = minTL;
		minManschaftstransporter = minMT;
		minLeiterwagen = minLT;
	}
	
	/**
	 * Bekomme den Einsatznamen
	 * @return Einsatz name
	 */
	public String getName(){
		return this.bezeichnung;
	}
}
