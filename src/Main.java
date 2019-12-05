import java.io.IOException;
import java.io.PushbackReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import newlang3.LexicalAnalyzer;
import newlang3.LexicalAnalyzerImpl;
import newlang3.LexicalUnit;

public class Main {
	public static void main(String[] args) {
		String filePath = args[0];
		LexicalUnit lexUni;
		
		if(args[0].equals(null)) filePath = ".\\BasicTestProg.bas";
		
			PushbackReader reader;
			try {

				reader = new PushbackReader(Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8));

				LexicalAnalyzer lexAna = new LexicalAnalyzerImpl(reader);

				while (true) {

					lexUni = lexAna.get();
					System.out.println(lexUni);

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			do {

			} while (true);
		
		
	}

}
