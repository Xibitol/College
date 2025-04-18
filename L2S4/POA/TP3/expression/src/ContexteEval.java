import java.util.HashMap;
import java.util.Map;

public class ContexteEval{

	private Map<Character, Boolean> variables = new HashMap<>();

	// GETTERS
	public Boolean donne(char name){
		return variables.get(name);
	}

	// SETTERS
	public Boolean affecte(char name, boolean value){
		return variables.put(name, value);
	}
}
