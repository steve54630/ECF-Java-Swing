package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import classes.Achat;
import classes.Adresse;
import classes.Client;
import classes.Medecin;
import classes.Medicament;
import classes.Mutuelle;
import classes.Pharmacie;
import exception.AppException;


class TestAchat {

	static Pharmacie pharmaTest = new Pharmacie();

	@Test
	void test() throws AppException, ParseException {
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
		Client client1 = new Client("Retournay", "Steve", adresse2,
				"06.81.30.29.76", "190017512703025", "steve54@wanadoo.fr", "1990",
				"01", "03", medecin1, mutuelle1, pharmaTest);
		Medicament medicament = null;
		try {
			medicament = new Medicament("Amoxicilline", "Antibiotique", -2, 60,
					"1953", "05", "02", pharmaTest);
		} catch (AppException nfe) {
			assert (nfe.getMessage().contains(
					"Le prix d'un medicament est forcement superieur à 0"));
		}
		medicament = new Medicament("Amoxicilline", "Antibiotique", 1, 60, "1953",
				"05", "02", pharmaTest);
		assertEquals(60, medicament.getStock());
		assertEquals("02/05/1953",medicament.dateToString());
		Achat achat = new Achat(client1);
		achat.setMedicaments(medicament);
		achat.setPrixTotal(achat.getPrixTotal()+medicament.getPrix());
		assertEquals(1.0, achat.getPrixTotal());
		assertEquals(client1, achat.getAcheteur());
		assertTrue(achat.getMedicaments().contains(medicament));
		try
		{achat.setPrixTotal(-1);
		fail("Le prix modifie est positif");
		}
		catch(AppException e)
		{assert(e.getMessage().contains("Un prix est forcément positif"));}
		pharmaTest.setAchats(achat);
	}

	@SuppressWarnings("unused")
	@Test
	void testAucunMedicament() throws AppException, ParseException {
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
		Client client1 = new Client("Retournay", "Steve", adresse2,
				"06.81.30.29.76", "190017512703025", "steve54@wanadoo.fr", "1990",
				"01", "03", medecin1, mutuelle1, pharmaTest);
		
		try
		{Medicament test = null;
		Achat achat = new Achat(client1);
		achat.setMedicaments(test);
		fail("Il y a un medicament dans la liste d'achat");
		}
		catch(AppException e)
		{assert(e.getMessage().contains("Il faut ajouter "
				+ "un medicament"));}
		
		try
		{Medicament test = new Medicament("Amoxicilline", "Antibiotique", 1, 60, "1953",
				"05", "02", pharmaTest);
		Achat achat = new Achat(null);
		fail("Il y a un acheteur pour cet achat");
		}
		catch(AppException e)
		{assert(e.getMessage().contains("Un achat a forcément"
				+ " un acheteur"));}
	}
	
	@AfterAll
	public static void clean() {
		pharmaTest = null;
	}
	
}
