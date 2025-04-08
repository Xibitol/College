import java.lang.reflect.Field;

public class Cours {
	
	private String intitule;
	private Integer ECTS;

	public String getIntitule(){ return intitule; }
	public Integer getECTS(){ return ECTS; }

	public void setIntitule(String intitule){ this.intitule = intitule; }
	public void setECTS(Integer ECTS){ this.ECTS = ECTS; }

	@Override
	public String toString() {
		String end = ", ";

		StringBuilder sb = new StringBuilder(this.getClass().getName() + "{");
		for(Field f : this.getClass().getDeclaredFields()){
			sb.append(f.getName());
			sb.append("=");

			// Useless condition because we are in the class of this object.
			try{ sb.append(f.get(this)); }catch(Exception ingored){}

			sb.append(end);
		}
		sb.replace(sb.length() - end.length(), sb.length(), "}");

		return sb.toString();
	}
}
