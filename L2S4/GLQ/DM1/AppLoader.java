package dev.pimous;

public class AppLoader{
	
	public static void main(String[] args){
		UserManager userManager1 = UserManager.getInstance();
		userManager1.addUser("Xibitol");
		
		UserManager userManager2 = UserManager.getInstance();
		userManager2.addUser("Rulietta");
		
		userManager1.displayUsers();
		userManager2.displayUsers();
	}
}