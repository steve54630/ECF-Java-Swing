package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import classes.Adresse;
import classes.Medecin;
import classes.Pharmacie;
import exception.AppException;

class TestMedecin {

	static Pharmacie pharmaTest = new Pharmacie();

	@SuppressWarnings("unused")
	@Test
	void testMedecin() throws AppException{
		Adresse adresse = new Adresse("15", "Rue des Ponts", "54000", "Nancy");
		try {
			Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse,
					"03.83.40.25.97", "c.nathalie@hotmail.fr",
					"1562038064121782l", pharmaTest);
		} catch (AppException e) {
			assert (e.getMessage()
					.contains("Un numero d'agréément contient 16 chiffres."));
		}
		try {
			Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse,
					"03.83.40.25.97", "c.nathalie@hotmail.fr", null,
					pharmaTest);
		} catch (AppException e) {
			assert (e.getMessage()
					.contains("Un medecin a forcément un numero d'aggrément"));
		}
		try {
			Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse,
					"03.83.40.25.97", "c.nathalie@hotmail.fr", null,
					pharmaTest);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			assert (e.getMessage()
					.contains("Un medecin a forcément un numero d'aggrément."));
		}
		Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse,
				"03.83.40.25.97", "c.nathalie@hotmail.fr", "1562038064121782",
				pharmaTest);
		assertEquals(medecin1.getNumeroAggrement(), "1562038064121782");
		
	}

	@Test
	void testAjouter() throws AppException {
		Adresse adresse = new Adresse("15", "Rue des Ponts", "54000", "Nancy");
		Medecin medecin1 = new Medecin("Chastagner", "Nathalie", adresse,
				"03.83.40.25.97", "c.nathalie@hotmail.fr", "1562038064121782",
				pharmaTest);
		try
		{medecin1.ajouterPatient(null);}
		catch (AppException e)
		{assert(e.getMessage().contains("On ne peut pas ajouter de patient ayant une valeur nulle"));}
		
		try
		{medecin1.ajouterOrdonnance(null);}
		catch (AppException e)
		{assert(e.getMessage().contains("On ne peut pas ajouter de ordonnance ayant une valeur nulle"));}
		
	}
	

	@AfterAll
	public static void clean() {
		pharmaTest = null;
	}
	
}
