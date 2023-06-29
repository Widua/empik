package widua.it.recruitmentEmpik.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import widua.it.recruitmentEmpik.models.AppUserDTO;
import widua.it.recruitmentEmpik.service.AppUserService;

@RestController
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/users/{login}")
    public AppUserDTO getGithubUser(@PathVariable String login){
        return appUserService.findUserByLogin(login);
    }

}
