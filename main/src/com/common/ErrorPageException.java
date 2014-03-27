package com.common;

import org.testng.Reporter;

public class ErrorPageException extends Exception {
	public String erroMessage = "Error Page Loaded in:" ;

	public ErrorPageException() {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		  String method = ste[2].getMethodName();
		  exit(method);

	}

	public ErrorPageException(String message) {
		super(message);
	}

	public ErrorPageException(Throwable cause) {
		super(cause);	}

	public ErrorPageException(String message, Throwable cause) {
		super(message, cause);
	}

	public void exit (String methodName){
		Reporter.log(erroMessage + methodName);
		return;
	}
}
