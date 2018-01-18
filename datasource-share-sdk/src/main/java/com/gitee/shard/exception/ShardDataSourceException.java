package com.gitee.shard.exception;

/**
 * @author fukui@gome.com.cn
 */
public class ShardDataSourceException extends RuntimeException {
    private static final long serialVersionUID = -227961057827878851L;

    protected String code;

    public ShardDataSourceException() {
    }

    public ShardDataSourceException(String code) {
        super(code);
        this.setCode(code);
    }

    public ShardDataSourceException(Throwable t) {
        super(t);
    }

    public ShardDataSourceException(String code, Throwable t) {
        super(t);
        this.setCode(code);
    }

    public ShardDataSourceException(String code, String msg) {
        super(msg);
        this.setCode(code);
    }

    public ShardDataSourceException(String code, String msg, Throwable t) {
        super(msg, t);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
