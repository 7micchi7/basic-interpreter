package newlang4.nodes;

import java.util.EnumSet;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;

public class Stmt extends Node {

	static final Set<LexicalType> firstSet = EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO
			);
	
	
	
 	public static boolean isFirst(LexicalUnit lu) {

		return firstSet.contains(lu.getType());

	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {
		
		return new Stmt();

	}
	
	@Override
	public boolean parse() throws Exception {

		LexicalUnit first = env.getInput().get();

        if (StmtList.isFirst(first)) {
        	Node handler = StmtList.getHandler(first, env);
        	handler.parse();
        }

		return true;
	}

}
