package com.xzit.test;

import com.xzit.util.Jwtutil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class jwtTest {
    @Autowired
    private Jwtutil jwtutil;
    @Test
    public void jwttest(){
        //生成令牌
        //Jwtutil jwtutil=new Jwtutil();
        //添加在令牌中的信息
        Map<String,Object> claims=new HashMap<>();
        claims.put("name","hubo");
        claims.put("age",20);
        String jwt = jwtutil.createJWT(claims);
        log.info("令牌为：{}",jwt);
        //解析令牌
        Claims claims1 = jwtutil.parthJwt(jwt);
        String name = (String)claims1.get("name");
        Integer age =Integer.parseInt((String) claims.get("age"));
        log.info("姓名为：{}",name);
        log.info("年龄为：{}",age);
    }

    public static void main(String[] args) {

    }


}
