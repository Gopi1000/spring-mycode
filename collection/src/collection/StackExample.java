package collection;

import java.util.Iterator;
import java.util.Stack;

public class StackExample {

	public static void main(String[] args) {
		Stack<Boolean> s=new Stack<>();
		s.add(true);
		s.add(false);
		s.add(true);
		Stack<Short> ss=new Stack<>();
		ss.push((short) 1);
		ss.push((short) 2);
		ss.push((short) 3);
		ss.pop();//remove Last Value of Stack
		Iterator<Boolean> itr=s.iterator();
		while(itr.hasNext()){
		System.out.println(itr.next());
		}
		Iterator<Short> itr1=ss.iterator();
		while(itr1.hasNext()){
		System.out.println(itr1.next());
		}
		}
		}

