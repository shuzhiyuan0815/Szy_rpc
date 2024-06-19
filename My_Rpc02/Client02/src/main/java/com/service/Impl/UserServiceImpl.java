package com.service.Impl;


import com.pojo.User;
import com.service.UserService;


import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询了"+id+"的用户");
        Random random = new Random();
        //模拟从数据库中取用户的行为
        User user = User.builder().username(UUID.randomUUID().toString())
                        .id(id)
                        .sex(random.nextBoolean()).build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("插入数据成功：" + user);
        return 1;
    }
}
