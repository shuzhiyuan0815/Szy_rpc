package com.Message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

//从服务端请求到想要的接口
@Data
@Builder
public class RPCRequest implements Serializable {
    //服务类名 接口名
    private String interfaceName;
    //方法名
    private String methodName;
    //参数列表
    private Object[] params;
    //参数类型
    private Class<?>[] paramsTypes;

}
