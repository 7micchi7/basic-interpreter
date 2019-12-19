package newlang4.nodes;

import java.util.List;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class StmtList extends Node{

	List<Node> nodeList = new ArrayList<>();
	
	static final Set<LexicalType> firstSet = EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO
			);
	
	
	
	
 	public static boolean isFirst(LexicalUnit lu) {
		return firstSet.contains(lu.getType());
	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {
		
		return new StmtList(env);
	}
	
	private StmtList(Environment env) {

		this.env = env;
		this.type = NodeType.STMT_LIST;

	}
	
	@Override
	public boolean parse() throws Exception {

		LexicalUnit first = env.getInput().get();

		//ツリー作る場所
		while(true) {
			
			Node handler;

			if (Stmt.isFirst(first)) {

				handler = Stmt.getHandler(first, env);

			} else if(Block.isFirst(first)) {

				handler = Block.getHandler(first, env);

			} else {

				break;

			}

			handler.parse();
			nodeList.add(handler);

		}

		return true;
	}
}
