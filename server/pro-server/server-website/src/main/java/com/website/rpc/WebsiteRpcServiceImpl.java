package com.website.rpc;

import com.pro.website.api.WebsiteRpcService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author guozhenhua
 * @date 2020/12/13
 */
@Service(interfaceClass = WebsiteRpcService.class)
public class WebsiteRpcServiceImpl implements WebsiteRpcService{
}
