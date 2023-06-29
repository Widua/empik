package widua.it.recruitmentEmpik.service;

import widua.it.recruitmentEmpik.models.AppUserDTO;


public interface AppUserService {

    AppUserDTO findUserByLogin(String login);

}
