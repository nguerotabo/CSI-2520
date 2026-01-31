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
    private int matchedRank;
    private int nextProgramIndex = 0; // index du prochain programme à proposer

    // constructs a Resident
    public Resident(int id, String fname, String lname) {

        residentID = id;
        firstname = fname;
        lastname = lname;
        matchedProgram = null;
        rol = new String[0];

    }

    // the rol in order of preference
    public void setROL(String[] rol) {

        this.rol = rol;
    }

    public String[] getROL() {
        return rol;
    }

    public void setId(int Id) {
        this.residentID = Id;
    }

    public int getId() {
        return residentID;
    }

    public void setFirstName(String fname) {
        this.firstname = fname;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setLastName(String lname) {
        this.lastname = lname;
    }

    public String getLastName() {
        return lastname;
    }

    // string representation
    public String toString() {

        return "[" + residentID + "]: " + firstname + " " + lastname + " (" + rol.length + ")";
    }

    public boolean hasMorePrograms() { // on veux voir savoir si il y a plus de programmes à proposer
        return nextProgramIndex < rol.length;
    }

    public void nextProgram() { // on incrémente l'index du prochain programme à proposer
        nextProgramIndex++;
    }

    public String getCurrentProgram() { // on retourne le programme courant à proposer
        if (nextProgramIndex < rol.length) {
            return rol[nextProgramIndex];
        }
        return null;
    }

    public void setMatchedProgram(String program) {// on assigne le programme matché
        this.matchedProgram = program;
    }

    public String getMatchedProgram() { // on retourne le programme matché
        return matchedProgram;
    }

    public boolean isMatched() { // on vérifie si le résident est matché
        return matchedProgram != null;
    }

    public int getRankOfMatchedProgram() { // on retourne le rang du programme matché
        for (int i = 0; i < rol.length; i++) {
            if (rol[i].equals(matchedProgram)) {
                return i;
            }
        }
        return -1; // si le programme n'est pas trouvé
    }
}
