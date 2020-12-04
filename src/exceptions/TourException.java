package exceptions;

public class TourException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3581921553542536432L;

	public TourException() {
	}

	public TourException(String arg0) {
		super(arg0);
	}

	public TourException(Throwable arg0) {
		super(arg0);
	}

	public TourException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public TourException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}
