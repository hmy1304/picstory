package picstroy.backend.web.dto;

import picstroy.backend.domain.PostCategory;

public record UpdatePostRequest(
        PostCategory category,
        String title,
        String content
) {
}
