package exceptions;

public class VisitanteException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3581921553542536432L;

	public VisitanteException() {
	}

	public VisitanteException(String arg0) {
		super(arg0);
	}

	public VisitanteException(Throwable arg0) {
		super(arg0);
	}

	public VisitanteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public VisitanteException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}
