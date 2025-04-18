public abstract sealed class ExpBinaire extends ExpBooleenne permits ExpEt, ExpOu{

	private static final String BINARY_EXPRESSION_REPR = "(%s %s %s)";

	private ExpBooleenne left;
	private ExpBooleenne right;

	protected ExpBinaire(ExpBooleenne left, ExpBooleenne right){
		this.left = left;
		this.right = right;
	}
	
	// GETTERS
	public ExpBooleenne getLeft(){ return left; }
	public ExpBooleenne getRight(){ return right; }

	// FUNCTIONS
	protected String toString(String operator){
		return BINARY_EXPRESSION_REPR.formatted(
			getLeft(), operator, getRight()
		);
	}
}
