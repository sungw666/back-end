package top.ptcc9.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {
    private static final Log log = LogFactory.get(JwtUtil.class);
    private static final long ONE_MINUTE = 60 * 1000;
    private static final long EXPIRE_TIME = 60 * ONE_MINUTE * 24;
    private static final String TOKEN_SECRET = "SDFdkmWEUTMC151kmho6dfeFGOlkc0ERDF";


    public String create(Map<String,String> map) {
        JWTCreator.Builder token = JWT.create();

        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        token.withHeader(header);

        map.forEach(token::withClaim);

        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        token.withExpiresAt(date);

        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        return token.sign(algorithm);
    }


    public boolean verity(String token){
        try {
            JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token);
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return false;
        }
    }


    public Map<String,Claim> getClaims(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token).getClaims();
    }


    public <T> T get(HttpServletRequest request, String key,Class<T> className) {
        Claim token = getClaims(request.getHeader("token")).get(key);
        T t = null;
        try {
            t = token.as(className);
        }catch (JWTDecodeException | NullPointerException e) {
            log.error("get => 读取token内数据时出错，key: {},className: {}",key,className.getName());
            return null;
        }
        return t;
    }
}
