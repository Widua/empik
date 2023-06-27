package widua.it.recruitmentEmpik.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AppUserDTO(
        long id,
        String login,
        String name,
        String type,
        @JsonProperty("avatar_url") String avatarUrl,
        @JsonProperty("created_at") String createdAt,
        double calculations
) {
}
