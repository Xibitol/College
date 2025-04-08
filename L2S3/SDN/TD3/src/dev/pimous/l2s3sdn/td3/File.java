package dev.pimous.l2s3sdn.td3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

class File extends ArrayList<String>{

	// GETTERS
	/** Returns {@code true} if this list contains no elements.<br><br>
	 * Equivalent to {@code isEmpty()}.
	 * @return {@code true} if this list contains no elements.
	 */
	public boolean empty(){
		return isEmpty();
	}

	// SETTERS
	/** Always throws an exception. Use instead {@link File#add(String)}.
	 * @throws UnsupportedOperationException A queue only accepts appending
	 * values to its end.
	 *
	 * @see File#add(String)
	 * @see File#addAll(java.util.Collection)
	 * @see File#addLast(String)
	 */
	@Override
	public void add(int index, String element)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts appending values to its end."
		);
	}
	/** Always throws an exception. Use instead {@link File#addLast(String)}.
	 * @throws UnsupportedOperationException A queue only accepts appending
	 * values to its end.
	 *
	 * @see File#add(String)
	 * @see File#addAll(java.util.Collection)
	 * @see File#addLast(String)
	 */
	@Override
	public void addFirst(String element)
		throws UnsupportedOperationException
	{
		super.addFirst(element);
	}
	/** Always throws an exception. Use instead {@link
	 * File#addAll(java.util.Collection)}.
	 * @throws UnsupportedOperationException A queue only accepts appending
	 * values to its end.
	 *
	 * @see File#add(String)
	 * @see File#addAll(java.util.Collection)
	 * @see File#addLast(String)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends String> c)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts appending values to its end."
		);
	}

	/** Always throws an exception. Use instead {@link File#removeFirst()} then
	 * {@link File#add(String)}.
	 * @throws UnsupportedOperationException A queue cannot be modified. Old
	 * values should be shifted out and new ones appended.
	 *
	 * @see File#removeFirst()
	 * @see File#add(String)
	 * @see File#addLast(String)
	 */
	@Override
	public String set(int index, String element)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue cannot be modified."
		);
	}
	/** Always throws an exception. Use instead {@link File#removeFirst()} then
	 * {@link Pile#add(String)}.
	 * @throws UnsupportedOperationException A queue cannot be modified. Old
	 * values should be shifted out and new ones appended.
	 *
	 * @see File#removeFirst()
	 * @see File#add(String)
	 * @see File#addLast(String)
	 */
	@Override
	public void replaceAll(UnaryOperator<String> operator) {
		throw new UnsupportedOperationException(
			"A stack cannot be modified."
		);
	}
	/** Always throws an exception. Use instead {@link File#removeFirst()} then
	 * {@link File#add(String)}.
	 * @throws UnsupportedOperationException A queue cannot be modified. Old
	 * values should be shifted out and new ones appended.
	 *
	 * @see File#removeFirst()
	 * @see File#add(String)
	 * @see File#addLast(String)
	 */
	@Override
	public void sort(Comparator<? super String> c) {
		throw new UnsupportedOperationException(
			"A stack cannot be modified."
		);
	}

	/** Always throws an exception. Use instead {@link File#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see File#removeFirst()
	 */
	@Override
	public String remove(int index)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts shifting out its first value."
		);
	}
	/** Always throws an exception. Use instead {@link File#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom top be shifted out before.
	 *
	 * @see File#removeFirst()
	 */
	@Override
	public String removeLast()
		throws UnsupportedOperationException
	{
		return remove(size() - 1);
	}
	/** Always throws an exception. Use instead {@link File#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see File#removeFirst()
	 */
	@Override
	protected void removeRange(int fromIndex, int toIndex)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts shifting out its first value."
		);
	}
	/** Always throws an exception. Use instead {@link File#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see File#removeFirst()
	 */
	@Override
	public boolean remove(Object o)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts shifting out its first value."
		);
	}
	/** Always throws an exception. Use instead {@link File#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see File#removeFirst()
	 */
	@Override
	public boolean removeAll(Collection<?> c)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts shifting out its first value."
		);
	}
	/** Always throws an exception. Use instead {@link File#removeFirst()}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see File#removeFirst()
	 */
	@Override
	public boolean removeIf(Predicate<? super String> filter)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A queue only accepts shifting out its first value."
		);
	}
	/** Always throws an exception. Use instead {@link File#removeFirst()} and
	 * {@link File#add(String)}.
	 * @throws UnsupportedOperationException A queue only accepts shifting out
	 * its first value. Values on bottom should be shifted out before.
	 *
	 * @see File#removeFirst()
	 * @see File#add(String)
	 * @see File#addLast(String)
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
	public String get(){
		return isEmpty() ? null : removeFirst();
	}
}