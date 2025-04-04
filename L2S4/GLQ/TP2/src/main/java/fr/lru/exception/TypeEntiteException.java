package fr.lru.exception;

public class TypeEntiteException extends RuntimeException{

	public TypeEntiteException(){
		super("Le premier paramètre doit être Personnage ou Monstre");
	}
}
