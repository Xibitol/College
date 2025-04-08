package dev.pimous.l2s3gl.tp1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.SequencedSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @author Xibitol;
 */
public class Catalogue{
	
	private SequencedSet<Animal> animaux = new TreeSet<>();

	// GETTERS
	public Set<Animal> getAnimaux(){
		return Collections.unmodifiableSequencedSet(animaux);
	}
	public Optional<Animal> getAnimalParID(int identifiant){
		return animaux.parallelStream()
			.filter(a -> a.getIdentifiant() == identifiant)
			.findFirst();
	}

	// SETTERS
	public boolean addAnimal(Animal animal){
		return animaux.add(animal);
	}

	public void importFromLine(String line, char delimiter){
		// Get permitted subclasses if sealed.
		if(!Animal.class.isSealed()) return;
		Class<?>[] classes = Animal.class.getPermittedSubclasses();

		// Store tokens (class and arguments).
		StringTokenizer st = new StringTokenizer(line,
			Character.toString(delimiter)
		);

		String className = null;
		List<String> tokens = new ArrayList<String>();
		while(st.hasMoreTokens()){
			if(Objects.isNull(className)) className = st.nextToken();
			else tokens.add(st.nextToken());
		}

		// Search and instanciate right permitted class.
		for(Class<?> clazz : classes)
			if(className.equals(clazz.getSimpleName()))
				addAnimal(newAnimal(
					clazz.asSubclass(Animal.class),
					tokens.toArray(new String[tokens.size()])
				));
	}
	public int importFromStream(InputStream stream, char delimiter){
		int linesReaded = 0;

		try(Scanner sc = new Scanner(stream)){
			while(sc.hasNextLine()){
				importFromLine(sc.nextLine(), delimiter);
				linesReaded++;
			}
		}

		return linesReaded;
	}
	public int importFromFile(File file, char delimiter) throws IOException{
		if(!file.canRead()) 
			throw new IOException("Cannot read %s".formatted(file.getPath()));

		return importFromStream(
			new BufferedInputStream(new FileInputStream(file)),
			delimiter
		);
	}

	// UTILS
	private <T extends Animal> T newAnimal(Class<T> clazz, String[] arguments){
		T animal = null;

		try{
			// TODO: Search the constructor with the most arguments.
			animal = clazz.getConstructor(
				Integer.TYPE,
				String.class,
				String.class,
				Float.TYPE,
				Boolean.TYPE
			).newInstance(
				Integer.parseInt(arguments[0]),
				arguments[1],
				arguments[2],
				Float.parseFloat(arguments[3]),
				Boolean.parseBoolean(arguments[4])
			);
		}catch(ReflectiveOperationException ignored){}

		return animal;
	}
}
