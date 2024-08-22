package collection;

import java.util.Iterator;
import java.util.Vector;

public class VectorExample {

	public static void main(String[] args) {
		Vector<Character> v=new Vector<Character>();
		v.add('r');
		v.add('a');
		v.add('n');
		v.add('d');
		v.add('o');
		v.add('m');
		Iterator<Character> itr=v.iterator();
		while(itr.hasNext()){
		System.out.println(itr.next());
		}
		}
		}

