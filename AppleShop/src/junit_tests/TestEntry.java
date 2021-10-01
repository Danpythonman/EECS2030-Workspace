package junit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.*;

public class TestEntry {

	@Test
	public void test_entry_1() {
		Product p = new Product("iPad Pro 12.9", 1709.00);

		p.setFinish("Space Grey");
		p.setStorage(1000);
		p.setHasCellularConnectivity(true);
		p.setDiscountValue(220.00);

		Entry e = new Entry("F9DN4NKQ1GC", p);

		assertEquals("F9DN4NKQ1GC", e.getSerialNumber());

		// This will work because p is used as the Product object in the Entry object,
		// so they are the same object
		assertTrue(e.getProduct() == p);
		// This checks if the two arguments refer to the same object in memory
		// (the same address)
		assertSame(p, e.getProduct());

		assertEquals("iPad Pro 12.9", e.getProduct().getModel());
		assertTrue(e.getProduct().getModel().equals("iPad Pro 12.9"));

		assertEquals("Space Grey", e.getProduct().getFinish());
		assertTrue(e.getProduct().getFinish().equals("Space Grey"));

		assertTrue(e.getProduct().getStorage() == 1000);
		assertEquals(1000, e.getProduct().getStorage());

		assertTrue(e.getProduct().hasCellularConnectivity());

		assertEquals(1709.00, e.getProduct().getOriginalPrice(), 0.1);

		assertEquals(220.00, e.getProduct().getDiscountValue(), 0.1);

		assertEquals(1709.00 - 220.00, e.getProduct().getPrice(), 0.1);

		assertEquals(
			"iPad Pro 12.9 Space Grey 1000GB (cellular connectivity: true): $(1709.00 - 220.00)",
			e.getProduct().toString()
		);

		assertEquals(
			"[F9DN4NKQ1GC] iPad Pro 12.9 Space Grey 1000GB (cellular connectivity: true): $(1709.00 - 220.00)",
			e.toString()
		);
	}

	@Test
	public void test_entry_2() {
		Product p = new Product("iPad Pro 12.9", 1709.00);

		p.setFinish("Space Grey");
		p.setStorage(1000);
		p.setHasCellularConnectivity(true);
		p.setDiscountValue(220.00);

		Entry e = new Entry("F9DN4NKQ1GC", p);

		assertEquals("F9DN4NKQ1GC", e.getSerialNumber());

		assertTrue(e.getProduct() == p);
		assertSame(p, e.getProduct());

		assertEquals(
				"[F9DN4NKQ1GC] iPad Pro 12.9 Space Grey 1000GB (cellular connectivity: true): $(1709.00 - 220.00)",
				e.toString()
			);

		Product p2 = new Product("iPad Air", 649.00);

		p2.setFinish("Gold");
		p2.setStorage(64);
		// Note that this line is not really needed because it is not explicitly
		// initialized, so it is initialized as the default boolean value (false)
		p2.setHasCellularConnectivity(false);
		p2.setDiscountValue(100.00);

		// Change the associated product of e from p to p2
		e.setProduct(p2);

		assertEquals("F9DN4NKQ1GC", e.getSerialNumber());

		assertFalse(e.getProduct() == p);
		assertNotSame(p, e.getProduct());
		assertTrue(e.getProduct() == p2);
		assertSame(p2, e.getProduct());

		assertEquals(
				"[F9DN4NKQ1GC] iPad Air Gold 64GB (cellular connectivity: false): $(649.00 - 100.00)",
				e.toString()
			);

		// Change the associated product of e from p2 to an anonymous object
		e.setProduct("iPad Air", 649.00);

		assertEquals("F9DN4NKQ1GC", e.getSerialNumber());

		assertFalse(e.getProduct() == p);
		assertNotSame(p, e.getProduct());
		assertFalse(e.getProduct() == p2);
		assertNotSame(p2, e.getProduct());

		assertEquals(
				"[F9DN4NKQ1GC] iPad Air null 0GB (cellular connectivity: false): $(649.00 - 0.00)",
				e.toString()
			);
	}

}
