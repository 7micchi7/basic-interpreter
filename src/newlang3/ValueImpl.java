package newlang3;

public class ValueImpl extends Value{

	public ValueImpl(String s, ValueType t) {
		super(s, t);
	}
	
	public ValueImpl(boolean b) {
		super(b);
		// TODO Auto-generated constructor stub
	}

	public ValueImpl(double d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public ValueImpl(int i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	public ValueImpl(String s) {
		super(s);
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
		return null;
	}

	@Override
	public int getIValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getBValue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ValueType getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
