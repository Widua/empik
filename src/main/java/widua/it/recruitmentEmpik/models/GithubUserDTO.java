package widua.it.recruitmentEmpik.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record GithubUserDTO(
        long id,
        String login,
        String name,
        String type,
        @JsonProperty("avatar_url") String avatarUrl,
        @JsonProperty("created_at") LocalDateTime createdAt,
        long followers,
        @JsonProperty("public_repos") long publicRepos )
{}
