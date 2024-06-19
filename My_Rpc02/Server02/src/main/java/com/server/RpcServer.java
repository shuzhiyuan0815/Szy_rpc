package com.server;

/**
 * @version 1.0
 * @Author szy
 * @Date 2024/6/19 21:46
 */


public interface RpcServer {
    void start(int port);

    void stop();
}
