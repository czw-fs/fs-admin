package org.example.fs.exception;

import lombok.Data;

/**
 * @author codekiller
 * @date 2020/5/30 12:41
 * 自定义异常父类
 */


@Data
public class LoginTimeOutException extends RuntimeException {

    private static final long serialVersionUID=8088897180748768529L;

    private Integer code;

    public LoginTimeOutException(Integer code,String msg){
        super(msg);
        this.code=code;
    }

}

