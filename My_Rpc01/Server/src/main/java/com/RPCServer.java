package com;

import com.Message.RPCRequest;
import com.Message.RPCResponse;
import com.service.Impl.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端接收解析request与封装发送response对象
 */
public class RPCServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("服务器启动了");
            //BIO的方式监听socket
            while(true){
                Socket socket = serverSocket.accept();
                //开启一个线程处理
                new Thread(() -> {
                    try{
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        //读取客户端传过来的request
                        RPCRequest request = (RPCRequest) ois.readObject();
                        //反射调用对应方法
                        Method method = userService.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
                        Object invoke = method.invoke(userService, request.getParams());
                        //封装，写入response对象
                        oos.writeObject(RPCResponse.success(invoke));
                        oos.flush();
                    }catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                            InvocationTargetException e){
                        e.printStackTrace();
                        System.out.println("从IO中读取数据错误");
                    }

                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }

    }
}
