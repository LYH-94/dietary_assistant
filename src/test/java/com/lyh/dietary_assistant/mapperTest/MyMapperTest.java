package com.lyh.dietary_assistant.mapperTest;

import com.lyh.dietary_assistant.mapper.UserInfoMapper;
import com.lyh.dietary_assistant.mapper.UserMapper;
import com.lyh.dietary_assistant.pojo.User;
import com.lyh.dietary_assistant.pojo.UserInfo;
import com.lyh.dietary_assistant.pojo.UserInfoExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"}) // 你的 Spring 配置文件路徑
public class MyMapperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void test(){
        // 查詢所有用戶帳號 (account)。
        List<User> users = userMapper.selectByExample(null);
        users.forEach(user -> System.out.println(user.getAccount()));
    }
    @Test
    public void test2(){
        // 查詢目標卡路里 (targetcalories) 大於等於 1700 的用戶暱稱 (nickName)。
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andTargetcaloriesGreaterThanOrEqualTo((short) 1700);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        userInfos.forEach(userInfo -> System.out.println(userInfo.getNickname()));
    }

}
