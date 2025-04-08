package dev.pimous.biblio;

public class LibraryFacade{

	private BookCatalog bookCatalog = new BookCatalog();
	private UserManager userMan = new UserManager();
	private LibraryLoanManager loanMan = new LibraryLoanManager();

	public LibraryFacade(){}

	// GETTERS
	public String getBookDetails(String bookTitle){
		return bookCatalog.searchBookDetails(bookTitle);
	}

	public boolean canBorrow(String userId){
		return userMan.checkUserEligbility(userId);
	}

	// SETTERS
	public void borrowBook(String userId, String bookTitle){
		if(canBorrow(userId))
			loanMan.borrowBook(userId, bookTitle);
	}
	public void returnBook(String userId, String bookTitle){
		if(canBorrow(userId))
			loanMan.returnBook(userId, bookTitle);
	}
}