package newlang4.nodes;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.EnumSet;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class Stmt extends Node {

	private Deque<Node> child = new ArrayDeque<Node>();
	private boolean endFlag = false;

	static final Set<LexicalType> firstSet = EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.DIM
			);
	
	
	
 	public static boolean isFirst(LexicalUnit lu) {

		return firstSet.contains(lu.getType());

	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {
		
		if(!isFirst(lu)) return null;

		return new Stmt(env);
	}
	
	private Stmt(Environment env) {

		this.env = env;
		this.type = NodeType.STMT;

	}

	public boolean getEndFlag() {
		return endFlag;
	}
	
	@Override
	public boolean parse() throws Exception {

		LexicalUnit first = env.getInput().peek();
		LexicalUnit second = env.getInput().peek(2);
		Node handler = null;
		
		try {
			switch(first.getType()) {
			case NAME:
				if(second.getType() == LexicalType.EQ) {
					handler = Subst.getHandler(first, env);
				} else if(ExprList.isFirst(second)) {
					handler = ExprList.getHandler(second, env);
				} else { 
					throw new Exception();
				}
				break;

			case FOR:
//				handler = ForStmt.getHandler(first, env);
			case END:
				endFlag = true;
				handler = End.getHandler(first, env);
				break;
			default:
				return false;
			}

			handler.parse();
			child.addFirst(handler);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return true;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return child.removeLast().toString();
//		return "stmt";
	}

}
