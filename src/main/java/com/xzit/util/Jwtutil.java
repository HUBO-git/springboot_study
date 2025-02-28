package com.xzit.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * jwt使用，总共有三部分构成，hander(令牌类型，加密算法类型),payload(负载),signature(算法签名)
 * */
@Component
public class Jwtutil {
    //自动生成一个更高级别秘钥
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 1800000; // 令牌有效时间
    // 指定签名的时候使用的签名算法，也就是header那部分
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    // 生成jwt令牌
    public String createJWT(Map<String, Object> claims) {
        // 生成jwt的body
        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, SECRET_KEY)
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));//当前的时间戳加上过期的号秒数
        String jwtString = builder.compact();
        return jwtString;
    }

    /**
     * Token解密
     *
     *
     * @return
     */
    // 解析jwt令牌
    public Claims parthJwt(String token) {
        Claims claims = Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(SECRET_KEY)
                // 设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }
}