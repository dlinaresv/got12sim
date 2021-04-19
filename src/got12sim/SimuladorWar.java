package got12sim;

import java.util.ArrayList;

import got12sim.entity.*;
import got12sim.util.UtilWar;

public class SimuladorWar {
	

	static public void iniciarGuerra(War war) {
		int numEjercitosAliados = 1;
		int numEjercitosEnemigos = 1;

		System.out.println(" --------------------------------------------------- ");
		System.out.println(" |           Presentacion Aliado                   | ");
		System.out.println(" --------------------------------------------------- ");
		
		UtilWar.presentacion("Aliado", war.ejectitosAliados, war.terrain, war.terrain.isAliedCity());
		
		System.out.println(" --------------------------------------------------- ");
		System.out.println(" |           Presentacion Enemigo                  | ");
		System.out.println(" --------------------------------------------------- ");
		
		UtilWar.presentacion("Enemigo", war.ejectitosEnemigos, war.terrain, war.terrain.isEnemyCity());
		
	    System.out.println("#######################################");
	    System.out.println("##        Inicio simulacion          ##");
	    System.out.println("#######################################");
		
		for (int round = 0; round < 10; round++) {
			
			// asignamos daño de los ejercitos
			for (Ejercito ejercitoAliado : war.ejectitosAliados) {
				ataqueDelEjercito(round,ejercitoAliado,war.ejectitosEnemigos,war.terrain,war.terrain.isAliedCity(), war.terrain.isEnemyCity());
			}
			for (Ejercito ejercitoEnemigo : war.ejectitosEnemigos) {
				ataqueDelEjercito(round,ejercitoEnemigo,war.ejectitosAliados,war.terrain,war.terrain.isEnemyCity(), war.terrain.isAliedCity());
			}
			
			// calculamos las bajas
			System.out.println("   - Casualties round "+round+" :");
			UtilWar.actualizarBajas(round,war.ejectitosAliados,war.terrain,war.terrain.isAliedCity());
			UtilWar.actualizarBajas(round,war.ejectitosEnemigos,war.terrain,war.terrain.isEnemyCity());

			System.out.println(" ------------------------------------ ");
			numEjercitosAliados = UtilWar.getNumEjercitosVivos(war.ejectitosAliados, war.terrain, war.terrain.isAliedCity());
			numEjercitosEnemigos = UtilWar.getNumEjercitosVivos(war.ejectitosEnemigos, war.terrain, war.terrain.isEnemyCity());
			
			
			if ((numEjercitosAliados == 0) || (numEjercitosEnemigos == 0)) {
				// Fin
				System.out.println(" -Fin combate, num Ejercitos Aliados vivos: "+numEjercitosAliados);
				System.out.println(" -Fin combate, num Ejercitos Enemigos vivos: "+numEjercitosEnemigos);
				break;
			}

		}
		
		System.out.println(" --------------------------------------------------- ");
		
		
		UtilWar.presentacion("Aliado", war.ejectitosAliados, war.terrain, war.terrain.isAliedCity());
		UtilWar.presentacion("Enemigo", war.ejectitosEnemigos, war.terrain, war.terrain.isEnemyCity());
		System.out.println(" ");
		System.out.println(" ");
		
		if ((numEjercitosEnemigos == 0)) {
			// Fin
			System.out.println("         _______     ");
			System.out.println("        |       |    ");
			System.out.println("       (|  WIN  |)   ");
			System.out.println("        |       |   ");
			System.out.println("         \\     /    ");
			System.out.println("           ---      ");
			System.out.println("          _|_|_     ");
		} else {
			System.out.println("          .-.     ");
			System.out.println("        __| |__   ");
			System.out.println("       [__   __]   ");
			System.out.println("          | |      ");
			System.out.println("LOST      | |      ");
			System.out.println("          | |      ");
			System.out.println("          '-'      ");
		}
		System.out.println(" ");
		System.out.println(" ");
		
	}
	
	/**
	 * Ataque de un ejercito contra el resto.
	 */
	static public void ataqueDelEjercito(int round, Ejercito ejercito,ArrayList<Ejercito> ejectitos, Terrain aTerrain, boolean cityAtaque, boolean cityDefensa) {
		
		int ataque = UtilWar.obtenerAtaqueEjercito(round,ejercito,aTerrain,cityAtaque);
		// 125% enemy hated
		int ataqueEnemyHated = (int) (ataque * 1.5);
		int totalAtaque = 0;
		if (round>0) {
			totalAtaque = ataqueEnemyHated;
			System.out.println(" Army round "+round+": Ejercito ["+ejercito.getName()+"] Attack: ("+ataque+"), 125% enemy hated: ("+ataqueEnemyHated+"), totalAtaque = "+totalAtaque);
		} else {
			// round 0: only first Attack
			totalAtaque = ataque;
			System.out.println(" Army round "+round+": Ejercito ["+ejercito.getName()+"] Attack: "+totalAtaque);
		}
		
		if (totalAtaque!=0) {
			// asignamos daño a los ejercitos.
			int dañoTotalSobrante = totalAtaque;
			for (int i = 0; i < 3; i++) {
				dañoTotalSobrante = UtilWar.actualizarDamageEjercitos(round,ejercito,ejectitos, aTerrain, dañoTotalSobrante,cityDefensa);
				if (dañoTotalSobrante==0) break;
			}
		}
		
	}

	

}
