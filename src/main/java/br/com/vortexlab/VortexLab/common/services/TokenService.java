package br.com.vortexlab.VortexLab.common.services;

import br.com.vortexlab.VortexLab.common.EmailVerificationData;
import br.com.vortexlab.VortexLab.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.email-verification.expiration-hours:24}")
  private int emailVerificationExpirationHours;

  private static final String ISSUER = "vortexlab_by_trentor";
  private static final String SUBJECT = "email-verification";

  public String generateEmailVerificationToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      return JWT.create()
          .withIssuer(ISSUER)
          .withSubject(SUBJECT)
          .withClaim("USER_ID_CLAIM", user.getId())
          .withClaim("EMAIL_CLAIM", user.getEmail())
          .withClaim("USERNAME_CLAIM", user.getUsername())
          .withExpiresAt(
              new Date(
                  System.currentTimeMillis() + emailVerificationExpirationHours * 60 * 60 * 1000L))
          .sign(algorithm);
    } catch (JWTCreationException e) {
      throw new RuntimeException("Erro ao gerar token de verificação de email", e);
    }
  }

  public EmailVerificationData verifyEmailVerificationToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secretKey);

      JWTVerifier verifier =
          JWT.require(algorithm).withIssuer(ISSUER).withSubject(SUBJECT).acceptLeeway(1).build();

      DecodedJWT decodedJWT = verifier.verify(token);

      Long userId = decodedJWT.getClaim("USER_ID_CLAIM").asLong();
      String email = decodedJWT.getClaim("EMAIL_CLAIM").asString();
      String username = decodedJWT.getClaim("USERNAME_CLAIM").asString();

      if (userId == null || email == null) {
        throw new InvalidTokenException("Token não contém dados obrigatórios");
      }

      return new EmailVerificationData(userId, email, username);

    } catch (JWTVerificationException e) {
      throw new InvalidTokenException("Token inválido ou expirado", e);
    }
  }
}

class InvalidTokenException extends RuntimeException {
  public InvalidTokenException(String message) {
    super(message);
  }

  public InvalidTokenException(String message, Throwable cause) {
    super(message, cause);
  }
}
