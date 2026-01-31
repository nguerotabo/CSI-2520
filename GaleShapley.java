// Project CSI2120/CSI2520
// Winter 2026
// Robert Laganiere, uottawa.ca

import java.io.*;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;

// this is the (incomplete) class that will generate the resident and program maps
public class GaleShapley {

    public HashMap<Integer, Resident> residents;
    public HashMap<String, Program> programs;

    public GaleShapley(String residentsFilename, String programsFilename) throws IOException,
            NumberFormatException {

        readResidents(residentsFilename);
        readPrograms(programsFilename);
    }

    // Reads the residents csv file
    // It populates the residents HashMap
    public void readResidents(String residentsFilename) throws IOException,
            NumberFormatException {

        String line;
        residents = new HashMap<Integer, Resident>();
        BufferedReader br = new BufferedReader(new FileReader(residentsFilename));

        int residentID;
        String firstname;
        String lastname;
        String plist;
        String[] rol;

        // Read each line from the CSV file
        line = br.readLine(); // skipping first line
        while ((line = br.readLine()) != null && line.length() > 0) {

            int split;
            int i;

            // extracts the resident ID
            for (split = 0; split < line.length(); split++) {
                if (line.charAt(split) == ',') {
                    break;
                }
            }
            if (split > line.length() - 2) {
                throw new IOException("Error: Invalid line format: " + line);
            }

            residentID = Integer.parseInt(line.substring(0, split));
            split++;

            // extracts the resident firstname
            for (i = split; i < line.length(); i++) {
                if (line.charAt(i) == ',') {
                    break;
                }
            }
            if (i > line.length() - 2) {
                throw new IOException("Error: Invalid line format: " + line);
            }

            firstname = line.substring(split, i);
            split = i + 1;

            // extracts the resident lastname
            for (i = split; i < line.length(); i++) {
                if (line.charAt(i) == ',') {
                    break;
                }
            }
            if (i > line.length() - 2) {
                throw new IOException("Error: Invalid line format: " + line);
            }

            lastname = line.substring(split, i);
            split = i + 1;

            Resident resident = new Resident(residentID, firstname, lastname);

            for (i = split; i < line.length(); i++) {
                if (line.charAt(i) == '"') {
                    break;
                }
            }

            // extracts the program list
            plist = line.substring(i + 2, line.length() - 2);
            String delimiter = ","; // Assuming values are separated by commas
            rol = plist.split(delimiter);

            resident.setROL(rol);

            residents.put(residentID, resident);
        }
    }

    // Reads the programs csv file
    // It populates the programs HashMap
    public void readPrograms(String programsFilename) throws IOException,
            NumberFormatException {

        String line;
        programs = new HashMap<String, Program>();
        BufferedReader br = new BufferedReader(new FileReader(programsFilename));

        String programID;
        String name;
        int quota;
        String rlist;
        int[] rol;

        // Read each line from the CSV file
        line = br.readLine(); // skipping first line
        while ((line = br.readLine()) != null && line.length() > 0) {

            int split;
            int i;

            // extracts the program ID
            for (split = 0; split < line.length(); split++) {
                if (line.charAt(split) == ',') {
                    break;
                }
            }
            if (split > line.length() - 2) {
                throw new IOException("Error: Invalid line format: " + line);
            }

            programID = line.substring(0, split);
            split++;

            // extracts the program name
            for (i = split; i < line.length(); i++) {
                if (line.charAt(i) == ',') {
                    break;
                }
            }
            if (i > line.length() - 2) {
                throw new IOException("Error: Invalid line format: " + line);
            }

            name = line.substring(split, i);
            split = i + 1;

            // extracts the program quota
            for (i = split; i < line.length(); i++) {
                if (line.charAt(i) == ',') {
                    break;
                }
            }
            if (i > line.length() - 2) {
                throw new IOException("Error: Invalid line format: " + line);
            }

            quota = Integer.parseInt(line.substring(split, i));
            split = i + 1;

            Program program = new Program(programID, name, quota);

            for (i = split; i < line.length(); i++) {
                if (line.charAt(i) == '"') {
                    break;
                }
            }

            // extracts the resident list
            rlist = line.substring(i + 2, line.length() - 2);
            String delimiter = ","; // Assuming values are separated by commas
            String[] rol_string = rlist.split(delimiter);
            rol = new int[rol_string.length];
            for (int j = 0; j < rol_string.length; j++) {

                rol[j] = Integer.parseInt(rol_string[j]);
            }

            program.setROL(rol);

            programs.put(programID, program);
        }
    }

    public ArrayList<Resident> match() {
        ArrayList<Resident> unmatched = new ArrayList<Resident>();
        Queue<Resident> available = new ArrayDeque<Resident>();

        for (Resident r : residents.values()) {
            if (!r.isMatched() && r.hasMorePrograms()) {
                available.add(r);
            }
        }

        // while (exists available resident)
        while (!available.isEmpty()) {

            // pick any available resident r 
            Resident r = available.remove();

            boolean isMatchedNow = false;

            // for all program p in ROLresidents(r) 
            while (r.hasMorePrograms() && !isMatchedNow) {
                String programID = r.getCurrentProgram();
                r.nextProgram();

                Program p = programs.get(programID);

                // si le programme n'existe pas, on passe au suivant
                if (p == null) {
                    continue;
                }

                // if (r ∉ ROLprograms(p)) continue
                if (!p.member(r.getId())) {
                    continue;
                }

                // sinon, on laisse le programme decider quota / remplacement / rejet
                Resident result = p.addResident(r);

                // si accepte sans expulser
                if (result == null) {
                    r.setMatchedProgram(p.getID());
                    isMatchedNow = true;
                } // si expulsion
                else if (result.getId() != r.getId()) {
                    Resident expelled = result;

                    expelled.setMatchedProgram(null);
                    if (expelled.hasMorePrograms()) {
                        available.add(expelled);
                    } else {
                        unmatched.add(expelled);
                    }

                    r.setMatchedProgram(p.getID());
                    isMatchedNow = true;
                }
                // sinon rejet, on continue dans la boucle while pour tester le prochain programme
            }

            // if (r exhausted their ROL) then Unmatched += r
            if (!r.isMatched() && !r.hasMorePrograms()) {
                unmatched.add(r);
            }

        }
        return unmatched;

    }

    public void writeOutput(String outputFilename, ArrayList<Resident> unmatched) throws IOException { // ecrire la sortie dans un fichier texte

    PrintWriter out = new PrintWriter(new FileWriter(outputFilename));

    out.println("lastname,firstname,residentID,programID,name");

    ArrayList<Resident> list = new ArrayList<Resident>(residents.values()); // on copie les residents dans une liste pour le tri
    Collections.sort(list, new Comparator<Resident>() {
        public int compare(Resident a, Resident b) {
            int c = a.getLastName().compareTo(b.getLastName());
            if (c != 0) return c;
            c = a.getFirstName().compareTo(b.getFirstName());
            if (c != 0) return c;
            return Integer.compare(a.getId(), b.getId());
        }
    });

    int unmatchedCount = 0;

for (Resident r : list) { // on parcourt les residents triés

        String pid = r.getMatchedProgram();

        if (pid == null) { // pas de programme matché
            out.println(r.getLastName() + "," + r.getFirstName() + "," + r.getId() + ",XXX,NOT_MATCHED");
            unmatchedCount++;
        } else { 
            Program p = programs.get(pid);
            String pname = (p == null) ? "" : p.getName();
            out.println(r.getLastName() + "," + r.getFirstName() + "," + r.getId() + "," + pid + "," + pname);
        }
    }

    int positionsAvailable = 0;
    for (Program p : programs.values()) { // on calcule le nombre de positions disponibles
        positionsAvailable += (p.getQuota() - p.getMatchedCount());
    }

    out.println("Number of unmatched residents: " + unmatchedCount);
    out.println("Number of positions available: " + positionsAvailable);

    out.close();
}


    public static void main(String[] args) {

        try {

            GaleShapley gs = new GaleShapley(args[0], args[1]);

            //retirer les deux system.out.println en commentaires pour debug 

            //System.out.println(gs.residents);
            //System.out.println(gs.programs);

            // lancer l'algorithme de jumelage
            java.util.ArrayList<Resident> unmatched = gs.match();

            // ecrire la sortie dans un fichier texte
            gs.writeOutput(args[2], unmatched);

        } catch (Exception e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

    }
}
