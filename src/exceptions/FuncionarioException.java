package exceptions;

public class FuncionarioException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3581921553542536432L;

	public FuncionarioException() {
	}

	public FuncionarioException(String arg0) {
		super(arg0);
	}

	public FuncionarioException(Throwable arg0) {
		super(arg0);
	}

	public FuncionarioException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FuncionarioException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}
