package newlang4.nodes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class Block extends Node {

	private static Deque<Node> child = new ArrayDeque<Node>();
	
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
		this.type = NodeType.BLOCK;
		
	}
	
	@Override
	public boolean parse() throws Exception {
		// TODO Auto-generated method stub
		
		LexicalUnit first = env.getInput().peek();
		
		Node handler = null;

		if (IfBlock.isFirst(first)) {
			
			handler = IfBlock.getHandler(first, env);
			
		} else if (LoopBlock.isFirst(first)) {
			
			handler = LoopBlock.getHandler(first, env);
			
		}

		handler.parse();
		child.addFirst(handler);

		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return child.removeLast().toString();
	}
}
