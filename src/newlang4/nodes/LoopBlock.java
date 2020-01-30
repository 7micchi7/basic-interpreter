package newlang4.nodes;

import java.util.EnumSet;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class LoopBlock extends Node{
	Node cond;
	Node process;

	static final Set<LexicalType> firstSet = EnumSet.of(

			LexicalType.DO,
			LexicalType.WHILE

			);
	
	public static boolean isFirst(LexicalUnit lu) {
		
		return firstSet.contains(lu.getType());
		
	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {
		
		return new LoopBlock(env);

	}
	
	private LoopBlock(Environment env) {
		
		this.env = env;
		this.type = NodeType.LOOP_BLOCK;
	}
	
	@Override
	public boolean parse() throws Exception {
		// TODO Auto-generated method stub
		LexicalUnit first = env.getInput().peek();
		if (first.getType() == LexicalType.WHILE) {
			
		} else if (first.getType() == LexicalType.DO) {
			//DO読み飛ばし
			env.getInput().get();

			if(env.getInput().peek().getType() == LexicalType.UNTIL) {
				//UNTIL読み飛ばし
				env.getInput().get();

				first = env.getInput().peek();
				cond = Cond.getHandler(first, env);
				cond.parse();

			}
			
			if(env.getInput().get().getType() != LexicalType.NL) {
				throw new Exception();
			}
			
			first = env.getInput().peek();
			if(Stmt.isFirst(first)) {
				process = StmtList.getHandler(first, env);
				process.parse();
			}
		}
			
		
		return true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String LoopBlock = "LOOPBLOCK:cond = ";
		LoopBlock += cond.toString();
		LoopBlock += "PROCESS [\n";
		LoopBlock += process.toString() + "]";

		return LoopBlock;
	}
}
