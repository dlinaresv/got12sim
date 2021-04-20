package got12sim.util;

import java.util.ArrayList;

import got12sim.entity.Ejercito;
import got12sim.entity.Terrain;
import got12sim.entity.Tropa;
import got12sim.entity.War;

public class UtilWar {


	
	public static void presentacion(boolean aliado, War war) {
		
		int totalAtaque = 0;
		int totalDefensa = 0;
		String name = "N-A";
		ArrayList<Ejercito> ejectitos;
		boolean cityEjercitos = false;
		
		if (aliado) {
			war.logSimulador.addLogInfo("EJERCITO ALIADO:");
			name = "ALIADO";
			ejectitos = war.ejectitosAliados;
			cityEjercitos = war.terrain.isAliedCity();
		} else {
			war.logSimulador.addLogInfo("EJERCITO ENEMIGO:");
			name = "ENEMIGO";
			ejectitos = war.ejectitosEnemigos;
			cityEjercitos = war.terrain.isEnemyCity();
		}
		
		
		for (Ejercito ejercito : ejectitos) {
			war.logSimulador.addSeparador();
			war.logSimulador.addLog("Ejercito de "+ejercito.getName());					
			Float ataque = obtenerAtaqueEjercito(ejercito,war.terrain, cityEjercitos);
			Float defensa = obtenerDefensaEjercito(ejercito,war.terrain,cityEjercitos);
			listarEjercito(ejercito,war,cityEjercitos);
			totalAtaque = totalAtaque + ataque.intValue();
			totalDefensa = totalDefensa + defensa.intValue();
			war.logSimulador.addSeparador();
		}
		war.logSimulador.addLogInfo("TOTAL "+name+"--> Att: "+totalAtaque+" Def: "+totalDefensa);
	}
	
	/**
	 * (Unit strength for the terrain) * (( training/none+weapons/armors + 100)/300/2) * (# of troops) 
	 * 
	 * @param aTerrain
	 * @return
	 */
	public static float obtenerAtaqueTropa(Tropa aTropa, Terrain aTerrain, Integer commandRang, Integer morale, boolean cityTropa) {

		int fuerzaTerrain = TablasEjectitos.getFuerza(aTropa,aTerrain,cityTropa);
		float calculo1 = commandRang+morale;
		float coefEjercito = calculo1/200;
		
		float calculoAux = aTropa.getTraining()+aTropa.getWeapon()+100;
		float fuerzaTropaAtacando = calculoAux/300;
		
		// (Unit strength for the terrain) * (( training/none+weapons/armors + 100)/300/2) * (# of troops) 
		float ataque = fuerzaTerrain*(fuerzaTropaAtacando/2)*aTropa.getQuantity();
		
    	float attCoeff = (ataque*coefEjercito);
    	float resultado = ataque + attCoeff;
    	
		return resultado;
	}
	
	
	/**
	 * (defensa según terreno) * ( armors + 1) * (# of troops) 
	 * 
	 * @param aTerrain
	 * @return
	 */
	public static float obtenerDefensaTropa(Tropa aTropa, Terrain aTerrain, boolean cityDefensor) {

		float fuerzaTerrain = TablasEjectitos.getDefensa(aTropa, aTerrain,cityDefensor);
		
		float calculoAux = aTropa.getArmor()+100;
		float calculo = calculoAux/100;
		
		// (defensa según terreno) * ( armors + 1) * (# of troops) 
		float defensa = fuerzaTerrain*(calculo)*aTropa.getQuantity();
		
		return defensa;
	}
	

	public static Float obtenerAtaqueEjercito(Ejercito aEjercito, Terrain aTerrain, boolean cityEjercito) {
		float totalAtaque = 0;
	    for (Tropa tropa : aEjercito.tropas) {
	    	float ataque = obtenerAtaqueTropa(tropa, aTerrain, aEjercito.getCommandRang(), aEjercito.getMorale(),cityEjercito);
	    	totalAtaque = totalAtaque + ataque;
	    }
	    return totalAtaque;
	}
	
	public static Float obtenerAtaqueEjercitoArqueros(Ejercito aEjercito, Terrain aTerrain, boolean cityEjercito) {
		float totalAtaque = 0;
	    for (Tropa tropa : aEjercito.tropas) {
	    	if (tropa.isTipo(Tropa.TIPO_ARCHER)) {
		    	float ataque = obtenerAtaqueTropa(tropa, aTerrain, aEjercito.getCommandRang(), aEjercito.getMorale(),cityEjercito);
		    	totalAtaque = totalAtaque + ataque;
	    	}
	    }
	    return totalAtaque;
	}
	
	public static Float obtenerDefensaEjercito(Ejercito aEjercito, Terrain aTerrain, boolean cityDefensor) {
		float totalDefensa = 0;
	    for (Tropa tropa : aEjercito.tropas) {
	    	float defensa = obtenerDefensaTropa(tropa,aTerrain,cityDefensor);
	    	totalDefensa = totalDefensa + defensa;
	    }
	    return totalDefensa;
	}
	
	
	public static void listarEjercito(Ejercito ejercito, War war, boolean haveCity) {
		war.logSimulador.addLog("   Listar Ejercito de "+ejercito.getName()+ 
							" Commad: "+ejercito.getCommandRang()+
							" Terreno: "+war.terrain.getTerrain()+ 
							" City: "+haveCity);
		war.logSimulador.addLog("     Magia [Att:"+ejercito.getMagicAttack()+", Deff: "+ejercito.getMagicDefense()+"] ");
		float totalAtaque = 0;
		float totalDefensa = 0;
		
		for (Tropa tropa : ejercito.tropas) {
			
			Float ataque = obtenerAtaqueTropa(tropa, war.terrain, ejercito.getCommandRang(), ejercito.getMorale(),haveCity);
			Float defensa = obtenerDefensaTropa(tropa , war.terrain,haveCity);
			Float coeff_txema =  ataque / defensa;
			tropa.setCoeff_txema(coeff_txema);
			war.logSimulador.addLog("     ["+tropa.getQuantity()+"] "+tropa.getTipo()+" con (Att: " +ataque.intValue()+", Def: "+defensa.intValue()+") coeff_Txema(Att/Def)="+coeff_txema+" (primero los de menos fuerza)");
			totalAtaque = totalAtaque + ataque.intValue();
			totalDefensa = totalDefensa + defensa.intValue();
		}
		war.logSimulador.addLogInfo("     Ejercito de "+ejercito.getName()
			+" ---> [Att: " +totalAtaque+ "("+(totalAtaque+ejercito.getMagicAttack())
			+"), Def: "+totalDefensa+ "("+(totalDefensa+ejercito.getMagicDefense())+")]");
	}
	
	/**
	 * metodo para retornar el numero de tropas que mueren.
	 * 
	 * @param tropa
	 * @param aTerrain
	 * @param totalInflicted
	 * @return
	 */
	static public int obtenerNumTropasMueren(Tropa tropa,War war, float totalInflicted, float defensaTropa) {
		int resultado = 0;
		float coste = defensaTropa / tropa.getQuantity();
		
		if (totalInflicted > 0) {
			resultado = ((int)(totalInflicted / coste))+1;
		}
		war.logSimulador.addLog("        ["+tropa.getQuantity()+"] "+tropa.getTipo()+" a "+coste+" cada uno = "+(coste*resultado)+" de daño efectivo --> "+resultado+" "+tropa.getTipo()+" muertos.");
				
		return resultado;
	}
	
	
	static public int obtenerAtaqueEjercito(int round, Ejercito ejercito, Terrain aTerrain, boolean cityEjercito) {
		int ataque = 0;
		if (round==0) {
			ataque = UtilWar.obtenerAtaqueEjercitoArqueros(ejercito,aTerrain,cityEjercito).intValue();
		} else {
			ataque = UtilWar.obtenerAtaqueEjercito(ejercito,aTerrain, cityEjercito).intValue();
		}
		return ataque;
	}

	static public int obtenerMagiaAtaqueEjercito(int round, ArrayList<Ejercito> ejectitos, War war) {
		
		int magiaTotalAtaque = 0;

		if (round==0) {
			return 0;
		}
		
		for (Ejercito ejercito : ejectitos) {
			// contamos la magia
			if (ejercito.getMagicAttack() > 0) {
				magiaTotalAtaque = magiaTotalAtaque + ejercito.getMagicAttack();
				war.logSimulador.addLog("     MagicAttack ("+ejercito.getMagicAttack()+") from "+ejercito.getName());
				ejercito.setMagicAttack(0);
			}
		}
		return magiaTotalAtaque;
	}
	
	static public int getNumEjercitosVivos(ArrayList<Ejercito> ejercitos, War war,boolean cityDelEjercito) {
		int numEjercitos = 0;
		for (Ejercito ejercito : ejercitos) {
			if (ejercito.isEstaVivo()) {
				float defensaTropa = UtilWar.obtenerDefensaEjercito(ejercito, war.terrain,cityDelEjercito);
				if (defensaTropa>0) {
					numEjercitos++;
				} else {
					ejercito.setEstaVivo(false);
					war.logSimulador.addLog("     marcamos el ejercito ["+ ejercito.getName() + "] como Eliminado");
				}
			}
		}
		return numEjercitos;
	}
	
	
	/**
	 * 
	 * nuevo calculo del daño basado en el numero de tropas.
	 * 
	 * @param round
	 * @param ejercitoAtacante
	 * @param ejercitosDefensores
	 * @param aTerrain
	 * @param totalAtaque
	 * @param cityEjercitosDefensores
	 * @return daño sobrante.
	 */
	static public int actualizarDamageEjercitos(int round,Ejercito ejercitoAtacante,ArrayList<Ejercito> ejercitosDefensores, War war,int totalAtaque,boolean cityEjercitosDefensores) {
		float numTropasEjercito = 0;
		float numTropasTotales = 0;
		float dañoMagico = 0;
		
		float numEjercitos = getNumEjercitosVivos(ejercitosDefensores,war, cityEjercitosDefensores);
		// parche
		if (numEjercitos==0) numEjercitos=1;
		// parche
		
		// Daño magico
		if (ejercitoAtacante.getMagicAttack()>0) {
			war.logSimulador.addLog("     Magic Attack!! ("+ejercitoAtacante.getMagicAttack()+")");
			dañoMagico = ejercitoAtacante.getMagicAttack();
			ejercitoAtacante.setMagicAttack(0);
		}
		
		// -------------------------------------
		// Calculo basado en numero de tropas.
		for (Ejercito ejercito : ejercitosDefensores) {
			numTropasEjercito = ejercito.getNumTropas();
			numTropasTotales = numTropasTotales + numTropasEjercito;
		}
		// -------------------------------------
		
		float totalInflicted = 0;
		float totalInflictedMagic = dañoMagico / numEjercitos;
		if (numEjercitos > 1) {
			war.logSimulador.addLog("     dividimos el daño ("+totalAtaque+") de "+ejercitoAtacante.getName()+" entre "+numEjercitos+"  total de tropas = "+numTropasTotales);
		} else {
			war.logSimulador.addLog("     NO dividimos el daño de "+ejercitoAtacante.getName()+" ("+totalAtaque+") ");
		}
		
		int dañoTotalSobrante = 0;
		for (Ejercito ejercito : ejercitosDefensores) {
			if (ejercito.isEstaVivo()) {
				// caluclo de daño por estrategia
				
				// añadimos daño magico
				
				// daño recivido segun el numero de tropas:
				float coeffDammage = ejercito.getNumTropas() / numTropasTotales;
				totalInflicted = totalAtaque * coeffDammage;
				totalInflictedMagic = dañoMagico * coeffDammage;
				war.logSimulador.addLog("     Coeff Dammage para "+ejercito.getName()+" es de: "+coeffDammage+" --> totalInflicted = "+totalInflicted+", totalInflictedMagic = "+totalInflictedMagic);
				
				// asignamos daño 
				int inflicted = (int) totalInflicted;
				int dañoSobrante = ejercito.setDammageInflicted(inflicted,(int) totalInflictedMagic, war,cityEjercitosDefensores);
				dañoTotalSobrante = dañoTotalSobrante + dañoSobrante;
			}
		}
		return dañoTotalSobrante;
	}
	
	static public void actualizarBajas(int round,ArrayList<Ejercito> ejercitos,War war, boolean cityDefensor) {
				
		for (Ejercito ejercito : ejercitos) {
			float inflicted = ejercito.getDammageInflicted();
			
			war.logSimulador.addLog("     inflicted ("+inflicted+") of damage to "+ejercito.getName());
			if (inflicted>0) {
				for (Tropa tropa : ejercito.tropas) {
					float defensaTropa = UtilWar.obtenerDefensaTropa(tropa, war.terrain,cityDefensor);
					if (defensaTropa>0) {
						if (defensaTropa > inflicted) {
							// restamos bajas y fin.
							int numTropas_a_restar = UtilWar.obtenerNumTropasMueren(tropa,war,inflicted, defensaTropa);
							int numTropas_inicial = tropa.getQuantity();
							int numTropas_quedan = numTropas_inicial - numTropas_a_restar;
							tropa.setQuantity(numTropas_quedan);
							war.logSimulador.addLog("        lost "+numTropas_a_restar+" "+tropa.getTipo()+" del ejercito: "+ejercito.getName()+" --> quedan: "+numTropas_quedan+" "+tropa.getTipo()+" (dammage inflicted:"+inflicted+")");
							break;
						} else {
							inflicted = inflicted - defensaTropa;
							UtilWar.obtenerNumTropasMueren(tropa,war,defensaTropa, defensaTropa);
							war.logSimulador.addLog("        lost all "+tropa.getQuantity()+" "+tropa.getTipo()+" del ejercito: "+ejercito.getName()+" (dammage inflicted:"+defensaTropa+")");
							tropa.setQuantity(0);
						}
					}
				}
			}
			// fin calculo de bajas
			ejercito.cleanDammageInflicted();
		}
	}
	
	static public float getTacticaFactor(String tA, String tD) {
		float coef = 1;
		
		//Charge	100	100	110	100	120	80
		if (("Charge".equalsIgnoreCase(tA)) && (("Charge".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Flank".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Standard".equalsIgnoreCase(tD)))) coef = 110;
		if (("Charge".equalsIgnoreCase(tA)) && (("Surround".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Guerrilla".equalsIgnoreCase(tD)))) coef = 120;
		if (("Charge".equalsIgnoreCase(tA)) && (("Ambush".equalsIgnoreCase(tD)))) coef = 80;
		
		//Flank	100	100	90	80	110	120
		if (("Charge".equalsIgnoreCase(tA)) && (("Charge".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Flank".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Standard".equalsIgnoreCase(tD)))) coef = 90;
		if (("Charge".equalsIgnoreCase(tA)) && (("Surround".equalsIgnoreCase(tD)))) coef = 80;
		if (("Charge".equalsIgnoreCase(tA)) && (("Guerrilla".equalsIgnoreCase(tD)))) coef = 110;
		if (("Charge".equalsIgnoreCase(tA)) && (("Ambush".equalsIgnoreCase(tD)))) coef = 120;
		
		//Standard	80	120	100	100	100	100
		if (("Charge".equalsIgnoreCase(tA)) && (("Charge".equalsIgnoreCase(tD)))) coef = 80;
		if (("Charge".equalsIgnoreCase(tA)) && (("Flank".equalsIgnoreCase(tD)))) coef = 120;
		if (("Charge".equalsIgnoreCase(tA)) && (("Standard".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Surround".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Guerrilla".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Ambush".equalsIgnoreCase(tD)))) coef = 100;
		
		//Surround	100	120	100	100	80	100
		if (("Charge".equalsIgnoreCase(tA)) && (("Charge".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Flank".equalsIgnoreCase(tD)))) coef = 120;
		if (("Charge".equalsIgnoreCase(tA)) && (("Standard".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Surround".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Guerrilla".equalsIgnoreCase(tD)))) coef = 80;
		if (("Charge".equalsIgnoreCase(tA)) && (("Ambush".equalsIgnoreCase(tD)))) coef = 100;
		
		//Guerrilla	90	80	100	120	100	100
		if (("Charge".equalsIgnoreCase(tA)) && (("Charge".equalsIgnoreCase(tD)))) coef = 90;
		if (("Charge".equalsIgnoreCase(tA)) && (("Flank".equalsIgnoreCase(tD)))) coef = 80;
		if (("Charge".equalsIgnoreCase(tA)) && (("Standard".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Surround".equalsIgnoreCase(tD)))) coef = 120;
		if (("Charge".equalsIgnoreCase(tA)) && (("Guerrilla".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Ambush".equalsIgnoreCase(tD)))) coef = 100;
		
		//Ambush	120	80	100	100	100	100
		if (("Charge".equalsIgnoreCase(tA)) && (("Charge".equalsIgnoreCase(tD)))) coef = 120;
		if (("Charge".equalsIgnoreCase(tA)) && (("Flank".equalsIgnoreCase(tD)))) coef = 80;
		if (("Charge".equalsIgnoreCase(tA)) && (("Standard".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Surround".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Guerrilla".equalsIgnoreCase(tD)))) coef = 100;
		if (("Charge".equalsIgnoreCase(tA)) && (("Ambush".equalsIgnoreCase(tD)))) coef = 100;
		
		return coef/100;
	}
}
