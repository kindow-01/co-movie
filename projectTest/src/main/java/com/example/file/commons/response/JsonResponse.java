package com.example.file.commons.response;

import lombok.Data;

/**
 * @auther Kindow
 * @date 2023/10/29
 * @project imooc-bilibili
 */
@Data
public class JsonResponse<T> {
    private String code;

    private String msg;

    private T data;

    public JsonResponse(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public JsonResponse(T data){
        this.data = data;
        this.code = "0";
        this.msg = "成功";
    }


    public static JsonResponse<String> success(){
        return new JsonResponse<>(null);
    }

    public static JsonResponse<String> success(String data){
        return new JsonResponse<>(data);
    }

    public static JsonResponse<String> fail(){
        return new JsonResponse<>("1","失败");
    }

    public static JsonResponse<String> fail(String code,String msg){
        return new JsonResponse<>(code,msg);
    }
}
