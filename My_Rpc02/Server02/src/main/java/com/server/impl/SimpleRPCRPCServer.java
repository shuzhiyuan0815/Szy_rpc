package com.server.impl;

import com.provider.ServiceProvider;
import com.server.RpcServer;
import com.work.WorkThread;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * @version 1.0
 * @Author szy
 * @Date 2024/6/19 21:45
 * 这个实现类代表着java原始的BIO监听模式，来一个任务就new一个线程处理
 * 处理的任务在workThread中
 */

@AllArgsConstructor
public class SimpleRPCRPCServer implements RpcServer {
    private ServiceProvider serviceProvide;


    @Override
    public void start(int port) {
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动了");
            //BIO的方式监听socket
            while(true){
                Socket socket = serverSocket.accept();
                //开启一个新线程去处理
                new Thread(new WorkThread(socket,serviceProvide)).start();
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }

    @Override
    public void stop() {

    }
}
