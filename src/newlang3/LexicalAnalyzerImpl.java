package newlang3;

import java.io.PushbackReader;
import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;

public class LexicalAnalyzerImpl implements LexicalAnalyzer{

	private PushbackReader reader;
	private static LexicalUnit unit;
	private static String unitStack;
	private static HashMap<String, LexicalType> WORD_MAP = new HashMap<String, LexicalType>();
	private static HashMap<String, LexicalType> NEED_VALUE_WORD_MAP = new HashMap<String, LexicalType>();
	
	static {
		NEED_VALUE_WORD_MAP.put("LITERAL", LexicalType.LITERAL);		//value必須
		NEED_VALUE_WORD_MAP.put("INTVAL", LexicalType.INTVAL);			//value必須
		NEED_VALUE_WORD_MAP.put("DOUBLEVAL", LexicalType.DOUBLEVAL);	//value必須
		NEED_VALUE_WORD_MAP.put("NAME", LexicalType.NAME);				//value必須
		WORD_MAP.put("IF", LexicalType.IF);
		WORD_MAP.put("THEN", LexicalType.THEN);
		WORD_MAP.put("ELSE", LexicalType.ELSE);
		WORD_MAP.put("ELSEIF", LexicalType.ELSEIF);
		WORD_MAP.put("ENDIF", LexicalType.ENDIF);
		WORD_MAP.put("FOR", LexicalType.FOR);
		WORD_MAP.put("FORALL", LexicalType.FORALL);
		WORD_MAP.put("NEXT", LexicalType.NEXT);
		WORD_MAP.put("SUB", LexicalType.FUNC);
		WORD_MAP.put("DIM", LexicalType.DIM);
		WORD_MAP.put("AS", LexicalType.AS);
		WORD_MAP.put("END", LexicalType.END);
		WORD_MAP.put("NL", LexicalType.NL);
		WORD_MAP.put("WHILE", LexicalType.WHILE);
		WORD_MAP.put("DO", LexicalType.DO);
		WORD_MAP.put("UNTIL", LexicalType.UNTIL);
		WORD_MAP.put("LOOP", LexicalType.LOOP);
		WORD_MAP.put("TO", LexicalType.TO);
		WORD_MAP.put("WEND", LexicalType.WEND);
		WORD_MAP.put("EOF", LexicalType.EOF);

		WORD_MAP.put("=", LexicalType.EQ);
		WORD_MAP.put("<", LexicalType.LT);
		WORD_MAP.put(">", LexicalType.GT);
		WORD_MAP.put("<=", LexicalType.LE);
		WORD_MAP.put("=<", LexicalType.LE);
		WORD_MAP.put("=>", LexicalType.GE);
		WORD_MAP.put(">=", LexicalType.GE);
		WORD_MAP.put("<>", LexicalType.NE);
		WORD_MAP.put(".", LexicalType.DOT);
		WORD_MAP.put("+", LexicalType.ADD);
		WORD_MAP.put("-", LexicalType.SUB);
		WORD_MAP.put("*", LexicalType.MUL);
		WORD_MAP.put("/", LexicalType.DIV);
		WORD_MAP.put(")", LexicalType.LP);
		WORD_MAP.put("(", LexicalType.RP);
		WORD_MAP.put(",", LexicalType.COMMA);
	}

	public LexicalAnalyzerImpl(PushbackReader reader) {
		this.reader = reader;
	}

	@Override
	public LexicalUnit get() throws Exception {
	
		int stringCode;
		
		while(true) {
			stringCode = reader.read();

			if(stringCode == -1) return new LexicalUnit(LexicalType.EOF);

			//タブと空白はいらないので除外
			if(stringCode != 32 && stringCode != 9) {
				unit = findUnit(stringCode);
			}
			
			stringCode = reader.read();

			if(unit == null) continue;
			
			return unit;
		}
	}

	@Override
	public boolean expect(LexicalType type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unget(LexicalUnit token) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private LexicalUnit findUnit(int stringCode) {
		boolean intFlag = false;
		
		if(String.valueOf((char)stringCode).matches("[0-9.]") && unitStack.matches("[0-9.]*?")) {
			intFlag = true;
			if(unitStack.matches("\\d+[.]")) ;
		}

		unitStack += (char)stringCode;
		
		if(WORD_MAP.containsKey(unitStack)) {

			return new LexicalUnit(WORD_MAP.get(unitStack));
			
		} else if(NEED_VALUE_WORD_MAP.containsKey(unitStack)) {

			return new LexicalUnit(NEED_VALUE_WORD_MAP.get(unitStack),);
			
		}
		return null;
	}

}
