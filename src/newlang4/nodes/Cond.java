package newlang4.nodes;

import java.util.EmptyStackException;
import java.util.EnumSet;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class Cond extends Node {
	
	Node leftNode;
	Node rightNode;
	LexicalType operator;
	

	static final Set<LexicalType> firstSet = EnumSet.of(

//			LexicalType.CALL_FUNC,
			LexicalType.LP,
			LexicalType.RP,
			LexicalType.NAME,
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			LexicalType.LITERAL

			);
	
	static final Set<LexicalType> operatorSet = EnumSet.of(
			
			LexicalType.EQ,
			LexicalType.GT,
			LexicalType.LT,
			LexicalType.GE,
			LexicalType.LE,
			LexicalType.NE
			
			);
	
	public static boolean isFirst(LexicalUnit lu) {
		
		return firstSet.contains(lu.getType());
		
	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {
		
		if(!isFirst(lu)) {
			return null;
		} else {
			return new Cond(env);
		}
		
	}
	
	private Cond(Environment env) {
		
		this.env = env;
		this.type = NodeType.COND;
		
	}
	
	
	@Override
	public boolean parse() throws Exception {
		// TODO Auto-generated method stub
		
		LexicalUnit first = env.getInput().peek();
		
		if(Expr.isFirst(first)) {
			leftNode = Expr.getHandler(first, env);
			leftNode.parse();

		} else {
			throw new Exception();
		}
		
		first = env.getInput().peek();
		if(operatorSet.contains(first.getType())) {

			operator = env.getInput().get().getType();

		} else {
			throw new Exception();
		}

		first = env.getInput().peek();
		if(Expr.isFirst(first)) {
			rightNode = Expr.getHandler(first, env);
			rightNode.parse();

		} else {
			throw new Exception();
		}
		
		return true;

	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "COND:[" + leftNode.toString() + "] "
				+ operator
				+ " [" + rightNode.toString() + "]" ;
	}

}
