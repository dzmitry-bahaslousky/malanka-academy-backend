package by.malanka.academy.security;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class RefreshTokenProviderService {

    public String generate(String accessToken) {
        return DigestUtils.md5DigestAsHex(accessToken.getBytes());
    }

}
