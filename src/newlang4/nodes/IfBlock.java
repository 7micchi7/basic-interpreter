package newlang4.nodes;

import java.util.EnumSet;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class IfBlock extends Node{
	
	static final Set<LexicalType> firstSet = EnumSet.of(

			LexicalType.IF

			);
	
	public static boolean isFirst(LexicalUnit lu) {
		
		return firstSet.contains(lu.getType());
		
	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {
		
		return new IfBlock(env);

	}
	
	private IfBlock(Environment env) {
		
		this.env = env;
		this.type = NodeType.IF_BLOCK;
	}
	

}
