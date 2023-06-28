package widua.it.recruitmentEmpik.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import widua.it.recruitmentEmpik.models.AppUserDTO;
import widua.it.recruitmentEmpik.service.AppUserService;

@RestController
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/users/{login}")
    public AppUserDTO getGithubUser(@PathVariable String login){
        return appUserService.findUserByLogin(login);
    }

}
