package got12sim.util;

import got12sim.entity.Terrain;
import got12sim.entity.Tropa;

public class TablasEjectitos {

	boolean obtenerFuerza;
	
	public static int getFuerza(Tropa tropa, Terrain aTerrain, boolean cityDeLaTropa) { 
		int resultado = 0;
		int caracteristica = getCaracteristicaTropa(tropa, aTerrain);
		
		// Double attack strength and normal defense when fighting in an allied city. 
		// Normal attack strength and half defense strength elsewhere
		if (tropa.isTipo(Tropa.TIPO_ARCHER)) {
			if (cityDeLaTropa) {
				resultado = caracteristica*2;
			} else {
				resultado = caracteristica;
			}
		} else if (tropa.isTipo(Tropa.TIPO_Gold_Cloaks)) {
			// Double attack strength when fighting in an allied city
			if (cityDeLaTropa) {
				resultado = caracteristica*2;
			} else {
				resultado = caracteristica;
			}
		} else if (tropa.isTipo(Tropa.TIPO_Rangers)) {
			// Double attack strength when fighting in an allied city
			if (cityDeLaTropa) {
				resultado = caracteristica*2;
			} else {
				resultado = caracteristica;
			}
		} else {
			resultado = caracteristica;
		}
		return resultado;
	}
	
	public static int getDefensa(Tropa tropa, Terrain aTerrain, boolean cityDeLaTropa) { 
		int resultado = 0;
		int caracteristica = getCaracteristicaTropa(tropa, aTerrain);
		
		// Double attack strength and normal defense when fighting in an allied city. 
		// Normal attack strength and half defense strength elsewhere
		if (tropa.isTipo(Tropa.TIPO_ARCHER)) {
			if (cityDeLaTropa) {
				resultado = caracteristica;
			} else {
				resultado = caracteristica/2;
			}
		} else {
			resultado = caracteristica;
		}
		return resultado;
	}
	
	
	private static void getTabla() {
		
		
		// Tropp - Coast - Desert - Hich Seas - Woods - Hills - Shore - Mountatins - Plains - Swamp
		// Archers	0	10	0	3	6	10	6	10	6
		// Bloodriders	0	12	0	6	12	16	4	16	8
		// Cargo Ships	100	100	100	100	100	100	100	100	100
		// Catapults	0	50	0	50	50	50	50	50	50
		// Chevalier	0	12	0	6	8	24	4	24	6
		// Elite Footmen	0	8	0	10	16	10	8	14	8
		// Freedmen	0	10	0	16	12	5	16	5	16
		// Gladiator Slaves	0	10	0	16	6	5	16	5	16
		// Gold Cloaks	0	8	0	10	16	10	8	10	8
		// Heavy Cavalry	0	12	0	6	12	16	4	16	8
		// Heavy Infantry	0	8	0	10	16	10	8	10	8
		// Hired Mercenaries	0	10	0	12	18	12	10	12	10
		// Infantry	0	10	0	16	6	5	16	5	16
		// Knights of the Vale	0	10	0	10	10	16	16	16	6
		// Krakens	300	300	300	300	300	300	300	300	300
		// Mountain Giants	0	6	0	18	32	15	16	20	2
		// Mounted Knights	0	10	0	6	10	18	4	18	6
		// Northmen	0	8	0	10	18	10	8	12	8
		// Peasants	0	2	0	2	2	2	2	2	2
		// Raiders	0	10	0	16	6	5	16	5	16
		// Rangers	0	10	0	20	8	12	16	12	16
		// Reavers	0	8	0	10	16	10	8	10	8
		// Sand Cavalry	0	22	0	10	10	16	6	16	10
		// Scouts	0	16	0	10	10	10	6	10	10
		// Seasoned Veterans	0	10	0	12	18	12	10	12	10
		// Slaves	0	2	0	2	2	2	2	2	2
		// Smugglers Vessels	200	200	200	200	200	200	200	200	200
		// Triremes	300	300	300	300	300	300	300	300	300
		// Unknown troops	0	1	0	1	1	1	1	1	1
		// Unsullied	0	10	0	16	16	10	16	10	16
		// Villager Conscripts	0	2	0	2	2	2	2	2	2
		// Wights	0	10	0	16	6	5	16	5	16
	}
	
	
	public static int getCaracteristicaTropa(Tropa tropa,Terrain aTerrain) {
		
		// ------------ SEA ---------------
		if (tropa.isTipo(Tropa.TIPO_TRIREMES)) {
			return 300;
		} else if (tropa.isTipo(Tropa.TIPO_SMUGGLERS_VESSELS)) {
			return 200;
		} else if (tropa.isTipo(Tropa.TIPO_CARGO_SHIPS)) {
			return 100;
		} else if (tropa.isTipo(Tropa.TIPO_KRAKENS)) {
			return 300;
		}
		// ------------ SEA ---------------
		
		
		if (aTerrain.isPlain()) {
			if (tropa.isTipo(Tropa.TIPO_ARCHER)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_INFANTRY)) {
				return 5;
			} else if (tropa.isTipo(Tropa.TIPO_SCOUTS)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_MOUNTED_KNIGHTS)) {
				return 18;
			} else if (tropa.isTipo(Tropa.TIPO_SAND_CAVALRY)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_SEASONED_VETERANS)) {
				return 12;
			} else if (tropa.isTipo(Tropa.TIPO_HEAVY_INFANTRY)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_SLAVES)) {
				return 2;
			} else if (tropa.isTipo(Tropa.TIPO_CHEVALIER)) {
				return 24;
			} else if (tropa.isTipo(Tropa.TIPO_NORTHMEN)) {
				return 12;
			} else if (tropa.isTipo(Tropa.TIPO_Reavers)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_HEAVY_CAVALRY)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_Gold_Cloaks)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_Knights_of_the_Vale)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_Rangers)) {
				return 12;
			}
			
			
		}else if (aTerrain.isHills()) {
			if (tropa.isTipo(Tropa.TIPO_ARCHER)) {
				return 6;
			} else if (tropa.isTipo(Tropa.TIPO_INFANTRY)) {
				return 6;
			} else if (tropa.isTipo(Tropa.TIPO_SCOUTS)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_MOUNTED_KNIGHTS)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_SAND_CAVALRY)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_SEASONED_VETERANS)) {
				return 18;
			} else if (tropa.isTipo(Tropa.TIPO_HEAVY_INFANTRY)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_SLAVES)) {
				return 2;
			} else if (tropa.isTipo(Tropa.TIPO_CHEVALIER)) {
				return 8;
			} else if (tropa.isTipo(Tropa.TIPO_NORTHMEN)) {
				return 18;
			} else if (tropa.isTipo(Tropa.TIPO_Reavers)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_HEAVY_CAVALRY)) {
				return 12;
			} else if (tropa.isTipo(Tropa.TIPO_Gold_Cloaks)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_Knights_of_the_Vale)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_Rangers)) {
				return 8;
			}
			
		}else if (aTerrain.isMountains()) {
			if (tropa.isTipo(Tropa.TIPO_ARCHER)) {
				return 6;
			} else if (tropa.isTipo(Tropa.TIPO_INFANTRY)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_SCOUTS)) {
				return 6;
			} else if (tropa.isTipo(Tropa.TIPO_MOUNTED_KNIGHTS)) {
				return 4;
			} else if (tropa.isTipo(Tropa.TIPO_SAND_CAVALRY)) {
				return 6;
			} else if (tropa.isTipo(Tropa.TIPO_SEASONED_VETERANS)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_HEAVY_INFANTRY)) {
				return 8;
			} else if (tropa.isTipo(Tropa.TIPO_SLAVES)) {
				return 2;
			} else if (tropa.isTipo(Tropa.TIPO_CHEVALIER)) {
				return 4;
			} else if (tropa.isTipo(Tropa.TIPO_NORTHMEN)) {
				return 8;
			} else if (tropa.isTipo(Tropa.TIPO_Reavers)) {
				return 8;
			} else if (tropa.isTipo(Tropa.TIPO_HEAVY_CAVALRY)) {
				return 4;
			} else if (tropa.isTipo(Tropa.TIPO_Gold_Cloaks)) {
				return 8;
			} else if (tropa.isTipo(Tropa.TIPO_Knights_of_the_Vale)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_Rangers)) {
				return 16;
			}
			
		}else if (aTerrain.isWoods()) {
			if (tropa.isTipo(Tropa.TIPO_ARCHER)) {
				return 3;
			} else if (tropa.isTipo(Tropa.TIPO_INFANTRY)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_SCOUTS)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_MOUNTED_KNIGHTS)) {
				return 6;
			} else if (tropa.isTipo(Tropa.TIPO_SAND_CAVALRY)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_SEASONED_VETERANS)) {
				return 12;
			} else if (tropa.isTipo(Tropa.TIPO_HEAVY_INFANTRY)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_SLAVES)) {
				return 2;
			} else if (tropa.isTipo(Tropa.TIPO_CHEVALIER)) {
				return 6;
			} else if (tropa.isTipo(Tropa.TIPO_NORTHMEN)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_Reavers)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_HEAVY_CAVALRY)) {
				return 6;
			} else if (tropa.isTipo(Tropa.TIPO_Gold_Cloaks)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_Knights_of_the_Vale)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_Rangers)) {
				return 20;
			}
			
		}else if (aTerrain.isShore()) {
			if (tropa.isTipo(Tropa.TIPO_ARCHER)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_INFANTRY)) {
				return 5;
			} else if (tropa.isTipo(Tropa.TIPO_SCOUTS)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_MOUNTED_KNIGHTS)) {
				return 18;
			} else if (tropa.isTipo(Tropa.TIPO_SAND_CAVALRY)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_SEASONED_VETERANS)) {
				return 12;
			} else if (tropa.isTipo(Tropa.TIPO_HEAVY_INFANTRY)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_SLAVES)) {
				return 2;
			} else if (tropa.isTipo(Tropa.TIPO_CHEVALIER)) {
				return 24;
			} else if (tropa.isTipo(Tropa.TIPO_NORTHMEN)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_Reavers)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_HEAVY_CAVALRY)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_Gold_Cloaks)) {
				return 10;
			} else if (tropa.isTipo(Tropa.TIPO_Knights_of_the_Vale)) {
				return 16;
			} else if (tropa.isTipo(Tropa.TIPO_Rangers)) {
				return 12;
			}
		}
		
		System.out.println("Terrain default = 0 ");
		return 0;
	}

}
