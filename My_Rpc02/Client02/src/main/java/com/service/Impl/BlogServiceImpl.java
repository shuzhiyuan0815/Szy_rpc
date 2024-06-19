package com.service.Impl;

import com.pojo.Blog;
import com.service.BlogService;

/**
 * @version 1.0
 * @Author szy
 * @Date 2024/6/19 19:55
 */


public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("我的博客").userId(22).build();
        System.out.println("客户端查询了"+id+"博客");
        return blog;
    }
}
