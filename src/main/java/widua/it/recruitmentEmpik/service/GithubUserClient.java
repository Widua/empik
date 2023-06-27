package widua.it.recruitmentEmpik.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import widua.it.recruitmentEmpik.models.GithubUserDTO;

import java.util.Optional;

public interface GithubUserClient {

    @GetExchange("/{login}")
    Optional<GithubUserDTO> findUser(@PathVariable String login);

}
