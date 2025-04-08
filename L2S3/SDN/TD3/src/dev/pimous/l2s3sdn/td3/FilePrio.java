package dev.pimous.l2s3sdn.td3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class FilePrio extends ArrayList<Personne>{

	// GETTERS
	/** Returns {@code true} if this list contains no elements.<br><br>
	 * Equivalent to {@code isEmpty()}.
	 * @return {@code true} if this list contains no elements.
	 */
	public boolean empty(){
		return isEmpty();
	}

	// SETTERS
	@Override
	public boolean add(Personne e) {
		int i = size();
		while(i > 0 && get(i - 1).getPrio() > e.getPrio()) i--;

		super.add(i, e);
		return true;
	}
	/** Always throws an exception. Use instead {@link FilePrio#add(Personne)}.
	 * @throws UnsupportedOperationException A queue only accepts appending
	 * values to its end.
	 *
	 * @see FilePrio#add(Personne)
	 * @see FilePrio#addAll(java.util.Collection)
	 * @see FilePrio#addLast(Personne)
	 */
	@Override
	public void add(int index, Personne element)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts appending values to its end."
		);
	}
	/** Always throws an exception. Use instea
	 * {@link FilePrio#addLast(Personne)}.
	 * @throws UnsupportedOperationException A queue only accepts appending
	 * values to its end.
	 *
	 * @see FilePrio#add(Personne)
	 * @see FilePrio#addAll(java.util.Collection)
	 * @see FilePrio#addLast(Personne)
	 */
	@Override
	public void addFirst(Personne element)
		throws UnsupportedOperationException
	{
		super.addFirst(element);
	}
	/** Always throws an exception. Use instead {@link
	 * FilePrio#addAll(java.util.Collection)}.
	 * @throws UnsupportedOperationException A queue only accepts appending
	 * values to its end.
	 *
	 * @see FilePrio#add(Personne)
	 * @see FilePrio#addAll(java.util.Collection)
	 * @see FilePrio#addLast(Personne)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends Personne> c)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts appending values to its end."
		);
	}

	/** Always throws an exception. Use instead {@link FilePrio#removeFirst()}
	 * then {@link FilePrio#add(Personne)}.
	 * @throws UnsupportedOperationException A queue cannot be modified. Old
	 * values should be shifted out and new ones appended.
	 *
	 * @see FilePrio#removeFirst()
	 * @see FilePrio#add(Personne)
	 * @see FilePrio#addLast(Personne)
	 */
	@Override
	public Personne set(int index, Personne element)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue cannot be modified."
		);
	}
	/** Always throws an exception. Use instead {@link FilePrio#removeFirst()}
	 * then {@link Pile#add(Personne)}.
	 * @throws UnsupportedOperationException A queue cannot be modified. Old
	 * values should be shifted out and new ones appended.
	 *
	 * @see FilePrio#removeFirst()
	 * @see FilePrio#add(Personne)
	 * @see FilePrio#addLast(Personne)
	 */
	@Override
	public void replaceAll(UnaryOperator<Personne> operator) {
		throw new UnsupportedOperationException(
			"A stack cannot be modified."
		);
	}
	/** Always throws an exception. Use instead {@link FilePrio#removeFirst()}
	 * then {@link FilePrio#add(Personne)}.
	 * @throws UnsupportedOperationException A queue cannot be modified. Old
	 * values should be shifted out and new ones appended.
	 *
	 * @see FilePrio#removeFirst()
	 * @see FilePrio#add(Personne)
	 * @see FilePrio#addLast(Personne)
	 */
	@Override
	public void sort(Comparator<? super Personne> c) {
		throw new UnsupportedOperationException(
			"A stack cannot be modified."
		);
	}

	/** Always throws an exception. Use instead {@link FilePrio#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see FilePrio#removeFirst()
	 */
	@Override
	public Personne remove(int index)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts shifting out its first value."
		);
	}
	/** Always throws an exception. Use instead {@link FilePrio#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom top be shifted out before.
	 *
	 * @see FilePrio#removeFirst()
	 */
	@Override
	public Personne removeLast()
		throws UnsupportedOperationException
	{
		return remove(size() - 1);
	}
	/** Always throws an exception. Use instead {@link FilePrio#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see FilePrio#removeFirst()
	 */
	@Override
	protected void removeRange(int fromIndex, int toIndex)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts shifting out its first value."
		);
	}
	/** Always throws an exception. Use instead {@link FilePrio#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see FilePrio#removeFirst()
	 */
	@Override
	public boolean remove(Object o)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts shifting out its first value."
		);
	}
	/** Always throws an exception. Use instead {@link FilePrio#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see FilePrio#removeFirst()
	 */
	@Override
	public boolean removeAll(Collection<?> c)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts shifting out its first value."
		);
	}
	/** Always throws an exception. Use instead {@link FilePrio#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see FilePrio#removeFirst()
	 */
	@Override
	public boolean removeIf(Predicate<? super Personne> filter)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts shifting out its first value."
		);
	}
	/** Always throws an exception. Use instead {@link FilePrio#removeFirst()}
	 * and {@link FilePrio#add(Personne)}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see FilePrio#removeFirst()
	 * @see FilePrio#add(Personne)
	 * @see FilePrio#addLast(Personne)
	 */
	@Override
	public boolean retainAll(Collection<?> c)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts shifting out its first value."
		);
	}
	/** Shifts and returns the next element of this queue (First of this list).
	 * <br><br>
	 * Equivalent to {@code removeFirst()}.
	 * @return Shifted element.
	 */
	public Personne get(){
		return isEmpty() ? null : removeFirst();
	}
}
