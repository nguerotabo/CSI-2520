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
	
	// constructs a Program
    public Program(String id, String n, int q) {
	
		programID= id;
		name= n;
		quota= q;
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

	public boolean member (int residentID){
		return false;
	}

	public int rank (int residentID){
		return -1;
	}

	public int leastPreferred(){
		return -1;
	}

	public void addResident(int resident){
	}
}