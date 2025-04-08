import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.TreeSet;

import dev.pimous.l2s4poa.tp1.CompteBancaire;

public class Test{

	private static final Class<?>[] testClasses = {
		TestBanque.class,
		TestBanqueComplet.class,
		TestCompteBancaire.class,
		TestCompteBancaireRemunere.class,
		TestEntrepriseParticulier.class
	};

	public static void main(String[] args){
		for(Class<?> tc : testClasses){
			System.out.printf("%s: ", tc.getSimpleName());

			try{
				tc.getMethod("main",String[].class)
					.invoke(null, new Object[]{args});
			}catch(InvocationTargetException e){
				Throwable t = e.getCause();

				System.out.printf("Tests failed... (%s: %s)\n",
					t.getClass().getSimpleName(), t.getMessage()
				);
				t.printStackTrace();
			}catch(ReflectiveOperationException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

			resetCompteBancaire();
		}
	}

	private static void resetCompteBancaire(){
		Field[] fs = CompteBancaire.class.getDeclaredFields();

		for(Field f : fs){
			f.setAccessible(true);

			try{
				switch(f.getName()){
					case "lastNumero":
						f.setInt(null, 0);
						break;
					case "usedNumeros":
						f.set(null, new TreeSet<Integer>());
						break;
				}
			}catch(ReflectiveOperationException ignored){}
		}
	}
}