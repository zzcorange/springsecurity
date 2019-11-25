package com.zzc.security.code;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author chengzi
 * @Date 2019/11/1711:24
 */
public enum ResultJSON {
    FAIL("0",""),
    SUCCESS("1","");
    private  String code;
    private String message;
    ResultJSON(String code,String message){
        this.code = code;
        this.message = message;
    }
    public String toJSON(String message){
        this.message = message;
        return toJSONString();

    }
   private String toJSONString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("message",message);
        return jsonObject.toJSONString();
   }
}
