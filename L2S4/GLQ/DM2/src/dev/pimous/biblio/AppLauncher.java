package dev.pimous.biblio;

class AppLauncher{

	private static final String BOOK_NAME =
		"Le Chat qui était perdu dans le Nether.";
	private static final String USER_NAME = "Xibitol";
	
	public static void main(String[] args){
		LibraryFacade lf = new LibraryFacade();

		System.out.println(lf.getBookDetails(BOOK_NAME));
		System.out.printf("Is Xibitol eligible ? %b\n",
			lf.canBorrow(USER_NAME)
		);

		lf.borrowBook(USER_NAME, BOOK_NAME);
		lf.returnBook(USER_NAME, BOOK_NAME);
	}
}
