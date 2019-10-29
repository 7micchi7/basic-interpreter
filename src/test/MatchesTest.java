package test;

public class MatchesTest {
	public static void main(String[] args) {

//		String str1 = null;
		String str2 = "";
		String str3 = "1";
		String str4 = "a";
		String str5 = ".";
		String str6 = "12.";
		String str7 = "a12.";
		
//		if(str1.matches("\\d")) System.out.println("true str1");
		if(str2.matches("[0-9.]*?")) System.out.println("true str2");
		if(str3.matches("[0-9.]*?")) System.out.println("true str3");
		if(str4.matches("[0-9.]*?")) System.out.println("true str4");
		if(str5.matches("[0-9.]*?")) System.out.println("true str5");
		if(str5.matches("\\d+[.]")) System.out.println("true str5 ex");
		if(str6.matches("[0-9.]*?")) System.out.println("true str6");
		if(str6.matches("\\d+[.]")) System.out.println("true str6 ex");
		if(str7.matches("[0-9.]*?")) System.out.println("true str7");
		
	}

}
