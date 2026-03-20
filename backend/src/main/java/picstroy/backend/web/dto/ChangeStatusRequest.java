package picstroy.backend.web.dto;

import picstroy.backend.domain.MemberStatus;

public record ChangeStatusRequest(
        MemberStatus status
) {
}
