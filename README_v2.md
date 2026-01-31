# Projet CSI2120/CSI2520 - Hiver 2026

## Nom et Numero Etudiant des membres du groupe

- Nom: ______________________  Numero etudiant: ______________________
- Nom: ______________________  Numero etudiant: ______________________
- Nom: ______________________  Numero etudiant: ______________________

## Reference

Partie du code concernee

Methode writeOutput dans GaleShapley, qui ecrit un fichier texte en utilisant PrintWriter et FileWriter, et qui itere sur des HashMap via values().

public void writeOutput(String outputFilename, ArrayList<Resident> unmatched) throws IOException {
    PrintWriter out = new PrintWriter(new FileWriter(outputFilename));
    ...
}

References officielles et tutoriels Oracle

1) Oracle. The Java Tutorials. Character Streams.
   https://docs.oracle.com/javase/tutorial/essential/io/charstreams.html

2) Oracle. The Java Tutorials. Catching and Handling Exceptions.
   Exemple qui utilise PrintWriter et FileWriter.
   https://docs.oracle.com/javase/tutorial/essential/exceptions/handling.html

3) Oracle. Java Platform, Standard Edition 8 API. Class PrintWriter.
   https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html

4) Oracle. Java Platform, Standard Edition. Class FileWriter.
   https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/io/FileWriter.html

5) Oracle. Java Platform, Standard Edition 8 API. Class HashMap.
   https://docs.oracle.com/javase/8/docs/api/?java%2Futil%2FHashMap.html=
