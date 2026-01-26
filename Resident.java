// Project CSI2120/CSI2520
// Winter 2026
// Robert Laganiere, uottawa.ca

// this is the (incomplete) Resident class
public class Resident {
	
	private int residentID;
	private String firstname;
	private String lastname;
	private String[] rol;
	private String matchedProgram;
	private String matchedRank;
	
	// constructs a Resident
    public Resident(int id, String fname, String lname) {
	
		residentID= id;
		firstname= fname;
		lastname= lname;

	}

    // the rol in order of preference
	public void setROL(String[] rol) {
		
		this.rol= rol;
	}

	public String[] getROL(){
		return rol;
	}

	public void setId(int Id){
		this.residentID = Id;
	}
	
	public int getId(){
		return residentID;
	}

	public void setFirstName(String fname){
		this.firstname = fname;
	}
	
	public String getFirstName(){
		return firstname;
	}

	public void setLastName(String lname){
		this.lastname = lname;
	}
	
	public String getLastName(){
		return lastname;
	}

	// string representation
	public String toString() {
      
       return "["+residentID+"]: "+firstname+" "+ lastname+" ("+rol.length+")";	  
	}
}