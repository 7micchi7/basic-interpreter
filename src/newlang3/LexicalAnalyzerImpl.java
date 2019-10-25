package newlang3;

import java.io.PushbackReader;

public class LexicalAnalyzerImpl implements LexicalAnalyzer{

	private PushbackReader reader;
	private static LexicalUnit unit;
	private static String unitStack;

	public LexicalAnalyzerImpl(PushbackReader reader) {
		this.reader = reader;
	}

	@Override
	public LexicalUnit get() throws Exception {
		int stringCode = reader.read();
		while(reader.read() != -1) {
			findUnit(stringCode);
			stringCode = reader.read();
		}
		return unit;
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
	
	private boolean findUnit(int stringCode) {
		unitStack += (char)stringCode;
		switch(unitStack) {
		//enumとのマッチを探すcase文
		//マッチしたならLexicalUnitクラスに投げ、unitに代入
		//タイプによるが一つのunitが完成した場合trueを返す。それ以外はfalse
		default:
			return false;
		}
	}

}
