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



//	public void iniciarGuerra() {
//		int numEjercitosAliados = 1;
//		int numEjercitosEnemigos = 1;
//
//		System.out.println(" --------------------------------------------------- ");
//		System.out.println(" |           Presentacion Aliado                   | ");
//		System.out.println(" --------------------------------------------------- ");
//		
//		UtilWar.presentacion("Aliado", ejectitosAliados, terrain, terrain.isAliedCity());
//		
//		System.out.println(" --------------------------------------------------- ");
//		System.out.println(" |           Presentacion Enemigo                  | ");
//		System.out.println(" --------------------------------------------------- ");
//		
//		UtilWar.presentacion("Enemigo", ejectitosEnemigos, terrain, terrain.isEnemyCity());
//		
//	    System.out.println("#######################################");
//	    System.out.println("##        Inicio simulacion          ##");
//	    System.out.println("#######################################");
//		
//		for (int round = 0; round < 10; round++) {
//			
//			// asignamos daño de los ejercitos
//			for (Ejercito ejercitoAliado : ejectitosAliados) {
//				ataqueDelEjercito(round,ejercitoAliado,ejectitosEnemigos,terrain,terrain.isAliedCity(), terrain.isEnemyCity());
//			}
//			for (Ejercito ejercitoEnemigo : ejectitosEnemigos) {
//				ataqueDelEjercito(round,ejercitoEnemigo,ejectitosAliados,terrain,terrain.isEnemyCity(), terrain.isAliedCity());
//			}
//			
//			// calculamos las bajas
//			System.out.println("   - Casualties round "+round+" :");
//			UtilWar.actualizarBajas(round,ejectitosAliados,terrain,terrain.isAliedCity());
//			UtilWar.actualizarBajas(round,ejectitosEnemigos,terrain,terrain.isEnemyCity());
//
//			System.out.println(" ------------------------------------ ");
//			numEjercitosAliados = UtilWar.getNumEjercitosVivos(ejectitosAliados, terrain, terrain.isAliedCity());
//			numEjercitosEnemigos = UtilWar.getNumEjercitosVivos(ejectitosEnemigos, terrain, terrain.isEnemyCity());
//			
//			
//			if ((numEjercitosAliados == 0) || (numEjercitosEnemigos == 0)) {
//				// Fin
//				System.out.println(" -Fin combate, num Ejercitos Aliados vivos: "+numEjercitosAliados);
//				System.out.println(" -Fin combate, num Ejercitos Enemigos vivos: "+numEjercitosEnemigos);
//				break;
//			}
//
//		}
//		
//		System.out.println(" --------------------------------------------------- ");
//		
//		
//		UtilWar.presentacion("Aliado", ejectitosAliados, terrain, terrain.isAliedCity());
//		UtilWar.presentacion("Enemigo", ejectitosEnemigos, terrain, terrain.isEnemyCity());
//		System.out.println(" ");
//		System.out.println(" ");
//		
//		if ((numEjercitosEnemigos == 0)) {
//			// Fin
//			System.out.println("         _______     ");
//			System.out.println("        |       |    ");
//			System.out.println("       (|  WIN  |)   ");
//			System.out.println("        |       |   ");
//			System.out.println("         \\     /    ");
//			System.out.println("           ---      ");
//			System.out.println("          _|_|_     ");
//		} else {
//			System.out.println("          .-.     ");
//			System.out.println("        __| |__   ");
//			System.out.println("       [__   __]   ");
//			System.out.println("          | |      ");
//			System.out.println("LOST      | |      ");
//			System.out.println("          | |      ");
//			System.out.println("          '-'      ");
//		}
//		System.out.println(" ");
//		System.out.println(" ");
//		
//	}
	
	/**
	 * 
	 * Ataque de un ejercito contra el resto.
	 */
//	public void ataqueDelEjercito(int round, Ejercito ejercito,ArrayList<Ejercito> ejectitos, Terrain aTerrain, boolean cityAtaque, boolean cityDefensa) {
//		
//		int ataque = UtilWar.obtenerAtaqueEjercito(round,ejercito,aTerrain,cityAtaque);
//		// 125% enemy hated
//		int ataqueEnemyHated = (int) (ataque * 1.5);
//		int totalAtaque = 0;
//		if (round>0) {
//			totalAtaque = ataqueEnemyHated;
//			System.out.println(" Army round "+round+": Ejercito ["+ejercito.getName()+"] Attack: ("+ataque+"), 125% enemy hated: ("+ataqueEnemyHated+"), totalAtaque = "+totalAtaque);
//		} else {
//			// round 0: only first Attack
//			totalAtaque = ataque;
//			System.out.println(" Army round "+round+": Ejercito ["+ejercito.getName()+"] Attack: "+totalAtaque);
//		}
//		
//		if (totalAtaque!=0) {
//			// asignamos daño a los ejercitos.
//			int dañoTotalSobrante = totalAtaque;
//			for (int i = 0; i < 3; i++) {
//				dañoTotalSobrante = UtilWar.actualizarDamageEjercitos(round,ejercito,ejectitos, aTerrain, dañoTotalSobrante,cityDefensa);
//				if (dañoTotalSobrante==0) break;
//			}
//		}
//		
//	}

	
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
