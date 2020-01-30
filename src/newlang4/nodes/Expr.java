package newlang4.nodes;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.EnumSet;
import java.util.Set;
import java.util.Stack;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;
import newlang4.Value;
import newlang4.ValueImpl;

public class Expr extends Node {

	private Deque<Node> sumQue = new ArrayDeque<Node>();

	static final Set<LexicalType> firstSet = EnumSet.of(
			
			LexicalType.SUB,
			LexicalType.ADD,
			LexicalType.MUL,
			LexicalType.DIV,
			LexicalType.LP,
			LexicalType.NAME,
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			LexicalType.LITERAL

			);
	
	static final Set<LexicalType> superiorOprerator = EnumSet.of(
			
			LexicalType.MUL,
			LexicalType.DIV
			
			);
	
	public static boolean isFirst(LexicalUnit lu) {
		
		return firstSet.contains(lu.getType());

	}
	
	public static Node getHandler(LexicalUnit lu, Environment env) {
		
		return new Expr(env);

	}
	
	private Expr(Environment env) {
		
		this.env = env;
		this.type = NodeType.EXPR;
		
	}
	
	@Override
	public boolean parse() throws Exception {
		// TODO Auto-generated method stub
		
		Deque<Node> operator = new ArrayDeque<Node>();
		Deque<Node> valueStack = new ArrayDeque<Node>();
		Node handler;
		
		while(true) {
			LexicalUnit first = env.getInput().peek();
			switch(first.getType()) {
			case LP:
				//LP削除
				env.getInput().get();
				
				handler = Expr.getHandler(env.getInput().peek(), env);
				handler.parse();
				sumQue.addFirst(handler);

				if(env.getInput().get().getType() != LexicalType.RP) {
					throw new Exception();
				}

				break;

			case SUB:
				/*
                while (!operator.isEmpty()) {
                    Node tmpNode = operator.getFirst();
                    if (tmpNode.getValue().getSValue() == "*"
                    		|| tmpNode.getValue().getSValue() == "/") {
                    	sumQue.addFirst(operator.removeFirst());
                    } else {
                        break;
                    }
                }
                */
				handler = Operator.getHandler(first, env);
//				operator.addFirst(handler);
				sumQue.addFirst(handler);
				env.getInput().get();

				break;

			case INTVAL:
			case DOUBLEVAL:
			case LITERAL:
				LexicalUnit getUnit = env.getInput().peek();
				sumQue.addFirst(Const.getHandler(getUnit, env));
				env.getInput().get();

				break;

			case NAME:
				first = env.getInput().get();
				handler = Variable.getHandler(first, env, first.getValue());
				sumQue.addFirst(handler);
				break;
				
//			case CALL_FUNC:
			default:
				break;

			}
			
			if(!firstSet.contains(env.getInput().peek().getType())) {
				while(!operator.isEmpty()) {
					sumQue.addFirst(operator.removeFirst());
				}
				break;
			}
					
		}
		
//		if(!operator.isEmpty()) {
			
//		}
		
//		if(!valueStack.isEmpty()) {
//			while(!valueStack.isEmpty()) {
				
//			}
//			sumResult = new ValueImpl(calSub(operator, valueStack));
//		} 

		return true;
	}
	
	private int calSub(Deque<LexicalUnit> operator, Deque<Node> valueStack) throws Exception {

		int result = valueStack.getFirst().getValue().getIValue();

		if(operator.isEmpty()) {
			return valueStack.getFirst().getValue().getIValue(); 
		} else if(valueStack.isEmpty()) {
			throw new Exception("Exception in calSub valueStack empty");
		}


		while(!operator.isEmpty()) {
			int tmp = valueStack.getFirst().getValue().getIValue();
			switch (operator.getFirst().getType()) {
			case SUB:
				result = tmp - result;
				break;

			case ADD:
				result = tmp + result;
				break;
			
			case MUL:
				result = tmp * result;
			
			case DIM:
				result = tmp / result;

			default:
				break;
				
			}
		}

		return result;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String exprPrint = "expr: ";
		try {
			while(!sumQue.isEmpty()) {
				if(sumQue.getLast().getType() == NodeType.INT_CONSTANT
					|| sumQue.getLast().getType() == NodeType.DOUBLE_CONSTANT
					|| sumQue.getLast().getType() == NodeType.STRING_CONSTANT
					|| sumQue.getLast().getType() == NodeType.VARIABLE) {
					exprPrint += sumQue.removeLast().getValue().getSValue() + " ";
				} else {
					exprPrint += sumQue.removeLast().getType() + " ";
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return exprPrint;
	}
}
