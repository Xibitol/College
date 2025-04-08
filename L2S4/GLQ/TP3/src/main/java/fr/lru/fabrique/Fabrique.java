package fr.lru.fabrique;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.SequencedCollection;
import java.util.StringTokenizer;

import fr.lru.exception.FormatInvalideException;
import fr.lru.exception.TypeEntiteException;
import fr.lru.exception.TypeMonstreException;
import fr.lru.exception.TypePersonnageException;
import fr.lru.jeu.Entite;
import fr.lru.jeu.Monstre;
import fr.lru.jeu.Personnage;
import fr.lru.jeu.TypeMonstre;
import fr.lru.jeu.TypePersonnage;

public class Fabrique{

	public static final short BUFFER_SIZE = 4096;
	public static final String DELIMITER = ";";
	public static final char EOL = '\n';
	public static final char EOF = '\0';

	private static Fabrique instance;

	private Fabrique(){}

	// GETTERS
	public static Fabrique getInstance(){
		if(Objects.isNull(instance)) instance = new Fabrique();
		return instance;
	}

	// FUNCTIONS
	public SequencedCollection<Entite> fromResource(String name)
		throws FileNotFoundException, IOException
	{
		InputStream is = Thread.currentThread()
			.getContextClassLoader()
			.getResourceAsStream(name);

		if(Objects.isNull(is)) throw new FileNotFoundException(name);
		else try(is){ return fromReader(new InputStreamReader(is)); }
	}
	public SequencedCollection<Entite> fromReader(Reader reader)
		throws IOException
	{
		SequencedCollection<Entite> entities = new ArrayList<>();

		char[] buffer = new char[BUFFER_SIZE];
		short from = 0, i = (short) buffer.length;

		while(reader.read(buffer, from, i - from) != -1){
			for(from = 0, i = 0;
				i < buffer.length && (i <= 0 || buffer[i - 1] != EOF);
				i++
			){
				if(buffer[i] == EOL || buffer[i] == EOF){
					entities.add(creerEntite(
						String.valueOf(Arrays.copyOfRange(buffer, from, i))
					));

					from = (short) (i + 1);
				}
			}

			if(from == 0) throw new OutOfMemoryError("Next line too wide.");
			else if(from == i) from = 0;
			else System.arraycopy(buffer, from, buffer, 0, i - from);

			Arrays.fill(buffer, EOF);
		}

		return entities;
	}

	public Entite creerEntite(String informations){
		Entite entity;
		StringTokenizer st = new StringTokenizer(informations, DELIMITER);

		String entClass, name, type;
		int health, attack, defense;

		try{
			entClass = st.nextToken();
			name = st.nextToken();
			type = st.nextToken();
			health = Integer.valueOf(st.nextToken());
			attack = Integer.valueOf(st.nextToken());
			defense = Integer.valueOf(st.nextToken());
		}catch(NoSuchElementException | NumberFormatException e){
			throw new FormatInvalideException(e);
		}

		switch(entClass){
			case Personnage.ID_STRING -> {
				TypePersonnage tp = TypePersonnage.fromDisplayName(type);

				if(Objects.isNull(tp))
					throw new TypePersonnageException();

				try{
					entity = new Personnage(name, health, attack, defense,
						tp, st.nextToken()
					);
				}catch(NoSuchElementException e){
					throw new FormatInvalideException(e);
				}
			}
			case Monstre.ID_STRING -> {
				TypeMonstre tm = TypeMonstre.fromDisplayName(type);

				if(Objects.isNull(tm))
					throw new TypeMonstreException();

				try{
					entity = new Monstre(name, health, attack, defense,
						tm, st.nextToken(), st.nextToken()
					);
				}catch(NoSuchElementException e){
					throw new FormatInvalideException(e);
				}
			}
			default -> throw new TypeEntiteException();
		}

		return entity;
	}
}