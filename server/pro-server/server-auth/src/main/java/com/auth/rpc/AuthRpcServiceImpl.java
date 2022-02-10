package com.auth.rpc;

import com.pro.auth.AuthRpcService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author guozhenhua
 * @date 2020/12/13
 */
@Service(interfaceClass = AuthRpcService.class)
public class AuthRpcServiceImpl implements AuthRpcService{

    @Override
    public void test() {

    }
}
