package newlang4.nodes;

import java.util.EnumSet;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;
import newlang4.Value;

public class Variable extends Node{
	
	private static String name; 

	static final Set<LexicalType> firstSet = EnumSet.of(
			LexicalType.NAME
			);
			
	public static Node getHandler(LexicalUnit lu, Environment env, Value val) {
		return new Variable(env, val);
	}
	
	private Variable(Environment env, Value val) {
		this.env = env;
		this.type = NodeType.VARIABLE;
		this.value = val;
		name = val.getSValue();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "variable:" + name;
	}

}
