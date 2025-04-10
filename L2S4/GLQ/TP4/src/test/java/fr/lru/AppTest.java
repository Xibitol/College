package fr.lru;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AppTest{

	@Test
	void testRevenuImposable(){
		assertEquals(0, FoyerFiscal.getRevenuImposable(0));
		assertEquals(0.9f, FoyerFiscal.getRevenuImposable(1));
		assertEquals(9, FoyerFiscal.getRevenuImposable(10));
	}
	@Test
	void testRevenuInvalide(){
		assertThrows(IllegalArgumentException.class,
			() -> new FoyerFiscal(-1f, false, (byte) 0)
		);
	}

	@Test
	void testParts(){
		assertEquals(1f, FoyerFiscal.getParts(false, (byte) 0));
		assertEquals(1.5f,
			FoyerFiscal.getParts(false, (byte) 1)
		);
		assertEquals(2f,
			FoyerFiscal.getParts(false, (byte) 2)
		);
		assertEquals(15f,
			FoyerFiscal.getParts(false, (byte) 15)
		);

		assertEquals(2f, FoyerFiscal.getParts(true, (byte) 0));
		assertEquals(2.5f,
			FoyerFiscal.getParts(true, (byte) 1)
		);
		assertEquals(3f,
			FoyerFiscal.getParts(true, (byte) 2)
		);
		assertEquals(15f,
			FoyerFiscal.getParts(true, (byte) 14)
		);
	}
	@Test
	void testPartsFoyerFiscal(){
		assertThrows(IllegalArgumentException.class,
			() -> new FoyerFiscal(0f, true, (byte) -1)
		);

		assertThrows(IllegalArgumentException.class,
			() -> new FoyerFiscal(0f, true, (byte) 15)
		);

		assertEquals(0f,
			new FoyerFiscal(0f, true, (byte) 14)
				.getImpots()
		);
	}

	@Test
	void testT1(){
		assertEquals(0f,
			new FoyerFiscal(0f, true, (byte) 0)
				.getImpots()
		);

		assertEquals(0f,
			new FoyerFiscal(3_120f, false, (byte) 0)
				.getImpots()
		);
		assertEquals(0f,
			new FoyerFiscal(5_000f, true, (byte) 2)
				.getImpotsRounded()
		);

		assertEquals(0f,
			new FoyerFiscal(12_775.54f, false, (byte) 0)
				.getImpots()
		);
	}

	@Test
	void testT2(){
		assertEquals(0.11f,
			new FoyerFiscal(12_775.55f, false, (byte) 0)
				.getImpots()
		);

		assertEquals(746.03f,
			new FoyerFiscal(20_310.1f, false, (byte) 0)
				.getImpots()
		);
		assertEquals(1_705f,
			new FoyerFiscal(30_000f, false, (byte) 0)
				.getImpotsRounded()
		);

		assertEquals(1_959.98f,
			new FoyerFiscal(32_573.32f, false, (byte) 0)
				.getImpots()
		);
	}

	@Test
	void testT3(){
		assertEquals(1_960.28f,
			new FoyerFiscal(32_573.33f, false, (byte) 0)
				.getImpots()
		);

		assertEquals(10_207.88f,
			new FoyerFiscal(63_120.01f, false, (byte) 0)
				.getImpots()
		);
		assertEquals(48_346f,
			new FoyerFiscal(255_000f, true, (byte) 2)
				.getImpotsRounded()
		);

		assertEquals(18_312.38f,
			new FoyerFiscal(93_137.77f, false, (byte) 0)
				.getImpots()
		);
	}

	@Test
	void testT4(){
		assertEquals(18_312.79f,
			new FoyerFiscal(93_137.78f, false, (byte) 0)
				.getImpots()
		);

		assertEquals(32_358.5f,
			new FoyerFiscal(131_202.01f, false, (byte) 0)
				.getImpots()
		);
		assertEquals(120_280f,
			new FoyerFiscal(500_000f, true, (byte) 3)
				.getImpotsRounded()
		);

		assertEquals(57_865.49f,
			new FoyerFiscal(200_327.77f, false, (byte) 0)
				.getImpots()
		);
	}

	@Test
	void testT5(){
		assertEquals(57_865.94f,
			new FoyerFiscal(200_327.78f, false, (byte) 0)
				.getImpots()
		);

		assertEquals(103_101.29f,
			new FoyerFiscal(312_020.01f, false, (byte) 0)
				.getImpots()
		);
		assertEquals(155_966f,
			new FoyerFiscal(500_000f, true, (byte) 0)
				.getImpotsRounded()
		);
	}
}
