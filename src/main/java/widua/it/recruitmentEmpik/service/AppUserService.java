package widua.it.recruitmentEmpik.service;

import org.springframework.stereotype.Service;
import widua.it.recruitmentEmpik.models.AppUserDTO;

@Service
public interface AppUserService {

    AppUserDTO findUserByLogin(String login);

}
