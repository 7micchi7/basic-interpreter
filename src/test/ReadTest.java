package test;

import java.io.IOException;
import java.io.PushbackReader;

public class ReadTest {
	
	private PushbackReader reader;
	private int backInt;

	public ReadTest(PushbackReader reader) {
		this.reader = reader;
	}
	
	public String getString() {
		
		int ci;
		String str1 = "";
		char ch;

		try {
//			ci = reader.read();
//			ch = (char)ci;
			
			if (backInt < 0) {
				reader.unread(backInt);
			}

			while (true) {
				ci = reader.read();
				ch = (char)ci;
//				System.out.println("readtestroop");

				if (String.valueOf(ci).matches("\\w")) {
					System.out.println("test");
					str1 += ch;
					continue;

				}
				
				break;
/*				if(String.valueOf(ch).matches("\\n")) {
//					System.out.print("lineSeparatorFind");
					str1 += "\n";
					break;
				}
			
				
				if(String.valueOf(ch).matches("\\s")) {
					str1 += "空";
					break;
				}
				
				str1 += ch;

				ci = reader.read();

				if(ci <= -1) {
					return str1;
				}

				ch = (char)ci;
				*/
			}

			return str1;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
