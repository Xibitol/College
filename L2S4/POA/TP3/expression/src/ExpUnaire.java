public abstract sealed class ExpUnaire extends ExpBooleenne permits ExpNegation{

	private static final String UNARY_EXPRESSION_REPR = "%s%s";

	private ExpBooleenne exp;

	protected ExpUnaire(ExpBooleenne exp){
		this.exp = exp;
	}
	
	// GETTERS
	public ExpBooleenne getExp(){ return exp; }

	// FUNCTIONS
	protected String toString(String operator){
		return UNARY_EXPRESSION_REPR.formatted(operator, getExp());
	}
}
