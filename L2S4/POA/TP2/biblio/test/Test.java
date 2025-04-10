import java.lang.reflect.InvocationTargetException;

public class Test{

	private static final Class<?>[] testClasses = {
		TestEltMM.class,
		TestCD.class,
		TestDVD.class,
		TestBiblioMM.class,
		TestToString.class
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
		}
	}
}