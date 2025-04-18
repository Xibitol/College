public final class VarBool extends ExpBooleenne{

	private char name;
	
	public VarBool(char name){
		this.name = name;
	}

	// FUNCTIONS
	@Override
	public ExpBooleenne cloneExpBool(){
		return new VarBool(name);
	}
	@Override
	public ExpBooleenne transformeNonEt(){ return cloneExpBool(); }
	@Override
	public ExpBooleenne simplifieEltNeutre(){ return cloneExpBool(); }

	@Override
	public boolean evalue(ContexteEval context)
		throws VariableInconnueException
	{
		Boolean value = context.donne(name);
		if(value == null)
			throw new VariableInconnueException();

		return value;
	}
	@Override
	public String toString(){
		return String.valueOf(name);
	}

	@Override
	public boolean equals(Object obj){
		return obj instanceof VarBool vb && vb.name == this.name;
	}
}
