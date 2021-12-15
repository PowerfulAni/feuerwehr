package mysql;

/**
 * Für die Datenbank als Rückgabewert der Einsätze (Hilfsobjekt)
 */
public class EinsatzDaten {

	int id;
	String name;
	
	/**
	 * Erstellt einen Einsatz für die Datenbank
	 * @param id des Einsatzes
	 * @param name des Einsatzes
	 */
	public EinsatzDaten(int id, String name) {
		this.id=id;
		this.name=name;
	}
	
	/**
	 * Bekomme die ID
	 * @return Einsatz Id
	 */
	public int getID() {
		return id;
	}
	/**
	 * Bekomme den Namen
	 * @return Einsatz namen
	 */
	public String getName() {
		return name;
	}
}
