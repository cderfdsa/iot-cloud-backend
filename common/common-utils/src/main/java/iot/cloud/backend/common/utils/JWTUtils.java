package iot.cloud.backend.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import iot.cloud.backend.common.utils.exception.InvalidateTokenException;

import java.time.Instant;

/**
 * @author weichuang
 */
public class JWTUtils {
    private static final String NAME_USER_ID = "u";
    private static final String NAME_ACCOUNT = "a";

    public static String createToken(Long userId, String account, String secret, Integer expHours) {
        return JWT.create().withClaim(NAME_USER_ID, userId).withClaim(NAME_ACCOUNT, account)
                .withExpiresAt(Instant.now().plusSeconds(60 * 60 * expHours)).sign(Algorithm.HMAC256(secret));
    }

    public static Long getUserId(String token, String secret) {
        try {
            return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getClaim(NAME_USER_ID).asLong();
        } catch (Exception exception) {
            throw new InvalidateTokenException();
        }
    }

    public static String createTokenByOld(String token, String secret, Integer expHours) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            Long userId = decodedJWT.getClaim(NAME_USER_ID).asLong();
            String account = decodedJWT.getClaim(NAME_ACCOUNT).asString();
            return createToken(userId, account, secret, expHours);
        } catch (Exception exception) {
            throw new InvalidateTokenException();
        }
    }
}
