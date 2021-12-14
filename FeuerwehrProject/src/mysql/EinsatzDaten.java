package mysql;

public class EinsatzDaten {

	int id;
	String name;
	
	public EinsatzDaten(int id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
}
