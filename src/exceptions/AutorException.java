package exceptions;

public class AutorException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3581921553542536432L;

	public AutorException() {
	}

	public AutorException(String arg0) {
		super(arg0);
	}

	public AutorException(Throwable arg0) {
		super(arg0);
	}

	public AutorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AutorException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}
