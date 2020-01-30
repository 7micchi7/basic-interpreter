package newlang4;

import java.io.FileInputStream;
import java.io.PushbackReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import newlang4.LexicalAnalyzerImpl;
import newlang4.nodes.Program;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		String filePath = ".\\BasicTestProg.bas";
		LexicalAnalyzerImpl lex;
		LexicalUnit		first;
		Environment		env;

		try(PushbackReader reader = new PushbackReader(
				Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8))) {

			System.out.println("basic parser");
			lex = new LexicalAnalyzerImpl(reader);
			env = new Environment(lex);
			first = lex.peek();

			if (Program.isFirst(first)) {
				Node handler = Program.getHandler(first, env);
				handler.parse();
				System.out.println(handler.toString());
//				System.out.println("value = " + Program.getValue());
			}
			else System.out.println("syntax error");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
