import java.lang.reflect.Field;

public class Etudiant {
	
	private Integer numero;
	private String nom, prenom;
	private Integer ECTS;

	public Integer getNumero(){ return numero; }
	public String getNom(){ return nom; }
	public String getPrenom(){ return prenom; }
	public Integer getECTS(){ return ECTS; }

	public void setNumero(Integer numero){ this.numero = numero; }
	public void setNom(String nom){ this.nom = nom; }
	public void setPrenom(String prenom){ this.prenom = prenom; }
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