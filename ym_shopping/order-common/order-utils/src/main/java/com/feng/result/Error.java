package com.feng.result;

import lombok.Data;

@Data
public class Error extends CResponse {

    public Error(Integer code,Object data){
        this.code = code;
        this.data = data;
    }


    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}