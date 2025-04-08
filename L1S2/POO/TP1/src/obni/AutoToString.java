package obni;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/** Gives a overriding of the toString method based on introspection of
 * non-static implementing class's fields.
 * @version 1.0.0
 * @author xibitol
 */
public abstract class AutoToString {
	
	@Override
	public String toString(){
		String end = ", ";

		StringBuilder sb = new StringBuilder(this.getClass().getName() + "{");
		for(Field f : this.getClass().getDeclaredFields()){
			if(!Modifier.isStatic(f.getModifiers())){
				sb.append(f.getName());
				sb.append("=");

				f.setAccessible(true); // BUG: Is this can fail ?
				try{ sb.append(f.get(this)); }catch(Exception ignored){}

				sb.append(end);
			}
		}
		sb.replace(sb.length() - end.length(), sb.length(), "}");

		return sb.toString();
	}
}
