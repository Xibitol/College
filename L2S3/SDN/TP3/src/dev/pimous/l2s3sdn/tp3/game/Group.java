package dev.pimous.l2s3sdn.tp3.game;

public class Group{

	public static final short MIN_PERSON_ORDERING_DURATION = 1;
	public static final short MAX_PERSON_ORDERING_DURATION = 5;
	public static final short MIN_PERSON_PREP_DURATION = 2;
	public static final short MAX_PERSON_PREP_DURATION = 5;
	public static final short MIN_CONSUMING_DURATION = 5;
	public static final short MAX_CONSUMING_DURATION = 15;
	
	protected final byte size;
	protected final short arrivingTime;
	protected short orderingDuration;
	protected short preparationDuration;
	protected short consumingDuration;

	public Group(byte size, short arrivingTime){
		this.size = size;
		this.arrivingTime = arrivingTime;
	}
}
