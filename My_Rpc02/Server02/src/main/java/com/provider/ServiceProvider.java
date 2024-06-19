package com.provider;

/**
 * @version 1.0
 * @Author szy
 * @Date 2024/6/19 22:43
 */

import com.sun.xml.internal.ws.developer.BindingTypeFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * 存放服务接口名与服务端对应的实现类
 * 服务启动时要暴露其相关的实现类
 * 根据request中的interface调用服务端中相关实现类
 */
public class ServiceProvider {

    private Map<String,Object> interfaceProvider;

    public ServiceProvider(){
        this.interfaceProvider = new HashMap<>();
    }

    public void provideServiceInterface(Object service){
        String serviceName = service.getClass().getName();
        Class<?>[] interfaces = service.getClass().getInterfaces();

        for(Class clazz : interfaces){
            System.out.println(clazz.getName());
            interfaceProvider.put(clazz.getName(),service);
        }
    }

    public Object getService(String interfaceName){
        return interfaceProvider.get(interfaceName);
    }
}
