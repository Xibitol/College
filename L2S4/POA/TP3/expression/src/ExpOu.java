public final class ExpOu extends ExpBinaire{

	private static final String OU_OPERATOR = "ou"; // "∨";
	
	public ExpOu(ExpBooleenne left, ExpBooleenne right){
		super(left, right);
	}

	// FUNCTIONS
	@Override
	public ExpBooleenne cloneExpBool(){
		return new ExpOu(getLeft().cloneExpBool(), getRight().cloneExpBool());
	}
	@Override
	public ExpBooleenne transformeNonEt(){
		return new ExpNegation(new ExpEt(
			new ExpNegation(getLeft().transformeNonEt()),
			new ExpNegation(getRight().transformeNonEt())
		));
	}
	@Override
	public ExpBooleenne simplifieEltNeutre(){
		ExpBooleenne left = getLeft().simplifieEltNeutre();
		ExpBooleenne right = getRight().simplifieEltNeutre();

		if(left.equals(ExpConstante.VRAI) || right.equals(ExpConstante.VRAI)) 
			return left;
		else if(left.equals(ExpConstante.FAUX)) return right;
		else if(right.equals(ExpConstante.FAUX)) return left;
		else if(left.equals(right)) return left;
		else return new ExpOu(left, right);
	}

	@Override
	public boolean evalue(ContexteEval context)
		throws VariableInconnueException
	{
		return getLeft().evalue(context) || getRight().evalue(context);
	}
	@Override
	public String toString(){
		return toString(OU_OPERATOR);
	}

	@Override
	public boolean equals(Object obj){
		return obj instanceof ExpOu eo
			&& (
				(
					eo.getLeft().equals(this.getLeft())
						&& eo.getRight().equals(this.getRight())
				) || (
					eo.getLeft().equals(this.getRight())
						&& eo.getRight().equals(this.getLeft())
				)
			);
	}
}
