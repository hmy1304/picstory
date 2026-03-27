package picstroy.backend.web.dto;

import picstroy.backend.domain.Post;
import picstroy.backend.domain.PostCategory;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        PostCategory category,
        String title,
        String content,
        Long memberId,
        String memberName,
        LocalDateTime createdAt
        ) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getCategory(),
                post.getTitle(),
                post.getContent(),
                post.getMember().getId(),
                post.getMember().getName(),
                post.getCreatedAt()
        );
    }
}
