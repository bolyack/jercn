package com.bamboo.utils.blockchain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bamboo.commons.enums.BlockEnums;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on 2017/2/28.
 * Hyperledger区块链相关工具类
 */
public class BlockChainUtils {

    private static final String CHARSET = "UTF-8";

    /**
     * 封装请求参数为JSON字符串
     * @param methcodName 操作名
     * @param funcName  调用的函数名
     * @param params 参数列表
     * @param id  交易类型
     * @return 请求参数JSON
     */
    public static String setParams(String methcodName, String funcName, String[] params, String id) {
        JSONObject json = new JSONObject();

        json.put("jsonrpc", BlockEnums.CONFIG_JSON_RPC.getCode());
        json.put("method", methcodName);

        Map map = new HashMap<String, Object>();
        map.put("type", 1);

        Map chaincodeMap = new HashMap<String, Object>();
        chaincodeMap.put("name", BlockEnums.CONFIG_CHAINCODE_ID.getCode());
        map.put("chaincodeID", chaincodeMap);

        Map ctorMsgMap = new HashMap<String, Object>();
        ctorMsgMap.put("function", funcName);
        JSONArray jsonArray = new JSONArray();
        if (null != params && params.length > 0) {
            for (String val : params) {
                jsonArray.add(val);
            }
        }
        ctorMsgMap.put("args", jsonArray);
        map.put("ctorMsg", ctorMsgMap);
        map.put("secureContext", BlockEnums.CONFIG_USER.getCode());
        json.put("params", map);

        if (null == id || "".equals(id.trim())) {
            json.put("id", BlockEnums.TRANS_TYPE_ID_INVOKE.getCode());
        } else {
            json.put("id", id);
        }

        String paramStr = json.toJSONString();

        return paramStr;
    }

    public static JSONObject invokeByPost(String url, String methcodName, String chaincodeID, String funcName, String[] params, String user, String id) throws Exception {
        //创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        JSONObject responseJSON = null;
        try {
            HttpPost request = new HttpPost(url);
            String requestBody = setParams(methcodName, funcName, params, id);
            StringEntity requesEntity = new StringEntity(requestBody, CHARSET);
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            request.setEntity(requesEntity);
            HttpResponse response = closeableHttpClient.execute(request);
            if (null != response) {
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity respEntity = response.getEntity();
                if (HttpStatus.SC_OK == statusCode) {
                    String responseBody = EntityUtils.toString(respEntity, CHARSET);
                    responseJSON =  JSONObject.parseObject(responseBody);
                } else {
                    String exceptionInfo = EntityUtils.toString(respEntity, CHARSET);
                    throw new Exception(exceptionInfo);
                }
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            //关闭流并释放资源
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                throw new Exception(e.getMessage(), e);
            }
        }

        return responseJSON;
    }

    public static JSONObject invokeByGET(String url, Map<String, Object> params) throws Exception {
        //创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        JSONObject responseJSON = null;
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (String key : params.keySet()) {
                    pairs.add(new BasicNameValuePair(key, params.get(key).toString()));
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            HttpGet request = new HttpGet(url);
            //执行get请求
            HttpResponse httpResponse = closeableHttpClient.execute(request);
            if (null != httpResponse) {
                //获取响应消息实体
                HttpEntity entity = httpResponse.getEntity();
                int statusCode = httpResponse.getStatusLine().getStatusCode();

                if (HttpStatus.SC_OK == statusCode) {
                    String responseResult = EntityUtils.toString(entity, CHARSET);
                    responseJSON =  JSONObject.parseObject(responseResult);
                } else {
                    String failInfo = EntityUtils.toString(entity, CHARSET);
                    throw new Exception(failInfo);
                }
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            //关闭流并释放资源
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                throw new Exception(e.getMessage(), e);
            }
        }

        return responseJSON;
    }

    /**
     * 实例对象的属性转化为String数组(若属性标记ignoreAnnotationClass注解，则不转化; 如果属性为null，则转化为字符串) 如下：
     *      User u = new User();
     *      u.setName("aaa");
     *      u.setGender(null);
     *      u.setAge(15)
     *  转化后
     *      new String[]{"name", "aaa", "gender", "", "age", "15"}
     * @param instance 实例对象
     * @param ignoreAnnotationClass 忽略实例对象标注ignoreAnnotationClass注解的属性
     * @return String[] 字符数组
     * @throws Exception
     */
    public static String[] parseObj2StringArray(Object instance, Class ignoreAnnotationClass) throws Exception {
        List<String> list = new ArrayList<String>();
        Class<?> clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(!field.isAnnotationPresent(ignoreAnnotationClass)) {
                Class typeClazz = field.getType();
                Object val = field.get(instance);
                list.add(field.getName());
                if (null == val) {
                    list.add("");
                } else {
                    if (typeClazz == Date.class) {
                        Date localDate = (Date) val;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        list.add(sdf.format(localDate));
                    }  else {
                        list.add(val.toString());
                    }

                }
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 实例对象的属性转化为String数组(若属性标记IgnoreAttr注解，则不转化; 如果属性值为null，不转化)
     * @param instance 实例对象
     * @return String[] 字符数组
     * @throws Exception
     */
    public static String[] parseObj2Arr4Con (Object instance) throws Exception {
        List<String> list = new ArrayList<String>();
        Class<?> clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object val = field.get(instance);
            if (null != val && !"".equals(val)) {
                list.add(field.getName());
                list.add(val.toString());
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * hyperledger查询返回(单个对象字符串)对应实体对象
     * @param jsonStr
     * @param clazz
     * @param <E>
     * @return
     * @throws Exception
     */
    private static <E> List<E> jsonStr2ListOne(String jsonStr, Class<E> clazz) throws Exception {
        //String js = "{[int64:0  string:\"1\"  string:\"1\"  string:\"1\"  string:\"1\" ]}";
        if (jsonStr == null || "".equals(jsonStr.trim())) return null;
        int bg = jsonStr.indexOf("[") + 1;
        int ed = jsonStr.lastIndexOf("]");
        String newStr = jsonStr.substring(bg, ed);
        String[] strArr = newStr.replace("\"", "").split("  ");

        List<E> list = new ArrayList<E>();

        Object instance = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < strArr.length; i++) {
            fields[i].setAccessible(true);
            Class varType = fields[i].getType();

            String[] localStr = strArr[i].split(":");

            initValue(instance, varType, fields[i], localStr[0], localStr[1]);
        }

        list.add((E)instance);

        return list;
    }

    /**
     * hyperledger查询返回(多个对象字符串)对应实体对象
     * @param jsonStr
     * @param clazz
     * @param <E>
     * @return
     * @throws Exception
     */
    public static <E> List<E> jsonStrtoList(String jsonStr, Class<E> clazz) throws Exception{
//        String jsonStr1 = "[{\"columns\":[{\"Value\":{\"Int64\":0}},{\"Value\":{\"String_\":\"18070506633\"}},{\"Value\":{\"String_\":\"123456\"}},{\"Value\":{\"String_\":\"fbw\"}},{\"Value\":{\"String_\":\"987\"}}]},{\"columns\":[{\"Value\":{\"Int64\":12}},{\"Value\":{\"String_\":\"12070506633\"}},{\"Value\":{\"String_\":\"112123456\"}},{\"Value\":{\"String_\":\"f11bw\"}},{\"Value\":{\"String_\":\"123987\"}}]}]" ;

        if (null == jsonStr || "".equals(jsonStr.trim())) return null;

        if (!jsonStr.contains("columns")) return jsonStr2ListOne(jsonStr, clazz);

        List<E> list = new ArrayList<E>();
        JSONArray clm = JSONArray.parseArray(jsonStr);
        for (int i = 0; i < clm.size(); i++) {

            JSONObject str = (JSONObject)clm.get(i);
            Object instance = null;
            JSONArray columArray = (JSONArray)str.get("columns");
            if (null != columArray && columArray.size() > 0) {

                instance = clazz.newInstance();
                int n = 0;
                for (int j = 0; j < columArray.size(); j++) {

                    JSONObject map = (JSONObject) columArray.get(j);
                    JSONObject valMap = (JSONObject) map.get("Value");
                    valMap.size();

                    Field[] fields = clazz.getDeclaredFields();

                    for (Map.Entry<String, Object> entry : valMap.entrySet()) {
                        Class varType = fields[n].getType();
                        initValue(instance, varType, fields[n], entry.getKey(), entry.getValue());

                        n++;
                    }
                }
                list.add((E)instance);
            }
        }
        return list;
    }

    /**
     * 根据反射给clazz对象赋值
     * @param target
     * @param varType
     * @param field
     * @param key
     * @param value
     * @throws Exception
     */
    private static void initValue(Object target, Class varType, Field field, String key,  Object value) throws Exception {
        field.setAccessible(true);
        if (key.toUpperCase().contains("STRING")) {
            if (varType.equals(String.class)) {
                field.set(target, value.toString());
            } else if (varType.equals(BigDecimal.class)) {
                field.set(target, new BigDecimal(value.toString()));
            } else if (varType.equals(double.class) || varType.equals(Double.class)) {
                field.set(target, Double.parseDouble(value.toString()));
            }
        } else if (key.toUpperCase().contains("INT") && (varType.equals(int.class) || varType.equals(Integer.class) )) {
            field.set(target, Integer.parseInt(value.toString()));
        } else if (key.toUpperCase().contains("INT") && (varType.equals(long.class) || varType.equals(Long.class) )) {
            field.set(target, Long.parseLong(value.toString()));
        }  else if (key.toUpperCase().contains("BOOL") && (varType.equals(boolean.class) || varType.equals(Boolean.class))) {
            field.set(target, Boolean.parseBoolean(value.toString()));
        }
    }


}
