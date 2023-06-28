package widua.it.recruitmentEmpik.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import widua.it.recruitmentEmpik.mapper.GithubUserMapper;
import widua.it.recruitmentEmpik.models.AppUserDTO;
import widua.it.recruitmentEmpik.models.GithubUserDTO;
import widua.it.recruitmentEmpik.models.UserNotFoundException;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService{
    private final GithubUserClient githubUserClient;
    private final GithubUserMapper mapper;
    private final RequestCountingService countingService;
    @Override
    public AppUserDTO findUserByLogin(String login) {
        countingService.registerNewRequest(login);
        GithubUserDTO githubUser = githubUserClient.findUser(login).orElseThrow(UserNotFoundException::new);
        return mapper.githubUserDTOtoAppUserDTOMapper(githubUser);
    }
}
