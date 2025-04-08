import java.lang.reflect.Field;

public class AutoEtudiant {

	private Integer numero;
	private String nom, prenom;
	private Cours cours1, cours2;

	public Integer getNumero(){ return numero; }
	public String getNom(){ return nom; }
	public String getPrenom(){ return prenom; }
	public Cours getCours1(){ return cours1; }
	public Cours getCours2(){ return cours2; }
	public Integer getECTS(){
		return cours1.getECTS() + cours2.getECTS();
	}

	public void setNumero(Integer numero){ this.numero = numero; }
	public void setNom(String nom){ this.nom = nom; }
	public void setPrenom(String prenom){ this.prenom = prenom; }
	public void setCours1(Cours cours1){ this.cours1 = cours1; }
	public void setCours2(Cours cours2){ this.cours2 = cours2; }

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
