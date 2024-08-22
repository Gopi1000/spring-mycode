package collection;

public class Demo{

public static void main(String[] args){

String name="gopikannan";
String name1="";
 for(int a= name.length()-1; a>=0;a--){
     name1 = name1 +name.charAt(a);
 }
System.out.println(name1);

if(name.equals(name1))
System.out.println("palindrome");
else System.out.println("not palindrome");



}
}

