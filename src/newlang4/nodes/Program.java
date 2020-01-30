package newlang4.nodes;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Set;
import java.util.Stack;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class Program extends Node {

	private static Deque<Node> child = new ArrayDeque<Node>();
	
	static final Set<LexicalType> firstSet = EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO,
			LexicalType.DIM

			);
	
	
	
 	public static boolean isFirst(LexicalUnit lu) {
		return firstSet.contains(lu.getType());
	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {

		return new Program(env);

	}
	
	private Program(Environment env) {

		this.env = env;
		this.type = NodeType.PROGRAM;

	}
	
	@Override
	public boolean parse() throws Exception {
		
		LexicalUnit first = env.getInput().peek();

        if (StmtList.isFirst(first)) {
        	Node handler = StmtList.getHandler(first, env);
        	handler.parse();
        	child.addFirst(handler);
        }
        

		return true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ProgramPrint = "";
		
		while(!child.isEmpty()) {
			ProgramPrint += child.removeFirst().toString();
		}

		return ProgramPrint;
	}
}
