package dev.pimous.l2s3sdn.tp3;

import dev.pimous.javautils.AutoToString;

public class Groupe extends AutoToString implements Comparable<Groupe>{

	private static final byte MIN_SIZE = 1;
	private static final byte MAX_SIZE = 5;
	public static final byte MIN_COMING_DURATION = 0;
	public static final byte MAX_COMING_DURATION = 5;
	public static final byte MIN_PERSON_ORDERING_DURATION = 1;
	public static final byte MAX_PERSON_ORDERING_DURATION = 5;
	public static final byte MIN_PERSON_PREP_DURATION = 2;
	public static final byte MAX_PERSON_PREP_DURATION = 5;
	public static final byte MIN_CONSUMING_DURATION = 5;
	public static final byte MAX_CONSUMING_DURATION = 15;

	private static int nextIdentifier = 0;
	
	private int identifier;
	private byte size;
	private GroupeStatus status = GroupeStatus.ARRIVING;
	private int wakeupTime;

	public Groupe(byte size, int wakeupTime){
		this.identifier = nextIdentifier++;
		this.size = size;
		this.wakeupTime = wakeupTime;
	}
	public Groupe(int wakeupTime){
		this((byte) Random.randint(MIN_SIZE, MAX_SIZE), wakeupTime);
	}

	// GETTERS
	public int getIdentifier(){ return identifier; }
	public byte getSize(){ return size; }
	public GroupeStatus getStatus(){ return status; }
	public int getWakeupTime(){ return wakeupTime; }

	// SETTERS
	public void setStatus(GroupeStatus status){
		this.status = status;
	}
	public void addWakeupTime(int wakeupTime){
		this.wakeupTime += wakeupTime;
	}

	// FUNCTIONS
	@Override
	public int compareTo(Groupe that){
		return Integer.compare(getWakeupTime(), that.getWakeupTime());
	}
}
