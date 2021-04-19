package got12sim.entity;

public class Terrain {

	
	public static String PLAIN = "Plain";
	public static String HILLS = "Hills";
	public static String MOUNTAIN = "Mountains";
	public static String WOODS = "Woods";
	public static String SHORE = "Shore";
	public static String SAIL = "Sail";

	
	//private boolean city;
	private String terrain;
	
	private boolean aliedCity = false;
	private boolean enemyCity = false;
	
	
	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String aTerrain) {
		if (verificarTerrain(aTerrain)) {
			this.terrain = aTerrain;
		} else {
			this.terrain = PLAIN;
		}
		
	}

	public Terrain (String aTerrain) {
		this.setTerrain(aTerrain);
	}
	
	
	public boolean isPlain() {
		return PLAIN.equalsIgnoreCase(terrain);
	}
	
	public boolean isHills() {
		return HILLS.equalsIgnoreCase(terrain);
	}
	
	public boolean isMountains() {
		return MOUNTAIN.equalsIgnoreCase(terrain);
	}
	
	public boolean isWoods() {
		return WOODS.equalsIgnoreCase(terrain);
	}
	
	public boolean isShore() {
		return SHORE.equalsIgnoreCase(terrain);
	}
	
	private boolean verificarTerrain(String terrain) {
		if ( PLAIN.equalsIgnoreCase(terrain)
				|| HILLS.equalsIgnoreCase(terrain)
				|| MOUNTAIN.equalsIgnoreCase(terrain) 
				|| WOODS.equalsIgnoreCase(terrain) 
				|| SHORE.equalsIgnoreCase(terrain) 
				|| SAIL.equalsIgnoreCase(terrain) ) {
			return true;
		}
		// no es ningun terreno reconocido.
		System.out.println("Terreno no reconocido.");
		return false;
	}

	public boolean isAliedCity() {
		return aliedCity;
	}

	public void setAliedCity(boolean aliedCity) {
		this.aliedCity = aliedCity;
	}

	public boolean isEnemyCity() {
		return enemyCity;
	}

	public void setEnemyCity(boolean enemyCity) {
		this.enemyCity = enemyCity;
	}
}
