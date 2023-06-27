package widua.it.recruitmentEmpik.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import widua.it.recruitmentEmpik.models.AppUserDTO;
import widua.it.recruitmentEmpik.models.GithubUserDTO;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = GithubUserMapperImpl.class)
class GithubUserMapperTest {

    @Autowired
    private GithubUserMapperImpl mapper;

    @Test
    public void GithubUserShouldTransformToAppUserAndReturnsValidCalculationsNumber(){
        // Given
        GithubUserDTO githubUser = new GithubUserDTO(1,
                "octocat",
                "The Octocat",
                "User",
                "https://avatars.githubusercontent.com/u/583231?v=4",
                LocalDateTime.now(),
                6,
                10);

        double validCalculations = 6d / githubUser.followers() * (2d + githubUser.publicRepos());

        AppUserDTO appUser = mapper.githubUserDTOtoAppUserDTOMapper(githubUser);
        assertEquals(validCalculations,appUser.calculations());
    }

}