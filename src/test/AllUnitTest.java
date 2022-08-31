package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestClient.class, TestAchat.class, TestMutuelle.class,
		TestAdresse.class, TestOrdonnance.class, TestMedecin.class, 
		TestSpecialiste.class, TestMedicament.class })
class AllUnitTest {
}
