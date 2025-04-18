public final class ExpEt extends ExpBinaire{

	private static final String ET_OPERATOR = "et"; // "∧";
	
	public ExpEt(ExpBooleenne left, ExpBooleenne right){
		super(left, right);
	}

	// FUNCTIONS
	@Override
	public ExpBooleenne cloneExpBool(){
		return new ExpEt(getLeft().cloneExpBool(), getRight().cloneExpBool());
	}
	@Override
	public ExpBooleenne transformeNonEt(){
		return new ExpEt(
			getLeft().transformeNonEt(),
			getRight().transformeNonEt()
		);
	}
	@Override
	public ExpBooleenne simplifieEltNeutre(){
		ExpBooleenne left = getLeft().simplifieEltNeutre();
		ExpBooleenne right = getRight().simplifieEltNeutre();

		if(left.equals(ExpConstante.VRAI)) return right;
		else if(right.equals(ExpConstante.VRAI)) return left;
		else if(
			left.equals(ExpConstante.FAUX) || right.equals(ExpConstante.FAUX)
		) return ExpConstante.FAUX;
		else if(left.equals(right)) return left;
		else return new ExpEt(left, right);
	}

	@Override
	public boolean evalue(ContexteEval context)
		throws VariableInconnueException
	{
		return getLeft().evalue(context) && getRight().evalue(context);
	}
	@Override
	public String toString(){
		return toString(ET_OPERATOR);
	}

	@Override
	public boolean equals(Object obj){
		return obj instanceof ExpEt ee
			&& (
				(
					ee.getLeft().equals(this.getLeft())
						&& ee.getRight().equals(this.getRight())
				) || (
					ee.getLeft().equals(this.getRight())
						&& ee.getRight().equals(this.getLeft())
				)
			);
	}
}
