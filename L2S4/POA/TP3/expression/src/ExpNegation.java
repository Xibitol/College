public final class ExpNegation extends ExpUnaire{

	private static final String NEGATION_OPERATOR = "non"; // "¬";
	
	public ExpNegation(ExpBooleenne exp){
		super(exp);
	}

	// FUNCTIONS
	@Override
	public ExpBooleenne cloneExpBool(){
		return new ExpNegation(getExp().cloneExpBool());
	}
	@Override
	public ExpBooleenne transformeNonEt(){
		return new ExpNegation(getExp().transformeNonEt());
	}
	@Override
	public ExpBooleenne simplifieEltNeutre() {
		ExpBooleenne exp = getExp().simplifieEltNeutre();

		if(exp.equals(ExpConstante.VRAI)) return ExpConstante.FAUX;
		else if(exp.equals(ExpConstante.FAUX)) return ExpConstante.VRAI;
		else return new ExpNegation(exp);
	}

	@Override
	public boolean evalue(ContexteEval context)
		throws VariableInconnueException
	{
		return !getExp().evalue(context);
	}
	@Override
	public String toString(){
		return toString(NEGATION_OPERATOR);
	}

	@Override
	public boolean equals(Object obj){
		return obj instanceof ExpNegation en
			&& en.getExp().equals(this.getExp());
	}
}
