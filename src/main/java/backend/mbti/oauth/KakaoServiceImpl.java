//package backend.mbti.configuration.oauth;
//
//import backend.mbti.configuration.jwt.JwtProvider;
//import backend.mbti.configuration.kakao.KakaoProfile;
//import backend.mbti.configuration.kakao.OAuthToken;
//import backend.mbti.dto.member.MemberSignUpRequest;
//import backend.mbti.domain.member.Member;
//import backend.mbti.exception.AppException;
//import backend.mbti.exception.ErrorCode;
//import backend.mbti.sign.SignRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class KakaoServiceImpl implements KakaoService {
//
//    private final AuthenticationManager authenticationManager;
//    private final SignRepository signRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    // JWT
//    @Value("${jwt.secret}")
//    private String key;
//    private Long expireTimeMs = 1000 * 60 * 60L; // 1시간
//    @Override
//    public String processKakaoCallback(String code) {
//        RestTemplate rt = new RestTemplate();
//
//
//        //httpheader 오브젝트 생성
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        //httpbody 오브젝트 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", "9394c1ee0de2fd55a8ccc154f6cc5114"); //카카오 디벨로퍼의 REST API 키
//        params.add("client_secret", "vDoTob3tFhPi0BPrrZEfTwc01tvcjb8S");
//        params.add("redirect_uri", "http://localhost:3000/kakao");
//        params.add("code", code);
//
//
//        // HttpHeader + HttpBody
//        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
//
//        //http 요청
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                kakaoTokenRequest,
//                String.class
//        );
//
//        //객체 저장
//        ObjectMapper objectMapper = new ObjectMapper();
//        OAuthToken oAuthToken = null;
//        try {
//            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        RestTemplate rt2 = new RestTemplate();
//
//        //httpheader 오브젝트 생성
//        HttpHeaders headers2 = new HttpHeaders();
//        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
//        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//
//        //httpheader와 httpbody를 하나의 오브젝트에 담기
//        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);
//
//        //http 요청
//        ResponseEntity<String> response2 = rt2.exchange(
//                "https://kapi.kakao.com/v2/user/me",
//                HttpMethod.POST,
//                kakaoProfileRequest,
//                String.class
//        );
//        log.info(response2.toString());
//
//        ObjectMapper objectMapper2 = new ObjectMapper();
//        KakaoProfile kakaoProfile = null;
//
//        String responseBody = response2.getBody();
//
//        try {
//            kakaoProfile = objectMapper2.readValue(responseBody, KakaoProfile.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        log.info(kakaoProfile.getProperties().getNickname());
//
//
//
//
//
//
//        Member selectedUser = signRepository.findByUsername(kakaoUser.getUsername())
//                .orElseThrow(() ->new AppException(ErrorCode.USERNAME_NOT_FOUND, kakaoUser.getUsername() + "없습니다"));
//
//        String token = JwtProvider.createToken(selectedUser.getUsername(), key, expireTimeMs);
//
//        return token;
//    }
//
//
//    }
//
//
