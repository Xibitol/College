package dev.pimous.l2s3gl.tp1;

import java.io.File;
import java.io.IOException;

/**
 * @author Xibitol;
 */
public class Launcher{

	public static void main(String[] args){
		Catalogue c = new Catalogue();
		
		try{
			System.out.println(c.importFromFile(
				new File("animals.txt"),
				';'
			));
		}catch(IOException e){
			e.printStackTrace();
		}

		c.getAnimaux().forEach(a -> System.out.println(a));
	}
}