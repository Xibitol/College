public abstract sealed class ExpBooleenne
	implements Cloneable
	permits ExpBinaire, ExpUnaire, ExpConstante, VarBool
{

	// FUNCTIONS
	public abstract ExpBooleenne cloneExpBool();
	@Override
	public Object clone(){ return cloneExpBool(); }
	public abstract ExpBooleenne transformeNonEt();
	public abstract ExpBooleenne simplifieEltNeutre();

	public abstract boolean evalue(ContexteEval context)
		throws VariableInconnueException;

	@Override
	public abstract String toString();

	@Override
	public abstract boolean equals(Object obj);
}
