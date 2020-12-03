package exceptions;

public class ObraException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3581921553542536432L;

	public ObraException() {
	}

	public ObraException(String arg0) {
		super(arg0);
	}

	public ObraException(Throwable arg0) {
		super(arg0);
	}

	public ObraException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ObraException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}
