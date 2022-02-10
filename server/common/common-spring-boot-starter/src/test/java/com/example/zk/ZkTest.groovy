package com.example.zk

import com.common.middle.zk.ZkUtils
import com.example.BaseTest
import org.junit.jupiter.api.Test

class ZkTest extends BaseTest{

    @Test
    void zkTest() {
        ZkUtils.create("/a", "aaa");
    }

    @Test
    void zkTest2() {
        ZkUtils.create("/a", "aaa");

    }
}
