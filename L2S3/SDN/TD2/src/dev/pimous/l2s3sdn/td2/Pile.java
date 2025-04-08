package dev.pimous.l2s3sdn.td2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class Pile extends ArrayList<String>{

	// GETTERS
	/** Returns {@code true} if this list contains no elements.<br><br>
	 * Equivalent to {@code isEmpty()}.
	 * @return {@code true} if this list contains no elements.
	 */
	public boolean estVide(){
		return isEmpty();
	}

	/** {@inheritDoc}
	 * @implSpec Stack is iterated in its logical order, from top to bottom, in
	 * reverse order as a {@link java.util.ArrayList}.
	 */
	@Override
	public Iterator<String> iterator(){
		return new PileIterator();
	}
	/** {@inheritDoc}
	 * @implSpec Stack is iterated in its logical order, from top to bottom, in
	 * reverse order as a {@link java.util.ArrayList}.
	 */
	@Override
	public ListIterator<String> listIterator(){
		return new PileListIterator(size() - 1);
	}
	/** {@inheritDoc}
	 * @implSpec Stack is iterated in its logical order, from top to bottom, in
	 * reverse order as a {@link java.util.ArrayList}.
	 */
	@Override
	public ListIterator<String> listIterator(int index){
		if(index < 0 || index >= size())
			new IndexOutOfBoundsException(
				"%d is out of bounds (Length: %d)".formatted(index, size())
			);

		return new PileListIterator(index);
	}

	/** {@inheritDoc}
	 * @implSpec Stack is streamed in its logical order, from top to bottom, in
	 * reverse order as a {@link java.util.ArrayList}.
	 */
	@Override
	public Spliterator<String> spliterator() {
		return new PileSpliterator(Spliterator.ORDERED);
	}
	/** {@inheritDoc}
	 * @implSpec Stack is streamed in its logical order, from top to bottom, in
	 * reverse order as a {@link java.util.ArrayList}.
	 */
	@Override
	public Stream<String> stream(){
		return StreamSupport.stream(
			new PileSpliterator(Spliterator.ORDERED),
			false
		);
	}
	/** {@inheritDoc}
	 * @implSpec Stack is streamed in its logical order, from top to bottom, in
	 * reverse order as a {@link java.util.ArrayList}.
	 */
	@Override
	public Stream<String> parallelStream(){
		return StreamSupport.stream(
			new PileSpliterator(Spliterator.ORDERED),
			true
		);
	}

	// SETTERS
	/** Always throws an exception. Use instead {@link Pile#add(String)}.
	 * @throws UnsupportedOperationException A stack only accepts appending
	 * values to its end.
	 *
	 * @see Pile#add(String)
	 * @see Pile#addAll(java.util.Collection)
	 * @see Pile#addLast(String)
	 */
	@Override
	public void add(int index, String element)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A stack only accepts appending values to its end."
		);
	}
	/** Always throws an exception. Use instead {@link
	 * Pile#addLast(String)}.
	 * @throws UnsupportedOperationException A stack only accepts appending
	 * values to its end.
	 *
	 * @see Pile#add(String)
	 * @see Pile#addAll(java.util.Collection)
	 * @see Pile#addLast(String)
	 */
	@Override
	public void addFirst(String element)
		throws UnsupportedOperationException
	{
		super.addFirst(element);
	}
	/** Always throws an exception. Use instead {@link
	 * Pile#addAll(java.util.Collection)}.
	 * @throws UnsupportedOperationException A stack only accepts appending
	 * values to its end.
	 *
	 * @see Pile#add(String)
	 * @see Pile#addAll(java.util.Collection)
	 * @see Pile#addLast(String)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends String> c)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A stack only accepts appending values to its end."
		);
	}
	/** Appends the specified element to the stack (End of this list).<br><br>
	 * Equivalent to {@code add(element)}.
	 * @param element Element to be appended.
	 * @return {@code true} (as specified by {@link Collection#add}).
	 */
	public boolean empiler(String element){
		return add(element);
	}

	/** Always throws an exception. Use instead {@link
	 * Pile#removeLast()} then {@link Pile#add(String)}.
	 * @throws UnsupportedOperationException A stack cannot be modified. Old
	 * values should be popped out and new ones appended.
	 *
	 * @see Pile#removeLast()
	 * @see Pile#add(String)
	 * @see Pile#addLast(String)
	 */
	@Override
	public String set(int index, String element)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A stack cannot be modified."
		);
	}
	/** Always throws an exception. Use instead {@link
	 * Pile#removeLast()} then {@link Pile#add(String)}.
	 * @throws UnsupportedOperationException A stack cannot be modified. Old
	 * values should be popped out and new ones appended.
	 *
	 * @see Pile#removeLast()
	 * @see Pile#add(String)
	 * @see Pile#addLast(String)
	 */
	@Override
	public void replaceAll(UnaryOperator<String> operator) {
		throw new UnsupportedOperationException(
			"A stack cannot be modified."
		);
	}
	/** Always throws an exception. Use instead {@link
	 * Pile#removeLast()} then {@link Pile#add(String)}.
	 * @throws UnsupportedOperationException A stack cannot be modified. Old
	 * values should be popped out and new ones appended.
	 *
	 * @see Pile#removeLast()
	 * @see Pile#add(String)
	 * @see Pile#addLast(String)
	 */
	@Override
	public void sort(Comparator<? super String> c) {
		throw new UnsupportedOperationException(
			"A stack cannot be modified."
		);
	}

	/** Always throws an exception. Use instead {@link
	 * Pile#removeLast()}.
	 * @throws UnsupportedOperationException A stack only accepts popping out
	 * its last value. Values on top should be popped out before.
	 *
	 * @see Pile#removeLast()
	 */
	@Override
	public String remove(int index)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A stack only accepts popping out its last value."
		);
	}
	/** Always throws an exception. Use instead {@link
	 * Pile#removeLast()}.
	 * @throws UnsupportedOperationException A stack only accepts popping out
	 * its last value. Values on top should be popped out before.
	 *
	 * @see Pile#removeLast()
	 */
	@Override
	public String removeFirst()
		throws UnsupportedOperationException
	{
		return remove(0);
	}
	/** Always throws an exception. Use instead {@link
	 * Pile#removeLast()}.
	 * @throws UnsupportedOperationException A stack only accepts popping out
	 * its last value. Values on top should be popped out before.
	 *
	 * @see Pile#removeLast()
	 */
	@Override
	protected void removeRange(int fromIndex, int toIndex)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A stack only accepts popping out its last value."
		);
	}
	/** Always throws an exception. Use instead {@link
	 * Pile#removeLast()}.
	 * @throws UnsupportedOperationException A stack only accepts popping out
	 * its last value. Values on top should be popped out before.
	 *
	 * @see Pile#removeLast()
	 */
	@Override
	public boolean remove(Object o)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A stack only accepts popping out its last value."
		);
	}
	/** Always throws an exception. Use instead {@link
	 * Pile#removeLast()}.
	 * @throws UnsupportedOperationException A stack only accepts popping out
	 * its last value. Values on top should be popped out before.
	 *
	 * @see Pile#removeLast()
	 */
	@Override
	public boolean removeAll(Collection<?> c)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A stack only accepts popping out its last value."
		);
	}
	/** Always throws an exception. Use instead {@link
	 * Pile#removeLast()}.
	 * @throws UnsupportedOperationException A stack only accepts popping out
	 * its last value. Values on top should be popped out before.
	 *
	 * @see Pile#removeLast()
	 */
	@Override
	public boolean removeIf(Predicate<? super String> filter)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A stack only accepts popping out its last value."
		);
	}
	/** Always throws an exception. Use instead {@link
	 * Pile#removeLast()} and {@link Pile#add(String)}.
	 * @throws UnsupportedOperationException A stack only accepts popping out
	 * its last value. Values on top should be popped out before.
	 *
	 * @see Pile#removeLast()
	 * @see Pile#add(String)
	 * @see Pile#addLast(String)
	 */
	@Override
	public boolean retainAll(Collection<?> c)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(
			"A stack only accepts popping out its last value."
		);
	}
	/** Pops out and returns the last element of this stack (Last of this list).
	 * <br><br>
	 * Equivalent to {@code removeLast()}.
	 * @return Popped element.
	 */
	public String dépiler(){
		return super.removeLast();
	}

	// INNER CLASSES
	protected class PileIterator implements Iterator<String>{

		protected int index = Pile.this.size();
		protected int expectedModCount = modCount;

		// GETTERS
		@Override
		public boolean hasNext(){
			return index > 0;
		}
		@Override
		public String next(){
			if(!hasNext()) throw new NoSuchElementException();
			else if(expectedModCount != modCount)
				throw new ConcurrentModificationException();

			index--;
			return Pile.this.get(index);
		}
	}
	protected class PileListIterator extends PileIterator
		implements ListIterator<String>
	{

		public PileListIterator(int index){
			this.index = index;
		}

		// GETTERS
		@Override
		public boolean hasPrevious(){
			return index < Pile.this.size() - 1;
		}
		@Override
		public int previousIndex(){
			return hasPrevious() ? index + 1 : -1;
		}
		@Override
		public String previous(){
			if(!hasPrevious()) throw new NoSuchElementException();
			else if(expectedModCount != modCount)
				throw new ConcurrentModificationException();

			index++;
			return Pile.this.get(index);
		}
		@Override
		public int nextIndex(){
			return hasNext() ? index - 1 : Pile.this.size();
		}

		// SETTERS
		@Override
		public void add(String e){
			// Invalid implementation; should just throw an exception.
			Pile.this.add(index, e);
		}
		@Override
		public void set(String e){
			// Invalid implementation; should just throw an exception.
			Pile.this.set(index, e);
		}
		@Override
		public void remove(){
			// Invalid implementation; should just throw an exception.
			Pile.this.remove(index);
		}
	}
	protected class PileSpliterator extends AbstractSpliterator<String>{

		protected PileIterator iterator = new PileIterator();

		public PileSpliterator(int characteristics){
			super(size(), characteristics);
		}

		// FUNCTIONS
		@Override
		public boolean tryAdvance(Consumer<? super String> action){
			if(iterator.hasNext()){
				action.accept(iterator.next());
				return true;
			}

			return false;
		}
	}
}