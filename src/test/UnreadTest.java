package test;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UnreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String filePath = ".\\BasicTestProg.bas";
		String returnStr;
		int stringChar;
		int roopTime = 5;
		List<String> returnStrList = new ArrayList<String>();
		
		ReadTest test;

		try {
			PushbackReader reader = new PushbackReader(Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8));
			
			test = new ReadTest(reader);
			
			while (true) {

				stringChar = reader.read();
				
				if (stringChar <= -1) {
					break;
				}
				
				reader.unread(stringChar);
				
				returnStr = test.getString();
				returnStrList.add(returnStr);
				System.out.print(returnStr);

			}

			System.out.println("readFinish");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String str :returnStrList) {
			System.out.println(str);
		}
//		System.out.println("finish");
	}

}
