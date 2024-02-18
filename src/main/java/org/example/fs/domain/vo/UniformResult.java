package org.example.fs.domain.vo;


import lombok.Data;

public class UniformResult<T> {

    private Integer code;

    private String msg;

    private T data;



    public UniformResult(Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public UniformResult(T data){
        this.data = data;
    }

    public static<T> UniformResult<T> success(Integer code,String msg,T data) {
        return new UniformResult<T>(code,msg,data);
    }

    public static<T> UniformResult<T> success(String msg,T data) {
        return new UniformResult<T>(200,msg,data);
    }

    public static<T> UniformResult<T> success(T data) {
        return new UniformResult<T>(200,"success",data);
    }

    public static<T> UniformResult<T> fail(Integer code ,String msg,T data) {
        return new UniformResult<T>(code,msg,data);
    }

    public static<T> UniformResult<T> fail(String msg,T data) {
        return new UniformResult<T>(500,msg,data);
    }

    public static<T> UniformResult<T> fail(T data) {
        return new UniformResult<T>(500,"fail",data);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "UniformResult{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
