package entiers;

/** Some tools about divisors and prime numbers. */
public class OutilsEntiers{

	// Diviseurs
	/** Returns a string of divisors of a number.
	 * @param n A positive number.
	 * @return Divisors of {@code n} joined together by ','.
	 */
	public String listeDiviseur(int n){
		StringBuilder sb = new StringBuilder("1");
		for(int m = 2; m <= n; m++)
			if(n%m == 0)
				sb.append(String.format(", %d", m));

		return sb.toString();
	}

	/** Prints to standard output a string of divisors of a number with a label.
	 * @param n A positive number.
	 * @return Divisors of {@code n} joined together by ',' with "Diviseurs de
	 * %d : " at start.
	 */
	public void afficherDiviseur(int n){
		System.out.println(String.format("Diviseurs de %d : %s.",
			n, listeDiviseur(n)
		));
	}

	/** Counts divisors of a positive number.
	 * @param n A positive number.
	 * @return Number of divisors of {@code n}.
	 */
	public int nbDiviseurs(int n){
		int c = 1;
		for(int m = 2; m <= n; c += n%m++ == 0 ? 1 : 0){}
		return c;
	}

	/** Counts divisors of a posivite number. This "V2" algorithm uses the
	 * number decomposition in prime factors.
	 * @param n A positive number.
	 * @return Number of divisors of {@code n}.
	 */
	public int nbDiviseursV2(int n){
		int c = 1;

		try{
			for(String str : decompositionFacteursPremiers(n).split(
				"x?[0-9]+\\^"
			))
				if(str.length() > 0) c *= Integer.parseInt(str) + 1;
		}catch(NumberFormatException e){
			throw e;
		}
		
		return c;
	}

	// Premiers
	/** Tells if a number is prime.
	 * @param n A positive number.
	 * @return True if it's prime, false otherwise.
	 */
	public boolean estPremier(int n){
		return nbDiviseurs(n) == 2;
	}

	/** Decomposes a number into a product of prime factors with exponents.
	 * @param n A positive number.
	 * @return A maths-like string calculus of the decomposition equal to
	 * {@code n}.
	 */
	public String decompositionFacteursPremiers(int n){
		StringBuilder sb = new StringBuilder();
		for(int m = 2, e = 0; n > 1 || e != 0;){
			if(estPremier(m)){
				if(n%m == 0){
					n /= m;
					e++;
				}
				else if(e == 0) m++;
				else{
					sb.append(String.format("%1$d^%2$dx", m, e));

					m = 1;
					e = 0;
				}
			}else m++;
		};
		return sb.deleteCharAt(sb.length() - 1).toString();
	}
}
