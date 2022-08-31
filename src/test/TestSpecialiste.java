package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import classes.Adresse;
import classes.Pharmacie;
import classes.Specialiste;
import exception.AppException;

class TestSpecialiste {

	static Pharmacie pharmaTest = new Pharmacie();
	
	@SuppressWarnings("unused")
	@Test
	void test() throws AppException {
		Adresse adresse2 = new Adresse("30", "rue de Nancy", "54630",
				"Richardmenil");
		Specialiste specialiste = null;
		try
		{specialiste = new Specialiste("Titor", "John", adresse2,
				"06.65.20.40.32", "j.titor@hotmail.fr", null,
				pharmaTest);
		fail("Ce specialiste a une specialite");}
		catch (NullPointerException e)
		{e.getMessage().contains("Un specialiste"
				+ "a une specialite.");
	}

}
	

	@AfterAll
	public static void clean() {
		pharmaTest = null;
	}
	
}