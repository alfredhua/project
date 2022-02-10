package com.example.dao


import com.common.dao.entity.EntityWrapper
import com.common.dao.enums.ConditionEnum
import com.common.util.GsonUtil
import com.common.util.IDGenerateUtil
import com.example.BaseTest
import com.example.dao.domain.User
import com.example.dao.mapper.UserMapper
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

class SqlUserTest extends BaseTest{

    @Autowired
    UserMapper userMapper

    @Test
    void insert(){
        User user=new User()
        user.setId(IDGenerateUtil.generateId())
        user.setName("用户名称")
        user.setAge(20)
        user.setCreateTime(LocalDateTime.now())
        user.setModifier("修改人")
        user.setUpdateTime(LocalDateTime.now())
        userMapper.insert(user)
    }


    @Test
    void update() {
        User user = new User()
        user.setId(1457029732368416)
        user.setAge(95)
        user.setUpdateTime(LocalDateTime.now())
        userMapper.updateById(user)
    }

    @Test
    void delete(){
        userMapper.deleteById(1457029793185824L)
    }

    @Test
    void queryById(){
        User user=userMapper.queryById(1457029732368416L)
        println GsonUtil.toJSON(user)
    }

    @Test
    void listByIds(){
        Set<Long> ids=new HashSet<>()
        ids.add(1L)
        ids.add(2L)
        userMapper.listByIds(ids)
    }

    @Test
    void deleteByIds(){
        Set<Long> set=new HashSet<>()
        set.add(1L)
        set.add(2L)
        userMapper.deleteByIds(set)
    }

    @Test
    void queryByPage(){
        EntityWrapper entityWrapper=new EntityWrapper().addCondition("name",ConditionEnum.like,"用户名")
                .addCondition("update_time",ConditionEnum.orderBy,"ASC").addCondition("name",ConditionEnum.orderBy,"ASC")
        List<User> userList=userMapper.listByPage(1,2,entityWrapper)
        println GsonUtil.toJSON(userList)
    }

    @Test
    void listAll(){
        EntityWrapper entityWrapper=new EntityWrapper().addCondition("name",ConditionEnum.like,"用户名")
                .addCondition("update_time",ConditionEnum.orderBy,"ASC").addCondition("name",ConditionEnum.orderBy,"ASC")
        List<User> userList=userMapper.listAll(entityWrapper)
        println GsonUtil.toJSON(userList)
    }
}
