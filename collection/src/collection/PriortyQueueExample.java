package collection;

import java.util.Iterator;
import java.util.PriorityQueue;

public class PriortyQueueExample {

	public static void main(String[] args) {
		PriorityQueue<String> queue=new PriorityQueue<String>();
		queue.add("gopi");
		queue.add("vijay");
		queue.add("krishna");
		queue.add("raja");
		queue.add("kishore");
		 queue.add("kishore");
		 Iterator<String> itr2=queue.iterator();
		 while(itr2.hasNext()){
		 System.out.println(itr2.next());
		 }
		 }
		 }

