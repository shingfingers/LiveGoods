package com.tz.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON转化工具类
 */
public class JSONUtils {
    //构造方法私有化
    private  JSONUtils(){

    }
    //全局Jackson对象
    private static final ObjectMapper MAPPER=new ObjectMapper();

    /**
     * 把对象转换为JSON字符串
     * @param obj 对象
     * @return json字符串,返回null说明出现异常。
     */
    public  static String object2json(Object obj){
        try {
            String json= MAPPER.writeValueAsString(obj);
            return json;
        } catch (JsonProcessingException e) {
         e.printStackTrace();
            return null;
        }
    }
    /**
     * 把json字符串转换为指定类型对象
     * @param json json字符串
     * @param cls 指定类型
     * @return 转换后的结果
     * @param <T> 指定的类型
     */
    public static <T> Object json2object(String json,Class<T>cls){
        T result= null;
        try {
            result = MAPPER.readValue(json,cls);
            return  result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json字符串转换为List
     * @param json 字符串
     * @param cls 泛型类型
     * @return 转换结果
     * @param <T> 泛型
     */
    public static <T> List<T> json2list(String json, Class<T> cls){
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(ArrayList.class, cls);
            List<T> list = MAPPER.readValue(json, javaType);
            return list;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
