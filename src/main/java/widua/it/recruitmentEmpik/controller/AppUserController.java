package widua.it.recruitmentEmpik.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import widua.it.recruitmentEmpik.mapper.GithubUserMapper;
import widua.it.recruitmentEmpik.models.AppUserDTO;
import widua.it.recruitmentEmpik.models.GithubUserDTO;
import widua.it.recruitmentEmpik.models.UserNotFoundException;
import widua.it.recruitmentEmpik.service.GithubUserClient;

@RestController
@AllArgsConstructor
public class AppUserController {

    private final GithubUserClient githubUserClient;
    private final GithubUserMapper mapper;

    @GetMapping("/users/{login}")
    public AppUserDTO getGithubUser(@PathVariable String login){
        GithubUserDTO githubUser =  githubUserClient.findUser(login).orElseThrow(UserNotFoundException::new);
        return mapper.githubUserDTOtoAppUserDTOMapper(githubUser);
    }

}
