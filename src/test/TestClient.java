package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import classes.*;
import exception.AppException;

class TestClient {

	private static Pharmacie pharmaTest = new Pharmacie();
	private Client client1 = null;

	@Test
	void testEmail() {
		String regex = "[\\S]{0,}@[\\S]{0,}.[\\S]{0,3}";
		String email = "tjupiter@gmail.com";
		assertTrue(Pattern.matches(regex, email));
	}

	@Test
	void testClient() throws AppException, ParseException {
		Adresse adresse = new Adresse("15", "Rue des Ponts", "54000", "Nancy");
		Adresse adresse2 = new Adresse("30", "rue de Nancy", "54630",
				"Richardmenil");
		Adresse adresse3 = new Adresse("9", "Rue Maurice Barres", "54000",
				"Nancy");
		Mutuelle mutuelle1 = new Mutuelle("MGEN", adresse3, "3976",
				"acceuil@mgen.fr", "Meurthe-et-Moselle", 80, pharmaTest);
		Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse,
				"03.83.40.25.97", "c.nathalie@hotmail.fr", "1562038064121782",
				pharmaTest);
		// test Nom mal entre
		try {
			client1 = new Client("retournay", "Steve", adresse2,
					"06.81.30.29.76", "190017512703025", "steve54wanadoo.fr",
					"1990", "01", "03", medecin1, mutuelle1, pharmaTest);
			fail("Le nom du client est correct");
		} catch (AppException e) {
			assert (e.getMessage().contains(
					"Un nom ne contient que des lettres et commence par une majuscule"));
		}
		// test Prenom mal entre
		try {
			client1 = new Client("Retournay", "Steve54", adresse2,
					"06.81.30.29.76", "190017512703025", "steve54wanadoo.fr",
					"1990", "01", "03", medecin1, mutuelle1, pharmaTest);
			fail("Le prénom du client est correct");
		} catch (AppException e) {
			assert (e.getMessage().contains(
					"Un prenom ne contient que des lettres et commence par une majuscule"));
		}
		// test email mal entre
		try {
			client1 = new Client("Retournay", "Steve", adresse2,
					"06.81.30.29.76", "190017512703025", "steve54wanadoo.fr",
					"1990", "01", "03", medecin1, mutuelle1, pharmaTest);
			fail("L'email du client est correct");
		} catch (AppException e) {
			assert (e.getMessage()
					.contains("Un E-Mail s'ecrit au format abc@domaine.fr."));
		}

		// test numero telephone mal entre
		try {
			client1 = new Client("Retournay", "Steve", adresse2,
					"0681.30.29.76", "190017512703025", "steve54@wanadoo.fr",
					"1990", "01", "03", medecin1, mutuelle1, pharmaTest);
			fail("Le telephone du client est correct");
		} catch (AppException e) {
			assert (e.getMessage().contains(
					"Un numero de telephone est forcement au format xx.xx.xx.xx.xx (x étant des chiffres)."));
		}

		try {
			client1 = new Client("Retournay", "Steve", null, "06.81.30.29.76",
					"190017512703025", "steve54@wanadoo.fr", "1990", "01", "03",
					medecin1, mutuelle1, pharmaTest);
			fail("L'adresse du client est correct");
		} catch (AppException e) {
			assert (e.getMessage().contains("Une personne a une adresse"));
		}

		try {
			client1 = new Client("Retournay", "Steve", adresse2, "06.81.30.29.76",
					"190017512703025", "steve54@wanadoo.fr", "1990", "01", "03", null,
					mutuelle1, pharmaTest);
			fail("Un medecin est ajoute");
		} catch (AppException e) {
			assert (e.getMessage().contains("Un medecin est necessaire"));
		}
	}

	@Test
	void testGetters() throws AppException, ParseException {
		Adresse adresse = new Adresse("15", "Rue des Ponts", "54000", "Nancy");
		Adresse adresse2 = new Adresse("30", "rue de Nancy", "54630",
				"Richardmenil");
		Adresse adresse3 = new Adresse("9", "Rue Maurice Barres", "54000",
				"Nancy");
		Mutuelle mutuelle1 = new Mutuelle("MGEN", adresse3, "3976",
				"acceuil@mgen.fr", "Meurthe-et-Moselle", 80, pharmaTest);
		Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse,
				"03.83.40.25.97", "c.nathalie@hotmail.fr", "1562038064121782",
				pharmaTest);
		client1 = new Client("Retournay", "Steve", adresse2, "06.81.30.29.76",
				"190017511902524", "steve54@wanadoo.fr", "1990", "01", "03", medecin1,
				mutuelle1, pharmaTest);
		assertEquals("190017511902524",
				medecin1.getPatients().get(0).getSecuriteSociale());
	}

	@Test
	void testSetterSpecialiste() throws AppException, ParseException {
		Adresse adresse = new Adresse("15", "Rue des Ponts", "54000", "Nancy");
		Adresse adresse2 = new Adresse("30", "rue de Nancy", "54630",
				"Richardmenil");
		Adresse adresse3 = new Adresse("9", "Rue Maurice Barres", "54000",
				"Nancy");
		Mutuelle mutuelle1 = new Mutuelle("MGEN", adresse3, "3976",
				"acceuil@mgen.fr", "Meurthe-et-Moselle", 80, pharmaTest);
		Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse,
				"03.83.40.25.97", "c.nathalie@hotmail.fr", "1562038064121782",
				pharmaTest);
		client1 = new Client("Retournay", "Steve", adresse2, "06.81.30.29.76",
				"190017511902524", "steve54@wanadoo.fr", "1990", "01", "03", medecin1,
				mutuelle1, pharmaTest);
		Specialiste specialiste3 = new Specialiste("Thess", "François",
				adresse2, "03.83.35.19.76", "t.francois@orange.fr",
				Specialite.Dermatologie, pharmaTest);
		client1.setSpecialiste(specialiste3);
		try {
			client1.setSpecialiste(specialiste3);
			fail("Ce specialiste n'est pas deja ajoute au client.");
		} catch (Exception e) {
			assert (e.getMessage()
					.contains("Ce specialiste est deja ajoute au client"));
		}
		assertEquals(client1.getSpecialiste().contains(specialiste3), true);
	}

	@Test
	void testSetterSpecialisteList() throws AppException, ParseException {
		Adresse adresse = new Adresse("15", "Rue des Ponts", "54000", "Nancy");
		Adresse adresse2 = new Adresse("30", "rue de Nancy", "54630",
				"Richardmenil");
		Adresse adresse3 = new Adresse("9", "Rue Maurice Barres", "54000",
				"Nancy");
		Mutuelle mutuelle1 = new Mutuelle("MGEN", adresse3, "3976",
				"acceuil@mgen.fr", "Meurthe-et-Moselle", 80, pharmaTest);
		Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse,
				"03.83.40.25.97", "c.nathalie@hotmail.fr", "1562038064121782",
				pharmaTest);
		Specialiste specialiste3 = new Specialiste("Thess", "François",
				adresse2, "03.83.35.19.76", "t.francois@orange.fr",
				Specialite.Dermatologie, pharmaTest);
		ArrayList<Specialiste> specialistes = new ArrayList<Specialiste>();
		specialistes.add(specialiste3);
		client1 = new Client("Retournay", "Steve", adresse2, "06.81.30.29.76",
				"190017512703025", "steve54@wanadoo.fr", "1990", "01", "03", medecin1,
				mutuelle1, pharmaTest);
		client1.setSpecialiste(specialiste3);
		assertEquals("Nom = Retournay, Prenom = Steve\n"
				+ "Adresse = 30, rue de Nancy, 54630 Richardmenil\n"
				+ "Numero de telephone = 06.81.30.29.76\n"
				+ "E-Mail =steve54@wanadoo.fr\n"
				+ "N°Sécurité Scoiale = 190017512703025\n"
				+ "Date de naissance = 03/01/1990\n" + "Mutuelle = MGEN\n"
				+ "Medecin traitant = Chastagner Nathalie\n"
				+ "Specialiste = Docteur Thess François, Dermatologie\n",
				client1.toString());
		assertEquals(specialistes, client1.getSpecialiste());
		assertEquals("Docteur Thess François, Dermatologie" + "\n",
				client1.afficherListeSpecialiste());
		try {
			client1.setSpecialiste(null);
			fail("Un specialiste est ajoute au client");
		} catch (AppException e) {
			assert (e.getMessage().contains(
					"Aucun specialiste ne peut etre ajoute au client"));
		}

	}

	@Test
	void TestSecuriteSociale() throws AppException, ParseException {
		Adresse adresse = new Adresse("15", "Rue des Ponts", "54000", "Nancy");
		Adresse adresse2 = new Adresse("30", "rue de Nancy", "54630",
				"Richardmenil");
		Adresse adresse3 = new Adresse("9", "Rue Maurice Barres", "54000",
				"Nancy");
		Mutuelle mutuelle1 = new Mutuelle("MGEN", adresse3, "3976",
				"acceuil@mgen.fr", "Meurthe-et-Moselle", 80, pharmaTest);
		Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse,
				"03.83.40.25.97", "c.nathalie@hotmail.fr", "1562038064121782",
				pharmaTest);
		try {
			client1 = new Client("Retournay", "Steve", adresse2,
					"06.81.30.29.76", "19001751190252", "steve54@wanadoo.fr",
					"1990", "01", "03", medecin1, mutuelle1, pharmaTest);
		} catch (AppException NFE) {
			assert (NFE.getMessage().contains(
					"Le numero de sécurité est composé de 15 chiffres uniquement."));
		}

		try {
			client1 = new Client("Retournay", "Steve", adresse2,
					"06.81.30.29.76", null, "steve54@wanadoo.fr", "1990", "01", "03",
					medecin1, mutuelle1, pharmaTest);
		} catch (AppException NFE) {
			assert (NFE.getMessage()
					.contains("Le numero de sécurité est nécessaire"));
		}
	}

	@Test
	void TestMutuelle() throws AppException, ParseException {
		Adresse adresse = new Adresse("15", "Rue des Ponts", "54000", "Nancy");
		Adresse adresse2 = new Adresse("30", "rue de Nancy", "54630",
				"Richardmenil");
		Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse,
				"03.83.40.25.97", "c.nathalie@hotmail.fr", "1562038064121782",
				pharmaTest);

		try {
			client1 = new Client("Retournay", "Steve", adresse2,
					"06.81.30.29.76", "190017511902524", "steve54@wanadoo.fr",
					"1990", "01", "03", medecin1, null, pharmaTest);
		} catch (AppException NFE) {
			assert (NFE.getMessage()
					.contains("Un client a obligatoirement une mutuelle"));
		}
	}

	@AfterAll
	public static void clean() {
		pharmaTest = null;
	}
}
