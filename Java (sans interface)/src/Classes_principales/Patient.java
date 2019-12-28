package Classes_principales;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Patient {
    public static boolean check(int ID) throws IOException {
	   boolean Check = false;
	   File Patients = new File("data/Patient.txt");
	   FileReader reader = new FileReader(Patients);
	   Scanner scanner = new Scanner(reader);
	   while (scanner.hasNextLine()) {
		  String ligne = scanner.nextLine();
		  if (Integer.parseInt(ligne.substring(0, ligne.indexOf(" "))) == (ID)) {
			 Check = true;
			 break;
		  } else {
			 Check = false;
		  }

	   }
	   scanner.close();
	   return Check;
    }		// verifier si le patient existe

    public static String recherche(int ID) throws FileNotFoundException {
	   File Patients = new File("data/Patient.txt");
	   FileReader reader = new FileReader(Patients);
	   Scanner scanner = new Scanner(reader);
	   String details = "";

	   while (scanner.hasNextLine()) {
		  String ligne = scanner.nextLine();
		  String nom = ligne.substring(ligne.indexOf("Nom: "), ligne.indexOf("$N"));
		  String prenom = ligne.substring(ligne.indexOf("Prenom: "), ligne.indexOf("$Pn"));
		  String adresse = ligne.substring(ligne.indexOf("Adresse: "), ligne.indexOf("$A"));
		  String ddn = ligne.substring(ligne.indexOf("Date de naissance: "), ligne.indexOf("$Date"));
		  if (Integer.parseInt(ligne.substring(0, ligne.indexOf(" "))) == (ID)) {
			 String Nom = nom.substring(nom.indexOf(" "));
			 String Prenom = prenom.substring(prenom.indexOf(" "));
			 String Adresse = adresse.substring(adresse.indexOf(" "));
			 String DDN = ddn.substring(ddn.lastIndexOf(" "));
			 // pour que les details s'affichent pas avec les $ et tout
			 details = "ID : " + ID + " ,Nom :" + Nom + " ,Prenom :" + Prenom + " ,Adresse :" + Adresse + ", Date de naissance : " + DDN;
			 break;
		  }
	   }
	   scanner.close();
	   return details;
    }		//details du patient

    public static void nouveauPatient(String nom, String prenom, int Adresse, String Date) {
	   File Patient = new File("data/Patient.txt");
	   File temporaire = new File("data/Temporaire_Patient.txt");
	   Consultations.copyTemporaire(Patient, temporaire);
	   try {
		  PrintWriter fileWriter = new PrintWriter(Patient);
		  InputStream inputStream = new FileInputStream(temporaire);
		  InputStreamReader reader = new InputStreamReader(inputStream);
		  Scanner scanner = new Scanner(reader);

		  String patient = "Nom: " + nom + "$N \t\tPrenom: " + prenom + "$P \t\t\tAdresse: " + Adresse + "$A \t\tDate de naissance: " + Date + "$Date";

		  int counter = 1;

		  while (scanner.hasNextLine()) {
			 String string = scanner.nextLine();
			 fileWriter.println(string);
			 counter = Integer.parseInt(string.substring(0, string.indexOf(" ")));
			 counter++;

		  }
		  fileWriter.write(counter + " " + patient);
		  fileWriter.close();
		  scanner.close();
		  boolean bool = temporaire.delete();
	   } catch (IOException e) {
		  e.printStackTrace();
	   }
    }	//creer un nouveau patient

    public static void supprimerPatient(int ID) {
	   File Patient = new File("data/Patient.txt");
	   File Temporaire = new File("data/Temporaire_Patient.txt");
	   Consultations.copyTemporaire(Patient, Temporaire);

	   try {
		  PrintWriter fileWriter = new PrintWriter(Patient);
		  InputStream inputStream = new FileInputStream(Temporaire);
		  InputStreamReader reader = new InputStreamReader(inputStream);
		  Scanner scanner = new Scanner(reader);

		  while (scanner.hasNextLine()) {
			 String string = scanner.nextLine();
			 if (Integer.parseInt(string.substring(0, string.indexOf(" "))) != ID) {
				fileWriter.println(string);
			 }
		  }
		  fileWriter.close();
		  scanner.close();
		  boolean bool = Temporaire.delete();
	   } catch (IOException i) {
		  i.printStackTrace();
	   }
    }		//supprimer un patient

    public static void modifier(int ID, String nom, String prenom, int Adresse, String Date) throws FileNotFoundException{
        File Patient = new File("data/Patient.txt");
        File Temporary_Patient = new File("data/Temporaire_Patient.txt");
	   Consultations.copyTemporaire(Patient, Temporary_Patient);

	   try{
            PrintWriter fileWriter = new PrintWriter(Patient);
            InputStream inputStream = new FileInputStream(Temporary_Patient);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                if (Integer.parseInt(string.substring(0, string.indexOf(" ")))== ID) {
                    fileWriter.println(ID + " Nom: " + nom +"$N\t\tPrenom: " + prenom + "$Pn\t\tAdresse:" + Adresse + "$A\t\tDate de naissance: " + Date + "$Date");
                } else {
                    fileWriter.println(string);
                }

            }
            fileWriter.close();
            scanner.close();
            boolean bool = Temporary_Patient.delete();
        } catch (IOException e) {
	       e.printStackTrace();
	   }
    }		//modifier un patient
    
}
