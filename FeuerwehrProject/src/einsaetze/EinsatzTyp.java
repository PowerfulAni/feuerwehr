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
	 * Ertelle einen neuen EinsatzTypen
	 * @param name des Einsatzes
	 * @param minMA minimum an mitarbeitern
	 * @param minEI minimum an Einsatz-Leitfahrzeugen
	 * @param minTL minimum an Tank-Löschfahrzeugen
	 * @param minMT minimum an Mannschaftstransporter
	 * @param minLT minimum an leiterwagen
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
	 * Bekomme den Einsatz namen
	 * @return Einsatz name
	 */
	public String getName(){
		return this.bezeichnung;
	}
}
