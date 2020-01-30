package newlang4.nodes;

import java.util.EnumSet;
import java.util.Set;

import newlang4.LexicalUnit;
import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.Node;
import newlang4.NodeType;

public class End extends Node{
	
	static final Set<LexicalType> firstSet = EnumSet.of(
			LexicalType.END
			);
	
	public static boolean isFirst(LexicalUnit lu) {
		
		return firstSet.contains(lu.getType());
		
	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {
		
		return new End(env);

	}
	
	private End(Environment env) {
		
		this.env = env;
		this.type = NodeType.END;

	}

	@Override
	public boolean parse() throws Exception {
		
		LexicalUnit first = env.getInput().get();

		if(!End.isFirst(first)) {
		}
		
		return true;

	}

	@Override
	public String toString() {
		return "END";
	}
}
