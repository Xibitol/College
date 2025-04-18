public final class ExpConstante extends ExpBooleenne{

	private static final String FALSE_VALUE_REPR = "faux"; // "⊥";
	private static final String TRUE_VALUE_REPR = "vrai"; // "⊤";

	public static final ExpConstante FAUX = new ExpConstante(false);
	public static final ExpConstante VRAI = new ExpConstante(true);

	private boolean value;
	
	private ExpConstante(boolean value){
		this.value = value;
	}

	// FUNCTIONS
	@Override
	public ExpBooleenne cloneExpBool(){ return this; }
	@Override
	public ExpBooleenne transformeNonEt(){ return cloneExpBool(); }
	@Override
	public ExpBooleenne simplifieEltNeutre(){ return cloneExpBool(); }

	@Override
	public boolean evalue(ContexteEval context)
		throws VariableInconnueException
	{
		return value;
	}
	@Override
	public String toString(){
		return value ? TRUE_VALUE_REPR : FALSE_VALUE_REPR;
	}

	@Override
	public boolean equals(Object obj){
		return this == obj;
	}
}
