package com.bamboo.commons.enums;

/**
 * Created by admin on 2017/2/28.
 */
public enum BlockEnums {

    METHOD_DEPLOY("deploy", "部署"),
    METHOD_QUERY("query", "查询"),
    METHOD_INVOKE("invoke", "调用、交易"),

    CONFIG_ULR("http://192.168.83.64:7050/chaincode", "区块链链上代码URL"),
    CONFIG_CHAINCODE_ID("0d57f1e60eaf8c9f96a7039ea1a882d2d9a4998dca55f3f7009e45d65ed10d93f6df188ede671325e4e4cd83150e08d95d55ab2e5f13f2b6f7165535b6c0d583", "链上代码ID"),
    CONFIG_USER("diego", "登录、认证用户"),
    CONFIG_JSON_RPC("2.0", "JSON-RPC协议"),

    TRANS_TYPE_ID_DEPLOY("1", "部署"),
    TRANS_TYPE_ID_INVOKE("3", "调用"),
    TRANS_TYPE_ID_QUERY("5", "查询"),

    ;

    private String code;
    private String name;

    private BlockEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
