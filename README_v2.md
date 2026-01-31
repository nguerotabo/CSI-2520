# Projet CSI2120/CSI2520 - Hiver 2026

## Nom et Numero Etudiant des membres du groupe

- Nom: ______________________ 
-  Numero etudiant: ______________________
- Nom: Brayan Adou  
- Numero etudiant: 300433616


## Reference

### Partie du code concernee

Methode writeOutput dans GaleShapley

- Ecriture d un fichier texte ligne par ligne avec PrintWriter et FileWriter
- Parcours des residents et programmes a partir de HashMap.values()
- Tri des residents avec Collections.sort et Comparator
- Calculs de compteurs et impression des lignes finales

Code concerne

public void writeOutput(String outputFilename, ArrayList<Resident> unmatched) throws IOException {
    PrintWriter out = new PrintWriter(new FileWriter(outputFilename));
    out.println("lastname,firstname,residentID,programID,name");
    ArrayList<Resident> list = new ArrayList<Resident>(residents.values());
    Collections.sort(list, new Comparator<Resident>() { ... });
    ...
    out.close();
}

### References officielles pertinentes

Ecriture de fichier texte

1) Oracle. The Java Tutorials. I O from the Command Line.
   Section Writing a File. (exemples d ecriture avec FileWriter et PrintWriter)
   https://docs.oracle.com/javase/tutorial/essential/io/cl.html

2) Oracle. The Java Tutorials. Character Streams. (FileWriter et ecriture de texte)
   https://docs.oracle.com/javase/tutorial/essential/io/charstreams.html

API Java utilisees

3) Oracle. Java SE API. Class FileWriter.
   https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html

4) Oracle. Java SE API. Class PrintWriter.
   https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html

Iteration sur HashMap

5) Oracle. Java SE API. Interface Map. Method values.
   https://docs.oracle.com/javase/8/docs/api/java/util/Map.html#values--

6) Oracle. Java SE API. Class HashMap.
   https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html

Tri et comparateur

7) Oracle. Java SE API. Class Collections. Method sort.
   https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#sort-java.util.List-java.util.Comparator-

8) Oracle. Java SE API. Interface Comparator.
   https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html

Structures de donnees

9) Oracle. Java SE API. Class ArrayList.
   https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

Gestion d exceptions

10) Oracle. Java SE API. Class IOException.
    https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html
