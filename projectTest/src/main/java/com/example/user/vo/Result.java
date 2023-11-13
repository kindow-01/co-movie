package com.example.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T>Result<T> success(){
        return new Result<>(200,"success",null);
    }
    public static <T>Result<T> success(T Data){
        return new Result<>(200,"success",Data);
    }
    public static <T>Result<T> success(T Data,String message){
        return new Result<>(200,message,Data);
    }
    public static <T>Result<T> success(String message){
        return new Result<>(200,message,null);
    }

    public static <T>Result<T> fail(){
        return new Result<>(401,"fail",null);
    }
    public static <T>Result<T> fail(T Data){
        return new Result<>(401,"fail",Data);
    }
    public static <T>Result<T> fail(T Data,String message){
        return new Result<>(401,message,Data);
    }
    public static <T>Result<T> fail(String message){
        return new Result<>(401,message,null);
    }
}
