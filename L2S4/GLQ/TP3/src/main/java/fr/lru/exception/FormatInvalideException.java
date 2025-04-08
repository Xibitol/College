package fr.lru.exception;

public class FormatInvalideException extends RuntimeException{

	public FormatInvalideException(Throwable cause){
		super("Le format des données est invalide.", cause);
	}
}
