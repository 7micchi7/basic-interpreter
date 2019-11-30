package newlang3;

import java.io.PushbackReader;
import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;

public class LexicalAnalyzerImpl implements LexicalAnalyzer{

	private boolean intFlag = false;
	private boolean doubleFlag = false;
	private boolean stringFlag = false;

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
		char readChar;

		stringCode = reader.read();

		if(stringCode == -1) return new LexicalUnit(LexicalType.EOF);
	
		if(String.valueOf(stringCode).matches("[0-9.]")) {
			reader.unread(stringCode);
			unit = getString();
		}

		if(String.valueOf(stringCode).matches("\\d")) {
			reader.unread(stringCode);
			unit = getInt();
		}

		if(String.valueOf(stringCode).matches("\\d")) {
			reader.unread(stringCode);
			unit = getLiteral();
		}
		
		reader.unread(stringCode);
		unit = getSymbol();

		if(unit == null) ;
		
		return unit;
	}

	private LexicalUnit getSymbol() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				int ci = reader.read();

				if (ci < 0) break;
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	private LexicalUnit getLiteral() {
		// TODO Auto-generated method stub
		return null;
	}

	private LexicalUnit getInt() {
		// TODO Auto-generated method stub
		return null;
	}

	private LexicalUnit getString() {
		// TODO Auto-generated method stub
		
		String target = "";

		try {
			

			while (true) {

				int ci = reader.read();
				char ch = (char)ci;
				
				if (String.valueOf(ci).matches("\\w")) {

					target += ch;
					continue;

				}
				
				break;
			}

			if (WORD_MAP.containsKey(target)) {
				return new LexicalUnit(WORD_MAP.get(target));
			} else {
				return new LexicalUnit(LexicalType.NAME, new ValueImpl(target));
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
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
		
		if(String.valueOf((char)stringCode).matches("[0-9.]") && unitStack.matches("[0-9.]*?")) {

			if(unitStack.matches("\\d+[.]")) {

				doubleFlag = true;
				unitStack += (char)stringCode;
				return null;

			} else {

				intFlag = true;
				doubleFlag = false;
				unitStack += (char)stringCode;
				return null;

			}

		} else if(unitStack.matches("\\d+.")){

			if (doubleFlag) {

				return new LexicalUnit(LexicalType.DOUBLEVAL, new ValueImpl(Double.parseDouble(unitStack)));

			} else if(intFlag) {

				return new LexicalUnit(LexicalType.INTVAL, new ValueImpl(Integer.parseInt(unitStack)));

			}	
		}
		
		
		
		if(WORD_MAP.containsKey(unitStack)) {

			return new LexicalUnit(WORD_MAP.get(unitStack));
			
		} else if(NEED_VALUE_WORD_MAP.containsKey(unitStack)) {

			return new LexicalUnit(NEED_VALUE_WORD_MAP.get(unitStack),);
			
		}
		return null;
	}

}
