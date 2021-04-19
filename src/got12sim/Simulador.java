package got12sim;


import got12sim.entity.War;
import got12sim.util.UtilFile;

public class Simulador {

	public static void main(String[] args) {

		
		War objetoWar;
		//String fileName = "c:\\personal\\\\Counselor\\simulador\\simulador_GOT20_T5_1922.csv";
		String fileName = "c:\\personal\\\\Counselor\\simulador\\simulador_704 (2).csv";
		
		System.out.println(" --------------------------------------------------- ");
		if (args.length > 0) {
			fileName = args[0];
			System.out.println("args[0] = "+fileName);
		}
		
		
		try {
			
			//War simulacion = UtilFile.leerSimulacion("c:\\personal\\\\Counselor\\simulador.csv");
			//War simulacion = UtilFile.leerSimulacion("c:\\personal\\\\Counselor\\simulador_T3.csv");
			//War simulacion = UtilFile.leerSimulacion("c:\\personal\\\\Counselor\\simulador_T12_1663.csv");
			//War simulacion = UtilFile.leerSimulacion("c:\\personal\\\\Counselor\\simulador_T12_2059.csv");
			//War simulacion = UtilFile.leerSimulacion("c:\\personal\\\\Counselor\\simulador_T12_2158.csv");
			objetoWar = UtilFile.leerSimulacion(fileName);
			

			SimuladorWar.iniciarGuerra(objetoWar);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR: "+e.getMessage());
		}

	}

	
	
}
