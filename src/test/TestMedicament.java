package test;

import org.junit.jupiter.api.Test;

import classes.Medicament;
import classes.Pharmacie;
import exception.AppException;

class TestMedicament {

	Pharmacie pharmaTest = new Pharmacie();

	@SuppressWarnings("unused")
	@Test
	void testSetNom() {
		try {
			Medicament medicament = new Medicament(null, "Antibiotique", 1, 60,
					"1953", "05", "02", pharmaTest);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			assert (e.getMessage().contains("Un medicament a besoin d'un nom"));
		}
		try {
			Medicament medicament = new Medicament("123", "Antibiotique", 1, 60,
					"1953", "05", "02", pharmaTest);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			assert (e.getMessage()
					.contains("Un nom de medicament est ecrit en lettres"));
		}
		try {
			Medicament medicament = new Medicament("ab", "Antibiotique", 1, 60,
					"1953", "05","02", pharmaTest);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			assert (e.getMessage()
					.contains("Un nom de medicament est ecrit en lettres"));
		}

	}

	@SuppressWarnings("unused")
	@Test
	void testSetCategorie() {
		try {
			Medicament medicament = new Medicament("Amoxicilline", null, 1, 60,
					"1953", "05", "02", pharmaTest);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			assert (e.getMessage()
					.contains("Un medicament a besoin d'une categorie"));
		}
		try {
			Medicament medicament = new Medicament("Amoxicilline", "12335", 1,
					60, "1953", "05", "02", pharmaTest);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			assert (e.getMessage().contains(
					"La categorie d'un medicament est " + "ecrit en lettres"));
		}
	}

	@SuppressWarnings("unused")
	@Test
	void testSetStock() {
		try {
			Medicament medicament = new Medicament("Amoxicilline", "Antibiotique", 1, -5,
					"1953", "05", "02", pharmaTest);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			assert (e.getMessage()
					.contains("Un stock est soit positif soit null."));}
	}
}
