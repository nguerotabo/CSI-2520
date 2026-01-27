// Project CSI2120/CSI2520
// Winter 2026
// Robert Laganiere, uottawa.ca

// this is the (incomplete) Program class
public class Program {
	
	private String programID;
	private String name;
	private int quota;
	private int[] rol;
	private String[] matchedResidents;
	private int matchedCount=0;
	 // index du prochain résident à proposer
	
	// constructs a Program
    public Program(String id, String n, int q) {
	
		programID= id;
		name= n;
		quota= q;
		matchedResidents= new String[quota];
		
	}

    // the rol in order of preference
	public void setROL(int[] rol) {
		
		this.rol= rol;
	}

	public int[] getROL(){
		return rol;
	}
	
	// string representation
	public String toString() {
      
       return "["+programID+"]: "+name+" {"+ quota+ "}" +" ("+rol.length+")";	  
	}

	//Getters et setters

	public void setID(String ID){
		this.programID = ID;
	}

	public String getID(){
		return programID;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setQuota(int quota){
		this.quota = quota;
	}

	public int getQuota(){
		return quota;
	}
	

	//Nouvelles méthodes 

	public boolean member (int residentID){ // pas faite
		return false;
	}

	public int rank (int residentID){ // retourne le rang du resident dans le rol du programme
		for (int i = 0; i < rol.length; i++){
			if (rol[i] == residentID){
				return i;
			}
		}
		return -1;
	}

	public int leastPreferred(){
		if (matchedCount == 0){
			return -1; // pas de resident
		}
		int leastPreferredRank = -1;
		int leastPreferredResidentID = -1;
		for (int i = 0; i < matchedCount; i++){
			int residentID = Integer.parseInt(matchedResidents[i]); // convertir en int
			int rank = rank(residentID);
			if (rank > leastPreferredRank){ // on veut le rang le plus élevé 
				leastPreferredRank = rank;
				leastPreferredResidentID = residentID;
			}
		}
		return leastPreferredResidentID;
	}

	public void addResident(int resident){ // pas faite 
	}
}