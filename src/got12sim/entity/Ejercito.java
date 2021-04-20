package got12sim.entity;
import java.util.ArrayList;

import got12sim.util.UtilWar;

public class Ejercito {

	String name;
	Integer morale;
	Integer commandRang;
	Integer tactica;
	Integer cityLevel = 0;
	Integer magicDefense = 0;
	Integer magicAttack = 0;
	Integer dammageInflicted = 0;
	Boolean estaVivo = true;
	String tacticaName;


	public String getTacticaName() {
		return tacticaName;
	}

	public void setTacticaName(String tacticaName) {
		this.tacticaName = tacticaName;
	}

	public boolean isEstaVivo() {
		return estaVivo;
	}

	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}

	public int getDammageInflicted() {
		return dammageInflicted;
	}

	public void cleanDammageInflicted() {
		dammageInflicted = 0;
	}
	public int setDammageInflicted(int inflicted,int inflictedMagic,War war, boolean cityDefensor) {
		
		int dammageRestante = 0;
		int totalInflicted = inflicted + inflictedMagic;
		war.logSimulador.addLog("     FALTA correccion de daño por Estrategia, sumamos el daño mas el daño magico ("+inflicted+"+"+inflictedMagic+")="+totalInflicted);
		
		if (this.getMagicDefense() > 0) {
			if (totalInflicted < this.getMagicDefense()) {
				this.setMagicDefense(this.getMagicDefense()-totalInflicted);
				war.logSimulador.addLog("     Powerful protection magic saved the lives, deflecting "+totalInflicted+" <-- Army: "+this.name);
				return 0;
			} 
			totalInflicted = totalInflicted - this.getMagicDefense();
			war.logSimulador.addLog("     Powerful protection magic saved the lives, deflecting "+this.getMagicDefense()+" --> inflicted ="+totalInflicted+" <-- Army: "+this.name);
			this.setMagicDefense(0);
		}
		
		int totalDefensa = UtilWar.obtenerDefensaEjercito(this, war.terrain,cityDefensor).intValue();
		// añadimos al daño que se ya tiene en esta ronda, al que de añadimos (por si sobra de otro ejercito).
		dammageInflicted = dammageInflicted + totalInflicted;
		
		// si añadimos mas daño del que soporta, retornamos el daño sobrante para "otro" ejercito.
		if (dammageInflicted.intValue() > totalDefensa) {
			dammageRestante = dammageInflicted.intValue()-totalDefensa;
			dammageInflicted = totalDefensa;
		} 
		war.logSimulador.addLog("     Ejercito de "+name+" (Def:"+totalDefensa+") recibe "+totalInflicted+"("+dammageInflicted+") --> dammageRestante:"+dammageRestante);
		
		if (dammageRestante>0) {
			war.logSimulador.addLog("     Ejercito de "+name+" --> ELIMINADO");
			this.setEstaVivo(false);
		}
		return dammageRestante;
	}

	public Integer getMagicDefense() {
		return magicDefense;
	}

	/**
	 * attack force by 20 per each wizards skill point.
	 * 
	 * @param magicAttack
	 */
	public void setMagicAttack(Integer magicAttack) {
		this.magicAttack = magicAttack;
	}
	
	public Integer getMagicAttack() {
		return magicAttack;
	}

	/**
	 * MagicDefense = 20 * nivel del mago.
	 * 
	 * @param magicDefense
	 */
	public void setMagicDefense(Integer magicDefense) {
		this.magicDefense = magicDefense;
	}
	
	public ArrayList<Tropa> tropas = new ArrayList<Tropa>(); // Create an ArrayList object
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMorale() {
		return morale;
	}

	public void setMorale(Integer morale) {
		this.morale = morale;
	}

	public Integer getCommandRang() {
		return commandRang;
	}

	public void setCommandRang(Integer commandRang) {
		this.commandRang = commandRang;
	}

	public Integer getTactica() {
		return tactica;
	}

	public void setTactica(Integer tactica) {
		this.tactica = tactica;
	}

	public Integer getCityLevel() {
		return cityLevel;
	}

	public void setCityLevel(Integer cityLevel) {
		this.cityLevel = cityLevel;
	}

	
	
	
	
	
    //Esto es un constructor para Ejercito
	public Ejercito (String aName) {
		name = aName;
		commandRang = 10;
		morale = 10;
		tactica = 100;
    }
	
	
	public void addTropa(Tropa tropa) {
		tropas.add(tropa);
		System.out.println("     Add to Ejercito: "+name+" <-- "+tropa.toString());

	}
	

	public String toString() {
		return "Ejercito de ["+name+"] con (commandRang: "+commandRang+" / morale: "+morale+"), tactica: "+tacticaName+", Magic Attack: "+magicAttack+" / Magic Defence: "+magicDefense;
	}
	
	/**
	 * calculo del numero de tropas de un ejercito, es para el reparto de daño.
	 * 
	 * @return
	 */
	public float getNumTropas() {
		float numTropas = 0;
		for (Tropa tropa : tropas) {
			numTropas = numTropas + tropa.getQuantity();
		}
		return numTropas;
	}
}
