package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.ObjectInputStream.GetField;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FilesLinesTest {
	public static void main(String[] args) {
		String str = null;
		char char1;
		int int1;
		try {
		//行数とか文字のポインタを自分で管理する必要が出てしまうため却下
		//一行ずつFiles.linesで読み取る。
/*
 * 			str = Files.lines(Paths.get(".\\basicTestProg.bas"), Charset.forName("UTF-8"))
 *						.collect(Collectors.joining(System.getProperty("line.separator")));
 */
			PushbackReader reader = new PushbackReader(
					Files.newBufferedReader(
							Paths.get(".\\basicTestProg.bas"), StandardCharsets.UTF_8));
			int1 = reader.read();
			while(int1 != -1) {
				if(int1 != 32 && int1 != 9)
				System.out.print((char)int1 /*+ "" + int1*/);
				int1 = reader.read();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println(str);
	}
}
