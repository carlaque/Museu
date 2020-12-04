package exceptions;

public class VisitaException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3581921553542536432L;

	public VisitaException() {
	}

	public VisitaException(String arg0) {
		super(arg0);
	}

	public VisitaException(Throwable arg0) {
		super(arg0);
	}

	public VisitaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public VisitaException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}
