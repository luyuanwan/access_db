package com.iydsj.sw;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 *
 */
@RestController
public class MyController {

    @Autowired
    UserDao userDao;

    Random random = new Random(100000);

    @RequestMapping(value = "/get/user",method = RequestMethod.GET)
    public String user(){
        int id = random.nextInt();
        User user = userDao.findUser(id);
        return JSON.toJSONString(user);
    }
}
