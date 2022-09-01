
package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import classes.*;
import exception.AppException;
import fen.MainMenu;

/**
 * Main de l'application
 * 
 * @author SRet
 */
public class App {

	/**
	 * Ensemble des donnees de la pharmacie
	 */
	private static Pharmacie pharma = new Pharmacie();
	/**
	 * fichier ou les donnees sont enregistres
	 */
	private static String file = "donnees";

	public static Pharmacie getPharma() {
		return pharma;
	}

	/**
	 * Entree de l'application
	 * 
	 * @param args : arguments de l'application
	 * @throws AppException : erreur de saisi
	 */
	public static void main(String[] args) throws AppException {

//		Adresse adresse1 = new Adresse("37", "Rue Julie Daubié", "54000",
//				"Nancy");
//		Adresse adresse2 = new Adresse("61", "rue Stanislas", "54000", "Nancy");
//		Adresse adresse3 = new Adresse("61", "avenue Foch", "54000", "Nancy");
//		Adresse adresse4 = new Adresse("30", "rue de Nancy", "54630",
//				"Richardménil");
//		Adresse adresse5 = new Adresse("75", "Boulevard Maréchal Foch", "54520",
//				"Laxou");
//		Adresse adresse6 = new Adresse("29", "Rue de la Commanderie", "54000",
//				"Nancy");
//		Adresse adresse7 = new Adresse("20", "Rue de la mine", "54230",
//				"Neuves-Maisons");
//		Adresse adresse8 = new Adresse("9", "Rue Maurice Barres", "54000",
//				"Nancy");
//		Adresse adresse9 = new Adresse("19", "rue Saint-Jean", "54000",
//				"Nancy");
//		Mutuelle mutuelle1 = new Mutuelle("MGEN", adresse8, "3976",
//				"acceuil@mgen.fr", "Meurthe-et-Moselle", 80, pharma);
//		Mutuelle mutuelle2 = new Mutuelle("Harmonie Mutuelle", adresse9,
//				"09.80.98.08.80", "contacter@harmoniemutuelle.fr",
//				"Meurthe-et-Moselle", 70, pharma);
//		Specialiste specialiste1 = new Specialiste("Titor", "John", adresse1,
//				"06.65.20.40.32", "j.titor@hotmail.fr", Specialite.Urologie,
//				pharma);
//		Specialiste specialiste2 = new Specialiste("Tisserand", "Anne",
//				adresse1, "03.83.95.10.95", "a.tisserand@gmail.com",
//				Specialite.Cardiologie, pharma);
//		Specialiste specialiste3 = new Specialiste("Thess", "François",
//				adresse2, "03.83.35.19.76", "t.francois@orange.fr",
//				Specialite.Dermatologie, pharma);
//		Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse6,
//				"03.83.40.25.97", "c.nathalie@hotmail.fr", "1562038064121782",
//				pharma);
//		Medecin medecin2 = new Medecin("Baruffle", "Mireille", adresse7,
//				"03.83.50.19.01", "m.baruffaldi@gmail.com", "4087203165981412",
//				pharma);
//		Client client1 = new Client("Retournay", "Steve", adresse4,
//				"06.81.30.29.76", "190017512703025", "steve54@wanadoo.fr", "1990",
//				"01", "03", medecin1, mutuelle1, pharma);
//		Client client2 = new Client("Sublion", "Julien", adresse5,
//				"06.25.64.13.20", "186015411726363", "j.sublion@hotmail.fr",
//				"1986", "08", "14", medecin2, mutuelle1, pharma);
//		Client client3 = new Client("Henri", "Thomas", adresse3,
//				"06.25.99.11.30", "154028814523624", "t.henri@orange.fr", "1954",
//				"02", "28", medecin1, mutuelle2, pharma);
//		Medicament medicament1 = new Medicament("Acuitel", "Antihypertenseur",
//				3, 50, "1989", "04", "14", pharma);
//		Medicament medicament2 = new Medicament("Doliprane", "antalgique", 2,
//				60, "1984", "01", "01", pharma);
//		Medicament medicament3 = new Medicament("Oxybutine",
//				"anticholinergique", 3, 40, "2012", "11", "16", pharma);
//		Medicament medicament4 = new Medicament("Parrafin", "Emoliant", 10, 80,
//				"2017", "07", "19", pharma);
//		Medicament medicament5 = new Medicament("Amoxicilline", "Antibiotique",
//				1, 60, "1953", "05", "02", pharma);
//		client1.setSpecialiste(specialiste1);
//		client1.setSpecialiste(specialiste2);
//		client1.setSpecialiste(specialiste3);
//		client2.setSpecialiste(specialiste1);
//		client2.setSpecialiste(specialiste2);
//		client2.setSpecialiste(specialiste3);
//		client3.setSpecialiste(specialiste1);
//		client3.setSpecialiste(specialiste2);
//		client3.setSpecialiste(specialiste3);
//		save(pharma, file);
		pharma = (Pharmacie) load(file);
		start();
	}

	/**
	 * methode d'ouverture de l'interface de l'applicattion
	 */
	private static void start() {
		MainMenu menu = new MainMenu();
		menu.setVisible(true);
	}

	/**
	 * Methode de sauvegarde des fichiers de l'application
	 * 
	 * @param object : objet a sauvegarder
	 * @param file   : fichier ou sauvegarder l'objet
	 */
	public static void save(Object object, String file) {

		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(object);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode de recuperation de la pharmacie sauvegarde
	 * 
	 * @param file : fichier a lire
	 * @return les donnees de la pharmacie sauvegarde dans le fichier
	 */
	public static Object load(String file) {

		Object objet = null;
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			objet = in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Le fichier 'donnees' n'existe pas.", "Erreur de lancement",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return objet;
	}

}
