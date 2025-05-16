import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TraceOperationRepertoire implements Observateur{

	private static final String OP_AJOUT_FORMAT = "%s: ajout de %s";
	private static final String OP_SUPPRESSION_FORMAT = "%s: suppression de %s";

	private final List<String> messages = new ArrayList<>();

	// GETTERS
	public List<String> donneMessages(){
		return Collections.unmodifiableList(messages);
	}

	// FUNCTIONS
	@Override
	public void miseAJour(Observable observable, Object o){
		if(!(o instanceof OpRepertoire op)) return;

		messages.add(
			(
				switch(op.donneOperation()){
					case AJOUT -> OP_AJOUT_FORMAT;
					case SUPPRESSION -> OP_SUPPRESSION_FORMAT;
				}
			).formatted(
				observable.donneNom(), op.donneNoeud().donneNom()
			)
		);
	}
}
