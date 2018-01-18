package com.gitee;

/**
 * Created with IntelliJ IDEA.
 * User: wanglinyong
 * Date: 2016/9/21
 * Time: 10:15
 *
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class AESException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AESException() {
    }

    public AESException(Throwable t) {
        super(t);
    }

    public AESException( String msg) {
        super(msg);
    }

    public AESException(String msg, Throwable t) {
        super(msg, t);
    }
}
