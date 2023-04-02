package com.feng.result;

//成功返回

import lombok.Data;
@Data
public class Success extends CResponse {

    public Success(Integer code,Object data){
        this.code = code;
        this.data = data;
    }



    @Override
    public String toString() {
        return "Success{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
