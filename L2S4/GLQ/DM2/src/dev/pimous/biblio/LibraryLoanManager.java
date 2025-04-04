package dev.pimous.biblio;

public class LibraryLoanManager{

	public void borrowBook(String userId, String bookTitle){
		System.out.printf("Book '%s' borrowed by user %s.\n",
			bookTitle, userId
		);
	}

	public void returnBook(String userId, String bookTitle){
		System.out.printf("Book '%s' returned by user %s.\n",
			bookTitle, userId
		);
	}
}
