package cn.edu.ntu.utils;

public class MyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public MyException(){
		super();
	}
	
	public MyException(String message){
		super(message);
	}
}
