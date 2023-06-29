package widua.it.recruitmentEmpik.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import widua.it.recruitmentEmpik.models.AppUserDTO;
import widua.it.recruitmentEmpik.models.UserNotFoundException;
import widua.it.recruitmentEmpik.repository.RequestCountRepository;
import widua.it.recruitmentEmpik.service.AppUserService;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@EnableAutoConfiguration(exclude={
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
@ExtendWith(MockitoExtension.class)
class AppUserControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    AppUserService appUserService;

    @MockBean
    RequestCountRepository countRepository;

    @Autowired
    AppUserControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void validRequestShouldReturnsValidAppUser() throws Exception {
        String existingUserLogin = "IExist";
        AppUserDTO existingUserDTO = new AppUserDTO(
                1,
                existingUserLogin,
                "User that exists",
                "user",
                "https://google.com",
                LocalDateTime.now().toString(),
                18);

        when(appUserService.findUserByLogin(existingUserLogin)).thenReturn(existingUserDTO);

        mockMvc
                .perform( get("/users/"+existingUserLogin) )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.calculations").value(18)
                );
    }

    @Test
    public void userThatDoesntExistReturnsNotFound() throws Exception{
        String notExistingUserLogin = "ImNotReal";

        when(appUserService.findUserByLogin(notExistingUserLogin)).thenThrow(UserNotFoundException.class);

        mockMvc
                .perform( get("/users/"+notExistingUserLogin) )
                .andDo(print())
                .andExpect(
                        status().isNotFound()
                );
    }
}
