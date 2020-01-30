package newlang4.nodes;

import java.util.EnumSet;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class Subst extends Node{
		
	String leftVar = "";
	Node expr;

	static final Set<LexicalType> firstSet = EnumSet.of(
			
				LexicalType.NAME

			);
	
	public static boolean isFirst(LexicalUnit lu) {
		
		return firstSet.contains(lu.getType());
		
	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {
		
		return new Subst(env);
		
	}
	
	public Subst(Environment env) {

		this.env = env;
		this.type = NodeType.SUBST_STMT;
		
	}

	@Override
	public boolean parse() throws Exception {
		// TODO Auto-generated method stub

		LexicalUnit first = env.getInput().peek();

		if(first.getType() == LexicalType.NAME) {
			leftVar = env.getInput().get().getValue().getSValue();
		} else {
			throw new InternalError();
		}

		if(env.getInput().get().getType() != LexicalType.EQ) {
			throw new Exception();
		}
		
		if(Expr.isFirst(first)) {
			
			expr = Expr.getHandler(first, env);
			expr.parse();
			
		} else {

			throw new Exception();

		}
			
		return true;
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		try {
			return "subst:" + expr.toString() + "->" + leftVar;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}
