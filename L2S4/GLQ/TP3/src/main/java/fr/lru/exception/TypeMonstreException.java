package fr.lru.exception;

public class TypeMonstreException extends RuntimeException{

	public TypeMonstreException(){
		super("Le type de monstre n'est pas reconnu");
	}
}
