package collection;

import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {

	public static void main(String[] args) {
		List<String> ss=new ArrayList<>();
		ss.add("hi");
		ss.add("welcome");
		ss.add("insert");
		ss.add("collection");
		ss.add(null);
		for(String s:ss){
		System.out.println(s);
		}
		}
		}

