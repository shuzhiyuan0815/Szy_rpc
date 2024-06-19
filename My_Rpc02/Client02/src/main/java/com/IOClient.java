package com;

import com.Message.RPCRequest;
import com.Message.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @version 1.0
 * @Author szy
 * @Date 2024/6/18 2:32
 */


public class IOClient {
    //负责底层与服务端的通信，发送的request，接收的是Response对象
    public static RPCResponse sendRequest(String host, int port, RPCRequest request){
        try {
            Socket socket = new Socket(host,port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println(request);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            RPCResponse response = (RPCResponse) objectInputStream.readObject();
            return response;
        } catch (IOException  | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}
