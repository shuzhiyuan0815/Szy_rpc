package com;

import com.pojo.Blog;
import com.pojo.User;
import com.proxy.ClientProxy;
import com.service.BlogService;
import com.service.UserService;


public class RPCClient {
    public static void main(String[] args) {

        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 9999);
        UserService proxy = clientProxy.getProxy(UserService.class);

        //服务的方法1
        User user = proxy.getUserByUserId(1);
        System.out.println("从服务端得到的user=" + user.toString());

        //服务的方法2
        User u = User.builder().id(101).username("szy").sex(true).build();
        Integer id = proxy.insertUserId(u);
        System.out.println("像服务端插入数据" + id);

        BlogService blogService  = clientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(10000);
        System.out.println("从服务端得到的blog为：" + blogById);


    }
}
