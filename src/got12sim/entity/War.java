package got12sim.entity;

import java.util.ArrayList;


public class War {
	
	public Terrain terrain;
	private String name;
	public ArrayList<Ejercito> ejectitosAliados = new ArrayList<Ejercito>(); 
	public ArrayList<Ejercito> ejectitosEnemigos = new ArrayList<Ejercito>(); 
	private static String WAR = "war";
	private static String OVERRUN = "overrun";
	private String combatType;
	
	public String getCombatType() {
		return combatType;
	}
	
	public Ejercito getEjercitoAliado(int pos) {
		return ejectitosAliados.get(pos);
	}
	public Ejercito getEjercitoEnemigo(int pos) {
		return ejectitosEnemigos.get(pos);
	}
	public void setCombatType(String aCombatType) {
		if (WAR.equalsIgnoreCase(aCombatType.trim())) {
			this.combatType = WAR;
		} else if (OVERRUN.equalsIgnoreCase(aCombatType.trim())) {
			this.combatType = OVERRUN;
			
		} else {
			System.out.println("CombatType desconocido --> pasamos a WAR");
			this.combatType = WAR;
		}
	}

	public War (String aName) {
		name = aName;
	}
	
	
	public void setTerrain(String aTerrain){
		terrain = new Terrain(aTerrain);
	}
	public void setCityEnemy() {
		terrain.setEnemyCity(true);
	}
	public void setAliedCity() {
		terrain.setAliedCity(true);
	}
	
	
	public void addEjectitoAliado(Ejercito ejercito) {
		ejectitosAliados.add(ejercito);
		System.out.println("     Add Ejercito Aliado: "+name+" <-- "+ejercito.getName());
	}
	
	public void addEjectitoEnemigo(Ejercito ejercito) {
		ejectitosEnemigos.add(ejercito);
		System.out.println("     Add Ejercito Enemigo: "+name+" <-- "+ejercito.getName());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	
	public String toString() {
		String terrainDesc = terrain.getTerrain();
		if (terrain.isAliedCity()) {
			terrainDesc = terrainDesc + " con ciudad Aliada.";
		} 

		if (terrain.isEnemyCity()) {
			terrainDesc = terrainDesc + " con ciudad Enemiga.";
		} 
		
		if (!terrain.isEnemyCity() && !terrain.isAliedCity()) {
			terrainDesc = terrainDesc + " sin ciudad.";
		}
		
		return "Simulacion ["+getName()+"] de un ("+getCombatType()+"): Terreno: "+terrainDesc;
	}
	

}
