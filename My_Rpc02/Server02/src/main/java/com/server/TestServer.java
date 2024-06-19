package com.server;

import com.provider.ServiceProvider;
import com.server.impl.SimpleRPCRPCServer;
import com.service.Impl.BlogServiceImpl;
import com.service.Impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author szy
 * @Date 2024/6/19 21:35
 */


public class TestServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();
//        Map<String,Object> serviceProvide = new HashMap<>();
//
//        serviceProvide.put("com.service.UserService",userService);
//        serviceProvide.put("com.service.BlogService",blogService);

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RpcServer rpcServer = new SimpleRPCRPCServer(serviceProvider);
        rpcServer.start(9999);
    }
}
