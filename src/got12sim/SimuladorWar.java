package got12sim;

import java.util.ArrayList;

import got12sim.entity.*;
import got12sim.util.UtilWar;

public class SimuladorWar {
	

	static public void iniciarGuerra(War war) {
		int numEjercitosAliados = 1;
		int numEjercitosEnemigos = 1;
	    
		UtilWar.presentacion(true, war);
		UtilWar.presentacion(false, war);
		
	    war.logSimulador.addSeparador();
	    war.logSimulador.addLogInfo("INICIO simulacion");
	    war.logSimulador.addSeparador();
		
		for (int round = 0; round < 10; round++) {
			
			// asignamos da�o de los ejercitos
			for (Ejercito ejercitoAliado : war.ejectitosAliados) {
				ataqueDelEjercito(round,ejercitoAliado,war.ejectitosEnemigos,war,war.terrain.isAliedCity(), war.terrain.isEnemyCity());
			}
			for (Ejercito ejercitoEnemigo : war.ejectitosEnemigos) {
				ataqueDelEjercito(round,ejercitoEnemigo,war.ejectitosAliados,war,war.terrain.isEnemyCity(), war.terrain.isAliedCity());
			}
			
			// calculamos las bajas
			war.logSimulador.addLog("   - Casualties round "+round+" :");
			UtilWar.actualizarBajas(round,war.ejectitosAliados,war,war.terrain.isAliedCity());
			UtilWar.actualizarBajas(round,war.ejectitosEnemigos,war,war.terrain.isEnemyCity());

			war.logSimulador.addSeparador();
			numEjercitosAliados = UtilWar.getNumEjercitosVivos(war.ejectitosAliados, war, war.terrain.isAliedCity());
			numEjercitosEnemigos = UtilWar.getNumEjercitosVivos(war.ejectitosEnemigos, war, war.terrain.isEnemyCity());
			
			
			if ((numEjercitosAliados == 0) || (numEjercitosEnemigos == 0)) {
				// Fin
				if ((numEjercitosEnemigos == 0)) war.setVictoriaAliada(true);
				
				war.logSimulador.addLog(" -Fin combate, num Ejercitos Aliados vivos: "+numEjercitosAliados);
				war.logSimulador.addLog(" -Fin combate, num Ejercitos Enemigos vivos: "+numEjercitosEnemigos);
				break;
			}

		}
		
	    war.logSimulador.addSeparador();
	    war.logSimulador.addLogInfo("FIN simulacion");
	    war.logSimulador.addSeparador();
		
		UtilWar.presentacion(true, war);
		UtilWar.presentacion(false, war);
		
		war.logSimulador.addSalto();
		war.logSimulador.addSeparador();
		war.logSimulador.addSalto();
		
		war.logSimulador.addLogVictoriaAliada(war.isVictoriaAliada());
		
		war.logSimulador.imprimirLog();
		
		war.logSimulador.imprimirLogInfo();
	}
	
	/**
	 * Ataque de un ejercito contra el resto.
	 */
	static public void ataqueDelEjercito(int round, Ejercito ejercito,ArrayList<Ejercito> ejectitos, War war, boolean cityAtaque, boolean cityDefensa) {
		
		int ataque = UtilWar.obtenerAtaqueEjercito(round,ejercito,war.terrain,cityAtaque);
		// 125% enemy hated
		int ataqueEnemyHated = (int) (ataque * 1.5);
		int totalAtaque = 0;
		if (round>0) {
			totalAtaque = ataqueEnemyHated;
			war.logSimulador.addLogInfo(" Army round "+round+": Ejercito ["+ejercito.getName()+"] Attack: ("+ataque+"), 125% enemy hated: ("+ataqueEnemyHated+"), totalAtaque = "+totalAtaque);
		} else {
			// round 0: only first Attack
			totalAtaque = ataque;
			war.logSimulador.addLogInfo(" Army round "+round+": Ejercito ["+ejercito.getName()+"] Attack: "+totalAtaque);
		}
		
		if (totalAtaque!=0) {
			// asignamos da�o a los ejercitos.
			int da�oTotalSobrante = totalAtaque;
			for (int i = 0; i < 3; i++) {
				da�oTotalSobrante = UtilWar.actualizarDamageEjercitos(round,ejercito,ejectitos, war, da�oTotalSobrante,cityDefensa);
				if (da�oTotalSobrante==0) break;
			}
		}
		
	}

	

}
