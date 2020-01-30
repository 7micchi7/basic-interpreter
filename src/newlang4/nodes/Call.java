package newlang4.nodes;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import newlang4.Environment;
import newlang4.LexicalType;
import newlang4.LexicalUnit;
import newlang4.Node;
import newlang4.NodeType;

public class Call extends Node {
	
	String funcName;
	
	static final Set<LexicalType> firstSet = EnumSet.of(
			LexicalType.NAME
			);
    
    public static boolean isFirst(LexicalUnit lu){
        return firstSet.contains(lu.getType());
    }
    
    public static Node getHandler(LexicalUnit lu, Environment env) {
    	
    	return new Call(env);
		
	}
    
    private Call(Environment env) {
    	this.env = env;
    	this.type = NodeType.FUNCTION_CALL;
    }
    
    
    @Override
    public boolean parse() throws Exception {
    	// TODO Auto-generated method stub

    	boolean bracketFlag = false;
    	LexicalUnit first = env.getInput().get();

    	if(first.getType() == LexicalType.NAME) {
    		funcName = first.getValue().get_sValue();
    	} else { 
    		throw new Exception();
    	}
    	
    	first = env.getInput().peek();
    	
    	if(first.getType() == LexicalType.LP) {
    		env.getInput().get();
    		bracketFlag = true;
    	}
    	
    	first = env.getInput().peek();
    	if(ExprList.isFirst(first))
    	
    	first = env.getInput().peek();
    	if(bracketFlag) {
    		if(first.getType() != LexicalType.RP) {
    			throw new Exception();
    		}
    	}
    	
    	return true;
    	
    }
    
    
}
