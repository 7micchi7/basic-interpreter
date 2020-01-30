package newlang4;

import java.util.Hashtable;

import newlang4.LexicalAnalyzerImpl;

public class Environment {
	   LexicalAnalyzerImpl input;
	   Hashtable var_table;
	    
	    public Environment(LexicalAnalyzerImpl my_input) {
	        input = my_input;
	        var_table = new Hashtable();
	    }
	    
	    public LexicalAnalyzerImpl getInput() {
	        return input;
	    }	    
}
