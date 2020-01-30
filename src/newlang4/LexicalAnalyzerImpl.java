package newlang4;

import java.io.PushbackReader;
import java.util.HashMap;
import java.util.Stack;

public class LexicalAnalyzerImpl implements LexicalAnalyzer{


	private PushbackReader reader;
	private static LexicalUnit unit;
	private static HashMap<String, LexicalType> WORD_MAP = new HashMap<String, LexicalType>();
	private static HashMap<String, LexicalType> SYMBOL_MAP = new HashMap<String, LexicalType>();
	private static Stack<LexicalUnit> buff = new Stack<LexicalUnit>();
	
	static {
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

		SYMBOL_MAP.put("=", LexicalType.EQ);
		SYMBOL_MAP.put("<", LexicalType.LT);
		SYMBOL_MAP.put(">", LexicalType.GT);
		SYMBOL_MAP.put("<=", LexicalType.LE);
		SYMBOL_MAP.put("=<", LexicalType.LE);
		SYMBOL_MAP.put("=>", LexicalType.GE);
		SYMBOL_MAP.put(">=", LexicalType.GE);
		SYMBOL_MAP.put("<>", LexicalType.NE);
		SYMBOL_MAP.put(".", LexicalType.DOT);
		SYMBOL_MAP.put("+", LexicalType.ADD);
		SYMBOL_MAP.put("-", LexicalType.SUB);
		SYMBOL_MAP.put("*", LexicalType.MUL);
		SYMBOL_MAP.put("/", LexicalType.DIV);
		SYMBOL_MAP.put(")", LexicalType.LP);
		SYMBOL_MAP.put("(", LexicalType.RP);
		SYMBOL_MAP.put(",", LexicalType.COMMA);
		SYMBOL_MAP.put("\n", LexicalType.NL);
	}

	public LexicalAnalyzerImpl(PushbackReader reader) {
		this.reader = reader;
	}
	
	public LexicalUnit peek() throws Exception {
		return peek(1);
	}
	
	public LexicalUnit peek(int num) throws Exception {

		Stack<LexicalUnit> stack = new Stack<LexicalUnit>();

		for(int i = 0; i < num; i++) {
			stack.push(get());
		}
		
		LexicalUnit result = stack.peek();
		while(!stack.empty()) {
			unget(stack.pop());
		}
		
		return result;
	}

	@Override
	public LexicalUnit get() throws Exception {
		while(true) {
			
			if(!buff.empty()) {
				
				return buff.pop();

			}
			
			int stringCode = reader.read();
			char ch = (char)stringCode;


			if(stringCode < 0 || stringCode == 0xffff ) return new LexicalUnit(LexicalType.EOF);
	
			if(String.valueOf(ch).matches("[a-zA-Z]")) {
				reader.unread(stringCode);
				unit = getString();
				return unit;
			}
	
			if(String.valueOf(ch).matches("[0-9.]")) {
				reader.unread(stringCode);
				unit = getInt();
				return unit;
			}
	
			if(String.valueOf(ch).matches("\"")) {
				reader.unread(stringCode);
				unit = getLiteral();
				return unit;
			}
		
			if(SYMBOL_MAP.containsKey(ch + "")) {
				reader.unread(stringCode);
				unit = getSymbol();
				return unit;
			}
		}
	}

	private LexicalUnit getSymbol() {
		// TODO Auto-generated method stub
		String target = "";
		try {
			while (true) 	{
				int ci = reader.read();

				if (ci < 0) return new LexicalUnit(SYMBOL_MAP.get(target));
				
				char ch = (char)ci;
				
				if(SYMBOL_MAP.containsKey(target + ch)) {
					target += ch;
				} else {
					reader.unread(ci);
					return new LexicalUnit(SYMBOL_MAP.get(target));
				}
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	private LexicalUnit getLiteral() {
		// TODO Auto-generated method stub
		String target = "";
		boolean startFlag = false;
		boolean endFlag = false;
		boolean escapeFlag = false;

		try {
			while(true) {
				int ci = reader.read();
				char ch = (char)ci;
			
				if(ch == '\\') {
					escapeFlag = !escapeFlag;
					ci = reader.read();
					ch = (char)ci;
				}

				if(ch == '\"' && escapeFlag == false) {

					if(startFlag == false) {
						startFlag = true;
						continue;
					} else if(endFlag == false) {
						endFlag = true;
						continue;
					}

				}
				
				if(endFlag == true) {
					break;
				}
				
				target += ch;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new LexicalUnit(LexicalType.LITERAL, new ValueImpl(target));
	}

	private LexicalUnit getInt() {
		// TODO Auto-generated method stub
		String target = "";
		boolean doubleFlag = false;
		
		try {
			while(true) {
				int ci = reader.read();
				char ch = (char)ci;
				
				if(String.valueOf(ch).matches("[\\d\\.]")) {
					if(ch == '.' && doubleFlag == false) {
						doubleFlag = true;
					}
					
					target += ch;
					continue;
				}
				
				reader.unread(ci);
				break;
			}
			
			if(doubleFlag) {
				return new LexicalUnit(LexicalType.DOUBLEVAL, new ValueImpl(Double.parseDouble(target)));
			} else {
				return new LexicalUnit(LexicalType.INTVAL, new ValueImpl(Integer.parseInt(target)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	private LexicalUnit getString() {
		// TODO Auto-generated method stub
		
		String target = "";

		try {
			
			while (true) {

				int ci = reader.read();
				char ch = (char)ci;
				
				if (String.valueOf(ch).matches("[a-zA-Z]")) {

					target += ch;
					continue;

				}
				
				reader.unread(ci);
				
				break;
			}
			
			if(WORD_MAP.containsKey(target)) {
				return new LexicalUnit(WORD_MAP.get(target));
			} else {
				return new LexicalUnit(LexicalType.NAME, new ValueImpl(target));
			}

				
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (WORD_MAP.containsKey(target)) {

			return new LexicalUnit(WORD_MAP.get(target));

		} else {

			return new LexicalUnit(LexicalType.NAME, new ValueImpl(target));

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
		
		buff.push(token);

	}
	
}
