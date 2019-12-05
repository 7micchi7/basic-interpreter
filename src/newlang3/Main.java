package newlang3;

import java.io.PushbackReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {

		String filePath = ".\\BasicTestProg.bas";
		int stringCode;
		char readChar;
		LexicalUnit unitTemp;

		List<LexicalUnit> unit = new ArrayList<LexicalUnit>();

		try(PushbackReader reader = new PushbackReader(
				Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8))) {

			LexicalAnalyzer LexAna = new LexicalAnalyzerImpl(reader);

			while(true) {

				System.out.println("yomikomi");
				unitTemp = LexAna.get();

//				System.out.println(unitTemp);
				unit.add(unitTemp);

				if (unitTemp.getType() == LexicalType.EOF) {
					break;
				}

			}
			
			for(LexicalUnit unitOut : unit) {
				System.out.println(unitOut);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		
	}
	
//	private PushbackReader readFile() {
//
//		if(args[0].equals(null)) filePath = ".\\BasicTestProg.bas";
//		else filePath = args[0];
//		
//		try(PushbackReader reader = new PushbackReader(
//			Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)))
//		{
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//		return reader;
//	}
//	
//	private void convert(PushbackReader reader) {
//		// TODO Auto-generated method stub
//		try {
//			
//			int stringCode = reader.read();
//			while(stringCode != -1) {
//				
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//	}
//	
}
