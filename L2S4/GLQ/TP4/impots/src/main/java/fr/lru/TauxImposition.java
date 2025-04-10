package fr.lru;

import org.decimal4j.immutable.Decimal2f;

public enum TauxImposition{

	TRANCHE_1(
		Decimal2f.valueOf(0f), Decimal2f.valueOf(11_497f),
		Decimal2f.valueOf(0f)
	),
	TRANCHE_2(
		Decimal2f.valueOf(11_498f), Decimal2f.valueOf(29_315f),
		Decimal2f.valueOf(0.11f)
	),
	TRANCHE_3(
		Decimal2f.valueOf(29_316f), Decimal2f.valueOf(83_823f),
		Decimal2f.valueOf(0.3f)
	),
	TRANCHE_4(
		Decimal2f.valueOf(83_824f), Decimal2f.valueOf(180_294f),
		Decimal2f.valueOf(0.41f)
	),
	TRANCHE_5(
		Decimal2f.valueOf(180_295f), Decimal2f.MAX_VALUE,
		Decimal2f.valueOf(0.45f)
	);

	public final Decimal2f minimum;
	public final Decimal2f maximum;
	public final Decimal2f taux;

	private TauxImposition(
		Decimal2f minimum, Decimal2f maximum,
		Decimal2f taux
	){
		this.minimum = minimum;
		this.maximum = maximum;
		this.taux = taux;
	}

	// GETTERS
	public Decimal2f getCotisation(Decimal2f quotientFamilial){
		return quotientFamilial.min(maximum)
			.subtract(minimum.subtract(1))
			.multiply(taux);
	}
	public Decimal2f getCotisation(FoyerFiscal foyerFiscal){
		return getCotisation(foyerFiscal.getQuotientFamilial());
	}
}
