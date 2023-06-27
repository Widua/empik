package widua.it.recruitmentEmpik.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;
import widua.it.recruitmentEmpik.models.GithubUserDTO;
import widua.it.recruitmentEmpik.models.AppUserDTO;

@Mapper( componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface GithubUserMapper {

    @Mapping( target = "calculations", expression = "java(  6d / githubUser.followers() * (2d + githubUser.publicRepos()) )")
    AppUserDTO githubUserDTOtoAppUserDTOMapper(GithubUserDTO githubUser);

}
