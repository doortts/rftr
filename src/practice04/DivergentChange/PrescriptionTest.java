package practice04.DivergentChange;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrescriptionTest {

	@Test (expected=IllegalArgumentException.class)
	public void testGetPrescription() throws Exception {
		Prescription prescription = new Prescription();
		assertEquals("two pill a day", prescription.getPrescription("adult", "serious"));
		assertEquals("one pill a day", prescription.getPrescription("adult", "mild"));
		assertEquals("one pill a day", prescription.getPrescription("child", "serious"));
		assertEquals("half pill a day", prescription.getPrescription("child", "mild"));

		prescription.getPrescription("children", "mild");
	}

	@Test (expected=IllegalArgumentException.class)
	public void testGetIngestionPerDay() throws Exception {
		Prescription prescription = new Prescription();
		assertTrue(2d == prescription.getMaxIngestionPerDay("adult", "serious") );
		assertTrue(1d == prescription.getMaxIngestionPerDay("adult", "mild") );
		assertTrue(1d == prescription.getMaxIngestionPerDay("child", "serious") );
		assertTrue(0.5d == prescription.getMaxIngestionPerDay("child", "mild") );

		prescription.getPrescription("adlut", "mild");
	}

}
