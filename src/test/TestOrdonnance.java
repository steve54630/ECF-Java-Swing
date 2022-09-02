package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import classes.Adresse;
import classes.Client;
import classes.Medecin;
import classes.Medicament;
import classes.Mutuelle;
import classes.Ordonnance;
import classes.Pharmacie;
import classes.Specialiste;
import classes.Specialite;
import exception.AppException;

class TestOrdonnance {

	static Pharmacie pharmaTest = new Pharmacie();

	@Test
	void testOrdonnance() throws AppException, ParseException {
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
		Medicament medicament = new Medicament("Amoxicilline", "Antibiotique",
				1, 60, "1953", "05","02", pharmaTest);
		Ordonnance achat = new Ordonnance(client1, medecin1);
		achat.setMedicaments(medicament);
		assertEquals(client1, achat.getAcheteur());
		assertTrue(achat.getMedicaments().contains(medicament));
		pharmaTest.setAchats(achat);
		assertEquals(0.2, achat.getMedicaments().get(0).getPrix());
		achat.toString();
	}

	@Test
	void testOrdonnanceSpecialiste()
			throws AppException, ParseException {
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
		Specialiste specialiste = new Specialiste("Titor", "John", adresse2,
				"06.65.20.40.32", "j.titor@hotmail.fr", Specialite.Urologie,
				pharmaTest);
		Medicament medicament = new Medicament("Amoxicilline", "Antibiotique",
				1, 60, "1953", "05", "02", pharmaTest);
		Ordonnance achat = new Ordonnance(client1, medecin1);
		achat.setMedicaments(medicament);
		achat.setSpecialiste(specialiste);
		assertEquals(client1, achat.getAcheteur());
		assertTrue(achat.getMedicaments().contains(medicament));
		pharmaTest.setAchats(achat);
		assertEquals(0.2, achat.getMedicaments().get(0).getPrix());
		achat.toString();
		assertTrue(pharmaTest.getClients().contains(client1));
		assertTrue(pharmaTest.getMedecins().contains(medecin1));
		assertTrue(pharmaTest.getAchats().contains(achat));
		assertTrue(pharmaTest.getOrdonnances()
				.contains(medecin1.getOrdonnancesPrescrites().get(0)));
		assertTrue(pharmaTest.getMedicaments().contains(medicament));
		assertTrue(pharmaTest.getMutuelles().contains(mutuelle1));
		assertTrue(pharmaTest.getSpecialiste().contains(specialiste));
		Medicament medicament2 = null;
		try
		{achat.setMedicaments(medicament2);
		fail("Un medicament a éte ajouté");
		}
		catch (AppException e)
		{e.getMessage().contains("Il faut ajouter "
				+ "un medicament");}
		try
		{achat.setMedecin(null);
		fail("Un medecin a éte ajouté");
		}
		catch (AppException e)
		{e.getMessage().contains("Une ordonnance est forcement lie a un medecin");}
		
	}


	@AfterAll
	public static void clean() {
		pharmaTest = null;
	}
	
}
