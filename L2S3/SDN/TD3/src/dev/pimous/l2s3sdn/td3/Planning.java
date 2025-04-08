package dev.pimous.l2s3sdn.td3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Planning{

	private static final byte WEEK_PER_YEARS = 52;
	private static final byte AMOUNT_PER_WEEK = 2;
	private static final char LINE_FEED = '\n';
	private static final String DISTRIBUTION_TITLE_FORMAT =
		"Distribution among weeks:\n";
	private static final String DISTRIBUTION_LINE_FORMAT = "\t%d) %s(%d);";
	private static final String DISTRIBUTION_UNKNOWN_FORMAT = "????";

	private FilePrio personnes = new FilePrio();

	public Planning(List<Personne> personnes){
		this.personnes.addAll(personnes);
	}

	// GETTERS
	public FilePrio getPersonnes(){
		return personnes;
	}
	public List<List<Personne>> getDistribution(){
		List<List<Personne>> distribution = new ArrayList<>(WEEK_PER_YEARS);
		List<Personne> temp = new ArrayList<>(personnes.size());
		List<Personne> weekDist = new ArrayList<>(AMOUNT_PER_WEEK);
		Personne pers;

		while(distribution.size() < WEEK_PER_YEARS){
			synchronized(personnes){ // Thread Safe
				while(!personnes.isEmpty()
					&& weekDist.size() < AMOUNT_PER_WEEK
				){
					pers = personnes.get();
	
					if(!pers.isAbsent(distribution.size() + 1)){
						pers.setPrio(pers.getPrio() + 1);
						weekDist.add(pers);
					}
	
					temp.add(pers);
				}

				personnes.addAll(temp);
				temp.clear();
			}

			distribution.add(List.copyOf(weekDist));
			weekDist.clear();
		}

		return distribution;
	}

	// FUNCTIONS
	public void repartition(){
		List<List<Personne>> distribution = getDistribution();

		System.err.println(
			IntStream.range(0, WEEK_PER_YEARS).boxed()
				.map(i -> {
					List<Personne> wd = distribution.get(i);

					return DISTRIBUTION_LINE_FORMAT.formatted(i + 1,
						wd.size() == AMOUNT_PER_WEEK ?
							wd.stream().map(p -> p.getNom()).toList()
							: DISTRIBUTION_UNKNOWN_FORMAT,
						wd.size()
					);
				})
				.collect(Collectors.joining(Character.toString(LINE_FEED),
					DISTRIBUTION_TITLE_FORMAT,
					""
				))
		);
	}
}