package newlang3;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.UTFDataFormatException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) throws Exception {
		String filePath;
		int stringCode;
		char readChar;

		if(args[0].equals(null)) filePath = ".\\BasicTestProg.bas";
		else filePath = args[0];

		try(PushbackReader reader = new PushbackReader(
				Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8))) {
		} catch (Exception e) {
			// TODO: handle exception
		}

		LexicalAnalyzer LexAna = new LexicalAnalyzerImpl();
		
		while() {
			LexAna.get();
		}
		
	}
	
	private PushbackReader readFile() {

		if(args[0].equals(null)) filePath = ".\\BasicTestProg.bas";
		else filePath = args[0];
		
		try(PushbackReader reader = new PushbackReader(
			Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)))
		{

		} catch (Exception e) {
			// TODO: handle exception
		}

		return reader;
	}
	
	private void convert(PushbackReader reader) {
		// TODO Auto-generated method stub
		try {
			
			int stringCode = reader.read();
			while(stringCode != -1) {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
}
