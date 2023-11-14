package com.example.file.commons.exception;

import lombok.Data;

/**
 * @auther Kindow
 * @date 2023/10/29
 * @project imooc-bilibili
 */

@Data
public class ConditionException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String code;

    public ConditionException(String code, String name) {
        super(name);
        this.code = code;
    }

    public ConditionException(String name) {
        super(name);
        this.code = "500";
    }


}
