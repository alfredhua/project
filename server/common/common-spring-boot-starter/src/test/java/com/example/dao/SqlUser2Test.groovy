package com.example.dao

import com.common.dao.BaseMapper
import com.common.util.IDGenerateUtil
import com.example.BaseTest
import com.example.dao.domain.User
import com.example.dao.domain.UserTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

class SqlUser2Test extends BaseTest{

    @Autowired
    BaseMapper<UserTest> baseMapper

    @Test
    void insert(){
        UserTest user=new UserTest()
        user.setId(IDGenerateUtil.generateId())
        user.setName("用户名称")
        user.setAge(20)
        user.setCreateTime(LocalDateTime.now())
        user.setModifier("修改人")
        user.setUpdateTime(LocalDateTime.now())
        baseMapper.insert(user)
    }


    @Test
    void update() {
        UserTest user = new UserTest()
        user.setId(1)
        user.setAge(25)
        user.setUpdateTime(LocalDateTime.now())
        user.setName("修改")
        user.setModifier("修改111")
        baseMapper.updateById(user)
    }

    @Test
    void delete(){
        baseMapper.deleteById(1L)
    }

}
