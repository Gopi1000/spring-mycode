package collection;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeExample {

	public static void main(String[] args) {
		
		Deque<String> deque = new ArrayDeque<String>();
		 deque.add("Vasanth");
		 deque.add("ramu");
		 deque.add("gopikanann");
		 deque.add("gopikannan");

		 for (String str : deque) {
		 System.out.println(str);
		 }
		 }
		}
