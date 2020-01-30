package newlang4.nodes;

import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Set;
import java.util.Stack;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class StmtList extends Node{

	private Deque<Node> child = new ArrayDeque<Node>();

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
		
		return new StmtList(env);

	}
	
	private StmtList(Environment env) {

		this.env = env;
		this.type = NodeType.STMT_LIST;

	}
	
	@Override
	public boolean parse() throws Exception {


		//ツリー作る場所
		while(true) {
			
			//NL読み飛ばし
			if(env.getInput().peek().getType() == LexicalType.NL) {
				env.getInput().get();
			}
			
			Node handler;
			LexicalUnit first = env.getInput().peek();

			if (Stmt.isFirst(first)) {

				handler = Stmt.getHandler(first, env);

			} else if(Block.isFirst(first)) {

				handler = Block.getHandler(first, env);

			} else {

				break;

			}

			handler.parse();
			child.addFirst(handler);

		}

		return true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String stmtPrint = "";
		stmtPrint = "stmtList(" + child.size() + "):\n";

		while(!child.isEmpty()) {
			stmtPrint += child.removeLast().toString() + "\n";
		}
		return stmtPrint;
	}
}
