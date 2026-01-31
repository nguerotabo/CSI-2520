// Project CSI2120/CSI2520
// Winter 2026
// Robert Laganiere, uottawa.ca

// this is the (incomplete) Program class
public class Program {

    private String programID;
    private String name;
    private int quota;
    private int[] rol;
    private Resident[] matchedResidents;

    private int matchedCount = 0;

    // index du prochain résident à proposer
    // constructs a Program
    public Program(String id, String n, int q) {

        programID = id;
        name = n;
        quota = q;
        matchedResidents = new Resident[quota];

    }

    // the rol in order of preference
    public void setROL(int[] rol) {

        this.rol = rol;
    }

    public int[] getROL() {
        return rol;
    }

    // string representation
    public String toString() {

        return "[" + programID + "]: " + name + " {" + quota + "}" + " (" + rol.length + ")";
    }

    //Getters et setters
    public void setID(String ID) {
        this.programID = ID;
    }

    public String getID() {
        return programID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getQuota() {
        return quota;
    }

    public int getMatchedCount() {
    return matchedCount;
}


    //Nouvelles méthodes 
    public boolean member(int residentID) { //on veut retourner vrai si l'utilisateur fait parti de la liste de preference pour ce programme
        for (int i = 0; i < rol.length; i++) {
            if (rol[i] == residentID) {
                return true;
            }
        }
        return false;
    }

    public int rank(int residentID) { // retourne le rang du resident dans le rol du programme
        for (int i = 0; i < rol.length; i++) {
            if (rol[i] == residentID) {
                return i;
            }
        }
        return -1;
    }

    public Resident leastPreferred() {
        if (matchedCount == 0) {
            return null; // pas de resident
        }
        Resident leastPreferredResident = matchedResidents[0];
        int leastPreferredRank = rank(leastPreferredResident.getId());
        for (int i = 0; i < matchedCount; i++) {
            Resident residentID = matchedResidents[i]; 
            int rank = rank(residentID.getId());
            if (rank > leastPreferredRank) { // on veut le rang le plus élevé 
                leastPreferredRank = rank;
                leastPreferredResident = residentID;
            }
        }
        return leastPreferredResident;
    }

    public void removeMatchedResident(Resident r) { // retire un resident de la liste des jumeles 
        int index = -1;
        for (int i = 0; i < matchedCount; i++) {
            if (matchedResidents[i].getId() == r.getId()) {
                index = i;
                break;
            }

        }
        if (index == -1) {
            return;
        }

        // on veut décaller les résidents restants sur la gauche
        for (int i = index; i < matchedCount - 1; i++) {
            matchedResidents[i] = matchedResidents[i + 1];
        }

        matchedCount--;
        matchedResidents[matchedCount] = null;

    }

    public Resident addResident(Resident r) { // tente d'ajouter un resident au programme, retourne le resident rejeté ou null si aucun rejet

        // Etape 1 : vérifier si le résident fait partie de la liste de préférence
        if (!member(r.getId())) {
            return r;
        }

        // etape 2 : vérifier si le programme a encore de la place
        if (matchedCount < quota) {
            matchedResidents[matchedCount] = r;
            matchedCount++;
            return null;
        }

        // etape 3 : le programme est plein, vérifier si le nouveau résident est préféré au actuel pire résident 
        Resident worst = leastPreferred();
        int rangR = rank(r.getId());
        int rangWorst = rank(worst.getId());

        // Si le candidat est meilleur que le pire, on remplace
        if (rangR < rangWorst) {
            removeMatchedResident(worst);

            matchedResidents[matchedCount] = r;
            matchedCount++;

            return worst;
        }

        // Sinon le candidat est rejete
        return r;

    }
}
