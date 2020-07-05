package net.crow.ptop.blockchain.core.exception;

public class ExecuteScriptException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExecuteScriptException() {
        super();
    }

    public ExecuteScriptException(String message) {
        super(message);
    }

    public ExecuteScriptException(String message, Throwable cause) {
        super(message, cause);
    }
}
