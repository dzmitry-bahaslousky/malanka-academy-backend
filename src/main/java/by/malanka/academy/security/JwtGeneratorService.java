package by.malanka.academy.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtGeneratorService {
    private final JwtEncoder jwtEncoder;
    private final long tokenValidityInSeconds;

    public JwtGeneratorService(
            JwtEncoder jwtEncoder,
            @Value("${application.security.access-token.expiry-in-seconds}") long tokenValidityInSeconds) {
        this.jwtEncoder = jwtEncoder;
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public String generate(UserDetails userDetails) {
        JwtClaimsSet claims = buildJwtClaims(userDetails);
        JwtEncoderParameters from = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(from).getTokenValue();
    }

    private JwtClaimsSet buildJwtClaims(UserDetails userDetails) {
        String roles = fetchRolesFromAuthentication(userDetails);

        Instant issuedAt = Instant.now();
        return JwtClaimsSet.builder()
                .issuer("self")
                .subject(userDetails.getUsername())
                .issuedAt(issuedAt)
                .expiresAt(issuedAt.plusSeconds(tokenValidityInSeconds))
                .claim("roles", roles)
                .build();
    }

    private static String fetchRolesFromAuthentication(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }

}
