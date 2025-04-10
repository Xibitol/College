package fr.lru;

import java.util.EnumSet;

import org.decimal4j.immutable.Decimal1f;
import org.decimal4j.immutable.Decimal2f;
import org.decimal4j.mutable.MutableDecimal1f;

public record FoyerFiscal(Decimal2f revenu, boolean couple, Decimal1f enfants){

	public static final Decimal1f PART_ADULTE = Decimal1f.valueOf(1);
	public static final Decimal1f PART_ENFANT = Decimal1f.valueOf(0.5f);
	public static final Decimal1f NB_PART_ENFANT = Decimal1f.valueOf(2);
	public static final Decimal1f MAX_PARTS = Decimal1f.valueOf(15);

	public static final Decimal2f ABATTEMENT_RESTE = Decimal2f.valueOf(0.9f);

	public FoyerFiscal{
		Decimal1f parts = getParts(couple, enfants);

		if(revenu.isNegative())
			throw new IllegalArgumentException("Revenu négatif;");
		else if(enfants.isNegative())
			throw new IllegalArgumentException("Nombre d'enfants négatif;");
		else if(parts.isGreaterThan(MAX_PARTS))
			throw new IllegalArgumentException(
				"Trop de parts (%s);".formatted(parts)
			);
	}
	public FoyerFiscal(float revenu, boolean couple, byte enfants){
		this(Decimal2f.valueOf(revenu), couple, Decimal1f.valueOf(enfants));
	}

	// GETTERS
	public static Decimal1f getParts(boolean couple, Decimal1f enfants){
		MutableDecimal1f parts = NB_PART_ENFANT.min(enfants)
			.multiply(PART_ENFANT)
			.add(1)
			.toMutableDecimal();

		if(couple) parts.add(1);

		if(enfants.isGreaterThan(NB_PART_ENFANT))
			parts.add(enfants.subtract(NB_PART_ENFANT).multiply(PART_ADULTE));

		return parts.toImmutableDecimal();
	}
	public static float getParts(boolean couple, byte enfants){
		return getParts(couple, Decimal1f.valueOf(enfants)).floatValue();
	}

	public static Decimal2f getRevenuImposable(Decimal2f revenu){
		return revenu.multiply(ABATTEMENT_RESTE);
	}
	public static float getRevenuImposable(float revenu){
		return getRevenuImposable(Decimal2f.valueOf(revenu)).floatValue();
	}

	public Decimal1f getParts(){
		return getParts(couple, enfants);
	}
	public Decimal2f getPartsD2f(){
		return Decimal2f.valueOf(getParts());
	}

	public Decimal2f getRevenuImposable(){
		return getRevenuImposable(revenu);
	}
	public Decimal2f getQuotientFamilial(){
		return getRevenuImposable().divide(getPartsD2f());
	}
	public Decimal2f getCotisation(){
		return EnumSet.allOf(TauxImposition.class).stream()
			.filter(ti ->
				getQuotientFamilial().isGreaterThanOrEqualTo(ti.minimum)
			)
			.map(ti -> ti.getCotisation(this))
			.reduce(Decimal2f.ZERO, Decimal2f::add);
	}

	public Decimal2f getImpotsD2f(){
		return getCotisation().multiply(getPartsD2f());
	}
	public float getImpots(){
		return getImpotsD2f().floatValue();
	}
	public float getImpotsRounded(){
		return getImpotsD2f().round(0).floatValue();
	}
}
