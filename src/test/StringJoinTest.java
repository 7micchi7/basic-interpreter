package test;

public class StringJoinTest {
	public static void main(String[] args) {
		String str1 = "aaa";
		String str2 = "bbb";
		String str3 = str1 + str2;
		String str4 = "ccc";
		for(int i = 0; i<5; i++) {
			str4 += str1;
		}
		
		System.out.println(str4);

	}

}
