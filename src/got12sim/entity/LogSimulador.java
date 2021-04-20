package got12sim.entity;

import java.util.ArrayList;

public class LogSimulador {
	
	public ArrayList<String> logDetalle = new ArrayList<String>(); 
	public ArrayList<String> logInfo = new ArrayList<String>(); 
	
	
	public void addLog(String log) {

		// DEBUG
		logDetalle.add(log);
	}
	public void addLogInfo(String log) {

		// INFO
		logDetalle.add(log);
		logInfo.add(log);
	}
	
	public void addSalto() {
		logDetalle.add(" ");
	}
	
	public void addSeparador() {
		logDetalle.add(" --------------------------------------------------- ");
	}
	
	
	public void imprimirLog() {

		System.out.println(" --------------------------------------------------- ");
		System.out.println(" LOG COMPLETO SIMULACION ");
		System.out.println(" --------------------------------------------------- ");
		
		// DEBUG
		for (String log : logDetalle) {
			System.out.println(log);
		}
	}

	public void imprimirLogInfo() {

		System.out.println(" --------------------------------------------------- ");
		System.out.println(" LOG RESUMEN ");
		System.out.println(" --------------------------------------------------- ");
		
		// INFO
		for (String log : logInfo) {
			System.out.println(log);
		}
	}

	public void addLogVictoriaAliada(boolean victoriaAliada) {
		addLog(" ");
		
		if (victoriaAliada) {
			// Fin
			addLogInfo("RESULTADO: Victoria");
			addLog("         _______     ");
			addLog("        |       |    ");
			addLog("       (|  WIN  |)   ");
			addLog("        |       |   ");
			addLog("         \\     /    ");
			addLog("           ---      ");
			addLog("          _|_|_     ");
		} else {
			addLogInfo("RESULTADO: Derrota");
			addLog("          .-.     ");
			addLog("        __| |__   ");
			addLog("       [__   __]   ");
			addLog("          | |      ");
			addLog("LOST      | |      ");
			addLog("          | |      ");
			addLog("          '-'      ");
		}
		addLog(" ");
		addLog(" ");
	}
	
	
}
