package junit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.*;

public class ComprehensiveTests {

	@Test
	public void test_comprehensive() {
		AppStore canadianStore = new AppStore("Canada", 100);
		Account acc1 = new Account("Suyeon", canadianStore);

		App app1 = new App("GoodNotes 5", 15);
		app1.releaseUpdate("5.7.27");
		app1.releaseUpdate("5.7.29");
		app1.releaseUpdate("5.7.31");
		app1.getUpdateHistory()[0].addFix("Support for new M1 ipad Pro");
		app1.getUpdateHistory()[1].addFix("Improved scroll animations");
		app1.getUpdateHistory()[2].addFix("Better logging");

		App app2 = new App("Eclipse for iPad", 5); /* Note that the maximum number of allowed ratings is different from app1 */
		app2.releaseUpdate("3.15.1");
		app2.getUpdateHistory()[0].addFix("More functionality");

		canadianStore.addApp(app1);
		canadianStore.addApp(app2);

		assertEquals(canadianStore, acc1.getBranch());

		assertEquals("GoodNotes 5 (Current Version: Version 5.7.31 contains 1 fixes [Better logging]; Average Rating: n/a)", app1.toString());

		acc1.download("GoodNotes 5");

		assertEquals("GoodNotes 5 is successfully downloaded for Suyeon.", acc1.toString());

		acc1.submitRating("GoodNotes 5", 4);

		assertEquals("Rating score 4 of Suyeon is successfully submitted for GoodNotes 5.", acc1.toString());

		app1.submitRating(2);

		assertEquals("Average of 2 ratings: 3.0 (Score 5: 0, Score 4: 1, Score 3: 0, Score 2: 1, Score 1: 0)", app1.getRatingReport());

		assertEquals("GoodNotes 5 (Current Version: Version 5.7.31 contains 1 fixes [Better logging]; Average Rating: 3.0)", app1.toString());

		acc1.submitRating("GoodNotes 5", 3);

		assertEquals("GoodNotes 5 (Current Version: Version 5.7.31 contains 1 fixes [Better logging]; Average Rating: 3.0)", app1.toString());

		acc1.download("Eclipse for iPad");
		assertEquals("Eclipse for iPad is successfully downloaded for Suyeon.", acc1.toString());

		acc1.uninstall("Eclipse for iPad");
		assertEquals("Eclipse for iPad is successfully uninstalled for Suyeon.", acc1.toString());

		acc1.uninstall("Eclipse for iPad");
		assertEquals("Error: Eclipse for iPad has not been downloaded for Suyeon.", acc1.toString());

		acc1.submitRating("Eclipse for iPad", 1);
		assertEquals("Error: Eclipse for iPad is not a downloaded app for Suyeon.", acc1.toString());

		acc1.download("Eclipse for iPad");
		assertEquals("Eclipse for iPad is successfully downloaded for Suyeon.", acc1.toString());

		assertTrue(canadianStore.getStableApps(0).length == 2);
		assertEquals("GoodNotes 5 (3 versions; Current Version: Version 5.7.31 contains 1 fixes [Better logging])", canadianStore.getStableApps(0)[0]);
		assertEquals("Eclipse for iPad (1 versions; Current Version: Version 3.15.1 contains 1 fixes [More functionality])", canadianStore.getStableApps(0)[1]);

		assertTrue(canadianStore.getStableApps(1).length == 2);
		assertEquals("GoodNotes 5 (3 versions; Current Version: Version 5.7.31 contains 1 fixes [Better logging])", canadianStore.getStableApps(0)[0]);
		assertEquals("Eclipse for iPad (1 versions; Current Version: Version 3.15.1 contains 1 fixes [More functionality])", canadianStore.getStableApps(0)[1]);

		assertTrue(canadianStore.getStableApps(2).length == 1);
		assertEquals("GoodNotes 5 (3 versions; Current Version: Version 5.7.31 contains 1 fixes [Better logging])", canadianStore.getStableApps(0)[0]);

		assertTrue(canadianStore.getStableApps(3).length == 1);
		assertEquals("GoodNotes 5 (3 versions; Current Version: Version 5.7.31 contains 1 fixes [Better logging])", canadianStore.getStableApps(0)[0]);

		assertTrue(canadianStore.getStableApps(4).length == 0);

		assertTrue(canadianStore.getStableApps(5).length == 0);
	}

}
