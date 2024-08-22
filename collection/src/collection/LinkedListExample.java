package collection;

import java.util.LinkedList;

public class LinkedListExample {

	public static void main(String[] args) {
		LinkedList<Integer> exa = new LinkedList<>();
		exa.add(1);
		exa.add(2);
		exa.add(3);
		exa.add(8);
		exa.add(5);
		for (Integer f : exa) {
			System.out.println(f);
		}
	}
}
