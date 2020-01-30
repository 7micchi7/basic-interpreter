package newlang4.nodes;

import java.util.EnumSet;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;
import newlang4.Value;

public class Operator extends Node {
	static final Set<LexicalType> firstSet = EnumSet.of(

			LexicalType.SUB,
			LexicalType.ADD,
			LexicalType.DIV,
			LexicalType.MUL

			);

	public static boolean isFirst(LexicalUnit lu) {

		return firstSet.contains(lu.getType());

	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) throws Exception {
		
		if(!firstSet.contains(lu.getType())) {
			return null;
		}
		
		return new Operator(env, env.getInput().peek().getValue());
	}

	public static Node getHandler(LexicalUnit lu, Environment env, Value val) {
		
		return new Operator(env, val);

	}
	
	private Operator(Environment env, Value val) {
		this.env = env;
		
		try {
			
			switch(env.getInput().peek().getType()) {
			case ADD:
				this.type = NodeType.ADD;
				break;
			case SUB:
				this.type = NodeType.SUB;
				break;
			case MUL:
				this.type = NodeType.MUL;
				break;
			case DIV:
				this.type = NodeType.DIV;
				break;
				
			default:
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
