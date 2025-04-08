package dev.pimous.l2s4gl.fvsw.factory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.SequencedCollection;
import java.util.StringTokenizer;

import dev.pimous.l2s4gl.fvsw.game.Character;
import dev.pimous.l2s4gl.fvsw.game.CharacterType;
import dev.pimous.l2s4gl.fvsw.game.Entity;
import dev.pimous.l2s4gl.fvsw.game.Monster;
import dev.pimous.l2s4gl.fvsw.game.MonsterType;

public class EntityFactory{

	public static final short BUFFER_SIZE = 4096;
	public static final String DELIMITER = ";";
	public static final char EOL = '\n';
	public static final char EOF = '\0';

	private static EntityFactory instance;

	private EntityFactory(){}

	// GETTERS
	public static EntityFactory get(){
		if(Objects.isNull(instance)) instance = new EntityFactory();
		return instance;
	}

	// FUNCTIONS
	public SequencedCollection<Entity> fromResource(String name)
		throws FileNotFoundException, IOException
	{
		InputStream is = Thread.currentThread()
			.getContextClassLoader()
			.getResourceAsStream(name);

		if(Objects.isNull(is)) throw new FileNotFoundException(name);
		else try(is){ return fromReader(new InputStreamReader(is)); }
	}
	public SequencedCollection<Entity> fromReader(Reader reader)
		throws IOException
	{
		SequencedCollection<Entity> entities = new ArrayList<>();

		char[] buffer = new char[BUFFER_SIZE];
		short from = 0, i = (short) buffer.length;

		while(reader.read(buffer, from, i - from) != -1){
			for(from = 0, i = 0;
				i < buffer.length && (i <= 0 || buffer[i - 1] != EOF);
				i++
			){
				if(buffer[i] == EOL || buffer[i] == EOF){
					entities.add(parseEntity(
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

	public Entity parseEntity(String text){
		StringTokenizer st = new StringTokenizer(text, DELIMITER);

		String entClass = st.nextToken();
		String name = st.nextToken();
		String type = st.nextToken();
		int health = Integer.valueOf(st.nextToken()),
			attack = Integer.valueOf(st.nextToken()),
			defense = Integer.valueOf(st.nextToken());

		switch(entClass){
			case Character.ENTITY_ID -> {
				return new Character(name, health, attack, defense,
					CharacterType.fromDisplayName(type), st.nextToken()
				);
			}
			case Monster.ENTITY_ID -> {
				return new Monster(name, health, attack, defense,
					MonsterType.fromDisplayName(type),
					st.nextToken(),
					st.nextToken()
				);
			}
			default -> throw new IllegalArgumentException(
				"No such entity class %s".formatted(entClass)
			);
		}
	}
}
