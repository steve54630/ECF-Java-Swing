package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import classes.Adresse;
import classes.Client;
import classes.Medecin;
import classes.Mutuelle;
import classes.Pharmacie;
import exception.AppException;

class TestMutuelle {

	static Pharmacie pharmaTest = new Pharmacie();

	@Test
	void test() throws AppException, ParseException {

		Adresse adresse = new Adresse("9", "Rue Maurice Barres", "54000",
				"Nancy");
		Adresse adresse2 = new Adresse("30", "rue de Nancy", "54630",
				"Richardménil");
		Mutuelle mutuelle = new Mutuelle("MGEN", adresse, "3976",
				"acceuil@mgen.fr", "Meurthe-et-Moselle", 80, pharmaTest);
		Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse2,
				"03.83.40.25.97", "c.nathalie@hotmail.fr", "1562038064121782",
				pharmaTest);
		Client client = new Client("Retournay", "Steve", adresse2,
				"06.81.30.29.76", "190017512703025", "steve54@wanadoo.fr", "1990",
				"01", "03", medecin1, mutuelle, pharmaTest);
		assertEquals("MGEN", mutuelle.getNom());
		assertEquals(adresse, mutuelle.getAdresse());
		assertEquals("3976", mutuelle.getTelephone());
		assertEquals("acceuil@mgen.fr", mutuelle.geteMail());
		assertEquals("Meurthe-et-Moselle", mutuelle.getDepartement());
		assertEquals(80, mutuelle.getRemboursement());
		assertTrue(mutuelle.getClients().contains(client));
		assertEquals("MGEN",mutuelle.toString());

		try {
			mutuelle = new Mutuelle("MGEN", adresse, "397a6", "acceuil@mgen.fr",
					"Meurthe-et-Moselle", 80, pharmaTest);
			fail("Le numero de telephone est correct");
		} catch (AppException e) {
			assert (e.getMessage().contains(
					"Un numero de telephone est compose de 4 chiffres minimum et que des chiffres"));
		}
	}

	@Test
	void testNull () throws AppException {
		Adresse adresse = new Adresse("9", "Rue Maurice Barres", "54000",
				"Nancy");
		Mutuelle mutuelle = new Mutuelle("MGEN", adresse, "3976",
				"acceuil@mgen.fr", "Meurthe-et-Moselle", 80, pharmaTest);
		try {
			mutuelle.setNom(null);
			fail("Le nom n'est pas nul");
		}
		catch (AppException e)
		{assert(e.getMessage().contains("Une mutuelle a forcement un nom"));}
		
		try {
			mutuelle.setAdresse(null);
			fail ("L'adresse n'est pas nul");
		}
		catch (AppException e)
		{assert(e.getMessage().contains("Une mutuelle a forcement une adresse"));}
		
		try {
			mutuelle.seteMail(null);
			fail("L'email n'est pas nul");
		}
		catch (AppException e)
		{assert(e.getMessage().contains("Une mutuelle a forcement un e-mail."));}
		
		try {
			mutuelle.setDepartement(null);
			fail("Le departement n'est pas nul");
		}
		catch (AppException e)
		{assert(e.getMessage().contains("Une mutuelle est forcement installe "
				+ "dans un departement"));}
		
		try {
			mutuelle.ajouterClients(null);
			fail("L'objet client n'est pas nul");
		}
		catch (AppException e)
		{assert(e.getMessage().contains("Aucun client n'est ajouté a la mutuelle"));}
	}
		
	@Test
	void testRegexMutuelle () throws AppException {
		Adresse adresse = new Adresse("9", "Rue Maurice Barres", "54000",
				"Nancy");
		Mutuelle mutuelle = new Mutuelle("MGEN", adresse, "3976",
				"acceuil@mgen.fr", "Meurthe-et-Moselle", 80, pharmaTest);
		
		String[] valeurTestNom = {"mg","Pays-fou"};
		for (String stringTest : valeurTestNom)
		{try
		{mutuelle.setNom(stringTest);}
		catch (AppException e)
		{assert(e.getMessage().contains("Un nom de mutuelle est ecrit en lettre"
				+ "et peut contenir des points."));}}
		
		String[] valeurTestNom2 = {"MGEN","Pays Fou"};
		for (String stringTest : valeurTestNom2)
		{mutuelle.setNom(stringTest);
		assertTrue(mutuelle.getNom()==stringTest);
		}
		
		String[] valeurTestTel = {"132","Pays-fou"};
		for (String stringTest : valeurTestTel)
		{try
		{mutuelle.setTelephone(stringTest);}
		catch (AppException e)
		{assert(e.getMessage().contains
		("Un numero de telephone est compose de 4 chiffres minimum et que des chiffres"));}}
	
		String[] valeurTestTel2 = {"3906","03.02.05.26.40"};
		for (String stringTest : valeurTestTel2)
		{mutuelle.setTelephone(stringTest);
		assertTrue(mutuelle.getTelephone()==stringTest);
		}
		
		String[] valeurTestMail = {"steve54","steve54@", "@wanadoo.fr", "steve54@wanadoo"};
		for (String stringTest : valeurTestMail)
		{try
		{mutuelle.seteMail(stringTest);
		fail("L'Email est correct");}
		catch (AppException e)
		{assert(e.getMessage().contains
		("Un E-Mail s'ecrit au format abc@domaine.fr."));}}
		
		String[] valeurTestMail2 = {"steve54@wanadoo.fr","abc@domaine.com"};
		for (String stringTest : valeurTestMail2)
		{mutuelle.seteMail(stringTest);
		assertTrue(mutuelle.geteMail()==stringTest);
		}
		
		int[] valeurTestRemboursement = {-20, 110, 0, 100};
		for (int test : valeurTestRemboursement)
		{try
		{mutuelle.setRemboursement(test);
		fail("Le taux de remboursement est au bon format");}
		catch (AppException e)
		{assert(e.getMessage().contains
		("Le taux de remboursement est compris " + "entre 0 et 100"));}}
		
		int[] valeurTestRemboursement2 = {10,60,99};
		for (int test : valeurTestRemboursement2)
		{mutuelle.setRemboursement(test);
		assertTrue(mutuelle.getRemboursement()==test);
		}
	
		String[] valeurTestDepartement = {"235", "ad", "allier"};
		for (String stringTest : valeurTestDepartement)
		{try
		{mutuelle.setDepartement(stringTest);
		fail("L'Email est correct");}
		catch (AppException e)
		{assert(e.getMessage().contains
		("Le departement contient seulement des lettres "
				+ "et des tirets"));}}
		
		String[] valeurTestDepartement2 = {"Ain","Meurthe-et-Moselle"};
		for (String stringTest : valeurTestDepartement2)
		{mutuelle.setDepartement(stringTest);
		assertTrue(mutuelle.getDepartement()==stringTest);
		}
		
	}
	
	
	@AfterAll
	public static void clean() {
		pharmaTest = null;
	}
}
