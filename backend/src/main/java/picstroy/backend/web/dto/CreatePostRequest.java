package picstroy.backend.web.dto;

import picstroy.backend.domain.PostCategory;

public record CreatePostRequest(
        PostCategory category,
        String title,
        String content
) {
}
