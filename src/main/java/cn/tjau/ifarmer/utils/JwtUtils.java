package cn.tjau.ifarmer.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtils {

    /**
     * 过期时间7天
     */
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;
    /**
     * jwt 密钥
     */
    private static final String SECRET = "#JMandJC99#@&^";

    /**
     * 生成签名，7天后过期
     *
     * @param userId
     * @return
     */
    public static String sign(String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            return JWT.create()
                    // 将 user id 保存到 token 里面
                    .withAudience(userId)
                    // 7天后token过期
                    .withExpiresAt(date)
                    // token 的密钥
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据token获取userId
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static DecodedJWT checkSign(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .build();
        return verifier.verify(token);
    }
}