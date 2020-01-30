package newlang4.nodes;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class ExprList extends Node {

	private static Deque<Node> child = new ArrayDeque<Node>();

	static final Set<LexicalType> firstSet = EnumSet.of(
			LexicalType.NAME,
			LexicalType.SUB,
			LexicalType.LP,
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			LexicalType.LITERAL
			);

	public static boolean isFirst(LexicalUnit lu) {

		return firstSet.contains(lu.getType());

	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {
		
		return new ExprList(env);
	}

	private ExprList(Environment env) {

		this.env = env;
		this.type = NodeType.EXPR_LIST;

	}
	
	@Override
	public boolean parse() throws Exception {
		
		LexicalUnit first = env.getInput().peek();
		boolean skipFlag = false;
		
		do {
			
			if(skipFlag) {
				env.getInput().get();
				skipFlag = false;
			}

			if(Expr.isFirst(first)) {
				Node handler = Expr.getHandler(first, env);
				handler.parse();
				child.addFirst(handler);
			} else {
				throw new Exception();
			}
			
			if(env.getInput().peek().getType() == LexicalType.COMMA) {
				skipFlag = true;
			}
			
		} while (first.getType() == LexicalType.COMMA);
		
		return true;
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return child.removeLast().toString();
	}
}
