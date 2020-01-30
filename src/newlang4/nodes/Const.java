package newlang4.nodes;

import java.util.EnumSet;
import java.util.Set;

import newlang4.Value;
import newlang4.ValueImpl;
import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class Const extends Node{
	
	private Value value;

	static final Set<LexicalType> firstSet = EnumSet.of(
				
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			LexicalType.LITERAL
			
			);
	
	public static boolean isFirst(LexicalUnit lu) {
		
		return firstSet.contains(lu.getType());
		
	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) throws Exception {

		if(!firstSet.contains(lu.getType())) {
			return null;
		}

		return new Const(env, env.getInput().peek().getValue());
		
	}
	
	public static Node getHandler(LexicalUnit lu, Environment env, Value val) {
		
		return new Const(env, val);

	}
	
	private Const(Environment env, Value value) {
		
		this.env = env;
		try {

			switch (value.getType()) {
			case INTEGER:
				this.type = NodeType.INT_CONSTANT;
				break;
				
			case DOUBLE:
				this.type = NodeType.DOUBLE_CONSTANT;
				break;
			
			case STRING:
				this.type = NodeType.STRING_CONSTANT;
				break;

			default:
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.value = value;

	}
	
	public Value getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Const:" + value;
	}
}
