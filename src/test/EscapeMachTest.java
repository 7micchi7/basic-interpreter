package test;

public class EscapeMachTest {
	public static void main(String[] args) {
		String str1 = "\"aaa\"";
		String str2 = "\"aaa";
		String str3 = "\"aaa\\\"";
		String str4 = "\"\"";
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		if(str1.matches("\"[^\\\"].+\"")) {
			System.out.println("str1");
		}
		if(str2.matches("\"[^\\\"].+\"")) {
			System.out.println("str2");
		}
		if(str3.matches("\"[^\\\"].+\"")) {
			System.out.println("str3");
		}
		if(str4.matches("\"[^\\\"].+\"")) {
			System.out.println("str4");
		}
		
	}

}
