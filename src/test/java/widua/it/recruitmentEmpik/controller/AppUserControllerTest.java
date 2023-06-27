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
import widua.it.recruitmentEmpik.models.GithubUserDTO;
import widua.it.recruitmentEmpik.service.GithubUserClient;

import java.time.LocalDateTime;
import java.util.Optional;

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
    GithubUserClient githubClient;
    @Autowired
    AppUserControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void validRequestShouldReturnsValidAppUser() throws Exception {
        String existingUserLogin = "IExist";
        GithubUserDTO existingUserDTO = new GithubUserDTO(1,
                existingUserLogin,
                "User that exists",
                "user",
                "https://google.com",
                LocalDateTime.now(),
                1,
                1);

        when(githubClient.findUser(existingUserLogin)).thenReturn(Optional.of(existingUserDTO));

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

        when(githubClient.findUser(notExistingUserLogin)).thenReturn(Optional.empty());

        mockMvc
                .perform( get("/users/"+notExistingUserLogin) )
                .andDo(print())
                .andExpect(
                        status().isNotFound()
                );
    }

}
