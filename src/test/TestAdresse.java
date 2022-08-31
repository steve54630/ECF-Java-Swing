package test;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import classes.Adresse;
import exception.AppException;
import exception.AppException;

@SuppressWarnings("unused")
class TestAdresse {

	@Test
	void testNomRue() throws AppException {

		String rue = null;

		try {
			Adresse adresse = new Adresse("20", rue, "54000", "Nancy");
			fail("Un nom de rue est entré");
		} catch (AppException e) {
			// TODO Auto-generated catch block
			assert (e.getMessage().contains("Un nom de rue est nécessaire."));
		}

		String ville = null;

		try {
			Adresse adresse = new Adresse("20", "rue des ponts", "54000",
					ville);
			fail("Un nom de ville est entré");
		} catch (AppException NFE) {
			// TODO Auto-generated catch block
			assert (NFE.getMessage().contains(
					"Un nom de ville est necessaire"));
		} catch (NullPointerException NPE) {
		}

		try {
			Adresse adresse = new Adresse("20", "rue des ponts", "54000a",
					ville);
			fail("Le code postal est juste");
		} catch (AppException NFE) {
			// TODO Auto-generated catch block
			assert (NFE.getMessage()
					.contains("Un code postal est composé de 5 chiffres"));
		} catch (NullPointerException NPE) {
		}

		try {
			Adresse adresse = new Adresse("20", "rue des ponts", "54000",
					"nancy");
			fail("Le nom de ville entré est juste");
		} catch (AppException NFE) {
			// TODO Auto-generated catch block
			assert (NFE.getMessage().contains(
					"Un nom de ville est nécessaire, commence par une majuscule,\n"
							+ "contient seulement des lettres (accentués ou non) et des tirets."));
		} catch (NullPointerException NPE) {
		}

		try {
			Adresse adresse = new Adresse("2a", "rue des ponts", "54000",
					"nancy");
			fail("Le numero de rue entre est juste");
		} catch (AppException NFE) {
			// TODO Auto-generated catch block
			assert (NFE.getMessage().contains("Un numero de rue est necessaire "
					+ "et ne contient que des chiffres."));
		} catch (NullPointerException NPE) {
		}
		
		String numRue = null;

		try {
			Adresse adresse = new Adresse(numRue, "Rue de Nancy", "54000", "Nancy");
			fail("Un numero de rue est entré");
		} catch (AppException e) {
			// TODO Auto-generated catch block
			assert (e.getMessage().contains("Un numero de rue est necessaire"));
		}
		
		String codePostal = null;
		
		try {
			Adresse adresse = new Adresse("30", "Rue de Nancy", codePostal, "Nancy");
			fail("Un code postal est entré");
		} catch (AppException e) {
			// TODO Auto-generated catch block
			assert (e.getMessage().contains("Un code postal est nécessaire"));
		}
		
	}
}
