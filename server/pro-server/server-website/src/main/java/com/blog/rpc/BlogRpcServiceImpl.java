package com.blog.rpc;

import com.pro.blog.BlogRpcService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author guozhenhua
 * @date 2020/12/13
 */

@Service(interfaceClass = BlogRpcService.class,
        loadbalance="roundrobin",
        cluster="failsafe",
        executes= 10
)
public class BlogRpcServiceImpl implements BlogRpcService{
}
