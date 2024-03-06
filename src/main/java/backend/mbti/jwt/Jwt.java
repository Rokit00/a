package backend.mbti.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Jwt {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
