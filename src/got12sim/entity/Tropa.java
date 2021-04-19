package got12sim.entity;


public class Tropa {

	
	public static String TIPO_ARCHER = "Archers";
	public static String TIPO_Bloodriders = "Bloodriders";
	public static String TIPO_CARGO_SHIPS = "Cargo Ships";
	public static String TIPO_Catapults = "Catapults";
	public static String TIPO_CHEVALIER = "Chevalier";
	public static String TIPO_Elite_Footmen = "Elite Footmen";
	public static String TIPO_Freedmen = "Freedmen";
	public static String TIPO_Gladiator_Slaves = "Gladiator Slaves";
	public static String TIPO_Gold_Cloaks = "Gold Cloaks";
	public static String TIPO_HEAVY_CAVALRY = "Heavy Cavalry";
	public static String TIPO_HEAVY_INFANTRY = "Heavy Infantry";
	// Hired Mercenaries	12	0	10	0	12	18	12	10	10
	public static String TIPO_INFANTRY = "Infantry";
	public static String TIPO_Knights_of_the_Vale = "Knights of the Vale";
	// Mountain Giants	20	0	6	0	18	32	15	16	2
	public static String TIPO_MOUNTED_KNIGHTS = "Mounted Knights";
	public static String TIPO_NORTHMEN = "Northmen";
	// Peasants	2	0	2	0	2	2	2	2	2
	// Raiders	5	0	10	0	16	6	5	16	16
	public static String TIPO_Rangers = "Rangers";
	public static String TIPO_Reavers = "Reavers";
	public static String TIPO_SAND_CAVALRY = "Sand Cavalry";
	public static String TIPO_SCOUTS = "Scouts";
	public static String TIPO_SEASONED_VETERANS = "Seasoned Veterans";
	public static String TIPO_SLAVES = "Slaves";
	public static String TIPO_SMUGGLERS_VESSELS = "Smugglers Vessels";
	public static String TIPO_TRIREMES = "Triremes";
	public static String TIPO_KRAKENS = "Krakens";
	// Unknown troops	1	0	1	0	1	1	1	1	1
	// Unsullied	10	0	10	0	16	16	10	16	16
	// Villager Conscripts	2	0	2	0	2	2	2	2	2
	// Wights	5	0	10	0	16	6	5	16	16
	
	private String tipo;
	private Integer quantity;
	private Integer training;
	private Integer weapon;
	private Integer armor;
	private Float coeff_txema;
	
	public Float getCoeff_txema() {
		return coeff_txema;
	}


	public void setCoeff_txema(Float coeff_txema) {
		this.coeff_txema = coeff_txema;
	}
	
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public boolean isTipo(String aTipo) {
		return aTipo.equalsIgnoreCase(tipo);
	}
	

	public Tropa (String atipo) {
		tipo = atipo;
		quantity = 1;
		training = 10;
		weapon = 10;
		armor = 0;
    }
	

	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Integer getTraining() {
		return training;
	}


	public void setTraining(Integer training) {
		this.training = training;
	}


	public Integer getWeapon() {
		return weapon;
	}


	public void setWeapon(Integer weapon) {
		this.weapon = weapon;
	}


	public Integer getArmor() {
		return armor;
	}


	public void setArmor(Integer armor) {
		this.armor = armor;
	}


	//Esto es un constructor para Tropa
	Tropa (String atipo, Integer aQuantity, Integer aTraining, Integer aWeapon, Integer aArmor) {
		tipo = atipo;
		quantity = aQuantity;
		training = aTraining;
		weapon = aWeapon;
		armor = aArmor;
    }
	
	void log() {
		System.out.println("("+quantity+") "+tipo+ " [weapon:"+weapon+"/armor:"+armor+"]");
	}
	
	public String toString() {
		return "("+quantity+") "+tipo+ " [training:"+training+"/weapon:"+weapon+"/armor:"+armor+"]";
	}
	

	

	
	
}
