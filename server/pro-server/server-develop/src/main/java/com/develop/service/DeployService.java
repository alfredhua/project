package com.develop.service;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.util.IDGenerateUtil;
import com.common.zk.client.ZkClient;
import com.develop.constants.NodePathEnum;
import com.develop.dao.DeployMapper;
import com.develop.entity.Deploy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guozhenhua
 * @date 2021/01/17
 */
@Component
public class DeployService {

    @Autowired
    DeployMapper deployMapper;

    public PageBean<Deploy> listDeploy(PageRequest pageRequest) {
        PageBean<Deploy> pageBean=new PageBean<>();
        pageBean.setPage_num(pageRequest.getPage_num());
        pageBean.setPage_size(pageRequest.getPage_size());
        pageBean.setTotal(NodePathEnum.values().length);
        List<NodePathEnum> nodePathEnums = Arrays.asList(NodePathEnum.values());
        List<Deploy> list = new ArrayList<>();
        int index=0;
        for (NodePathEnum nodePathEnum:nodePathEnums){
            Deploy deploy = deployMapper.getByName(nodePathEnum.getName());
            if (deploy!=null){
                if (deploy.getDel()==1){
                    continue;
                }
                deploy.setDefault_value(nodePathEnum.getDefault_value());
                list.add(deploy);
                continue;
            }
            deploy=new Deploy();
            deploy.setId(index);
            deploy.setName(nodePathEnum.getName());
            deploy.setName_value(nodePathEnum.getDefault_value());
            deploy.setDefault_value(nodePathEnum.getDefault_value());
            deploy.setDescription(nodePathEnum.getDesc());
            list.add(deploy);
            index++;
        }
        pageBean.setList(list);
        return pageBean;
    }


    public void update(Deploy deployReqDTO){
        Deploy deploy = deployMapper.getByName(deployReqDTO.getName());
        if (deploy==null){
            deploy=new Deploy();
            deploy.setId(IDGenerateUtil.generateId());
            deploy.setName(deployReqDTO.getName());
            deploy.setName_value(deployReqDTO.getName_value());
            deploy.setDescription(deployReqDTO.getDescription());
            deploy.setCreate_time(LocalDateTime.now());
            deploy.setUpdate_time(LocalDateTime.now());
            deploy.setOperator(deployReqDTO.getOperator());
            deployMapper.insert(deploy);
            ZkClient.updateNode("/"+deployReqDTO.getName(),deployReqDTO.getName_value());
            return;
        }
        if (deployMapper.updateByName(deployReqDTO)>0){
            ZkClient.updateNode("/"+deployReqDTO.getName(),deployReqDTO.getName_value());
            return;
        }
    }


    public void delDevelop(String name,String operator){
        deployMapper.deleteByName(name, operator);
        ZkClient.deleteNode("/" + name);
    }

}
