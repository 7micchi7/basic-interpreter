package newlang3;

public class ValueImpl extends Value{
	
	private ValueType type;
	private String value;

	public ValueImpl(String s, ValueType t) {
		super(s, t);
	}
	
	public ValueImpl(boolean b) {
		super(b);
		type = ValueType.BOOL;
		value = b + "";
		// TODO Auto-generated constructor stub
	}

	public ValueImpl(double d) {
		super(d);
		type = ValueType.DOUBLE;
		value = d + "";
		// TODO Auto-generated constructor stub
	}

	public ValueImpl(int i) {
		super(i);
		type = ValueType.INTEGER;
		value = i + "";
		// TODO Auto-generated constructor stub
	}

	public ValueImpl(String s) {
		super(s);
		type = ValueType.STRING;
		value = s + "";
		// TODO Auto-generated constructor stub
	}

	@Override
	public String get_sValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public int getIValue() {
		// TODO Auto-generated method stub
		return Integer.parseInt(value);
	}

	@Override
	public double getDValue() {
		// TODO Auto-generated method stub
		return Double.parseDouble(value);
	}

	@Override
	public boolean getBValue() {
		// TODO Auto-generated method stub
		return Boolean.parseBoolean(value);
	}

	@Override
	public ValueType getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
}
