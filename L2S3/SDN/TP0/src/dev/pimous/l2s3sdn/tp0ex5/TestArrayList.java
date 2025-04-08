package dev.pimous.l2s3sdn.tp0ex5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Xibitol
 */
public class TestArrayList{
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>(10);

		list.add("Un");
		list.add("Deux");
		list.addFirst("Premier");
		list.addLast("Dernier");
		list.add("Trois");
		list.addFirst("Premier des premiers");

		TestArrayList.println(list);

		System.out.println(list.containsAll(List.of("Un", "Deux")));

		// Multi-process stream
		// list.parallelStream()

		list.sort(Comparator.naturalOrder());
		// OR
		/*list.sort(new Comparator<String>() {
			public int compare(String o1, String o2){
				return o1.compareTo(o2);
			};
		});*/

		list.trimToSize();

		TestArrayList.println(list);
	}

	// FUNCTIONS
	public static void println(Collection<?> c){
		System.out.println("{%s}(%d)".formatted(
			c.stream().map(obj -> obj.toString()).collect(
				Collectors.joining(", ")
			),
			c.size()
		));
	}
}
