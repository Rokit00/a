package backend.mbti.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    private String content;
    private Character option;
}
