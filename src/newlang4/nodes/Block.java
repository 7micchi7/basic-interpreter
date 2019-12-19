package newlang4.nodes;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class Block extends Node {

	List<Node> nodeList = new ArrayList<>();
	
	static final Set<LexicalType> firstSet = EnumSet.of(

			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO

			);

	public static boolean isFirst(LexicalUnit lu) {
		
		return firstSet.contains(lu.getType());
		
	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {
		
		return new Block(env);
		
	}

	private Block(Environment env) {
		
		this.env = env;
		this.type = NodeType.END;
		
	}
	
	@Override
	public boolean parse() throws Exception {
		// TODO Auto-generated method stub
		
		LexicalUnit first = env.getInput().get();
		
		while(true) {
			
			Node handler;
			
			if (IfBlock.isFirst) {
				
				handler = IfBlock.getHander(first, env);
				
			} else if (Loop.isFirst) {
				
				handler = Loop.getHander(first, env);
				
			} else {

				break;

			}
		}
		
		handler.parse();
		nodeList.add(handler);

	}
	
	return true;
}
