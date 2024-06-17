package com.proxy;

import com.IOClient;
import com.Message.RPCRequest;
import com.Message.RPCResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @version 1.0
 * @Author szy
 * @Date 2024/6/18 2:39
 */

@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    //传入参数Service接口的class对象，反射封装成一个request
    private String host;
    private int port;


    //jdk动态代理，每一次代理对象调用方法，会经过此方法增强（反射获取request对象，socket发送至客户端）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //构建request
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args).paramsTypes(method.getParameterTypes())
                .build();
        //数据传输
        RPCResponse response = IOClient.sendRequest(host, port, request);
        return response.getData();
    }

    public <T>T getProxy(Class<T> clazz){
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)o;
    }




}
