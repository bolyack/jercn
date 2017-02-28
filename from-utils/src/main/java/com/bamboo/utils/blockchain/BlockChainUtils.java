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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on 2017/2/28.
 * 区块链相关工具类
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
                    String responseBody = EntityUtils.toString(entity, CHARSET);
                    responseJSON =  JSONObject.parseObject(responseBody);
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


}
