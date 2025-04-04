package fr.lru.exception;

public class TypePersonnageException extends RuntimeException{

	public TypePersonnageException(){
		super("Le type de personnage n'est pas reconnu");
	}
}
