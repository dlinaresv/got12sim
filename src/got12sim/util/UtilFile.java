package got12sim.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import got12sim.entity.Ejercito;
import got12sim.entity.Tropa;
import got12sim.entity.War;

public class UtilFile {

	   public static final String SEPARATOR=";";
	   public static final String QUOTE="\"";
	   

	   public static War leerSimulacion(String filePath) throws Exception {
		   	
		   War simulacion = new War("test");
		   BufferedReader br = null;
		   
		   System.out.println("#######################################");
		   System.out.println("##        Lectura del fichero        ##");
		   System.out.println("#######################################");
		   try {
		         
			   br =new BufferedReader(new FileReader(filePath));
		         String line = br.readLine();
		         while (null!=line) {
		            String [] fields = line.split(SEPARATOR);
		            int codigo = Integer.parseInt(fields[0].trim());
		            if (codigo!=0) {
		            	System.out.println("Lectura de linea: "+Arrays.toString(fields));
		            }
		            
		            if (codigo==10) {
		            	// tipo simulacion
			            simulacion.setCombatType(fields[1].trim());
			            simulacion.setName(fields[2].trim());
			            simulacion.setTerrain(fields[3].trim());
			            String city = fields[4].trim();
			            if ("alied".equalsIgnoreCase(city)) {
			            	simulacion.setAliedCity();
			            } else if ("enemy".equalsIgnoreCase(city)) {
			            	simulacion.setCityEnemy();
			            }
			            System.out.println("     "+simulacion.toString());
		            }
		            
		            if (codigo==20) {
		            	// ejercito Aliado
		        		Ejercito ejercito = new Ejercito(fields[1].trim());
		        		int commandRang = Integer.parseInt(fields[2].trim());
		        		int morale = Integer.parseInt(fields[3].trim());
		        		String tacticaName = fields[4].trim();
		        		int magicAttack = Integer.parseInt(fields[5].trim());
		        		int magicDefense = Integer.parseInt(fields[6].trim());
		        		
		        		ejercito.setCommandRang(commandRang);
		        		ejercito.setMorale(morale);
		        		ejercito.setMagicAttack(magicAttack);
		        		ejercito.setMagicDefense(magicDefense);
		        		ejercito.setTacticaName(tacticaName);
		        		
			            System.out.println("     "+ejercito.toString());
			            simulacion.addEjectitoAliado(ejercito);
		            }
		            
		            if (codigo==21) {
		            	// tropa Aliado 1
		            	
		            	String tipo = fields[1].trim();
		            	int quantity = Integer.parseInt(fields[2].trim());
		            	int training = Integer.parseInt(fields[3].trim());
		            	int weapon = Integer.parseInt(fields[4].trim());
		            	int armor = Integer.parseInt(fields[5].trim());
		            	
		            	Tropa tropa = new Tropa(tipo);
		        		
		        		tropa.setQuantity(quantity);
		        		tropa.setTraining(training);
		        		tropa.setWeapon(weapon);
		        		tropa.setArmor(armor);
		        		
		        		System.out.println("     tropa Aliado 1 <--"+tropa.toString());
		        		
		        		Ejercito ejercito = simulacion.getEjercitoAliado(0);
		        		ejercito.addTropa(tropa);
		        		
			            //simulacion.addEjectitoAliado(ejercito);
		            }
		            if (codigo==22) {
		            	// tropa Aliado 2
		            	String tipo = fields[1].trim();
		            	int quantity = Integer.parseInt(fields[2].trim());
		            	int training = Integer.parseInt(fields[3].trim());
		            	int weapon = Integer.parseInt(fields[4].trim());
		            	int armor = Integer.parseInt(fields[5].trim());
		            	
		            	Tropa tropa = new Tropa(tipo);
		        		
		        		tropa.setQuantity(quantity);
		        		tropa.setTraining(training);
		        		tropa.setWeapon(weapon);
		        		tropa.setArmor(armor);
		        		
		        		System.out.println("     tropa Aliado 2 <--"+tropa.toString());
		        		
		        		Ejercito ejercito = simulacion.getEjercitoAliado(1);
		        		ejercito.addTropa(tropa);
		        		
			            //simulacion.addEjectitoAliado(ejercito);
		            }
		            if (codigo==23) {
		            	// tropa Aliado 3
		            	String tipo = fields[1].trim();
		            	int quantity = Integer.parseInt(fields[2].trim());
		            	int training = Integer.parseInt(fields[3].trim());
		            	int weapon = Integer.parseInt(fields[4].trim());
		            	int armor = Integer.parseInt(fields[5].trim());
		            	
		            	Tropa tropa = new Tropa(tipo);
		        		
		        		tropa.setQuantity(quantity);
		        		tropa.setTraining(training);
		        		tropa.setWeapon(weapon);
		        		tropa.setArmor(armor);
		        		
		        		System.out.println("     tropa Aliado 3 <--"+tropa.toString());
		        		
		        		Ejercito ejercito = simulacion.getEjercitoAliado(2);
		        		ejercito.addTropa(tropa);
		        		
			            //simulacion.addEjectitoAliado(ejercito);
		            }
		            if (codigo==24) {
		            	// tropa Aliado 4
		            	String tipo = fields[1].trim();
		            	int quantity = Integer.parseInt(fields[2].trim());
		            	int training = Integer.parseInt(fields[3].trim());
		            	int weapon = Integer.parseInt(fields[4].trim());
		            	int armor = Integer.parseInt(fields[5].trim());
		            	
		            	Tropa tropa = new Tropa(tipo);
		        		
		        		tropa.setQuantity(quantity);
		        		tropa.setTraining(training);
		        		tropa.setWeapon(weapon);
		        		tropa.setArmor(armor);
		        		
		        		System.out.println("     tropa Aliado 4 <--"+tropa.toString());
		        		
		        		Ejercito ejercito = simulacion.getEjercitoAliado(3);
		        		ejercito.addTropa(tropa);
		        		
			            //simulacion.addEjectitoAliado(ejercito);
		            }
		            if (codigo==25) {
		            	// tropa Aliado 5
		            	String tipo = fields[1].trim();
		            	int quantity = Integer.parseInt(fields[2].trim());
		            	int training = Integer.parseInt(fields[3].trim());
		            	int weapon = Integer.parseInt(fields[4].trim());
		            	int armor = Integer.parseInt(fields[5].trim());
		            	
		            	Tropa tropa = new Tropa(tipo);
		        		
		        		tropa.setQuantity(quantity);
		        		tropa.setTraining(training);
		        		tropa.setWeapon(weapon);
		        		tropa.setArmor(armor);
		        		
		        		System.out.println("     tropa Aliado 5 <--"+tropa.toString());
		        		
		        		Ejercito ejercito = simulacion.getEjercitoAliado(4);
		        		ejercito.addTropa(tropa);
		        		
			            //simulacion.addEjectitoAliado(ejercito);
		            }
		            
		            if (codigo==30) {
		            	// ejercito Enemigo
		        		Ejercito ejercito = new Ejercito(fields[1].trim());
		        		int commandRang = Integer.parseInt(fields[2].trim());
		        		int morale = Integer.parseInt(fields[3].trim());
		        		String tacticaName = fields[4].trim();
		        		int magicAttack = Integer.parseInt(fields[5].trim());
		        		int magicDefense = Integer.parseInt(fields[6].trim());
		        		
		        		ejercito.setCommandRang(commandRang);
		        		ejercito.setMorale(morale);
		        		ejercito.setMagicAttack(magicAttack);
		        		ejercito.setMagicDefense(magicDefense);
		        		ejercito.setTacticaName(tacticaName);
		        		
			            System.out.println("     "+ejercito.toString());
			            simulacion.addEjectitoEnemigo(ejercito);
		            }
		            
		            if (codigo==31) {
		            	// tropa Enemiga
		            	String tipo = fields[1].trim();
		            	int quantity = Integer.parseInt(fields[2].trim());
		            	int training = Integer.parseInt(fields[3].trim());
		            	int weapon = Integer.parseInt(fields[4].trim());
		            	int armor = Integer.parseInt(fields[5].trim());
		            	
		            	Tropa tropa = new Tropa(tipo);
		        		
		        		tropa.setQuantity(quantity);
		        		tropa.setTraining(training);
		        		tropa.setWeapon(weapon);
		        		tropa.setArmor(armor);
		        		
		        		System.out.println("     tropa Enemigo 1 <--"+tropa.toString());
		        		
		        		Ejercito ejercito = simulacion.getEjercitoEnemigo(0);
		        		ejercito.addTropa(tropa);
		        		
			            //simulacion.addEjectitoAliado(ejercito);
		            }
		            if (codigo==32) {
		            	// tropa Enemiga 2
		            	String tipo = fields[1].trim();
		            	int quantity = Integer.parseInt(fields[2].trim());
		            	int training = Integer.parseInt(fields[3].trim());
		            	int weapon = Integer.parseInt(fields[4].trim());
		            	int armor = Integer.parseInt(fields[5].trim());
		            	
		            	Tropa tropa = new Tropa(tipo);
		        		
		        		tropa.setQuantity(quantity);
		        		tropa.setTraining(training);
		        		tropa.setWeapon(weapon);
		        		tropa.setArmor(armor);
		        		
		        		System.out.println("     tropa Enemigo 2 <--"+tropa.toString());
		        		
		        		Ejercito ejercito = simulacion.getEjercitoEnemigo(1);
		        		ejercito.addTropa(tropa);
		        		
			            //simulacion.addEjectitoAliado(ejercito);
		            }
		            if (codigo==33) {
		            	// tropa Enemiga 3
		            	String tipo = fields[1].trim();
		            	int quantity = Integer.parseInt(fields[2].trim());
		            	int training = Integer.parseInt(fields[3].trim());
		            	int weapon = Integer.parseInt(fields[4].trim());
		            	int armor = Integer.parseInt(fields[5].trim());
		            	
		            	Tropa tropa = new Tropa(tipo);
		        		
		        		tropa.setQuantity(quantity);
		        		tropa.setTraining(training);
		        		tropa.setWeapon(weapon);
		        		tropa.setArmor(armor);
		        		
		        		System.out.println("     tropa Enemigo 3 <--"+tropa.toString());
		        		
		        		Ejercito ejercito = simulacion.getEjercitoEnemigo(2);
		        		ejercito.addTropa(tropa);
		        		
			            //simulacion.addEjectitoAliado(ejercito);
		            }
		            line = br.readLine();
		         }
		         
		   	} catch (Exception e) {
		         System.out.println("ERROR al leer el fichero csv: "+e.getMessage());
		   	} finally {
		         if (null!=br) {
		            br.close();
		         }
		    }
		   
		   System.out.println("#######################################");
		   System.out.println("##      Fin Lectura del fichero      ##");
		   System.out.println("#######################################");
		   
		   return simulacion;
	   }
		      
		      
}
