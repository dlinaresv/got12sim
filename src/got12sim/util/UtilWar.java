package got12sim.util;

import java.util.ArrayList;

import got12sim.entity.Ejercito;
import got12sim.entity.Terrain;
import got12sim.entity.Tropa;

public class UtilWar {

	
	public static void presentacion(String name, ArrayList<Ejercito> ejectitos, Terrain aTerrain, boolean cityEjercitos) {
		
		int totalAtaque = 0;
		int totalDefensa = 0;
		
		for (Ejercito ejercito : ejectitos) {
			System.out.println(" --------------------------------------------------- ");
			System.out.println("Ejercito de "+ejercito.getName());					
			Float ataque = obtenerAtaqueEjercito(ejercito,aTerrain, cityEjercitos);
			Float defensa = obtenerDefensaEjercito(ejercito,aTerrain,cityEjercitos);
			listarEjercito(ejercito,aTerrain,cityEjercitos);
			//System.out.println(" Ejercito "+ejercito.name+" --> ataque:"+ataque.intValue()+" defensa:"+defensa.intValue());
			totalAtaque = totalAtaque + ataque.intValue();
			totalDefensa = totalDefensa + defensa.intValue();
			System.out.println(" --------------------------------------------------- ");
		}
		System.out.println("TOTAL "+name+"--> Att: "+totalAtaque+" Def: "+totalDefensa);
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
    	
//    	if (aTropa.getQuantity() != 0) {
//	    	System.out.println("           CALCULO ATAQUE TROPA: ["+aTropa.toString()+"] fuerzaTerrain="+fuerzaTerrain);
//	    	System.out.println("           CALCULO ATAQUE TROPA: ["+aTropa.toString()+"] fuerzaTropaAtacando = [ (Training="+aTropa.getTraining()+")+(Weapon="+aTropa.getWeapon()+")+100 ="+calculoAux+" ]/300 ="+fuerzaTropaAtacando);
//	    	System.out.println("           CALCULO ATAQUE TROPA: ["+aTropa.toString()+"] ataque = fuerzaTerrain*(fuerzaTropaAtacando/2)*Quantity = "+fuerzaTerrain+"*("+fuerzaTropaAtacando+" / 2)*"+aTropa.getQuantity()+"="+ataque);
//	    	System.out.println("           CALCULO ATAQUE TROPA: ["+aTropa.toString()+"] coefEjercito = (commandRang="+commandRang+")+(morale="+morale+")/200="+coefEjercito);
//	    	System.out.println("           CALCULO ATAQUE TROPA: ["+aTropa.toString()+"] attCoeff = [(ataque="+ataque+")*(coefEjercito="+coefEjercito+")] = "+attCoeff);
//	    	System.out.println("           CALCULO ATAQUE TROPA: ["+aTropa.toString()+"] ----------------------------------------");
//	    	System.out.println("           CALCULO ATAQUE TROPA: ["+aTropa.toString()+"] resultado = "+resultado);
//    	} else {
//    		System.out.println("           CALCULO ATAQUE TROPA: ["+aTropa.toString()+"] = resultado = "+resultado);
//    	}
    	
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
	
	public static void listarEjercito(Ejercito ejercito, Terrain aTerrain, boolean haveCity) {
		System.out.println("   Listar Ejercito de "+ejercito.getName()+ 
							" Commad: "+ejercito.getCommandRang()+
							" Terreno: "+aTerrain.getTerrain()+ 
							" City: "+haveCity);
		System.out.println("     Magia [Att:"+ejercito.getMagicAttack()+", Deff: "+ejercito.getMagicDefense()+"] ");
		float totalAtaque = 0;
		float totalDefensa = 0;
		
		for (Tropa tropa : ejercito.tropas) {
			
			Float ataque = obtenerAtaqueTropa(tropa, aTerrain, ejercito.getCommandRang(), ejercito.getMorale(),haveCity);
			Float defensa = obtenerDefensaTropa(tropa , aTerrain,haveCity);
			Float coeff_txema =  ataque / defensa;
			tropa.setCoeff_txema(coeff_txema);
			System.out.println("     ["+tropa.getQuantity()+"] "+tropa.getTipo()+" con (Att: " +ataque.intValue()+", Def: "+defensa.intValue()+") coeff_Txema(Att/Def)="+coeff_txema+" (primero los de menos fuerza)");
			totalAtaque = totalAtaque + ataque.intValue();
			totalDefensa = totalDefensa + defensa.intValue();
		}
		System.out.println("     Ejercito de "+ejercito.getName()
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
	static public int obtenerNumTropasMueren(Tropa tropa,Terrain aTerrain, float totalInflicted, float defensaTropa) {
		int resultado = 0;
		float coste = defensaTropa / tropa.getQuantity();
		
		if (totalInflicted > 0) {
			resultado = ((int)(totalInflicted / coste))+1;
		}
		System.out.println("        ["+tropa.getQuantity()+"] "+tropa.getTipo()+" a "+coste+" cada uno = "+(coste*resultado)+" de daño efectivo --> "+resultado+" "+tropa.getTipo()+" muertos.");
				
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

	static public int obtenerMagiaAtaqueEjercito(int round, ArrayList<Ejercito> ejectitos) {
		
		int magiaTotalAtaque = 0;

		if (round==0) {
			return 0;
		}
		
		for (Ejercito ejercito : ejectitos) {
			// contamos la magia
			if (ejercito.getMagicAttack() > 0) {
				magiaTotalAtaque = magiaTotalAtaque + ejercito.getMagicAttack();
				System.out.println("     MagicAttack ("+ejercito.getMagicAttack()+") from "+ejercito.getName());
				ejercito.setMagicAttack(0);
			}
		}
		return magiaTotalAtaque;
	}
	
	static public int getNumEjercitosVivos(ArrayList<Ejercito> ejercitos, Terrain aTerrain,boolean cityDelEjercito) {
		int numEjercitos = 0;
		for (Ejercito ejercito : ejercitos) {
			if (ejercito.isEstaVivo()) {
				float defensaTropa = UtilWar.obtenerDefensaEjercito(ejercito, aTerrain,cityDelEjercito);
				if (defensaTropa>0) {
					numEjercitos++;
				} else {
					ejercito.setEstaVivo(false);
					System.out.println("     marcamos el ejercito ["+ ejercito.getName() + "] como Eliminado");
				}
			}
		}
		return numEjercitos;
	}
	
	/**
	 * @deprecated
	 * 
	 * @param round
	 * @param ejercitoAtacante
	 * @param ejercitosDefensores
	 * @param aTerrain
	 * @param totalAtaque
	 * @param cityEjercitosDefensores
	 * @return
	 */
	static public int actualizarDamageEjercitosOLD(int round,Ejercito ejercitoAtacante,ArrayList<Ejercito> ejercitosDefensores, Terrain aTerrain,int totalAtaque,boolean cityEjercitosDefensores) {
		int dañoMagico = 0;
		
		int numEjercitos = getNumEjercitosVivos(ejercitosDefensores,aTerrain, cityEjercitosDefensores);
		// parche
		if (numEjercitos==0) numEjercitos=1;
		// parche
		
		// Daño magico
		if (ejercitoAtacante.getMagicAttack()>0) {
			System.out.println("     Magic Attack!! ("+ejercitoAtacante.getMagicAttack()+")");
			dañoMagico = ejercitoAtacante.getMagicAttack();
			ejercitoAtacante.setMagicAttack(0);
		}
		
		float totalInflicted = totalAtaque / numEjercitos;
		float totalInflictedMagic = dañoMagico / numEjercitos;
		if (numEjercitos > 1) {
			System.out.println("     dividimos el daño ("+totalAtaque+") de "+ejercitoAtacante.getName()+" entre "+numEjercitos+"  = "+totalInflicted);
		} else {
			System.out.println("     NO dividimos el daño de "+ejercitoAtacante.getName()+" ("+totalAtaque+") ");
		}
		
		int dañoTotalSobrante = 0;
		for (Ejercito ejercito : ejercitosDefensores) {
			if (ejercito.isEstaVivo()) {
				// caluclo de daño por estrategia
				
				// añadimos daño magico
				
				// asignamos daño 
				int inflicted = (int) totalInflicted;
				int dañoSobrante = ejercito.setDammageInflicted(inflicted,(int) totalInflictedMagic, aTerrain,cityEjercitosDefensores);
				dañoTotalSobrante = dañoTotalSobrante + dañoSobrante;
			}
		}
		return dañoTotalSobrante;
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
	static public int actualizarDamageEjercitos(int round,Ejercito ejercitoAtacante,ArrayList<Ejercito> ejercitosDefensores, Terrain aTerrain,int totalAtaque,boolean cityEjercitosDefensores) {
		float numTropasEjercito = 0;
		float numTropasTotales = 0;
		float dañoMagico = 0;
		
		float numEjercitos = getNumEjercitosVivos(ejercitosDefensores,aTerrain, cityEjercitosDefensores);
		// parche
		if (numEjercitos==0) numEjercitos=1;
		// parche
		
		// Daño magico
		if (ejercitoAtacante.getMagicAttack()>0) {
			System.out.println("     Magic Attack!! ("+ejercitoAtacante.getMagicAttack()+")");
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
			System.out.println("     dividimos el daño ("+totalAtaque+") de "+ejercitoAtacante.getName()+" entre "+numEjercitos+"  total de tropas = "+numTropasTotales);
		} else {
			System.out.println("     NO dividimos el daño de "+ejercitoAtacante.getName()+" ("+totalAtaque+") ");
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
				System.out.println("     Coeff Dammage para "+ejercito.getName()+" es de: "+coeffDammage+" --> totalInflicted = "+totalInflicted+", totalInflictedMagic = "+totalInflictedMagic);
				
				// asignamos daño 
				int inflicted = (int) totalInflicted;
				int dañoSobrante = ejercito.setDammageInflicted(inflicted,(int) totalInflictedMagic, aTerrain,cityEjercitosDefensores);
				dañoTotalSobrante = dañoTotalSobrante + dañoSobrante;
			}
		}
		return dañoTotalSobrante;
	}
	
	static public void actualizarBajas(int round,ArrayList<Ejercito> ejercitos,Terrain aTerrain, boolean cityDefensor) {
				
		for (Ejercito ejercito : ejercitos) {
			float inflicted = ejercito.getDammageInflicted();
			
			System.out.println("     inflicted ("+inflicted+") of damage to "+ejercito.getName());
			if (inflicted>0) {
				for (Tropa tropa : ejercito.tropas) {
					float defensaTropa = UtilWar.obtenerDefensaTropa(tropa, aTerrain,cityDefensor);
					if (defensaTropa>0) {
						if (defensaTropa > inflicted) {
							// restamos bajas y fin.
							int numTropas_a_restar = UtilWar.obtenerNumTropasMueren(tropa,aTerrain,inflicted, defensaTropa);
							int numTropas_inicial = tropa.getQuantity();
							int numTropas_quedan = numTropas_inicial - numTropas_a_restar;
							tropa.setQuantity(numTropas_quedan);
							System.out.println("        lost "+numTropas_a_restar+" "+tropa.getTipo()+" del ejercito: "+ejercito.getName()+" --> quedan: "+numTropas_quedan+" "+tropa.getTipo()+" (dammage inflicted:"+inflicted+")");
							break;
						} else {
							inflicted = inflicted - defensaTropa;
							UtilWar.obtenerNumTropasMueren(tropa,aTerrain,defensaTropa, defensaTropa);
							System.out.println("        lost all "+tropa.getQuantity()+" "+tropa.getTipo()+" del ejercito: "+ejercito.getName()+" (dammage inflicted:"+defensaTropa+")");
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
