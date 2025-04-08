package dev.pimous.l2s3sdn.tp3;

import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Cafe{
	
	private CafePrintUtils out;
	private int size;
	private String name;
	private int closingHour;
	private PriorityQueue<Groupe> queue = new PriorityQueue<>();

	public Cafe(PrintStream out, String name, int closingHour, int size){
		this.out = new CafePrintUtils(out, this);
		this.name = name;
		this.closingHour = closingHour;
		this.size = size;

		this.out.printCreated();
	}

	// GETTERS
	public String getName(){ return name; }
	public int getSize(){ return size; }
	public int getExpectedGroupCount(){ return queue.size(); }
	public int getExpectedFilling(){
		return queue.stream().collect(Collectors.summingInt(g -> g.getSize()));
	}
	public int getGroupCount(){
		return (int) queue.stream()
			.filter(g -> 
				!g.getStatus().equals(GroupeStatus.ARRIVING)
				&& !g.getStatus().equals(GroupeStatus.LEAVING)
			)
			.count();
	}
	public int getFilling(){
		return queue.stream()
			.filter(g -> 
				!g.getStatus().equals(GroupeStatus.ARRIVING)
				&& !g.getStatus().equals(GroupeStatus.LEAVING)
			)
			.collect(Collectors.summingInt(g -> g.getSize()));
	}
	public boolean isFull(){
		return getSize() == getFilling();
	}

	// FUNCTIONS
	public void open(int groupCount){
		int lastWT = 0;
		while(groupCount-- > 0)
			queue.add(new Groupe(
				lastWT += Random.randint(
					Groupe.MIN_COMING_DURATION,
					Groupe.MAX_COMING_DURATION
				)
			));

		out.printOpening();
	}
	public synchronized void handle(double delay){
		Groupe grp = null;
		int lastActiveTime = 0;
		boolean activeLastTime = false;
		int time = 0;

		out.printStarting();

		queue.forEach(g -> out.printStep(0, 0, g));

		while(time/60 < closingHour && !queue.isEmpty()){
			if(time >= queue.peek().getWakeupTime()){
				grp = queue.poll();

				// TODO: Track delays with records (Java 21).

				switch(grp.getStatus()){
					case ARRIVING:
						if(isFull()){
							// TODO: Track not accepted groups.
							grp.setStatus(GroupeStatus.LEAVING);
							break;
						}

						grp.setStatus(GroupeStatus.ORDERING);
						grp.addWakeupTime(Random.randint(
							Groupe.MIN_PERSON_ORDERING_DURATION,
							Groupe.MAX_PERSON_ORDERING_DURATION
						)*grp.getSize());

						queue.add(grp);
						break;
					case ORDERING:
						grp.setStatus(GroupeStatus.WAITING);
						grp.addWakeupTime(Random.randint(
							Groupe.MIN_PERSON_PREP_DURATION,
							Groupe.MAX_PERSON_PREP_DURATION
						));

						queue.add(grp);
						break;
					case WAITING:
						grp.setStatus(GroupeStatus.CONSUMING);
						grp.addWakeupTime(Random.randint(
							Groupe.MIN_CONSUMING_DURATION,
							Groupe.MAX_CONSUMING_DURATION
						)*grp.getSize());

						queue.add(grp);
						break;
					case CONSUMING:
						grp.setStatus(GroupeStatus.LEAVING);
						break;
					case LEAVING:
						break;
				}

				lastActiveTime = time;
				activeLastTime = true;
			}else if(activeLastTime){
				lastActiveTime = time;
				activeLastTime = false;
			}

			out.printStep(lastActiveTime, time, grp);
			grp = null;

			time++;
			if(delay > 0)
				try{
					wait(Math.round(delay*1000));
				}catch(InterruptedException e){
					e.printStackTrace();
					return;
				}
		}

		if(queue.isEmpty()) out.printSucceed();
		else out.printDefeated();
	}
	public void close(){
		if(!queue.isEmpty()){
			out.printClosing();
			queue.clear();
		}
	}
}