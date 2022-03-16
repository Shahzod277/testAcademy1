package uz.jurayev.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import uz.jurayev.academy.domain.ResponseToken;
import uz.jurayev.academy.repository.TokenRepository;
import uz.jurayev.academy.security.SecurityConstant;
import uz.jurayev.academy.service.TokenService;
import java.net.URI;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseToken getToken() {

        URI getTokenUrl = URI.create(SecurityConstant.GENERATE_TOKEN_URL);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set(SecurityConstant.AUTHORIZATION, SecurityConstant.AUTHORIZATION_VALUE);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", SecurityConstant.GRANT_TYPE);
        map.add("username", SecurityConstant.USERNAME);
        map.add("password", SecurityConstant.PASSWORD);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, httpHeaders);
//        ResponseEntity<ResponseToken> tokenResponseEntity = restTemplate
//                .exchange(getTokenUrl, HttpMethod.POST, httpEntity, ResponseToken.class);

        ResponseEntity<ResponseToken> response = restTemplate.postForEntity(getTokenUrl, httpEntity , ResponseToken.class);
        ResponseToken body = response.getBody();
        assert body != null;
        body.setCreatedTokenDate(new Date(System.currentTimeMillis()).getTime());
        tokenRepository.save(body);
        return body;
    }

//        public ResponseToken refreshToken(){
//
//            ResponseToken token = tokenRepository.getCreatedTokenDateByDesc();
//            if (token.getCreatedTokenDate() + 3_600_000 <= System.currentTimeMillis()){
//               token = getToken();
//            }
//            return token;
//        }
}
